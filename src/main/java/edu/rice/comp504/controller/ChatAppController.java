package edu.rice.comp504.controller;

import edu.rice.comp504.Dispatcher;
import spark.Spark;

import static spark.Spark.*;

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

        get("/joined_rooms/:username", (request, response) -> {
            return dispatcher.getJoinedRoom(request.params(":username"));
        });

        get("/possible_rooms/:username", (request, response) -> {
            return dispatcher.getPossibleRoom(request.params(":username"));
        });

        get("/member_list/:roomID", (request, response) -> {
            int roomID = Integer.parseInt(request.params(":roomID"));
            return dispatcher.getMemberList(roomID);
        });
        Spark.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
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
