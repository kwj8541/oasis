<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.junyweb.oasis.enums.ticket.MusicTicketResult" %>
<%--@elvariable id="userEntity" type="com.junyweb.oasis.entities.UserEntity"--%>
<%--@elvariable id="ticketResult" type="com.junyweb.oasis.enums.ticket.MusicTicketResult"--%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/ticket/resources/stylesheets/ticket.css">
    <title>이용권 구매</title>
    <c:if test="${userEntity == null}">
        <script>
            alert('로그인후 이용해주세요');
            window.location.href="/user/login";
        </script>
    </c:if>
</head>
<header>
    <%@ include file="/WEB-INF/views/root/header.jsp" %>
</header>
<body>
<main>
    <div class="img">
        <a href="/">
            <img class="logo" alt="로고" src="/resources/images/oasisLogo2.png">
        </a>
    </div>
    <div class="box">
        <h2 style="margin-bottom: 1rem">무제한 듣기</h2>
        <div class="box1">
            <h1>스트리밍 클럽 정기결제</h1>
            <h3>2,000원</h3>
            <span>
                <input value="구매" type="button">
            </span>
        </div>

        <h2 style="margin-top: 2rem;">쿠폰</h2>
        <div class="box2">
            <form method="post">
                <div>
                    <input id="ticketBox" name="ticketName" placeholder="(오아시스 무료이용권) 을 입력하시면 스트리밍 이용권을 드립니다." type="text">
                    <input id="insert" value="쿠폰등록" type="submit">
                </div>
            </form>
        </div>
    </div>
</main>
</body>

</html>