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

    <link href="${pageContext.request.contextPath}/resources/css/sign-up-page-style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/navbar-main.css" rel="stylesheet">

    <%@ include file="../jspf/localization.jsp" %>

</head>
<body>

<c:if test="${applicant != null || employee != null}">
    <%@ include file="../jspf/navbar/authNavbar.jsp" %>
</c:if>
<c:if test="${applicant == null && employee == null}">
    <%@ include file="../jspf/navbar/noAuthNavbar.jsp" %>
</c:if>

<main>
    <div class="container">
        <div class="row">

            <div class="col-md-8 col-md-offset-2 sign-up-form">
                <form role="form" action="<c:url value="/controller"/>" method="post">

                    <input type="hidden" name="command" value="sign_up"/>
                    <input type="hidden" name="type" value="company"/>

                    <legend class="text-center">${company_registration}</legend>

                    <c:if test="${param.fail != null}">
                        <div>
                            <div class="msg msg-warning msg-danger-text">
                                <span class="glyphicon glyphicon-exclamation-sign"></span>
                                    ${failMessage}</div>
                        </div>
                    </c:if>

                    <fieldset>
                        <legend>${company_details}</legend>

                        <div class="form-group col-md-6">
                            <label for="companyName">${company_name}</label>
                            <div class="input-group" data-validate="companyName">
                                <input type="text" class="form-control" name="companyName" id="companyName"
                                       placeholder="${company_name}" required>
                                <span class="input-group-addon danger masterTooltip" title="${validation_company_name}">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="website">${website}</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="website" id="website"
                                       placeholder="${website}">
                                <span class="input-group-addon info"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="country">${country}</label>
                            <div class="input-group" data-validate="name">
                                <input type="text" class="form-control" name="country" id="country"
                                       placeholder="${country}" required>
                                <span class="input-group-addon danger masterTooltip" title="${validation_name}">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="city">${city}</label>
                            <div class="input-group" data-validate="name">
                                <input type="text" class="form-control" name="city" id="city" placeholder="${city}"
                                       required>
                                <span class="input-group-addon danger masterTooltip" title="${validation_name}">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group col-md-12">
                            <label for="companyDescription">${company_description}</label>
                            <div class="input-group">
                            <textarea class="form-control input-sm" name="companyDescription" id="companyDescription"
                                      rows="3"></textarea>
                                <span class="input-group-addon info"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>

                    </fieldset>

                    <fieldset>
                        <legend>${admin_details}</legend>

                        <div class="form-group col-md-12">
                            <label for="fullName">${full_name}</label>
                            <div class="input-group" data-validate="name">
                                <input type="text" class="form-control" name="fullName" id="fullName"
                                       placeholder="${full_name}" required>
                                <span class="input-group-addon danger masterTooltip" title="${validation_name}">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </span>
                            </div>
                        </div>


                        <div class="form-group col-md-12">
                            <label for="email">${email}</label>
                            <div class="input-group" data-validate="email">
                                <input type="email" class="form-control" name="email" id="email" placeholder="${email}"
                                       required>
                                <span class="input-group-addon danger masterTooltip" title="${validation_email}">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="password">${password}</label>
                            <div class="input-group" data-validate="password">
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="${password}" required>
                                <span class="input-group-addon danger masterTooltip" title="${validation_password}">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="confirmPassword">${confirm_password}</label>
                            <div class="input-group" data-validate="confirmPassword">
                                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                                       placeholder="${confirm_password}" required>
                                <span class="input-group-addon danger masterTooltip" title="${validation_confirm_password}">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="phone">${phone}</label>
                            <div class="input-group" data-validate="phone">
                                <input type="text" class="form-control" name="phone" id="phone" placeholder="${phone}"
                                       required>
                                <span class="input-group-addon danger masterTooltip" title="${validation_phone}">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="skype">Skype</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="skype" id="skype" placeholder="Skype">
                                <span class="input-group-addon info"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>

                    </fieldset>

                    <div class="form-group">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary" disabled>
                                ${sign_up}
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
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-confirmation.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/page.script.js"></script>
</body>
</html>