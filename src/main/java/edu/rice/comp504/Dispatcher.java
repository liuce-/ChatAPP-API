package edu.rice.comp504;

import com.google.gson.Gson;
import edu.rice.comp504.cmd.CreateChatRoomCmd;
import edu.rice.comp504.cmd.JoinChatRoomCmd;
import edu.rice.comp504.cmd.SendChatRoomAnnouncementCmd;
import edu.rice.comp504.cmd.SendMessageCmd;
import edu.rice.comp504.model.ChatRoom;
import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.*;
import edu.rice.comp504.payload.response.*;
import org.eclipse.jetty.websocket.api.Session;

import java.awt.*;
import java.beans.PropertyChangeListener;
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
                UserLoginResponse response = new UserLoginResponse(null, false);

                // the user must has registered before.
                if (allUsers.containsKey(loginMsg.getUsername())) {
                    User user = allUsers.get(loginMsg.getUsername());
                    userNameMap.put(userSession, user);
                    user.setSession(userSession);
                    pcs.addPropertyChangeListener("user", user);
                    response.setSuccess(true);
                    response.setUser(user);
                }
                try {
                    userSession.getRemote().sendString(response.getJsonRepresentation(gson));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;
            case "enter_room": {
                JoinChatRoom joinChatRoom = gson.fromJson(msg.getInfo(), JoinChatRoom.class);
                User newMemeber = allUsers.get(joinChatRoom.getUsername());
                // TODO(Serena): check if user exists.

                // notify others in this chatroom
                JoinChatRoomCmd cmd = new JoinChatRoomCmd(newMemeber, joinChatRoom.getRoomID());
                pcs.firePropertyChange(String.valueOf(joinChatRoom.getRoomID()), null, cmd);

                // the new member should listen to this chat room
                // (DO NOT switch the order of listening to this chat room and firing the evt).
                pcs.addPropertyChangeListener(String.valueOf(joinChatRoom.getRoomID()), newMemeber);

                // store this new chat room in user's room list.
                newMemeber.joinChatRoom(chatRoomMap.get(joinChatRoom.getRoomID()));
            }
            break;
            case "announcement": {
                SendAnnouncement sendAnnouncement = gson.fromJson(msg.getInfo(), SendAnnouncement.class);
                SendChatRoomAnnouncementCmd sendChatRoomAnnouncementCmd = new SendChatRoomAnnouncementCmd(sendAnnouncement.getAnnouncement());
                pcs.firePropertyChange(String.valueOf(sendAnnouncement.getRoomID()), null, sendChatRoomAnnouncementCmd);
            }
            break;
            case "chat": {
                SendChattingMsg sendChattingMsg = gson.fromJson(msg.getInfo(), SendChattingMsg.class);
                SendMessageCmd cmd = new SendMessageCmd(sendChattingMsg);

                // TODO(Serena): check if the msg contains word "hate" before sending it.

                pcs.firePropertyChange("user", null, cmd);
            }
            break;
            default:
                System.out.println("No matched result for " + message);
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

        return userRegisterResponse.getJsonRepresentation(gson);
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
        return new CreateRoomResponse(room.getName(), true).getJsonRepresentation(gson);
    }

    public String getJoinedRoom(String username) {
        User user = allUsers.get(username);
        return new GetJoinedRoomResponse(user.getUsername(), user.getChatRooms()).getJsonRepresentation(gson);
    }

    public String getPossibleRoom(String username) {
        return "Not implemented";
    }

    public String getMemberList(int roomID) {
        PropertyChangeListener[] listeners = pcs.getPropertyChangeListeners(String.valueOf(roomID));
        GetMemberListResponse response = new GetMemberListResponse(roomID, listeners);
        return response.getJsonRepresentation(gson);
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
