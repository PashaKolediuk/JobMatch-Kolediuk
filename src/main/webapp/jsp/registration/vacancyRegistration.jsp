<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${sessionScope.employee == null}">
    <c:redirect url="../../index.jsp"/>
</c:if>

<html lang="${sessionScope.local}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Job Match</title>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/css/navbar-main.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sign-up-page-style.css" rel="stylesheet">

    <%@ include file="../jspf/localization.jsp" %>

</head>
<body>

    <%@ include file="../jspf/navbar/authNavbar.jsp" %>

<main>
    <div class="container">
        <div class="row">

            <div class="col-md-8 col-md-offset-2 sign-up-form">
                <form role="form" action="<c:url value="/controller"/>" method="post">

                    <input type="hidden" name="command" value="sign_up"/>
                    <input type="hidden" name="type" value="vacancy"/>

                    <legend class="text-center">${vacancy_registrtion}</legend>

                    <c:if test="${param.fail != null}">
                        <div>
                            <div class="msg msg-warning msg-danger-text">
                                <span class="glyphicon glyphicon-exclamation-sign"></span>
                                    ${failMessage}</div>
                        </div>
                    </c:if>

                    <fieldset>
                        <legend>${vacancy_details}</legend>

                        <div class="form-group col-md-6">
                            <label for="vacancyName">${vacancy_name}</label>
                            <input type="text" class="form-control" name="vacancyName" id="vacancyName"
                                   placeholder="${vacancy_name}">
                        </div>

                        <div class="form-group col-md-3">
                            <label for="requiredExperience">${required_experience}</label>
                            <input type="number" class="form-control" name="requiredExperience" id="requiredExperience"
                                   placeholder="${required_experience}">
                        </div>

                        <div class="form-group col-md-3">
                            <label for="salary">${salary}</label>
                            <input type="number" class="form-control" name="salary" id="salary"
                                   placeholder="${salary}">
                        </div>

                        <div class="form-group col-md-12">
                            <label for="requiredSkills">${required_skills}</label>
                            <textarea class="form-control input-sm" name="requiredSkills" id="requiredSkills"
                                      rows="3"></textarea>
                        </div>

                        <div class="form-group col-md-12">
                            <label for="vacancyDescription">${vacancy_description}</label>
                            <textarea class="form-control input-sm" name="vacancyDescription" id="vacancyDescription"
                                      rows="3"></textarea>
                        </div>

                    </fieldset>

                    <div class="form-group">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary">
                                ${add}
                            </button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</main>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/page.script.js"></script>
</body>
</html>