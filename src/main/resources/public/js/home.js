(function () {
    // websocket should already exists here...
    // if not, user refresh the page (connection closed), go back to login page
    if (!window.socket) {
        window.location.href = '/';
        return;
    }

    const profileBtn = document.querySelector("#profile-btn"),
          createRoomBtn = document.querySelector("#create-room-btn"),
          createRoomActionBtn = document.querySelector("#create-room-action"),
          profileDialog = document.querySelector("#profile-dialog"),
          createRoomDialog = document.querySelector("#create-room-dialog"),
          logoutBtn = document.querySelector("[data-logout]"),
          profileInfo = document.querySelectorAll("[data-profile]"),
          joined_rooms = document.querySelector("#joined_rooms"),
          possible_rooms = document.querySelector("#possible_rooms");
          schoolsWrapper = document.getElementById('schools-wrapper');
          locationsWrapper = document.getElementById('locations-wrapper');
          creatingRoomName = document.getElementById('creating-room-name');
          minAge = document.getElementById(`creating-room-age-lower`);
          maxAge = document.getElementById(`creating-room-age-upper`);
    const locations = ['Africa', 'Asia', 'Europe', 'North America', 'South America', 'Antarctica', 'Australia'];
    const schools = ['Dont care', 'Rice', 'ABC']; // TODO 'full' school list
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

    // show room list
    const renderRoomlist = (rooms, type) => {
        let tpl;
        if (type === "joined") {
            tpl = rooms.map(item => {
                return `<li><span class="name">${item.name}${item.isOwned ? '<i class="own-icon">*</i>'
                    : ''}</span><a class="join-btn" href="#/room${item.id}${item.isOwned ? "?owner" : ''}">Enter</a></li>`;
            });
            joined_rooms.innerHTML = tpl.length ? tpl.join("") : `<li>Empty...</li>`;
        } else if (type === "possible") {
            tpl = rooms.map(item => {
                return `<li><span class="name">${item.name}</span><a class="join-btn" href="#/room${item.id}">Join</a></li>`;
            });
            possible_rooms.innerHTML = tpl.length ? tpl.join("") : `<li>Empty...</li>`;
        }
    };

    // get two room lists
    const getJoinedRooms = async () => {
        try {
            let joinedRoomData = await fetch(`/joined_rooms/${username}`);
            renderRoomlist(joinedRoomData.roomlist, "joined");
        } catch (err) {
            console.log(err);
        }
    };
    getJoinedRooms();

    const getPossibleRooms = async () => {
        try {
            let possibleRoomData = await fetch(`/possible_rooms/${username}`);
            renderRoomlist(possibleRoomData.roomlist, "possible");
        } catch (err) {
            console.log(err);
        }
    };
    getPossibleRooms();

    // fake profile data
    let d = {
        username: 'data.info.username',
        age: 'profile.age',
        location: 'profile.location',
        school: 'profile.school',
    };
    renderProfile(d);

    // socket give the user profile info and room list
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
        } else if (type === 'joined_rooms' && data.info.msg) {
            getJoinedRooms();
        } else if (type === 'possible_rooms' && data.info.msg) {
            getPossibleRooms();
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
        renderLocations(locations);
        renderSchool(schools);
    });

    // close dialog
    Array.from(document.querySelectorAll('[data-close]')).forEach(item => {
        item.addEventListener('click', () => {
            createRoomDialog.classList.add('hidden');
            profileDialog.classList.add('hidden');
        })
    });

    // Render location options in creating room
    const renderLocations = (locations) => {
        let wrapper = locationsWrapper;
        let template = locations.reduce((prev, loc) => `${prev} <label><input type="checkbox" value="${loc}" id="creating-room-loc-${loc}">${loc}</label>`, '');
        wrapper.innerHTML = template;
    };

    // Render schools list in creating room
    const renderSchool = (schools) => {
        let wrapper = schoolsWrapper;
        let template = schools.reduce((prev, school) => `${prev} <option value="${school}" id="creating-room-school-${school}">${school}</option>`, '');
        wrapper.innerHTML = template;
    };

    // click create-room-action button in create-room-dialog
    createRoomActionBtn.addEventListener('click', async () => {
        try {
            // Get locations
            let selectedLocations = locations.reduce((prev, loc) => {
                prev = (document.getElementById(`creating-room-loc-${loc}`).checked) ? prev.concat([loc]) : prev;
                return prev;
            }, []);

            // Get school
            let schoolSelect = schoolsWrapper;
            let school = schoolSelect.options[schoolSelect.selectedIndex].value;

            // Send room info
            let payload = {
                ownerName: username,
                room_name: creatingRoomName.value,
                min_age: minAge.value,
                max_age: maxAge.value,
                locations: selectedLocations,
                school,
            };
            // console.log(payload);
            const resp = await fetch('/create_room', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload),
            });

            // // TODO resp handling
            // if (resp.result !== 'ok') {
            //     throw `room creation failed, message: ${resp.result}`;
            // }

            // Set up room
            // resp: {room_name: xx, room_id: xx, result: 'ok'}
            joined_rooms.innerHTML = `${joined_rooms.innerHTML} <a class="join-btn" href="#/room/${resp.room_id}?owner">${payload.room_name}</a>`;
        } catch (error) {
            console.error(error);
        }
    });
})();
