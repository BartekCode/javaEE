<%--
  Created by IntelliJ IDEA.
  User: Bartekk
  Date: 31.01.2021
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add comment</title>
    <%@include file="../segments/stylesheets.jspf"%>
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jspf"%>
    <form action="${pageContext.request.contextPath}/discovery/comment" method="post" class="discovery-form">
        <h2 class="discovery-form-title">Dodaj komentarz</h2>
        <textarea name="description" placeholder="Tekst"></textarea>
        <button class="discovery-form-button">Dodaj</button>
    </form>
    <%@include file="../segments/footer.jspf"%>
</div>
</body>
</html>
