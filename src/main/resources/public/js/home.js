(async function() {
    // websocket should already exists here...
    // if not, user refresh the page (connection closed), go back to login page
    if(!window.socket) {
        window.location.href = '/';
        return;
    }

    const profileBtn = document.querySelector("#profile-btn"),
          createRoomBtn = document.querySelector("#create-room-btn"),
          profileDialog = document.querySelector("#profile-dialog"),
          createRoomDialog = document.querySelector("#create-room-dialog"),
          logoutBtn = document.querySelector("[data-logout]");
    let userProfile = null;

    /*
        socket status:
            CONNECTING (0)
            OPEN (1)
            CLOSING (2)
            CLOSED (3)
     */
    // send username when connection established
    socket.addEventListener('open', () => {
        let usernameMsg = {
            type: 'login',
            info: JSON.stringify({username: username, msg: username})
        };
        socket.send(JSON.stringify(usernameMsg));
    });

    // socket give the user profile info
    socket.addEventListener('message', (ev) => {
        userProfile = JSON.parse(ev.data);
        // todo: render profile
    });

    // get joined room data
    let joinedRoomUrl = `/joined_rooms/${username}`;
    let joinedRoomData = await fetch(joinedRoomUrl);
    // todo: render data

    // get could join room data
    let possibleRoomUrl = `/possible_rooms/${username}`;
    let possibleRoomData = await fetch(possibleRoomUrl);
    // todo: render data


    // click logout button
    logoutBtn.addEventListener('click', () => {
        socket.close();
        window.location.href = '/';
    });

    // click profile button
    profileBtn.addEventListener('click', () => {
        createRoomDialog.classList.add('hidden');
        profileDialog.classList.remove('hidden');
    });

    // click create-room button
    createRoomBtn.addEventListener('click', () => {
        createRoomDialog.classList.remove('hidden');
        profileDialog.classList.add('hidden');
    });

    // close dialog
    Array.from(document.querySelectorAll('[data-close]')).forEach(item => {
        item.addEventListener('click', () => {
            createRoomDialog.classList.add('hidden');
            profileDialog.classList.add('hidden');
        })
    });
})();