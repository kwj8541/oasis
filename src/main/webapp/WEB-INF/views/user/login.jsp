<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.junyweb.oasis.enums.user.LoginResult" %>
<%--@elvariable id="userEntity" type="com.junyweb.oasis.entities.UserEntity"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OASIS</title>
    <link rel="stylesheet" href="/user/resources/stylesheets/login.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500&display=swap">
    <script defer src="/user/resources/scripts/login.js"></script>
    <script>
        <c:if test="${loginResult != null}">
        <c:choose>
        <c:when test="${loginResult == LoginResult.DELETED}">
        <c:set var="alert" value="해당계정은 삭제된 계정입니다. 관리자에게 문의해주세요."/>
        </c:when>
        <c:when test="${loginResult == LoginResult.EMAIL_NOT_VERIFIED}">
        <c:set var="alert" value="이메일인증이 완료되지 않았습니다."/>
        </c:when>
        <c:when test="${loginResult == LoginResult.SUSPENDED}">
        <c:set var="alert" value="해당 계정은 정지되어 있습니다. 관리자에게 문의해주세요."/>
        </c:when>
        <c:otherwise>
        <c:set var="alert" value="이메일 혹은 비밀번호가 올바르지 않습니다."/>
        </c:otherwise>
        </c:choose>
        alert('${alert}');
        window.history.back();
        </c:if>
    </script>
</head>
<body>
<main>
    <div class="head">
        <div style="margin: 5rem auto;">
            <a href="/">
                <img class="logo" alt="로고" src="/resources/images/oasisLogo2.png">
            </a>
        </div>
    </div>
    <form method="post" rel="login-form">
        <div style="width: 100%; justify-content: center; align-items: center; display: contents;">
            <div style=" justify-content: center;
    align-items: center;
    display: flex;">
                <input autofocus id="email-input" maxlength="50" name="email" placeholder="이메일"
                       type="email" value="2pat@naver.com">
            </div>
            <div style="justify-content: center;
    align-items: center;
    display: flex;">
                <input autofocus id="password-input" maxlength="50" name="password" placeholder="비밀번호"
                       type="password" value="dhdktltm">
            </div>
        </div>
        <div style="align-items: center; justify-content: center; display: flex; margin-top: 2rem;">
            <input style="cursor: pointer; display: flex; align-items: center; justify-content: center; "class="login" type="submit"
                   value="로그인">
        </div>
    </form>
    <div style="height: 4.5rem; border-radius: 9px; display: flex; justify-content: center; align-items: center; width: 100%;">
        <span style="justify-content: center; align-items: center; display: flex; background: antiquewhite; width: 20rem; height: 4rem; border-radius: 9px;">
            <a href="/user/register-agree" style="text-decoration: none; color: black;">회원가입</a>
        </span>
    </div>
    <div>
        <footer>
            <div style="margin-top: 50rem" class="footer">(이용약관, 이메일, 회사명, 연락처, 저작권?)</div>
        </footer>
    </div>
</main>
</body>
</html>