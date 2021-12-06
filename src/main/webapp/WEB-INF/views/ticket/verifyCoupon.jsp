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
    <title>쿠폰 확인</title>
    <script>
        <c:choose>
        <c:when test="${resultOfTicket == MusicTicketResult.SUCCESS}">
        <c:set var="alert" value="쿠폰이 등록되었습니다."/>
        </c:when>
        <c:when test="${resultOfTicket == MusicTicketResult.ALREADY_HAVE}">
        <c:set var="alert" value="이미 스트리밍을 이용중이십니다."/>
        </c:when>
        <c:otherwise>
        <c:set var="alert" value="쿠폰을 다시 확인해주세요."/>
        </c:otherwise>
        </c:choose>
        alert('${alert}');
        <c:choose>
        <c:when test="${resultOfTicket == MusicTicketResult.SUCCESS}">
        window.location.href = '/';
        </c:when>
        <c:otherwise>
        window.history.back();
        </c:otherwise>
        </c:choose>
    </script>
</head>
<body>
결과는 ${resultOfTicket.toString()} 입니다
</body>
</html>