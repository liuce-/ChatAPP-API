(function() {
    let curTemplate = null;
    let template = null;
    let pageContainer = null;
    let insertScript = null;

    const onHashChange = () => {
        switch (location.hash) {
            case '#/home':
                curTemplate = template.home;
                insertScript.src = './js/home.js';
                break;
            case '#/room':
                curTemplate = template.room;
                insertScript.src = './js/room.js';
                break;
            case '#/chat':
                curTemplate = template.chat;
                insertScript.src = './js/chat.js';
                break;
            default:
                curTemplate = template.index;
                insertScript.src = './js/index.js';

        }
        pageContainer.innerHTML = curTemplate.textContent;
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