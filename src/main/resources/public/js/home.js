(function() {
    // websocket should already exists here...
    // if not, user refresh the page (connection closed), go back to login page
    if(!window.webSocket) {
        window.location.href = '/';
        return;
    }

    const profileBtn = document.querySelector("#profile-btn"),
          createRoomBtn = document.querySelector("#create-room-btn"),
          profileDialog = document.querySelector("#profile-dialog"),
          createRoomDialog = document.querySelector("#create-room-dialog"),
          logoutBtn = document.querySelector("#logout-btn");

    // click logout button
    logoutBtn.addEventListener('click', () => {
        webSocket.close();
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