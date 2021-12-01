<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="practiceEntity" type="com.junyweb.oasis.entities.PracticeEntity"--%>
<%--@elvariable id="practiceVo" type="com.junyweb.oasis.vos.album.PracticeVo"--%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<main>
    <div>
        <c:forEach items="${practiceEntity}" var="practice">
            <tbody>
            <tr>
                <td>${practice.p1}</td>
                <td>${practice.p2}</td>
            </tr>
            </tbody>
        </c:forEach>
    </div>
</main>

</body>
</html>