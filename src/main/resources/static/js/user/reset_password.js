document.addEventListener('DOMContentLoaded', function () {
    const pwViewBtn = document.querySelector('.pw-view-btn');
    const passwordInput = document.querySelector('input[name="password"]');

    pwViewBtn.addEventListener('click', function () {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
        this.innerHTML = type === 'password' ? '<i class="fa-solid fa-eye"></i>' : '<i class="fa-solid fa-eye-slash"></i>';
    });
});
