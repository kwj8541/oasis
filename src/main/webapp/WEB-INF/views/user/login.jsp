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
    <script defer src="resources/scripts/login.js"></script>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500&display=swap">
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
    <div>
        <div style="margin: 5rem auto;">
            <a href="/">
                <img class="logo" alt="로고" src="/resources/images/oasisLogo2.png">
            </a>
        </div>
    </div>
    <form method="post" rel="login-form">
        <div style="margin-left: 49rem;">
            <div>
                <input autofocus id="email-input" maxlength="50" name="email" placeholder="이메일"
                       type="email" value="2pat@naver.com">
            </div>
            <div>
                <input autofocus id="password-input" maxlength="50" name="password" placeholder="비밀번호"
                       type="password" value="dhdktltm">
            </div>
        </div>
        <div>
            <input style="cursor: pointer; margin-top: 1rem; margin-left: 745px" class="login" type="submit" value="로그인">
        </div>
    </form>
    <div style="margin-left: 48rem; margin-top: 1rem;">
        <span>
            <a href="/user/register-agree" style="text-decoration: none">회원가입</a>
        </span>
        <span style="margin-left: 3rem">
            <a href="#" style="text-decoration: none">아이디/비밀번호 찾기</a>
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