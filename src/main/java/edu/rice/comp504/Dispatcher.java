package edu.rice.comp504;

import com.google.gson.Gson;
import edu.rice.comp504.cmd.CreateChatRoomCmd;
import edu.rice.comp504.cmd.JoinChatRoomCmd;
import edu.rice.comp504.cmd.SendChatRoomAnnouncementCmd;
import edu.rice.comp504.model.ChatRoom;
import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.*;
import edu.rice.comp504.payload.response.CreateRoomResponse;
import edu.rice.comp504.payload.response.UserRegisterResponse;
import org.eclipse.jetty.websocket.api.Session;

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Dispatcher {
    // only one instance
    private static Dispatcher dispatcher = new Dispatcher();

    public Map<String, User> allUsers = new ConcurrentHashMap<String, User>();
    public Map<Session, User> userNameMap = new ConcurrentHashMap<>();

    public Map<Integer, ChatRoom> chatRoomMap = new ConcurrentHashMap<>();
    public int nextUserId = 1;
    public Gson gson;

    private PropertyChangeSupport pcs;

    private Dispatcher() {
        this.pcs = new PropertyChangeSupport(this);
        this.gson = new Gson();
    }

    public void handleMsg(Session userSession, String message) {
        Message msg = gson.fromJson(message, Message.class);
        switch (msg.getType()) {
            case "login": {
                LoginMsg loginMsg = gson.fromJson(msg.getInfo(), LoginMsg.class);
                String response = "fail";

                // the user must has registered before.
                if (allUsers.containsKey(loginMsg.getUsername())) {
                    User user = allUsers.get(loginMsg.getUsername());
                    userNameMap.put(userSession, user);
                    user.setSession(userSession);
                    pcs.addPropertyChangeListener("user", user);
                    response = gson.toJson(user);
                }
                try {
                    userSession.getRemote().sendString(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;
            case "enter_room": {
                JoinChatRoom joinChatRoom = gson.fromJson(msg.getInfo(), JoinChatRoom.class);
                User newMemeber = allUsers.get(joinChatRoom.getUsername());
                // TODO: check if user exists.

                // notify others in this chatroom
                JoinChatRoomCmd cmd = new JoinChatRoomCmd(newMemeber);
                pcs.firePropertyChange(String.valueOf(joinChatRoom.getRoomID()), null, cmd);

                // the new member should listen to this chatroom
                // (DO NOT switch the order of listening to this chat room and firing the evt).
                pcs.addPropertyChangeListener(String.valueOf(joinChatRoom.getRoomID()), newMemeber);
            }
            break;
            case "announcement": {
                SendAnnouncement sendAnnouncement = gson.fromJson(msg.getInfo(), SendAnnouncement.class);
                SendChatRoomAnnouncementCmd sendChatRoomAnnouncementCmd = new SendChatRoomAnnouncementCmd(sendAnnouncement.getAnnouncementList());
                pcs.firePropertyChange(String.valueOf(sendAnnouncement.getRoomID()), null, sendChatRoomAnnouncementCmd);
            }
            break;
            case "chat": {

            }
            break;
            default:
                System.out.println("No matching result for " + message);
        }

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
        // create this room
        CreateRoom roomPayload = gson.fromJson(body, CreateRoom.class);
        // TODO: check if the owner exists.
        ChatRoom room = new ChatRoom(roomPayload.getRoomName(), new Point(roomPayload.getMinAge(), roomPayload.getMaxAge()), roomPayload.getLocation(), roomPayload.getSchool(), allUsers.get(roomPayload.getOwnerName()));

        chatRoomMap.put(room.getId(), room);
        pcs.addPropertyChangeListener(String.valueOf(room.getId()), room.getOwner());

        // broadcast this new room to all (qualified) users
        CreateChatRoomCmd cmd = new CreateChatRoomCmd(room.getRestriction());
        pcs.firePropertyChange("user", null, cmd);

        // response
        return gson.toJson(new CreateRoomResponse(room.getName(), true));
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
