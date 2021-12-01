<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.junyweb.oasis.enums.album.CommentResult" %>
<%--@elvariable id="commentResult" type="com.junyweb.oasis.enums.album.CommentResult"--%>
<%--@elvariable id="userEntity" type="com.junyweb.oasis.entities.UserEntity"--%>
<%--@elvariable id="music" type="com.junyweb.oasis.entities.MusicEntity"--%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/album/resources/stylesheets/album.css">
    <title>${albums.title}</title>
    <c:if test="${commentResult != null}">
        <c:choose>
            <c:when test="${commentResult == CommentResult.NOT_ALLOWED}"> <%-- 컨트롤러에서 request 객체로 결과넘겨받음 --%>
                <script>
                    alert('로그인후 작성해주세요');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${commentResult == CommentResult.NORMALIZATION_FAILURE}">
                <script>
                    alert('댓글은 50자 이내로 작성하여 주시기 바랍니다.');
                    window.history.back();
                </script>
            </c:when>
        </c:choose>
        <% out.close(); %>
    </c:if>
</head>

<body>
<%@ include file="/WEB-INF/views/root/header.jsp" %>
<main style="display: flex; justify-content: flex-start; align-items: center; flex-direction: column">
    <table style="width: 40rem">
        <thead>
        <tr>
            <td>
                <img style="height: 25rem; width: 40rem; border-width: 2px; border-style: solid; border-color: lightgray; margin-top: 1rem;" alt="앨범사진"
                     src="<c:url value="/oasis/album-image/${albums.title}"/>">
            </td>
        </tr>
        </thead>

        <tbody style="align-items: center; display: flex; justify-content: flex-start; flex-direction: column; margin-top: 1rem; margin-bottom: 3rem">
        <tr id="titleArtist">
            <td><b>${albums.title}</b> (artist : ${albums.artist})</td>
        </tr>
        <tr style="display: contents">
            <c:forEach var="music" items="${music}">
                <td style="display: none">
                        ${music.titleName}
                </td>
                <td style="margin-top: 0.5rem">
                        ${music.musicName}
                    <a class="hover" style="text-decoration: none; font-weight: bold; border-radius: 0.5rem; padding-right: 0.25rem; background-color: paleturquoise; margin-left: 0.5rem; color: orangered;"
                       onclick="window.open('/oasis/albums/music/${music.titleName}/${music.musicName}','','width=400,height=300,left=750rem,top=300rem')"
                       target="_blank"> > </a>
                </td>
            </c:forEach>
        </tr>
        </tbody>

        <tfoot>
        <tr>
            <td>
                <form method="post">
                    <label>
                        <span hidden>댓글</span>
                        <input style="height: 2rem; width: 35rem; border-style: revert; border-radius: 0.3rem; border-color: darkgray; margin-bottom: 2rem; padding-left: 0.5rem"
                               maxlength="100" name="content" placeholder="댓글" type="text">
                    </label>
                    <input style="height: 2rem; width: 3rem; border-style: revert; border-radius: 0.3rem; border-color: darkgray;"
                           type="submit" value="작성">
                </form>
            </td>
        </tr>
        <c:if test="${Arrays.stream(albums.comments).count() == 0}">
            <tr>
                <td style="padding: 1rem 0; text-align: center;">댓글이 없습니다.</td>
            </tr>
        </c:if>
        <c:forEach var="comment" items="${albums.comments}">
            <tr>
                <td>
                    <div style="background-color: lightgray; padding: 0.5rem; border-radius: 0.3rem; margin-bottom: 1.5rem; padding-bottom: 1rem;">
                        <div style="height: 2rem; display: flex; flex-direction: row; background-color: whitesmoke; border-style: none; border-radius: 0.3rem; padding: 0.4rem; margin-bottom: 1rem; margin-top: 0.2rem; align-items: center;">
                            <span>${comment.userNickname}</span>
                            <span style="flex: 1;"></span>
                            <span>${comment.formatCreatedAt()}</span>
                        </div>
                        <div style="display: flex; padding-right: 0.5rem; padding-left: 0.1rem;">
                        <span style="margin-left: 0.3rem">
                                ${comment.content}
                        </span>
                            <span style="flex: 1;"></span>
                            <span>
                                 <c:if test="${userEntity.email.equals(comment.userEmail)}">
                                     <a style="text-decoration: none; border-style: solid; border-radius: 0.3rem; color: red; border-color: red; font-weight: bold; border-width: 2px;" href="#" onclick="if (confirm('댓글을 삭제하시겠습니까?')) {window.location.href='/oasis/delete/${comment.index}/${albums.title}/${albums.albumPage}';}">X</a>
                                 </c:if>
                            </span>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tfoot>
    </table>

    <footer>
        <input type="button" onclick="window.location.href='/oasis/album-list/1';" value="앨범으로 돌아가기">
    </footer>
</main>
</body>
</html>