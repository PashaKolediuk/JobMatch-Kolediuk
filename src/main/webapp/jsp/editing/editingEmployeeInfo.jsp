<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="${sessionScope.local}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Job Match</title>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/css/navbar-main.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/employee-list-style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sign-up-page-style.css" rel="stylesheet">


    <%@ include file="../jspf/localization.jsp" %>

</head>
<body>

<c:if test="${sessionScope.employee == null}">
    <jsp:forward page="../../index.jsp"/>
</c:if>

<%@ include file="../jspf/navbar/authNavbar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1 sign-up-form">

            <legend class="text-center">${profile_editing}</legend>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab"
                                      href="#profile-details">
                    ${profile_details}
                </a></li>
                <li><a data-toggle="tab"
                       href="#password-details">${password}</a>
                </li>
            </ul>

            <div class="tab-content">
                <div id="profile-details"
                     class="tab-pane fade in active">
                    <form role="form" action="<c:url value="/controller"/>"
                          method="post">

                        <input type="hidden" name="command" value="edit"/>
                        <input type="hidden" name="type"
                               value="employee_profile"/>
                        <input type="hidden" name="id" value="${employee.id}"/>
                        <input type="hidden" name="status" value="HR"/>

                        <fieldset>


                            <div class="form-group col-md-6">
                                <label for="fullName">${full_name}</label>
                                <input type="text" class="form-control"
                                       name="fullName" id="fullName"
                                       value="${employee.fullName}">
                            </div>


                            <div class="form-group col-md-6">
                                <label for="email">${email}</label>
                                <input type="email" class="form-control"
                                       name="email" id="email"
                                       value="${employee.email}">
                            </div>

                            <div class="form-group col-md-6">
                                <label for="phone">${phone}</label>
                                <input type="text" class="form-control"
                                       name="phone" id="phone"
                                       value="${employee.phone}">
                            </div>

                            <div class="form-group col-md-6">
                                <label for="skype">Skype</label>
                                <input type="text" class="form-control"
                                       name="skype" id="skype"
                                       value="${employee.skype}">
                            </div>

                        </fieldset>

                        <div class="form-group">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-primary">
                                    ${edit}
                                </button>
                            </div>
                        </div>

                    </form>
                </div>
                <div id="password-details"
                     class="tab-pane fade">
                    <h3>ПАРОЛЬ</h3>
                    <form role="form" action="<c:url value="/controller"/>"
                          method="post">

                        <input type="hidden" name="command" value="edit"/>
                        <input type="hidden" name="type"
                               value="employee_password"/>
                        <input type="hidden" name="id" value="${employee.id}"/>
                        <input type="hidden" name="status" value="HR"/>


                        <legend class="text-center">РЕДАКТИР</legend>

                        <fieldset>


                            <div class="form-group col-md-6">
                                <label for="password">${password}</label>
                                <input type="password" class="form-control"
                                       name="password" id="password"
                                       placeholder="${password}">
                            </div>

                            <div class="form-group col-md-6">
                                <label for="confirmPassword">${confirm_password}</label>
                                <input type="password" class="form-control"
                                       name="confirmPassword"
                                       id="confirmPassword"
                                       placeholder="${confirm_password}">
                            </div>


                        </fieldset>

                        <div class="form-group">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-primary">
                                    ${edit}
                                </button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/page.script.js"></script>
</body>