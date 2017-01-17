<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${sessionScope.applicant != null || sessionScope.employee != null}">
    <c:redirect url="/controller?command=get_info&type=index&page=main"/>
</c:if>

<c:if test="${requestScope.statistics == null}">
    <c:redirect url="/controller?command=get_info&type=index&page=index"/>
</c:if>

<html lang="${sessionScope.local}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Job Match</title>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/font-awesome.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/css/navbar-index.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">

    <%@ include file="jsp/jspf/localization.jsp" %>

</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div class="small-logo-container">
                <a class="small-logo" href="<c:url value="index.jsp"/>">
                    <img src="${pageContext.request.contextPath}/resources/images/jobmatch.png" alt="Website logo"
                         width="190" height="60">
                </a>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="<c:url value="index.jsp"/>">${home_page}</a></li>
                    <li>
                        <a href="<c:url value="/controller?command=get_list&type=search&page=1&search=&sort=date+desc&experienceFilter=0,100&salaryFilter=0&countryFilter=&cityFilter="/>">${vacancies}</a>
                    </li>
                    <li><a href="#">${contact}</a></li>
                </ul>
            </div>
        </div>
        <div class="navbar-collapse collapse">
            <div class="locale-wrapper">
                <ul>
                    <li>
                        <form action="<c:url value="/controller"/>" method="post">
                            <input type="hidden" name="command" value="locale"/>
                            <input type="hidden" name="local" value="en"/>
                            <button type="submit" class="locale-button">EN</button>
                        </form>
                    </li>
                    <li>
                        <form action="<c:url value="/controller"/>" method="post">
                            <input type="hidden" name="command" value="locale"/>
                            <input type="hidden" name="local" value="ru"/>
                            <button type="submit" class="locale-button">RU</button>
                        </form>
                    </li>
                </ul>
            </div>
            <div class="search-wrapper">
                <form action="<c:url value="/controller"/>" method="get">
                    <input type="hidden" name="command" value="get_list"/>
                    <input type="hidden" name="type" value="search"/>
                    <input type="hidden" name="page" value="1"/>
                    <input type="hidden" name="sort" value="date desc"/>
                    <input type="hidden" name="experienceFilter" value="0,100"/>
                    <input type="hidden" name="salaryFilter" value="0"/>
                    <input type="hidden" name="countryFilter" value=""/>
                    <input type="hidden" name="cityFilter" value=""/>
                    <input type="text" name="search" placeholder="${search}">
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid big-logo-row z-depth-1">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 big-logo-container">
                <div class="big-logo">
                    <div class="big-logo-preview">
                        <p>${slogan}</p>
                        <div class="preview-button-section">
                            <ul>
                                <li>
                                    <a href="<c:url value="/controller?command=get_list&type=search&page=1&search=&sort=date&experienceFilter=0,100&salaryFilter=0&countryFilter=&cityFilter="/>"
                                       class="preview-button">${find_job}</a>
                                </li>
                                <li>
                                    <a href="<c:url value="jsp/registration/companyRegistration.jsp"/>" class="preview-button">${post_job}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="auth-form z-depth-5">
                        <!-- Warnings -->
                        <form action="<c:url value="/controller"/>" method="post">
                            <input type="hidden" name="command" value="sign_in"/>
                            <c:if test="${param.fail != null}">
                                <div>
                                    <div class="msg msg-warning msg-danger-text">
                                        <span class="glyphicon glyphicon-exclamation-sign"></span>
                                            ${failMessage}</div>
                                </div>
                            </c:if>
                            <div class="field">
                                <label for="email">${email}</label>
                                <input type="text" name="email" id="email" <%--required--%>/>
                            </div>
                            <div class="field">
                                <label for="password">${password}</label>
                                <input type="password" name="password" id="password" <%--required--%>/>
                            </div>
                            <div class="auth-button">
                                <input type="submit" value="${sign_in}">
                                <div id="sign-up-section">
                                    <div class="pre-sign-up">
                                        ${sign_up_preview}
                                        <a href="#" class="sign-up-reference"
                                           onclick="signUpSection()">${sign_up}</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<main>
    <div class="container">
        <div class="row">
            <div class="col-lg-5 col-md-6 col-sm-8">

                <%@ include file="jsp/jspf/info/statistics.jsp" %>

            </div>
        </div>
    </div>
</main>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="resources/bootstrap/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/index.page.script.js"></script>
<script src="resources/js/page.script.js"></script>
</body>
</html>