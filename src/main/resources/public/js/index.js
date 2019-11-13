(function() {
    let curTemplate = null;
    let template = null;
    let pageContainer = null;

    const onHashChange = () => {
        switch (location.hash) {
            case '#/home':
                curTemplate = template.home;
                break;
            case '#/room':
                curTemplate = template.room;
                break;
            case '#/chat':
                curTemplate = template.chat;
                break;
            default:
                curTemplate = template.index;
        }
        pageContainer.innerHTML = curTemplate.textContent;
    };

    const onPageLoad = () => {
        pageContainer = document.querySelector('#page-container');
        template = {
            index: document.querySelector('#index-template'),
            home: document.querySelector('#home-template'),
            room: document.querySelector('#room-template'),
            chat: document.querySelector('#chat-template')
        };
        onHashChange();
    };

    window.addEventListener('DOMContentLoaded', onPageLoad);
})();