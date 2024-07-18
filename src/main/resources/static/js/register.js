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

// IMP.init("imp14271731");
// // 아이디 중복검사
// idDuplicationBtn.onclick = () => {
//     const userId = idInput.value;
//     if(userId.trim().length === 0){
//         alert('아이디를 입력하세요');
//         return;
//     }
//
//     fetch(`/user/find/id/${userId}`)
//         .then(response => response.json())
//         .then(result => {
//             console.log(result)
//             result ? alert('해당 아이디는 사용할 수 없습니다') : alert('해당 아이디는 사용가능합니다');
//         })
// }
// 인증하기
// certBtn.onclick = () => {
//     const certValueInput = document.getElementById('cert-value');
//     IMP.certification(
//         { pg: "inicis_unified.MIIiasTest" },
//         function (response) {
//             if (response.success) {
//                 // 인증 성공 시 로직
//                 alert('인증 성공')
//                 certValueInput.value = response.imp_uid;
//                 certBtn.textContent = '인증완료'
//                 certBtn.disabled = true;
//             } else {
//                 // 인증 실패 시 로직
//                 console.log('실패...')
//                 console.log(response)
//             }
//         },
//     );
// }

// 회원가입 버튼 누를 시 / 취소할 시
registerBtn.onclick = () => {
    input_validate_check();
    document.forms.item(0).submit();
};
cancelBtn.onclick = () => {
    location.href = '/user/login';
}

pwEyeBtn.onclick = () => {
    pw_view_change(pwEyeBtn);
};
pwReEyeBtn.onclick = () => {
    pw_view_change(pwReEyeBtn);
};

// 패스워드 눈 버튼 눌렀을 때 변화
function pw_view_change(eyeBtn){
    const inputTag = eyeBtn.previousElementSibling;
    const icon = eyeBtn.querySelector('i');
    // 원래 보이다가 안보이도록 한다
    if(icon.classList.contains('fa-eye-slash')){
        icon.className = 'fa-solid fa-eye';
        inputTag.type = 'password';
    }
    // 원래 안보이다가 보이도록 한다
    else{
        icon.className = 'fa-solid fa-eye-slash';
        inputTag.type = 'text';
    }
}

// 드모르간 법칙
// input 값 들의 유효성 검증
function input_validate_check(){
    // const validInputs = document.getElementsByClassName('valid-check');
    // [...validInputs].forEach(validInput => {validInput.removeAttribute('active');});
    // // id 체크
    // const idValue = idInput.value;
    // if(idValue.length < 4 || idValue.length > 8){
    //     const idValidDiv = document.querySelector('.id.valid-check');
    //     idValidDiv.toggleAttribute('active', true);
    //     return;
    // }
    // // pw 체크...
    // const pwValue = pwInput.value;
    // const pwReValue = pwReInput.value;
    // const pwValidDiv = document.querySelector('.valid-check.pw');
    // if(!/[a-z]/ig.test(pwValue)){
    //     pwValidDiv.textContent = '비밀번호는 영문자가 포함되어야 합니다';
    //     pwValidDiv.toggleAttribute('active', true);
    //     return;
    // }
    // else if(!/[0-9]/g.test(pwValue)){
    //     pwValidDiv.textContent = '비밀번호는 숫자가 포함되어야 합니다';
    //     pwValidDiv.toggleAttribute('active', true);
    //     return;
    // }
    // else if(pwValue.length < 4 || pwValue.length > 10){
    //     pwValidDiv.textContent = '비밀번호는 4-10글자 사이로 작성해주세요';
    //     pwValidDiv.toggleAttribute('active', true);
    //     return;
    // }
    // else if(/[!@#$%^&*_=-]/g.test(pwValue)){
    //     pwValidDiv.textContent = '비밀번호는 특수문자가 포함되어야 합니다';
    //     pwValidDiv.toggleAttribute('active', true);
    //     return;
    // }
    //
    // if(pwValue !== pwReValue){
    //     pwValidDiv.textContent = '비밀번호와 재입력 된 비밀번호가 다릅니다';
    //     pwValidDiv.toggleAttribute('active', true);
    //     return;
    // }

    // 휴대폰 번호 두 자리 합치기
    phoneInput.value = phoneHead.value + phoneBody.value;
    // 선택된 이메일 입력 방식에 따라 이메일을 설정한다
    if(emailBody.value === '직접입력'){
        emailInput.value = emailHead.value;
    }else{
        emailInput.value = emailHead.value + '@' + emailBody.value;
    }

    if(!/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(emailInput.value)){
        const emailValidDiv = document.querySelector('.valid-check.email');
        emailValidDiv.toggleAttribute('active', true);
        return;
    }



}



for(let i = 0 ; i < formSections.length; i++){
    // select 태그는 하나가 아니라 여러개 존재할 수 있음
    const selectTags = formSections[i].getElementsByTagName('select');
    for(let j = 0; j < selectTags.length; j++){
        const selectTag = selectTags[j];
        // input태그에 커서가 위치했을 경우
        selectTag.onfocus = () => {
            selectTag.parentElement.toggleAttribute('active', true);
        }
        // input태그가 focus를 잃을 때
        selectTag.onblur = () => {
            selectTag.parentElement.toggleAttribute('active', false);
        }
    }


    const inputTag = formSections[i].querySelector('input');
    // input태그에 커서가 위치했을 경우
    inputTag.onfocus = () => {
        inputTag.parentElement.toggleAttribute('active', true);
    }
    // input태그가 focus를 잃을 때
    inputTag.onblur = () => {
        inputTag.parentElement.toggleAttribute('active', false);
    }
}
