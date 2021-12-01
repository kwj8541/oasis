<%@ page import="com.junyweb.oasis.enums.album.CommentDeleteResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="commentDeleteVo" type="com.junyweb.oasis.vos.album.CommentDeleteVo"--%>
<%--@elvariable id="albumVo" type="com.junyweb.oasis.vos.album.AlbumVo"--%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>댓글 삭제</title>
    <c:choose>
        <c:when test="${commentDeleteVo.commentDeleteResult == CommentDeleteResult.FAILURE}">
            <script>
                alert('존재하지 않는 댓글입니다.');
                window.history.back();
            </script>
        </c:when>
        <c:when test="${commentDeleteVo.commentDeleteResult == CommentDeleteResult.NOT_ALLOWED}">
            <script>
                alert('댓글을 삭제할 권한이 없습니다.');
                window.history.back();
            </script>
        </c:when>
        <c:when test="${commentDeleteVo.commentDeleteResult == CommentDeleteResult.SUCCESS}">
            <script>
                alert('댓글을 삭제하였습니다.');
                window.location.href = '/oasis/albums/${commentDeleteVo.albumPage}/${commentDeleteVo.title}';
            </script>
        </c:when>
        <c:otherwise>
            <script>
                alert('댓글을 삭제하는 도중 오류가 발생하였습니다.\n\n잠시 후 다시 시도해주세요.');
                window.history.back();
            </script>
        </c:otherwise>
    </c:choose>
</head>
</html>