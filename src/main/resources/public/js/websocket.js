// global variable for webSocket
window.socket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chatapp");

socket.onclose = () => alert("Websocket connection closed");

socket.onerror = (err) => console.log(err);