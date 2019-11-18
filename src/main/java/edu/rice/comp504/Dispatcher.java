package edu.rice.comp504;

import com.google.gson.Gson;
import edu.rice.comp504.cmd.CreateChatRoomCmd;
import edu.rice.comp504.cmd.JoinChatRoomCmd;
import edu.rice.comp504.cmd.SendChatRoomAnnouncementCmd;
import edu.rice.comp504.cmd.SendMessageCmd;
import edu.rice.comp504.model.ChatRoom;
import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.request.*;
import edu.rice.comp504.payload.response.*;
import org.eclipse.jetty.websocket.api.Session;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * dispatcher for handling and dispatching the HTTP/WS request.
 */
public class Dispatcher {
    // only one instance
    private static Dispatcher dispatcher;

    public Map<String, User> allUsers;
    public Map<Session, User> userNameMap;

    public Map<Integer, ChatRoom> chatRoomMap;
    public Gson gson;

    private PropertyChangeSupport pcs;
    private Logger logger;

    /**
     * constructor.
     */
    private Dispatcher() {
        this.pcs = new PropertyChangeSupport(this);
        this.gson = new Gson();
        this.allUsers = new ConcurrentHashMap<String, User>();
        this.userNameMap = new ConcurrentHashMap<>();
        this.chatRoomMap = new ConcurrentHashMap<>();
        this.logger = Logger.getLogger(Dispatcher.class.getName());
        this.logger.setLevel(Level.INFO);
    }

    /**
     * handle websocket message.
     *
     * @param userSession msg is sent in this session.
     * @param message     the msg.
     */
    public void handleMsg(Session userSession, String message) {
        Message msg = gson.fromJson(message, Message.class);
        switch (msg.getType()) {
            case "login": {
                logger.info("user")
                LoginMsg loginMsg = gson.fromJson(msg.getInfo(), LoginMsg.class);
                UserLoginResponse response = new UserLoginResponse(null, false);

                // the user must has registered before.
                if (allUsers.containsKey(loginMsg.getUsername())) {
                    User user = allUsers.get(loginMsg.getUsername());
                    userNameMap.put(userSession, user);
                    user.setSession(userSession);
                    pcs.addPropertyChangeListener("user", user);
                    response.setStatus(true);
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
                SendChatRoomAnnouncementCmd sendChatRoomAnnouncementCmd = new SendChatRoomAnnouncementCmd(sendAnnouncement);
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

    /**
     * respond to close event.
     *
     * @param session    this session was closed.
     * @param statusCode status code.
     * @param reason     the reason.
     */
    public void closeSession(Session session, int statusCode, String reason) {

    }

    /**
     * handler for register endpoint.
     *
     * @param body the http payload.
     * @return response to be sent.
     */
    public String register(String body) {
        logger.info("register with body " + body);
        UserRegister userRegister = gson.fromJson(body, UserRegister.class);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse(userRegister.getUsername(), false);

        if (!allUsers.containsKey(userRegister.getUsername())) {
            User newUser = new User(userRegister.getUsername(), userRegister.getAge(), userRegister.getSchool(), userRegister.getLocation());
            allUsers.put(newUser.getUsername(), newUser);
            userRegisterResponse.setResult(true);
        }
        logger.info("response with " + userRegisterResponse.getJsonRepresentation(gson));
        return userRegisterResponse.getJsonRepresentation(gson);
    }

    /**
     * handler for creating room endpoint.
     *
     * @param body payload.
     * @return response to be sent.
     */
    public String createRoom(String body) {
        logger.info("create room with body " + body);
        // create this room
        CreateRoom roomPayload = gson.fromJson(body, CreateRoom.class);
        // TODO: check if the owner exists.
        ChatRoom room = new ChatRoom(
            roomPayload.getRoomName(),
            new Point(roomPayload.getMinAge(), roomPayload.getMaxAge()),
            new HashSet<String>(Arrays.asList(roomPayload.getLocation())),
            new HashSet<String>(Arrays.asList(roomPayload.getSchool())),
            allUsers.get(roomPayload.getOwnerName()));

        chatRoomMap.put(room.getId(), room);
        pcs.addPropertyChangeListener(String.valueOf(room.getId()), room.getOwner());

        // broadcast this new room to all (qualified) users
        CreateChatRoomCmd cmd = new CreateChatRoomCmd(room.getRestriction());
        pcs.firePropertyChange("user", null, cmd);

        // response
        CreateRoomResponse response = new CreateRoomResponse(room.getName(), true);
        logger.info("response with " + response.getJsonRepresentation(gson));
        return response.getJsonRepresentation(gson);
    }

    /**
     * handler for getting joined rooms of a user.
     *
     * @param username get joined rooms of this user.
     * @return response containing all joined rooms.
     */
    public String getJoinedRoom(String username) {
        User user = allUsers.get(username);
        return new GetJoinedRoomResponse(user.getUsername(), user.getChatRooms()).getJsonRepresentation(gson);
    }

    /**
     * handler for getting possible rooms that a user is qualified to join.
     *
     * @param username get possible rooms for this user.
     * @return response to be sent.
     */
    public String getPossibleRoom(String username) {
        return "Not implemented";
    }

    /**
     * handler for getter all members of a chat room.
     *
     * @param roomID get members of this room.
     * @return response containing all users' info.
     */
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
        if (dispatcher == null) {
            dispatcher = new Dispatcher();
        }

        return dispatcher;
    }

}
