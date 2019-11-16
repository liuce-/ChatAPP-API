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
          logoutBtn = document.querySelector("[data-logout]"),
          profileInfo = document.querySelectorAll("[data-profile]");

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
            info: JSON.stringify({username}),
        };
        socket.send(JSON.stringify(usernameMsg));
    });

    // profile info in the dialogue
    const renderProfile = (profile) => {
        Array.from(profileInfo).forEach(item => {
            switch (item.dataset.profile) {
                case "username":
                    item.innerHTML = profile['username'];
                    break;
                case "age":
                    item.innerHTML = profile['age'];
                    break;
                case "location":
                    item.innerHTML = profile['location'];
                    break;
                case "school":
                    item.innerHTML = profile['school'];
                    break;
            }
        });
    };

    // fake data
    let d = {
        username: 'data.info.username',
        age: 'profile.age',
        location: 'profile.location',
        school: 'profile.school',
    };
    renderProfile(d);

    // socket give the user profile info
    socket.addEventListener('message', (ev) => {
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
        }
    });

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
