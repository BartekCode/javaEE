<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>ReadStack</title>
    <%--  wklejamy nasze pliki jsp zastepujemy powtarzalny kod  --%>
    <%@include file="../segments/stylesheets.jspf"%>

<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400&display=swap" rel="stylesheet">--%>
<%--    <link rel="stylesheet" type="text/css" href="../../styles/main.css">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">--%>
</head>
<body>
<div class="container">
<%--   stopka --%>
    <%@include file="../segments/header.jspf"%>
<%--<nav class="navbar">--%>
<%--    <a href="#" class="logo">--%>

<%--<!--    dodanie loga    -->--%>
<%--        <i class="fas fa-share-alt-square"></i>--%>
<%--        ReadStack--%>
<%--    </a>--%>
<%--    <a href="#" class="login-button">Zaloguj</a>--%>
<%--</nav>--%>
<aside class="categories">
    <ul>
        <c:forEach var="category" items="${requestScope.categories}">

<%--       adres strony głównej, do której będziemy przeniesieni po kliknięciu logo strony.     --%>
            <li><a href="${pageContext.request.contextPath.concat('/category?id=').concat(category.id)}">${category.name}</a> </li>
        </c:forEach>
    </ul>
    </aside>
    <main>
        
        <%@include file="../segments/discovery-list.jspf" %>
<%--        <c:forEach var="discovery" items="${requestScope.discoveries}">--%>
<%--        <article class="discovery">--%>
<%--            <h2 class="discovery-header"><c:out value="${discovery.title}"/></h2>--%>
<%--            <p class="discovery-details">Dodane przez: Mietek, ${discovery.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>--%>
<%--            <a href="<c:out value="${discovery.url}"/>" target="_blank" class="discovery-link"><c:out value="${discovery.url}"/></a>--%>
<%--            <p><c:out value="${discovery.description}"/> </p>--%>
<%--      <section class="discovery-bar">--%>
<%--          <a href="#" class="discovery-link upvote">--%>
<%--              <i class="fas fa-arrow-alt-circle-up discovery-upvote"></i>--%>
<%--          </a>--%>
<%--          <p class="discovery-votes">32</p>--%>
<%--          <a href="#" class="discovery-link downvote">--%>
<%--              <i class="fas fa-arrow-alt-circle-down discovery-downvote"></i>--%>
<%--          </a>--%>
<%--      </section>--%>
<%--        </article>--%>
<%--        </c:forEach>--%>

    </main>

<%@include file="../segments/footer.jspf"%>
</div>
</body>
</html>