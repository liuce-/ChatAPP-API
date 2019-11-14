package edu.rice.comp504.controller;

import edu.rice.comp504.model.User;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * Create a web socket for the server.
 */
@WebSocket
public class WebSocketController {

    /**
     * Open user's session.
     * @param userSession The user whose session is opened.
     */
    @OnWebSocketConnect
    public void onConnect(Session userSession) {
//        //user name is currently created automatically based on number
//        // instead we will have user enter username to check if profile exists
//        // if username in map - show existing chat rooms
//        //otherwise create new user
//        String username = "User" + ChatAppController.nextUserId++;
//        User user = new User(username);
//        ChatAppController.userNameMap.put(userSession, user);
//        System.out.println(username);

        // do nothing here.
    }

    /**
     * Close the user's session.
     * @param user The use whose session is closed.
     */
    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        String username = ChatAppController.userNameMap.get(user).getUsername();
        ChatAppController.userNameMap.remove(user);
    }

    /**
     * Send a message.
     * @param user  The session user sending the message.
     * @param message The message to be sent.
     */
    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
       // message.setFrom(users.get(session.getId()));
        User sender = ChatAppController.userNameMap.get(user);
        ChatAppController.broadcastMessage(sender,message);
        System.out.println(message);
    }
}
