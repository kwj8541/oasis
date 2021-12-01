<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>약관동의</title>
    <link rel="stylesheet" href="/user/resources/stylesheets/login.css">
    <script defer src="/user/resources/scripts/register-agree.js"></script>
</head>
<body>
<main>
    <div>
        <div>
            <a href="/">
                <img style="margin-top: 3rem;" class="logo" alt="로고"
                     src="/resources/images/oasisLogo2.png">
            </a>
        </div>
    </div>
    <div>
        <h1 style="font-size: 3rem; margin-left: 25px; color: dimgray; margin-right: auto">약관동의</h1>
    </div>
    <form style="margin-left: -5rem; margin-top: -6rem;" method="post" rel="register-agree-form">
        <section>
            <table style="margin-left: auto; margin-right: auto;">
                <thead>
                <tr>
                    <th>
                        <h3 style="color: dimgray;">개인정보처리방침</h3>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <textarea style="height: 5rem; width: 29rem;" readonly>개인정보처리방침</textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>
                            <input name="checkBox" type="checkbox" style="cursor: pointer">
                            <span style="color: dimgray;">[필수] 개인정보처리방침을 읽어보았으며 이해하고 동의합니다.</span>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input style="height: 5rem; width: 15rem; margin: 2rem 7rem; font-size: 1rem;
    border-radius: 10px;
    border-style: hidden;
color: dimgray; cursor: pointer" name="registerSubmit"
                               type="submit" value="회원가입">
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </form>
</main>

</body>
</html>