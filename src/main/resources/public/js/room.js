(function() {
    if(!window.socket) {
        window.location.href = '/';
        return;
    }

    const announementList = document.querySelector("#announements"),
          announementBtn = document.querySelector("#announce-btn"),
          roomTitle = document.querySelector("#room-title"),
          logoutBtn = document.querySelector("[data-logout]"),
          leaveTempBtn = document.querySelector("#leave-temp-btn"),
          leavePermBtn = document.querySelector("#leave-perm-btn"),
          peopleList = document.querySelector("#people-list");

    // some initial info
    // current room id
    const hashTmpArr = location.hash.split('/');
    const roomId = Number(hashTmpArr[hashTmpArr.length - 1]);

    // owner can send announcement
    location.href.includes('owner') ? announementBtn.classList.remove('hidden'): announementBtn.classList.add('hidden');


    // send room id first
    let msg = {
        type: "enter_room",
        info: JSON.stringify({username, room_id: roomId}),
    };
    socket.send(JSON.stringify(msg));

    // show all announcements
    const renderAnnouncement = (announce) => {
        let tpl = announce.map(item => {
            return `<li>${item.content}</li>`
        });
        announementList.innerHTML = tpl.length ? tpl.join("") : `<li>Empty...</li>`;
    };

    // show people list
    const renderPeopleList = (people) => {
        let tpl = people.map(item => {
            if(item.username === username) {
                return `<li><span class="name">${item.username}${item.isOwner ? '<i class="own-icon">*</i>' : ''}</span></li>`
            } else {
                return `<li><span class="name">${item.username}${item.isOwner ? '<i class="own-icon">*</i>' : ''}</span><a class="chat-btn btn" href="#/chat?user=${item.username}">Chat</a><span class="kick-btn btn ${item.isOwner ? '' : 'hidden'}">Kick</span></li>`
            }
        });
        peopleList.innerHTML = tpl.join("");
    };

    // socket give the announcements
    socket.addEventListener('message', (ev) => {
        let data = JSON.parse(ev.data);
        let type = data.type;

        if(type === 'enter_room') {
            roomTitle.innerHTML = `${roomTitle.innerHTML} ${data.info.room_name}`
        } else if (type === 'announcement') {
            renderAnnouncement(data.info.announcements);
        } else if (type === 'people_list') {
            renderPeopleList(data.info.people);
        }
    });

    // click logout button
    logoutBtn.addEventListener('click', () => {
        socket.close();
        window.location.href = '/';
    });

    // click home page btn
    leaveTempBtn.addEventListener('click', () => {
        let msg = {
            type: "leave_room_temp",
            info: JSON.stringify({username, room_id: roomId}),
        };
        socket.send(JSON.stringify(msg));
        location.href = "#/home";
    });

    // click leave room btn
    leavePermBtn.addEventListener('click', () => {
        let msg = {
            type: "leave_room_perm",
            info: JSON.stringify({username, room_id: roomId}),
        };
        socket.send(JSON.stringify(msg));
        location.href = "#/home";
    });
})();