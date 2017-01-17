<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Job Match</title>

    <link href="${pageContext.request.contextPath}/resources/css/error-page-style.css" rel="stylesheet">
</head>
<body>
<main>
    <div class="error-wrapper">
        <img src="${pageContext.request.contextPath}/resources/images/error-404.png">
        <a href="<c:url value="/index.jsp"/>"><img
                src="${pageContext.request.contextPath}/resources/images/jobmatch.png"></a>
    </div>
</main>
</body>
</html>
