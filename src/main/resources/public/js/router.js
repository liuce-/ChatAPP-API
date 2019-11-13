(function() {
    let curTemplate = null;
    let template = null;
    let pageContainer = null;
    let insertScript = null;

    const onHashChange = () => {
        let src = "";
        switch (true) {
            case /#\/home/.test(location.hash):
                curTemplate = template.home;
                src = './js/home.js';
                break;
            case /#\/room\/\d+/.test(location.hash):
                curTemplate = template.room;
                src = './js/room.js';
                break;
            case /#\/chat/.test(location.hash):
                curTemplate = template.chat;
                src = './js/chat.js';
                break;
            default:
                curTemplate = template.index;
                src = './js/index.js';

        }
        pageContainer.innerHTML = curTemplate.textContent;
        // only create new script and append can be run immediately
        insertScript.innerHTML = "";
        let script = document.createElement("script");
        script.type = "text/javascript";
        script.src = src;
        insertScript.appendChild(script);
    };

    const onPageLoad = () => {
        pageContainer = document.querySelector('#page-container');
        insertScript = document.querySelector('#insert-script');
        template = {
            index: document.querySelector('#index-template'),
            home: document.querySelector('#home-template'),
            room: document.querySelector('#room-template'),
            chat: document.querySelector('#chat-template')
        };
        onHashChange();
    };

    window.addEventListener('DOMContentLoaded', onPageLoad);
    window.addEventListener('hashchange', onHashChange);
})();