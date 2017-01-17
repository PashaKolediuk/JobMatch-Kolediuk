<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${sessionScope.employee == null || sessionScope.employee.status != 'ADMIN'}">
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
                    <input type="hidden" name="type" value="employee"/>

                    <legend class="text-center">${employee_registration}</legend>

                    <c:if test="${param.fail != null}">
                        <div>
                            <div class="msg msg-warning msg-danger-text">
                                <span class="glyphicon glyphicon-exclamation-sign"></span>
                                    ${failMessage}</div>
                        </div>
                    </c:if>

                    <fieldset>
                        <legend>${employee_details}</legend>

                        <div class="form-group col-md-12">
                            <label for="fullName">${full_name}</label>
                            <input type="text" class="form-control" name="fullName" id="fullName"
                                   placeholder="${full_name}">
                        </div>


                        <div class="form-group col-md-12">
                            <label for="email">${email}</label>
                            <input type="email" class="form-control" name="email" id="email" placeholder="${email}">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="password">${password}</label>
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="${password}">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="confirmPassword">${confirm_password}</label>
                            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                                   placeholder="${confirm_password}">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="phone">${phone}</label>
                            <input type="text" class="form-control" name="phone" id="phone" placeholder="${phone}">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="skype">Skype</label>
                            <input type="text" class="form-control" name="skype" id="skype" placeholder="Skype">
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