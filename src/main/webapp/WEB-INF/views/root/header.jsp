<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="userEntity" type="com.junyweb.oasis.entities.UserEntity"--%>
<header style="z-index: 999; text-decoration: none; border-bottom-style: inset; border-color: aquamarine;">
    <ul>
        <c:if test="${userEntity.haveTicket}">
            <li style="margin-right: 2rem; border-style: solid; border-radius: 10px; padding: 2px; border-width: thin; background: linear-gradient(180deg, aqua, aquamarine); color: ivory; border-color: aquamarine">스트리밍 이용중</li>
        </c:if>
        <c:if test="${userEntity == null}">
            <li>
                <a style="text-decoration: none; color: cornflowerblue" href="${pageContext.request.contextPath}/user/login">로그인</a>
            </li>
        </c:if>

        <c:if test="${userEntity != null}">
            <li>
                <b style="color: black; margin-right: 2rem">${userEntity.name}</b>
            </li>
            <li>
                <a style="text-decoration: none; color: cornflowerblue" href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
            </li>
        </c:if>
    </ul>
</header>