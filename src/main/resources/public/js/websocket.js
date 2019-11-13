'use strict';

const webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chatapp");
console.log(webSocket);

webSocket.onclose = () => alert("Websocket connection closed");