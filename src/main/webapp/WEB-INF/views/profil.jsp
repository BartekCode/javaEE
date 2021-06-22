<%--
  Created by IntelliJ IDEA.
  User: Bartekk
  Date: 30.01.2021
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profil użytkownika</title>
    <%@include file="../segments/stylesheets.jspf"%>
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jspf"%>
</div>
<div class="profildata">
    <div >
        <h1>Twoje Dane</h1>
        <h2>Nazwa użytkownika : ${pageContext.request.userPrincipal.name}</h2>
        <h2>Adres mailowy: ${requestScope.logedemail}</h2>
    </div>
<div>
<h2> Znaleziska dodane przez Ciebie </h2>
            <c:forEach var="usersdisoveries" items="${requestScope.loggedusersdiscoveries}">
                <article class="discovery">
                    <h2 class="discovery-header"><c:out value="${usersdisoveries.title}"/></h2>
                    <p class="discovery-details">Dodane przez: ${usersdisoveries.author}, ${usersdisoveries.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
                    <a href="<c:out value='${usersdisoveries.url}'/>" target="_blank" class="discovery-link"><c:out value="${usersdisoveries.url}"/></a>
                    <p><c:out value="${usersdisoveries.description}"/></p>
                    <section class="discovery-bar">
                        <a href="${pageContext.request.contextPath.concat('/discovery/vote?id=').concat(usersdisoveries.id).concat('&type=UP')}" class="discovery-link upvote">
                            <i class="fas fa-arrow-alt-circle-up discovery-upvote"></i>
                        </a>
                        <p class="discovery-votes">${usersdisoveries.voteCount}</p>
                        <a href="${pageContext.request.contextPath.concat('/discovery/vote?id=').concat(usersdisoveries.id).concat('&type=DOWN')}" class="discovery-link downvote">
                            <i class="fas fa-arrow-alt-circle-down discovery-downvote"></i>
                        </a>
                    </section>
                </article>
            </c:forEach>
            </div>
</div>
<div class="favDisc">
    <h2>Znaleziska dodane do ulubionych</h2>
    <c:forEach var="uservoteddiscoveries" items="${requestScope.userfavdisc}">
        <article class="discovery">
            <h2 class="discovery-header"><c:out value="${uservoteddiscoveries.title}"/></h2>
            <p class="discovery-details">Dodane przez: ${uservoteddiscoveries.author}, ${uservoteddiscoveries.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
            <a href="<c:out value='${uservoteddiscoveries.url}'/>" target="_blank" class="discovery-link"><c:out value="${uservoteddiscoveries.url}"/></a>
            <p><c:out value="${uservoteddiscoveries.description}"/></p>
            <section class="discovery-bar">
                <a href="${pageContext.request.contextPath.concat('/discovery/vote?id=').concat(uservoteddiscoveries.id).concat('&type=UP')}" class="discovery-link upvote">
                    <i class="fas fa-arrow-alt-circle-up discovery-upvote"></i>
                </a>
                <p class="discovery-votes">${uservoteddiscoveries.voteCount}</p>
                <a href="${pageContext.request.contextPath.concat('/discovery/vote?id=').concat(uservoteddiscoveries.id).concat('&type=DOWN')}" class="discovery-link downvote">
                    <i class="fas fa-arrow-alt-circle-down discovery-downvote"></i>
                </a>
            </section>
        </article>
    </c:forEach>
</div>
            <a href="${pageContext.request.contextPath}/discovery/add" class="discovery-add-button">
                <i class="fa fa-plus"></i>
            </a>

<%@include file="../segments/footer.jspf"%>
</body>
</html>
