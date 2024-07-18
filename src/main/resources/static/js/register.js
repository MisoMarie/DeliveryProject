const registerBtn = document.getElementById('register-btn');
const idInput = document.getElementById('id');
const passwordInput = document.getElementById('pw');
const phoneInput = document.getElementById('phone');
const emailInput = document.getElementById('email');
const checkIdBtn = document.getElementById('id-duplication-btn');

const togglePasswordBtn = document.querySelector('.pw-view-btn');
const togglePasswordIcon = togglePasswordBtn.querySelector('i');


const phoneHeadInput = document.getElementById('phone-head');
const phoneBodyInput = document.getElementById('phone-body');
if (phoneHeadInput && phoneBodyInput) {
    phoneBodyInput.addEventListener('input', function () {
        phoneInput.value = `${phoneHeadInput.value}${phoneBodyInput.value}`;
    });
}

const emailHeadInput = document.getElementById('email-head');
const emailBodyInput = document.getElementById('email-body');
if (emailHeadInput && emailBodyInput) {
    const updateEmailInput = () => {
        if (emailBodyInput.value === '직접입력') {
            emailInput.value = emailHeadInput.value;
        } else {
            emailInput.value = `${emailHeadInput.value}@${emailBodyInput.value}`;
        }
    };

    emailHeadInput.addEventListener('input', updateEmailInput);
    emailBodyInput.addEventListener('change', updateEmailInput);
}

// registerBtn.addEventListener('click', function (event) {
//     event.preventDefault(); // 기본 폼 제출 방지
//
//     // 유효성 검사
//     const idValue = idInput.value;
//     const passwordValue = passwordInput.value;
//
//     if (idValue.length < 4 || idValue.length > 8) {
//         alert('ID는 최소 4글자에서 최대 8글자여야 합니다.');
//         return;
//     }
//
//     const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*]).{4,10}$/;
//     if (!passwordRegex.test(passwordValue)) {
//         alert('비밀번호는 4~10글자로 영문자, 숫자, 특수문자를 포함해야 합니다.');
//         return;
//     }
//
//     // 전화번호와 이메일 설정 업데이트
//     if (phoneHeadInput && phoneBodyInput) {
//         phoneInput.value = `${phoneHeadInput.value}${phoneBodyInput.value}`;
//     }
//
//     if (emailHeadInput && emailBodyInput) {
//         if (emailBodyInput.value === '직접입력') {
//             emailInput.value = emailHeadInput.value;
//         } else {
//             emailInput.value = `${emailHeadInput.value}@${emailBodyInput.value}`;
//         }
//     }
//
//     document.forms.item(0).submit();
// });



togglePasswordBtn.addEventListener('click', function () {
    const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordInput.setAttribute('type', type);

    // 아이콘 변경 (fa-eye <-> fa-eye-slash)
    if (type === 'password') {
        togglePasswordIcon.classList.remove('fa-eye-slash');
        togglePasswordIcon.classList.add('fa-eye');
    } else {
        togglePasswordIcon.classList.remove('fa-eye');
        togglePasswordIcon.classList.add('fa-eye-slash');
    }
});





checkIdBtn.addEventListener('click', function () {
    const idValue = idInput.value;
    if (idValue.length < 4 || idValue.length > 8) {
        alert('ID는 최소 4글자에서 최대 8글자여야 합니다.');
        return;
    }

    fetch(`/user/checkId?id=${idValue}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('응답이 안온다');
            }
            return response.json();
        })
        .then(data => {
            if (data) {
                alert('이미 사용 중인 ID입니다.');
            } else {
                alert('사용 가능한 ID입니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});

registerBtn.addEventListener('click', function (event) {
    event.preventDefault(); // 기본 폼 제출 방지

    const idValue = idInput.value;
    const passwordValue = passwordInput.value;

    if (idValue.length < 4 || idValue.length > 8) {
        alert('ID는 최소 4글자에서 최대 8글자여야 합니다.');
        return;
    }

    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*]).{4,10}$/;
    if (!passwordRegex.test(passwordValue)) {
        alert('비밀번호는 4~10글자로 영문자, 숫자, 특수문자를 포함해야 합니다.');
        return;
    }


    fetch(`/user/checkId?id=${idValue}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('응답이 안온다');
            }
            return response.json();
        })
        .then(data => {
            if (data) {
                alert('이미 사용 중인 ID입니다.');
            } else {
                document.forms.item(0).submit();
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});