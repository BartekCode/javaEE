<%--
  Created by IntelliJ IDEA.
  User: Bartekk
  Date: 26.01.2021
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <title>Zaloguj się - ReadStack</title>
    <%@include file="../segments/stylesheets.jspf"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jspf"%>
<%--  Strona logowania będzie wyglądała niemal identycznie. Będzie się różniła tylko tym jakie pola znajdują się w formularzu,
jaka jest wartość atrybutu action. Należy też pamiętać o ustawieniu odpowiednich nazw parametrów, czyli j_username i j_password.  --%>
    <form action="j_security_check" method="post" class="user-form">
        <h2 class="user-form-title">Zaloguj się na ReadStack</h2>
        <input name="j_username" placeholder="Nazwa użytkownika" required>
        <input name="j_password" placeholder="Hasło" type="password" required>
        <button class="user-form-button">Zaloguj się</button>
        <p>Nie masz konta? <a href="${pageContext.request.contextPath}/signup">Zarejestruj się</a></p>
<%--     pageContext... odpowiada za adres do naszej apki i dodajemy /signup   --%>
    </form>
    <%@include file="../segments/footer.jspf"%>
</div>
</body>
</html>
