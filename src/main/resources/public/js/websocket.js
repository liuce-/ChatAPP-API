// global variable for webSocket
window.webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chatapp");

webSocket.onclose = () => alert("Websocket connection closed");