// global variable for webSocket
window.socket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chatapp");

socket.onclose = () => alert("Websocket connection closed");

// socket give the user profile info
socket.addEventListener('message', (ev) => {
    // Decide type
    let data = JSON.parse(ev.data);
    let type = data.type;

    if (type === 'login') {
      let profile = data.info.profile;
      renderProfile({
        username: data.info.username,
        age: profile.age,
        location: profile.location,
        school: profile.school,
      });
    } else if (type === 'joined_rooms') {
        renderJoinedRooms(data.info);
    } else if (type === 'possible_rooms') {
        renderPossibleRooms(data.info);
    } else if (type === 'announcement') {
        // TODO
    } else if (type === 'message') {
        // TODO
    } else {
        // TODO
    }
});
