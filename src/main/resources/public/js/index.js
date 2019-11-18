(function () {
    const loginWrapper = document.querySelector('#login-wrapper'),
        registerWrapper = document.querySelector('#register-wrapper'),
        loginBtn = document.querySelector('#login-btn'),
        registerBtn = document.querySelector('#register-btn'),
        loginForm = document.querySelector('#login-form'),
        registerForm = document.querySelector('#register-form'),
        registerErr = document.querySelector('#register-err');


    const addScript = (src) => {
        let newScript = document.createElement('script');
        newScript.setAttribute("type", "text/javascript");
        newScript.setAttribute("src", src);
        document.getElementsByTagName("head")[0].appendChild(newScript);
    };

    // toggle login and register
    // Array.from(document.querySelectorAll('[data-form]')).forEach(item => {
    //     item.addEventListener('click', (ev) => {
    //         if(ev.target.dataset.form === 'login') {
    //             loginWrapper.classList.add('hidden');
    //             registerWrapper.classList.remove('hidden');
    //         } else if(ev.target.dataset.form === 'register') {
    //             loginWrapper.classList.remove('hidden');
    //             registerWrapper.classList.add('hidden');
    //         }
    //     })
    // });

    // click login
    // loginBtn.addEventListener('click', async (ev) => {
    //     ev.preventDefault();
    //     let username = loginForm.elements.username.value;
    //     if(!username) return;
    //
    //     let params = {
    //         method: 'post',
    //         body: JSON.stringify({username: username.trim()})
    //     };
    //     //let loginData = await fetch('/login', params);
    //
    //     let loginData = {res: 'ok'};
    //     if(loginData.res === 'ok') {
    //         // add websocket to the page
    //         addScript("./js/websocket.js");
    //         // global variable for username used in all templates
    //         window.username = username.trim();
    //         window.location.hash = '#/home';
    //     }
    // });

    // click register
    registerBtn.addEventListener('click', async (ev) => {
        ev.preventDefault();
        let username = registerForm.elements.username.value;
        let age = registerForm.elements.age.value;
        let location = registerForm.elements.location.value;
        let school = registerForm.elements.school.value;
        if (!username || !age || !location || !school) return;

        let params = {
            method: 'post',
            body: JSON.stringify({
                username: username.trim(),
                age: age,
                location: location,
                school: school
            })
        };
        let registerData = await fetch('/register', params);
        registerData = await registerData.json();

        if (registerData.result) {
            window.username = username.trim();
            // this should before location changes
            addScript("./js/websocket.js");
            addScript("./js/msgReminder.js");
            window.location.hash = '#/home';
        } else {
            registerErr.classList.remove('hidden');
        }
    });
})();