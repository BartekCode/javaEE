<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Zarejestruj się - ReadStack</title>
   <%@include file="../segments/stylesheets.jspf"%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">--%>
<%--    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;600&display=swap" rel="stylesheet">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <link rel="stylesheet" href="../../styles/main.css">--%>
<%--    <link rel="stylesheet" href="../../styles/forms.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jspf"%>
<%--    <nav class="navbar">--%>
<%--        <a href="#" class="logo">--%>
<%--            <i class="fas fa-share-alt-square"></i>--%>
<%--            ReadStack--%>
<%--        </a>--%>
<%--        <a href="#" class="login-button">Zaloguj</a>--%>
<%--    </nav>--%>

    <form action="#" method="post" class="user-form">
<!--    required wymagane    r-->
        <h2 class="user-form-title">Zarejestruj się na ReadStack</h2>
        <input name="username"  placeholder="Nazwa użytkownika" required>
        <input name="email" placeholder="email" type="email" required>
        <input name="password" type="password" placeholder="Hasło" required>
        <button class="user-form-button">Zarejestruj się</button>
    </form>
    <%@include file="../segments/footer.jspf"%>
<%--    <footer>ReadStack ®, developed by JavaStart.pl</footer>--%>
</div>
</body>
</html>