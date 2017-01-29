<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="${sessionScope.local}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Job Match</title>

    <link href="${pageContext.request.contextPath}/resources/images/favicon.ico" rel="shortcut icon" type="image/x-icon" />

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/font-awesome.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/css/vacancy-list-style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/employee-list-style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/navbar-main.css" rel="stylesheet">

    <%--<link href="${pageContext.request.contextPath}/resources/css/sign-up-page-style.css" rel="stylesheet">--%> <%--Delete--%>

    <%@ include file="jspf/localization.jsp" %>

</head>
<body>
<div>
    <c:if test="${applicant != null || employee != null}">
        <%@ include file="jspf/navbar/authNavbar.jsp" %>
    </c:if>
    <c:if test="${applicant == null && employee == null}">
        <%@ include file="jspf/navbar/noAuthNavbar.jsp" %>
    </c:if>
</div>
<main>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <c:if test="${requestScope.vacancyList!=null}">
                    <%@ include file="jspf/list/vacancyList.jsp" %>
                </c:if>
                <c:if test="${requestScope.vacancyListById!=null}">
                    <%@ include file="jspf/list/vacancyListForEmployee.jsp" %>
                </c:if>
                <c:if test="${requestScope.employeeList!=null}">
                    <%@ include file="jspf/list/employeeList.jsp" %>
                </c:if>
                <c:if test="${requestScope.vacancyInfo!=null && requestScope.companyInfo!=null}">
                    <%@ include file="jspf/info/vacancyInfo.jsp" %>
                </c:if>
                <c:if test="${requestScope.vacancyInfo==null && requestScope.companyInfo!=null}">
                    <%@ include file="jspf/info/companyInfo.jsp" %>
                </c:if>
                <c:if test="${requestScope.respondListOfVacancy!=null}">
                    <%@ include file="jspf/list/respondListOfVacancy.jsp" %>
                </c:if>
                <c:if test="${requestScope.respondInfo!=null}">
                    <%@ include file="jspf/info/respondInfo.jsp" %>
                </c:if>
                <c:if test="${requestScope.respondListOfApplicant!=null}">
                    <%@ include file="jspf/list/respondListOfApplicant.jsp" %>
                </c:if>

            </div>
        </div>
    </div>
    <c:if test="${requestScope.statistics!=null}">
        <%@ include file="jspf/info/statistics.jsp" %>
    </c:if>
</main>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-confirmation.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/page.script.js"></script>
</body>
</html>