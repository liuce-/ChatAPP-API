package edu.rice.comp504.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.rice.comp504.cmd.AbstractCmd;
import edu.rice.comp504.cmd.CmdFactory;
import edu.rice.comp504.cmd.RegisterCmd;
import edu.rice.comp504.model.ChatRoom;
import edu.rice.comp504.model.User;
import org.eclipse.jetty.websocket.api.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;
import static j2html.TagCreator.*;

/**
 * The chat app controller communicates with all the clients on the web socket.
 */
public class ChatAppController {
    public static Map<String, User> allUsers = new ConcurrentHashMap<>();
    public static Map<String, ChatRoom> chatRoomMap = new ConcurrentHashMap<>();
    public static Map<Session, User> userNameMap = new ConcurrentHashMap<>();
    public static int nextUserId = 1;
    public static Gson gson;

    /**
     * Chat App entry point.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFiles.location("/public");
        gson = new Gson();
        webSocket("/chatapp", WebSocketController.class);
        init();

        post("/register", (request, response) -> {
            AbstractCmd registerCmd = CmdFactory.getCmd(RegisterCmd.class);
            return registerCmd.execute(request.body());
        });
    }

    /**
     * Broadcast message to all users.
     *
     * @param sender  The message sender.
     * @param message The message.
     */
    static void broadcastMessage(User sender, String message) {
        userNameMap.keySet().forEach(session -> {
            try {
                session.getRemote().sendString(gson.toJson(sender) + " says " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * Get the heroku assigned port number.
     *
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
