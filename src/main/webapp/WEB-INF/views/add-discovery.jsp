<%--
  Created by IntelliJ IDEA.
  User: Bartekk
  Date: 27.01.2021
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <title>Dodaj nowe znalezisko</title>
    <%@include file="../segments/stylesheets.jspf"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/add-discovery-form.css">
</head>
<body>
<div class="container">
<%@include file="../segments/header.jspf"%>
<form action="${pageContext.request.contextPath}/discovery/add" method="post" class="discovery-form">
    <h2 class="discovery-form-title">Dodaj nowe znalezisko</h2>
    <input name="title" placeholder="Tytuł" required>
    <input name="url" placeholder="URL" type="url" required>
    <select name="categoryId">
        <c:forEach var="category" items="${requestScope.categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
    <textarea name="description" placeholder="Opis"></textarea>
    <button class="discovery-form-button">Dodaj</button>
</form>
<%@include file="../segments/footer.jspf"%>
</div>
</body>
</html>
