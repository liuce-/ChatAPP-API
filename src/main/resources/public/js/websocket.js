// global variable for webSocket
window.socket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chatapp");

socket.onclose = () => alert("Websocket connection closed");

// socket give the user profile info
socket.addEventListener('message', (ev) => {
    // Decide type
    let data = JSON.parse(ev.data);
    let type = data.type;

    if (type === 'login') {
      renderProfile(data.info);
    }
});
