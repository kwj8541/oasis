<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true" %>
<%@ page import="com.junyweb.oasis.enums.user.RegisterResult" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입</title>
    <link rel="stylesheet" href="/user/resources/stylesheets/register.css">
    <script>
        <c:if test="${registerResult != null}">
        <c:choose>
        <c:when test="${registerResult == RegisterResult.SUCCESS}">
        <c:set var="alert" scope="request" value="입력하신 이메일주소로 인증 메일을 전송하였습니다. \\n\\n 이메일 인증후 로그인하여주시기 바랍니다."/>
        </c:when>

        <c:when test="${registerResult == RegisterResult.EMAIL_DUPLICATE}">
        <c:set var="alert" scope="request" value="이미 사용 중인 이메일입니다."/>
        </c:when>

        <c:when test="${registerResult == RegisterResult.NICKNAME_DUPLICATE}">
        <c:set var="alert" scope="request" value="이미 사용 중인 닉네임입니다."/>
        </c:when>

        <c:otherwise>
        <c:set var="alert" scope="request" value="알 수 없는 오류가 발생하였습니다. 잠시 후 다시 시도하시거나 관리자에게 문의해주세요."/>
        </c:otherwise>
        </c:choose>

        alert('${alert}');

        <c:choose>
        <c:when test="${registerResult == RegisterResult.SUCCESS}">
        window.location.href = 'login';
        </c:when>
        <c:otherwise>
        window.history.back();
        </c:otherwise>
        </c:choose>

        </c:if>
    </script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="/resources/scripts/ajax.class.js"></script>
    <script defer src="/user/resources/scripts/register.js"></script>
</head>
<body>
<div rel="cover"
     style="width: 100%; height: 100%; background-color: #00020475; display:none; position: fixed; z-index: 1;"></div>
<div rel="address-container"
     style="top: 50%; left: 50%; width: 30rem; height: 30rem;
     display: none; position: fixed; transform: translate(-50%, -50%); z-index: 2;"></div>
<main>
    <form method="post" rel="register-form">
        <section>
            <h2>정보 입력</h2>
            <table style="width: 50rem">
                <tbody>
                <tr>
                    <th>
                        <label for="email-input">이메일</label>
                    </th>
                    <td>
                        <input autofocus id="email-input" maxlength="50" name="email" placeholder="이메일" type="email">
                        <span class="input-message" rel="email-message">이미 사용중인 이메일입니다.</span>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="password-input">비밀번호</label>
                    </th>
                    <td>
                        <input autofocus id="password-input" maxlength="50" name="password" placeholder="8글자 이상 비밀번호를 입력해주세요"
                               type="password">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="password-check-input">비밀번호 재입력</label>
                    </th>
                    <td>
                        <input autofocus id="password-check-input" maxlength="50" name="passwordCheck"
                               placeholder="비밀번호 재입력" type="password">
                        <span class="input-message" rel="password-message">비밀번호가 일치하지않습니다</span>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="nickname-input">닉네임</label>
                    </th>
                    <td>
                        <input autofocus id="nickname-input" maxlength="10" name="nickname" placeholder="닉네임"
                               type="text">
                        <span class="input-message" rel="nickname-message">이미 사용중인 닉네임입니다.</span>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="name-input">이름</label>
                    </th>
                    <td>
                        <input autofocus id="name-input" maxlength="10" name="name" placeholder="이름" type="text">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="address-input">주소</label>
                    </th>
                    <td>
                        <input autofocus id="address-input" maxlength="5" name="addressPostal" placeholder="우편번호"
                               readonly type="text">
                        <input style="height: 23px; width: 66px;" rel="address-find-button" type="button" value="주소찾기">
                        <br>
                        <input name="addressPrimary" placeholder="기본주소" readonly type="text">
                        <br>
                        <input name="addressSecondary" placeholder="상세주소" type="text">
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
        <div>
            <input type="submit" value="회원가입">
            <input type="button" onclick="window.location.href = '/';" value="홈으로">
        </div>
    </form>
</main>

</body>
</html>