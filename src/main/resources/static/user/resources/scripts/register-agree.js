const registerAgree = window.document.body.querySelector('[rel="register-agree-form"]');

registerAgree.onsubmit = () => { // form 데이터 submit 했을때
    if (!registerAgree['checkBox'].checked) { // 체크박스에 check 가 안되어 있을시
        alert('개인정보 처리방침에 동의해주세요'); // alert 띄움
        return false;
    }
    return true;
}