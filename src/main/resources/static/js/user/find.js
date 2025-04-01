const reName  = /^[가-힣]{2,10}$/
const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

document.addEventListener('DOMContentLoaded', function () {

    let isNameOk = false;
    let isEmailOk = false;

    // 이름 유효성 검사
    const nameResult = document.getElementsByClassName('nameResult')[0];

    formfindId.name.addEventListener('focusout', function(){

        const value = this.value;

        if(!value.match(reName)){
            nameResult.innerText = '이름이 유효하지 않습니다.';
            nameResult.style.color = 'red';
            isNameOk = false;
        }else{
            nameResult.innerText = '';
            isNameOk = true;
        }
    });

    // 5. 이메일 유효성 검사(중복/인증처리 포함)
    const btnSendEmail = document.getElementById('btnSendEmail');
    const emailResult = document.querySelector('.emailResult');
    const auth = document.querySelector('.auth');
    let preventDoubleClick = false;

    btnSendEmail.onclick = async function(){

        const value = formfindId.email.value;

        // 이중 클릭 방지
        if(preventDoubleClick){
            return;
        }

        if(!value.match(reEmail)){
            emailResult.innerText = '이메일이 유효하지 않습니다.';
            emailResult.style.color = 'red';
            isEmailOk = false;
            return;
        }

        preventDoubleClick = true;
        const response = await fetch(`/find/email/${value}`);
        const data = await response.json();

            // 인증번호 입력 필드 출력
            auth.style.display = 'block';
    };

    // 인증 코드 비교
    const btnAuthEmail = document.getElementById('btnAuthEmail');

    btnAuthEmail.onclick = async function(){

        const value = formfindId.auth.value;

        // JSON 데이터 생성
        const jsonData = {
            "authCode": value
        };

        // 서버 전송
        const response = await fetch('/find/email/auth', {
            method: 'POST',
            headers: {'Content-Type' : 'application/json'},
            body: JSON.stringify(jsonData)
        });

        const data = await response.json();
        console.log(data);

        if(data){
            emailResult.innerText = '이메일이 인증 되었습니다.';
            emailResult.style.color = 'green';
            isEmailOk = true;
        }else{
            emailResult.innerText = '유효한 인증코드가 아닙니다.';
            emailResult.style.color = 'red';
            isEmailOk = false;
        }
    }

});