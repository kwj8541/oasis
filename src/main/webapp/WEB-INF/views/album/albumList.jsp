<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="imageVo" type="com.junyweb.oasis.vos.album.AlbumImageVo"--%>
<%--@elvariable id="userEntity" type="com.junyweb.oasis.entities.UserEntity"--%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/album/resources/stylesheets/albumList.css">
    <title>AlbumList</title>
    <c:if test="${userEntity == null}">
        <script>
            alert('로그인후 이용해주시기 바랍니다.');
            window.location.href="/user/login";
        </script>
    </c:if>
</head>
<body>
<%@ include file="/WEB-INF/views/root/header.jsp"%>
<main>
<div class="img">
    <a href="/">
        <img class="logo" alt="로고" src="/resources/images/oasisLogo2.png">
    </a>
</div>
<form method="get">
<div id="cover">
    <c:forEach var="album" items="${albumListVo}">
        <div class="item">
            <div class="image"><a href="${pageContext.request.contextPath}/oasis/albums/${album.index}/${album.title}"><img alt="앨범사진" src="<c:url value="/oasis/album-image/${album.title}"/>"></a></div>

            <span style="display: flex; flex-direction: column; align-items: center;" class="title"><a href="${pageContext.request.contextPath}/oasis/albums/${album.index}/${album.title}"><b style="text-decoration: none;">${album.title}</b></a></span>

            <span style="display: flex; flex-direction: column; align-items: center;" class="artist"><a>artist : ${album.artist}</a></span>
        </div>
    </c:forEach>
</div>
</form>
<div id="foot">
    <c:if test="${listVo.maxPage > 0}">
        <div>
            <c:if test="${listVo.page > 1}">
<%--                <a href="${pageContext.request.contextPath}/album-list/1"> << 맨앞으로가기 </a>--%>
                <a href="${pageContext.request.contextPath}/oasis/album-list/${listVo.page - 1}"> < </a>
            </c:if>
            <c:forEach var="page" begin="${listVo.startPage}" end="${listVo.endPage}">
                <c:choose>
                    <c:when test="${listVo.page == page}">
                        <a style="color: dimgray; font-family: fantasy">${page}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/oasis/album-list/${page}">${page}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${listVo.page < listVo.maxPage}">
                <a href="${pageContext.request.contextPath}/oasis/album-list/${listVo.page + 1}"> > </a>
<%--                <a href="${pageContext.request.contextPath}/album-list/${listVo.maxPage}"> >> 맨뒤로가기 </a>--%>
            </c:if>
        </div>
    </c:if>
</div>

</main>
</body>

</html>