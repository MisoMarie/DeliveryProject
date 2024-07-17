const formSections = document.getElementsByClassName('form-section');
const [pwEyeBtn, pwReEyeBtn] = document.getElementsByClassName('pw-view-btn');
const idInput = document.getElementById('id');
const idDuplicationBtn = document.getElementById('id-duplication-btn');
const pwInput = document.getElementById('pw');
const pwReInput = document.getElementById('pw-re');
const phoneHead = document.getElementById('phone-head');
const phoneBody = document.getElementById('phone-body');
const phoneInput = document.getElementById('phone');
const certBtn = document.getElementById('cert-btn');

const emailHead = document.getElementById('email-head');
const emailBody = document.getElementById('email-body');
const emailInput = document.getElementById('email');
const registerBtn = document.getElementById('register-btn');
const cancelBtn = document.getElementById('cancel-btn');



registerBtn.onclick = () => {
    // input_validate_check();
    document.forms.item(0).submit();
};
cancelBtn.onclick = () => {
    location.href = '/user/login';
}


// 휴대폰 번호 두 자리 합치기
phoneInput.value = phoneHead.value + phoneBody.value;
// 선택된 이메일 입력 방식에 따라 이메일을 설정한다
if(emailBody.value === '직접입력'){
    emailInput.value = emailHead.value;
}else{
    emailInput.value = emailHead.value + '@' + emailBody.value;
}



























// 휴대폰 번호 두 자리 합치기
phoneInput.value = phoneHead.value + phoneBody.value;
// 선택된 이메일 입력 방식에 따라 이메일을 설정한다
if(emailBody.value === '직접입력'){
    emailInput.value = emailHead.value;
}else{
    emailInput.value = emailHead.value + '@' + emailBody.value;
}

// if(!/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(emailInput.value)){
//     const emailValidDiv = document.querySelector('.valid-check.email');
//     emailValidDiv.toggleAttribute('active', true);
//     return;
// }