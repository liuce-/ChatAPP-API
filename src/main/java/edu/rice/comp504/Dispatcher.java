package edu.rice.comp504;

import com.google.gson.Gson;
import edu.rice.comp504.model.ChatRoom;
import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.UserRegister;
import edu.rice.comp504.payload.UserRegisterResponse;
import org.eclipse.jetty.websocket.api.Session;

import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Dispatcher {
    // only one instance
    private static Dispatcher dispatcher = new Dispatcher();

    public Map<String, User> allUsers = new ConcurrentHashMap<>();
    public Map<String, ChatRoom> chatRoomMap = new ConcurrentHashMap<>();
    public Map<Session, User> userNameMap = new ConcurrentHashMap<>();
    public int nextUserId = 1;
    public Gson gson;

    private PropertyChangeSupport pcs;

    private Dispatcher() {
        this.pcs = new PropertyChangeSupport(this);
        this.gson = new Gson();
    }

    public void handleMsg(Session user, String message) {

    }

    public void closeSession(Session session, int statusCode, String reason) {

    }

    public String register(String body) {
        UserRegister userRegister = gson.fromJson(body, UserRegister.class);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse(userRegister.getUserName(), false);

        if (!allUsers.containsKey(userRegister.getUserName())) {
            User newUser = new User(userRegister.getUserName(), userRegister.getAge(), userRegister.getSchool(), userRegister.getLocation());
            allUsers.put(newUser.getUsername(), newUser);
            userRegisterResponse.setResult(true);
        }

        return gson.toJson(userRegisterResponse);
    }

    public String createRoom(String body) {
        return null;
    }

    /**
     * Broadcast message to all users.
     *
     * @param sender  The message sender.
     * @param message The message.
     */
    public void broadcastMessage(User sender, String message) {
        userNameMap.keySet().forEach(session -> {
            try {
                session.getRemote().sendString(gson.toJson(sender) + " says " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * get the only instance.
     *
     * @return dispatcher.
     */
    public static Dispatcher getOnly() {
        return dispatcher;
    }

}
