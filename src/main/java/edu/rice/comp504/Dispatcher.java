package edu.rice.comp504;

import com.google.gson.Gson;
import edu.rice.comp504.model.ChatRoom;
import edu.rice.comp504.model.User;
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

    public String register(String message) {
        return null;
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
