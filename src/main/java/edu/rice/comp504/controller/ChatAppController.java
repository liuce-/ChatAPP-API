package edu.rice.comp504.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.rice.comp504.Dispatcher;
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
    /**
     * Chat App entry point.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFiles.location("/public");
        Dispatcher dispatcher = Dispatcher.getOnly();

        webSocket("/chatapp", WebSocketController.class);
        init();

        post("/register", (request, response) -> {
            return dispatcher.register(request.body());
        });

        post("/create_room", (request, response) -> {
            return dispatcher.createRoom(request.body());
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
