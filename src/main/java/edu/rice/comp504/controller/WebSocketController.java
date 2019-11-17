package edu.rice.comp504.controller;

import edu.rice.comp504.Dispatcher;
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
    private Dispatcher dispatcher = Dispatcher.getOnly();

    /**
     * Open user's session.
     *
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
     *
     * @param user The use whose session is closed.
     */
    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        dispatcher.closeSession(user, statusCode, reason);
    }

    /**
     * Send a message.
     *
     * @param user    The session user sending the message.
     * @param message The message to be sent.
     */
    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        System.out.println(message);
        dispatcher.handleMsg(user, message);
    }
}
