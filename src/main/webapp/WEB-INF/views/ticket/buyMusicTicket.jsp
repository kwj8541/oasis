<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true" %>
<%--@elvariable id="userEntity" type="com.junyweb.oasis.entities.UserEntity"--%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/ticket/resources/stylesheets/ticket.css">
    <title>이용권 구매</title>
</head>
<body>
<%@ include file="/WEB-INF/views/root/header.jsp"%>
<main>
    <div class="img">
        <a href="/">
            <img class="logo" alt="로고" src="/resources/images/oasisLogo2.png">
        </a>
    </div>
    <div style="border-style: solid">
        <h1>무제한 듣기</h1>
        <h3>스트리밍 클럽 정기결제</h3>
    </div>
    <form method="post" style="border-style: solid">
        <div>
           <input name="ticket" placeholder="쿠폰을 입력해주세요" type="submit" value="쿠폰 등록">
        </div>
    </form>
</main>
</body>

</html>