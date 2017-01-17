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
    <link href="${pageContext.request.contextPath}/resources/css/sign-up-page-style.css" rel="stylesheet">

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
                    <input type="hidden" name="type" value="applicant"/>

                    <legend class="text-center">${applicant_registration}</legend>

                    <c:if test="${param.fail != null}">
                        <div>
                            <div class="msg msg-warning msg-danger-text">
                                <span class="glyphicon glyphicon-exclamation-sign"></span>
                                    ${failMessage}</div>
                        </div>
                    </c:if>

                    <fieldset>
                        <legend>${account_details}</legend>

                        <div class="form-group col-md-6">
                            <label for="firstName">${first_name}</label>
                            <input type="text" class="form-control" name="firstName" id="firstName"
                                   placeholder="${first_name}">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="lastName">${last_name}</label>
                            <input type="text" class="form-control" name="lastName" id="lastName"
                                   placeholder="${last_name}">
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

                    <fieldset>
                        <legend>${optional_details}</legend>

                        <div class="form-group col-md-6">
                            <label for="country">${country}</label>
                            <input type="text" class="form-control" name="country" id="country" placeholder="${country}">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="city">${city}</label>
                            <input type="text" class="form-control" name="city" id="city" placeholder="${city}">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="university">${university}</label>
                            <input type="text" class="form-control" name="university" id="university"
                                   placeholder="${university}">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="graduationYear">${graduation_year}</label>
                            <input type="number" class="form-control" name="graduationYear" id="graduationYear"
                                   placeholder="${graduation_year}" required/>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="englishLevel">${english_level}</label>
                            <select class="form-control input-sm" name="englishLevel" id="englishLevel">
                                <option>A1</option>
                                <option>A2</option>
                                <option>B1</option>
                                <option>B2</option>
                                <option>C1</option>
                                <option>C2</option>
                            </select>
                        </div>

                        <div class="form-group col-md-12">
                            <label for="professionalSkills">${professional_skills}</label>
                            <textarea class="form-control input-sm" name="professionalSkills" id="professionalSkills"
                                      rows="3"></textarea>
                        </div>

                    </fieldset>

                    <%--<div class="form-group">
                        <div class="col-md-12">
                            <div class="checkbox">
                                <label>
                                    <input type_impl="checkbox" value="" id="">
                                    I accept the <a href="#" >terms and conditions</a>.
                                </label>
                            </div>
                        </div>
                    </div>--%>


                    <div class="form-group">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary">
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
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/page.script.js"></script>
</body>
</html>