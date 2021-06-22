<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Bartekk
  Date: 25.01.2021
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <title class="category-title">${requestScope.category.name} - ReadStack</title>
<%--    <meta name="viewport" content="width=device-widths, initial-scale1.0">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">--%>
<%--    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400&display=swap" rel="stylesheet">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">--%>
    <%@include file="../segments/stylesheets.jspf"%>
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jspf"%>
<%--    <nav class="navbar">--%>
<%--        <a href="${pageContext.request.contextPath}" class="logo">--%>
<%--            <i class="fas fa-share-alt-square"></i>--%>
<%--            ReadStack--%>
<%--        </a>--%>
<%--        <a href="#" class="login-button">Zaloguj</a>--%>
<%--    </nav>--%>
    <main>
        <h1>${requestScope.category.name}</h1>
        <p>${requestScope.category.description}</p>
        <%@include file="../segments/discovery-list.jspf"%>
<%--        <c:forEach var="discovery" items="${requestScope.discoveries}">--%>
<%--            <article class="discovery">--%>
<%--                <h2 class="discovery-header"><c:out value="${discovery.title}"/></h2>--%>
<%--                <p class="discovery-details">Dodane przez: Mietek, ${discovery.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>--%>
<%--                <a href="<c:out value="${discovery.url}"/>" target="_blank" class="discovery-link"><c:out value="${discovery.url}"/></a>--%>
<%--                <p><c:out value="${discovery.description}"/></p>--%>
<%--                <section class="discovery-bar">--%>
<%--                    <a href="#" class="discovery-link upvote">--%>
<%--                        <i class="fas fa-arrow-alt-circle-up discovery-upvote"></i>--%>
<%--                    </a>--%>
<%--                    <p class="discovery-votes">32</p>--%>
<%--                    <a href="#" class="discovery-link downvote">--%>
<%--                        <i class="fas fa-arrow-alt-circle-down discovery-downvote"></i>--%>
<%--                    </a>--%>
<%--                </section>--%>
<%--            </article>--%>
<%--        </c:forEach>--%>
    </main>
    <%@include file="../segments/footer.jspf"%>
<%--    <footer>ReadStack ®, Bartek 2021</footer>--%>
</div>
</body>
</html>
