<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="userEntity" type="com.junyweb.oasis.entities.UserEntity"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OASIS</title>
    <link rel="stylesheet" href="./resources/stylesheets/main.css">
</head>
<body>
<%@ include file="/WEB-INF/views/root/header.jsp" %>
<div class="main">
    <div class="header">
        <img class="logo" alt="로고" src="./resources/images/oasisLogo2.png">
    </div>
    <div style="justify-content: center; align-items: center; display: flex; font-size: 1.5rem; margin-bottom: 1rem; font-weight: bold; color: dimgray;">
        <span style="margin-right: 34rem;">조회수 TOP5</span>
        <span>앨범</span>
    </div>
    <div class="body" style="justify-content: center;">
        <span style="display: flex; width: 40rem; border-style: solid; border-color: darkorange; border-width: thin; padding-left: 1rem;">
        <span style="align-items: center; display: inline-block; flex-direction: column; width: 3rem; height: 30rem; font-size: 2rem">
                    <c:forEach var="num" begin="1" end="5">
                        <span style="display: flex; color: lightgray; font-weight: bold; margin-top: 1.5rem; flex-direction: column; width: 2rem; height: 4rem; align-items: center">${num}</span>
                    </c:forEach>
        </span>
        <span class="TOP" style="display: block; flex-direction: column; width: 37rem; height: 30rem; font-size: 2rem">
            <c:forEach var="music" items="${musicTop5}">
                    <span style="display: flex; margin-top: 1.5rem; flex-direction: column; width: 37rem; height: 4rem; color: salmon; font-family: 'Montserrat', 'Noto Sans KR', sans-serif; font-weight: 400">
                        <a style="cursor:pointer;" onclick="window.open('/oasis/albums/music/${music.titleName}/${music.musicName}','','width=400,height=300,left=750rem,top=300rem')">[${music.titleName}] - ${music.musicName}</a>
                    </span>
            </c:forEach>
        </span>
            </span>
        <span style="height: 30rem; width: 40rem;">
            <a href="/oasis/album-list/1">
                <img style="margin-left: 2rem" alt="앨범사진" src="./resources/images/album.jpg">
            </a>
        </span>
    </div>
</div>
<footer style="margin-top: 15rem; color: white;">
    <div>저작권</div>
</footer>

</body>
</html>