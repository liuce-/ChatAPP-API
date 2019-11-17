(function() {
    const insertMsgReminder = document.querySelector("#insert-msg-reminder");

    socket.addEventListener('message', (ev) => {
        let data = JSON.parse(ev.data);
        let type = data.type;

        if (type === 'chat') {

            if(!insertMsgReminder.innerHTML) {
                let reminderTpl = `<div class="msg-reminder-container">
                                    <div class="msg-reminder" id="msg-reminder">Receive a new message</div>
                                    <div class="msg-container hidden" id="msg-list">
                                        <div class="msg-tit" id="close-msg">New messages</div>
                                        <ul id="add-msg">
                                            <li class="msg-tip">xxx from room xxx sent you a message</li>
                                        </ul>
                                    </div>
                                   </div>`;
                insertMsgReminder.innerHTML = reminderTpl;
                // add event
                document.querySelector("#msg-reminder").addEventListener('click', (ev) => {
                    ev.target.classList.add("hidden");
                    document.querySelector("#msg-list").classList.remove("hidden");
                    // show new reminder
                    let reminderTpl = `<li class="msg-tip" data-msg=${data.info.msg}>${data.info.sender} from room ${data.info.room_name} sent you a message</li>`;
                    let newMsgListContainer = document.querySelector('add-msg');
                    newMsgListContainer.innerHTML += reminderTpl;
                    // delegate event
                    newMsgListContainer.addEventListener('click', (ev) => {
                        if(ev.target.dataset && ev.target.dataset.msg) {
                            location.href = `#/chat?user=${data.info.sender}&room=${data.info.room_id}&isOwner=${data.info.room_owner === username}`;
                        }
                    });
                });
                // min message box
                document.querySelector("#close-msg").addEventListener('click', (ev) => {
                    document.querySelector("#msg-reminder").classList.remove("hidden");
                    document.querySelector("#msg-list").classList.add("hidden");
                });
            } else {
                // blinking several times if receive a new message
                let count = 1;
                let timer = setInterval(() => {
                    document.querySelector("#msg-reminder").classList.toggle("hidden");
                    if (count++ === 6) {
                        clearInterval(timer);
                    }
                }, 300);
            }
        }
    });

})();
