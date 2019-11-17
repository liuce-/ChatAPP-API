(function () {
    if (!window.socket) {
        window.location.href = '/';
        return;
    }

    const announementList = document.querySelector("#announements"),
        announementBtn = document.querySelector("#announce-btn"),
        roomTitle = document.querySelector("#room-title"),
        logoutBtn = document.querySelector("[data-logout]"),
        leavePermBtn = document.querySelector("#leave-perm-btn"),
        peopleList = document.querySelector("#people-list");

    // some initial info
    // current room id
    const hashTmpArr = location.hash.split('/');
    const roomId = Number(hashTmpArr[hashTmpArr.length - 1]);

    // owner can send announcement
    location.href.includes('owner') ? announementBtn.classList.remove('hidden') : announementBtn.classList.add('hidden');


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
            if (item.username === username) {
                return `<li><span class="name">${item.username}${item.isOwner ? '<i class="own-icon">*</i>' : ''}</span></li>`
            } else {
                return `<li><span class="name">${item.username}${item.isOwner ? '<i class="own-icon">*</i>' : ''}</span><span class="chat-btn btn" data-chat=${item.username}>Chat</span><span class="kick-btn btn ${item.isOwner ? '' : 'hidden'}" data-kick=${item.username}>Kick</span></li>`
            }
        });
        peopleList.innerHTML = tpl.join("");
    };

    // socket give the announcements
    socket.addEventListener('message', (ev) => {
        let data = JSON.parse(ev.data);
        let type = data.type;

        if (type === 'enter_room') {
            roomTitle.innerHTML = `${roomTitle.innerHTML} ${data.info.room_name}`
        } else if (type === 'announcement') {
            renderAnnouncement(data.info.announcements);
        } else if (type === 'member_list') {
            renderPeopleList(data.info.people);
        }
    });

    // click logout button
    logoutBtn.addEventListener('click', () => {
        socket.close();
        window.location.href = '/';
    });

    // click leave room btn
    leavePermBtn.addEventListener('click', () => {
        let msg = {
            type: "leave_room",
            info: JSON.stringify({username, room_id: roomId}),
        };
        socket.send(JSON.stringify(msg));
        location.href = "#/home";
    });

    // chat or kick with one person
    peopleList.addEventListener('click', (ev) => {
        let target = ev.target;
        if (target.dataset && (target.dataset.chat || target.dataset.kick)) {
            // chat
            if (target.dataset.chat) {
                location.href = `#/chat?user=${target.dataset.chat}`;
            } else if (target.dataset.kick) {
                // kick
                let msg = {
                    type: "kick",
                    info: JSON.stringify({username, room_id: roomId, member: target.dataset.kick}),
                };
                socket.send(JSON.stringify(msg));
            }
        }
    })

})();