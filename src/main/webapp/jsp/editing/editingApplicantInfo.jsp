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
    <%--Delete--%>

    <%@ include file="../jspf/localization.jsp" %>

</head>
<body>

<c:if test="${sessionScope.applicant == null}">
    <jsp:forward page="../../index.jsp"/>
</c:if>

<%@ include file="../jspf/navbar/authNavbar.jsp" %>

<main>
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 sign-up-form">

                <legend class="text-center">${profile_editing}</legend>

                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab"
                                          href="#profile-details">${profile_details}</a></li>
                    <li><a data-toggle="tab"
                           href="#password-details">${password}</a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="profile-details"
                         class="tab-pane fade in active">
                        <form role="form" action="<c:url value="/controller"/>" method="post">

                            <input type="hidden" name="command" value="edit"/>
                            <input type="hidden" name="type" value="applicant_profile"/>


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
                                           value="${applicant.firstName}">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="lastName">${last_name}</label>
                                    <input type="text" class="form-control" name="lastName" id="lastName"
                                           value="${applicant.lastName}">
                                </div>

                                <div class="form-group col-md-12">
                                    <label for="email">${email}</label>
                                    <input type="email" class="form-control" name="email" id="email"
                                           value="${applicant.email}">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="phone">${phone}</label>
                                    <input type="text" class="form-control" name="phone" id="phone"
                                           value="${applicant.phone}">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="skype">Skype</label>
                                    <input type="text" class="form-control" name="skype" id="skype" placeholder="Skype"
                                           value="${applicant.skype}">
                                </div>

                            </fieldset>

                            <fieldset>
                                <legend>${optional_details}</legend>

                                <div class="form-group col-md-6">
                                    <label for="country">${country}</label>
                                    <input type="text" class="form-control" name="country" id="country"
                                           value="${applicant.country}">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="city">${city}</label>
                                    <input type="text" class="form-control" name="city" id="city"
                                           value="${applicant.city}">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="university">${university}</label>
                                    <input type="text" class="form-control" name="university" id="university"
                                           value="${applicant.university}">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="graduationYear">${graduation_year}</label>
                                    <input type="number" class="form-control" name="graduationYear" id="graduationYear"
                                           value="${applicant.graduationYear}" required/>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="englishLevel">${english_level}</label>
                                    <select class="form-control input-sm" name="englishLevel" id="englishLevel">
                                        <c:if test="${applicant.englishLevel == 'A1'}">
                                            <option selected>A1</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel != 'A1'}">
                                            <option>A1</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel == 'A2'}">
                                            <option selected>A2</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel != 'A2'}">
                                            <option>A2</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel == 'B1'}">
                                            <option selected>B1</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel != 'B1'}">
                                            <option>B1</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel == 'B2'}">
                                            <option selected>B2</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel != 'B2'}">
                                            <option>B2</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel == 'C1'}">
                                            <option selected>C1</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel != 'C1'}">
                                            <option>C1</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel == 'C2'}">
                                            <option selected>C2</option>
                                        </c:if>
                                        <c:if test="${applicant.englishLevel != 'C2'}">
                                            <option>C2</option>
                                        </c:if>
                                    </select>
                                </div>

                                <div class="form-group col-md-12">
                                    <label for="professionalSkills">${professional_skills}</label>
                            <textarea class="form-control input-sm" name="professionalSkills" id="professionalSkills"
                                      rows="3">${applicant.professionalSkills}</textarea>
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
                                   value="applicant_password"/>

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
            <div class="col-md-8 col-md-offset-2">

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