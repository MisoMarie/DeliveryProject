// 로그아웃 버튼을 찾기
const logoutBtn = document.getElementById('logout-btn');


if (logoutBtn !== null) {
    logoutBtn.onclick = (e) => {
        e.preventDefault();

        // 'header-csrf'라는 ID를 가진 폼을 찾기
        const csrfForm = document.getElementById('header-csrf');
        const csrfInput = csrfForm.querySelector('input[type="hidden"]');
        const csrfName = csrfInput.name;
        const csrfToken = csrfInput.value;

        // 기존 폼을 사용하여 로그아웃 요청 처리
        const form = document.createElement('form');
        form.action = "/user/logout";
        form.method = 'POST';

        // CSRF 토큰을 새로운 폼에 추가
        const csrfField = document.createElement('input');
        csrfField.type = 'hidden';
        csrfField.name = csrfName;
        csrfField.value = csrfToken;
        form.appendChild(csrfField);

        // 새로운 폼을 문서 본문에 추가하고 제출
        document.body.appendChild(form);
        form.submit();
    };

    document.getElementById('searchButton').addEventListener('click', function() {
        const foodValue = document.getElementById('foodInput').value;
        if (foodValue) {
            window.location.href = '/filter?food=' + encodeURIComponent(foodValue);
        } else {
            window.location.href = '/filter';
        }
    });
}
