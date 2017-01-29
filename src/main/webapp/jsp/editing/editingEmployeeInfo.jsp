<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <link href="${pageContext.request.contextPath}/resources/css/employee-list-style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sign-up-page-style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/navbar-main.css" rel="stylesheet">

    <%@ include file="../jspf/localization.jsp" %>

</head>
<body>

<c:if test="${sessionScope.employee == null}">
    <jsp:forward page="../../index.jsp"/>
</c:if>

<%@ include file="../jspf/navbar/authNavbar.jsp" %>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1 sign-up-form">

            <legend class="text-center">${profile_editing}</legend>

            <ul class="nav nav-tabs default-background">
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

                    <c:if test="${param.fail != null}">
                        <div>
                            <div class="msg msg-warning msg-danger-text col-md-offset-4">
                                <span class="glyphicon glyphicon-exclamation-sign"></span>
                                    ${failMessage}</div>
                        </div>
                    </c:if>

                    <form role="form" action="<c:url value="/controller"/>"
                          method="post">

                        <input type="hidden" name="command" value="edit"/>
                        <input type="hidden" name="type"
                               value="employee_profile"/>
                        <input type="hidden" name="id" value="${employee.id}"/>
                        <input type="hidden" name="status" value="HR"/>

                        <fieldset>
                            <legend class="text-left">${profile_details}</legend>

                            <div class="form-group col-md-6">
                                <label for="fullName">${full_name}</label>
                                <div class="input-group" data-validate="name">
                                <input type="text" class="form-control"
                                       name="fullName" id="fullName"
                                       value="${employee.fullName}" required>
                                    <span class="input-group-addon danger masterTooltip" title="${validation_name}">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </span>
                                </div>
                            </div>


                            <div class="form-group col-md-6">
                                <label for="email">${email}</label>
                                <div class="input-group" data-validate="email">
                                <input type="email" class="form-control"
                                       name="email" id="email"
                                       value="${employee.email}" required>
                                    <span class="input-group-addon danger masterTooltip" title="${validation_email}">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </span>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label for="phone">${phone}</label>
                                <div class="input-group" data-validate="phone">
                                <input type="text" class="form-control"
                                       name="phone" id="phone"
                                       value="${employee.phone}" required>
                                    <span class="input-group-addon danger masterTooltip" title="${validation_phone}">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </span>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label for="skype">Skype</label>
                                <div class="input-group">
                                <input type="text" class="form-control"
                                       name="skype" id="skype"
                                       value="${employee.skype}">
                                    <span class="input-group-addon info"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
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
                    <form role="form" action="<c:url value="/controller"/>"
                          method="post">

                        <input type="hidden" name="command" value="edit"/>
                        <input type="hidden" name="type"
                               value="employee_password"/>
                        <input type="hidden" name="id" value="${employee.id}"/>
                        <input type="hidden" name="status" value="HR"/>

                        <fieldset>

                            <legend class="text-left">${password}</legend>

                            <div class="form-group col-md-6">
                                <label for="password">${password}</label>
                                <div class="input-group" data-validate="password">
                                <input type="password" class="form-control"
                                       name="password" id="password"
                                       placeholder="${password}" required>
                                    <span class="input-group-addon danger masterTooltip" title="${validation_password}">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </span>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label for="confirmPassword">${confirm_password}</label>
                                <div class="input-group" data-validate="confirmPassword">
                                <input type="password" class="form-control"
                                       name="confirmPassword"
                                       id="confirmPassword"
                                       placeholder="${confirm_password}" required>
                                    <span class="input-group-addon danger masterTooltip" title="${validation_confirm_password}">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </span>
                                </div>
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
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-confirmation.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/page.script.js"></script>
</body>