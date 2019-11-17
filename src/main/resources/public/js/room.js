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
        peopleListContainer = document.querySelector("#people-list");

    // some initial info
    // current room id
    const hashTmpArr = location.hash.split('/');
    const roomId = Number(hashTmpArr[hashTmpArr.length - 1].split('?')[0]);
    const isOwner = location.href.includes('owner');

    // owner can send announcement
    isOwner ? announementBtn.classList.remove('hidden') : announementBtn.classList.add('hidden');

    // send room id first
    let msg = {
        type: "enter_room",
        info: JSON.stringify({username, room_id: roomId}),
    };
    socket.send(JSON.stringify(msg));

    // show the new announcement
    const renderAnnouncement = (announce) => {
        announementList.innerHTML += `<li>${announce}</li>`
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
        peopleListContainer.innerHTML = tpl.join("");
    };

    const getPeopleList = async () => {
        try {
            let PeopleList = await fetch(`/member_list/${roomId}`);
            renderPeopleList(PeopleList.people);
        } catch (err) {
            console.log(err);
        }
    };
    getPeopleList();

    // socket give the announcements
    socket.addEventListener('message', (ev) => {
        let data = JSON.parse(ev.data);
        let type = data.type;

        if (type === 'enter_room') {
            roomTitle.innerHTML = `${roomTitle.innerHTML} ${data.info.room_name}`
        } else if (type === 'announcement') {
            renderAnnouncement(data.info.announcement);
        } else if (type === 'enter_room') {
            if (data.info.room_id === roomId) {
                // Todo: add this to announcement
                renderAnnouncement();
                // get member list
                getPeopleList();
            }
        } else if (type === 'leave_room') {
            if (data.info.room_id === roomId) {
                // Todo: add this to announcement with leaving reason
                renderAnnouncement();
                // get member list
                getPeopleList();
            }
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
    peopleListContainer.addEventListener('click', (ev) => {
        let target = ev.target;
        if (target.dataset && (target.dataset.chat || target.dataset.kick)) {
            // chat
            if (target.dataset.chat) {
                location.href = `#/chat?user=${target.dataset.chat}&room=${roomId}&isOwner=${isOwner}`;
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