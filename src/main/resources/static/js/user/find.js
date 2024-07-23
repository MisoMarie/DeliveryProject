document.addEventListener('DOMContentLoaded', function() {
    const findIdEmailInput = document.getElementById('find-id-email');
    const findIdBtn = document.getElementById('find-id-btn');

    const findPwIdInput = document.getElementById('find-pw-id');
    const findPwEmailInput = document.getElementById('find-pw-email');
    const findPwBtn = document.getElementById('find-pw-btn');

    findIdBtn.onclick = () => {
        const email = findIdEmailInput.value;
        // 이메일을 입력하지 않았다면
        if(email.trim() === ''){
            alert('이메일을 입력해주세요');
            return;
        }
        // 이메일 형식을 검사한다.... (생략..)
        if(true){}
        // 해당 이메일로 아이디를 찾아서 보내준다
        fetch(`/user/find/id?email=${email}`)
            .then(response => response.text())
            .then(alert);
    }

    findPwBtn.onclick = () => {
        const id = findPwIdInput.value;
        const email = findPwEmailInput.value;
        // 아이디를 입력하지 않았다면
        if(id.trim() === ''){
            alert('아이디를 입력해주세요');
            return;
        }
        // 이메일을 입력하지 않았다면
        if(email.trim() === ''){
            alert('이메일을 입력해주세요');
            return;
        }
        fetch(`/user/find/pw?id=${id}&email=${email}`)
            .then(response => response.text())
            .then(alert)
    }
});
