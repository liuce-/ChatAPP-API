(function () {
    if (!window.socket) {
        window.location.href = '/';
        return;
    }

    const chatUserContainer = document.querySelector("#chat-user"),
        closeBtn = document.querySelector("#close-btn");

    // some initial info
    // user=aa&room=1&isOwner=true
    const chatInfoTmp = location.hash.split('?')[1];
    const chatInfo = chatInfoTmp.split('&');
    let chatUser, roomId, isOwner;
    chatInfo.forEach(item => {
        let key = item.split('=')[0];
        let val = item.split('=')[1];
        if (key === 'user') {
            chatUser = val;
            chatUserContainer.innerHTML = chatUser;
        } else if (key === 'room') {
            roomId = Number(val);
        } else if (key === 'isOwner') {
            isOwner = val === 'true';
        }
    });

    // click close dialogue
    closeBtn.addEventListener('click', () => {
        location.href = `#/room/${roomId}${isOwner ? '?owner' : ''}`;
    })
})();