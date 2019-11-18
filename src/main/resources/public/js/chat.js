(function () {
    if (!window.socket) {
        window.location.href = '/';
        return;
    }

    const chatUserContainer = document.querySelector("#chat-user"),
        closeBtn = document.querySelector("#close-btn"),
        sendBtn = document.querySelector("#close-btn"),
        sendInput = document.querySelector("#text-input"),
        chatContainer = document.querySelector("#chat-msgs");

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
    });

    // send message
    sendBtn.addEventListener('click', () => {
        let msg = sendInput.value.trim();
        let sendMsg = {
            type: 'chat',
            info: {sender: username, receiver: chatUser, roomID: roomId, msg}
        };
        socket.send(JSON.stringify(sendMsg));

        let sendTpl = `<div>
                         <div class="chat-message-container justify-end">
                            <p class="chat-msg-name pr-10">${username}</p>
                            <div class="chat-message background-blue">
                                <p class="chat-msg-text color-white">${msg}</p>
                            </div>
                          </div>
                        </div>`;
        chatContainer.innerHTML += sendTpl;
    });

    socket.addEventListener('message', (ev) => {
        let data = JSON.parse(ev.data);
        let type = data.type;

        if (type === 'chat') {
            let receiveTpl = `<div>
                            <div class="chat-message-container justify-start">
                                <div class="chat-message background-light">
                                    <p class="chat-msg-text color-dark">${data.info.msg}</p>
                                </div>
                                <p class="chat-msg-name pl-10">${data.info.sender}</p>
                            </div>
                          </div>`;
            chatContainer.innerHTML += receiveTpl;
        }
    });
})();