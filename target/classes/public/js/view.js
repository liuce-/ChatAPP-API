'use strict';


const webSocket = new WebSocket("ws://localhost:4567/chatapp");

/**
 * Entry point into chat room
 */
window.onload = function () {

    webSocket.onclose = () => alert("WebSocket connection closed");
    $("#btn-msg").click(function (event) {
        let msg = $("#message").val();
        sendMessage(msg);
    })
};

/**
 * Send a message to the server.
 * @param msg  The message to send to the server.
 */
function sendMessage(msg) {
    if (msg !== "") {
        webSocket.send(msg);
        $("#message").val("");
    }
}
webSocket.addEventListener('message', function (event) {
    console.log('Message from server ', event.data);
    updateChatRoom(event.data);
});

/**
 * Update the chat room with a message.
 * @param message  The message to update the chat room with.
 */
function updateChatRoom(message) {
    let msg = $("#show-msg").html() + "<br>" + message;
    $("#show-msg").html(msg);
}