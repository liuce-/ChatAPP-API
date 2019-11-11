package edu.rice.comp504.controller;

import com.google.gson.JsonObject;
import org.eclipse.jetty.websocket.api.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;
import static j2html.TagCreator.*;

/**
 * The chat app controller communicates with all the clients on the web socket.
 */
public class ChatAppController {
    static Map<Session,String> userNameMap = new ConcurrentHashMap<>();
    static int nextUserId = 1;

    /**
     * Chat App entry point.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFiles.location("/public");

        webSocket("/chatapp", WebSocketController.class);
        init();
       // System.out.println(web)
    }

    /**
     * Broadcast message to all users.
     * @param sender  The message sender.
     * @param message The message.
     */
    static void broadcastMessage(String sender, String message) {
        userNameMap.keySet().forEach(session -> {
            try {
                JsonObject jo = new JsonObject();
                jo.addProperty("userMessage",p(sender + " says " + message).render());
                session.getRemote().sendString(String.valueOf(jo));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * Get the heroku assigned port number.
     * @return The heroku assigned port number
     */
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set.
    }
}
