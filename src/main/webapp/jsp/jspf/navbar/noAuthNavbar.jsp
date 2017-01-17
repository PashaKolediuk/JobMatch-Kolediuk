<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="navbar navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="small-logo" href="<c:url value="/index.jsp"/>">
                <img src="${pageContext.request.contextPath}/resources/images/jobmatch.png" alt="Website logo"
                     width="190" height="60">
            </a>
            <ul class="nav navbar-nav navbar-right navbar-main">
                <li><a href="<c:url value="/index.jsp"/>">${home_page}</a></li>
                <li><a href="<c:url value="/controller?command=get_list&type=search&page=1&search=&sort=date+desc&experienceFilter=0,100&salaryFilter=0&countryFilter=&cityFilter="/>">${vacancies}</a></li>
                <li><a href="<c:url value="/index.jsp"/>">${contact}</a></li>
                <%--  <- reference --%>
            </ul>
            <div class="search-wrapper-for-sign-up">
                <form action="<c:url value="/controller"/>" method="get">
                    <input type="hidden" name="command" value="get_list"/>
                    <input type="hidden" name="type" value="search"/>
                    <input type="hidden" name="page" value="1"/>
                    <input type="hidden" name="sort" value="date desc"/>
                    <input type="hidden" name="experienceFilter" value="0,100"/>
                    <input type="hidden" name="salaryFilter" value="0"/>
                    <input type="hidden" name="countryFilter" value=""/>
                    <input type="hidden" name="cityFilter" value=""/>
                    <input type="text" name="search" placeholder="${search}" value="${param.search}">
                </form>
            </div>
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

        </div>
    </div>
</div>
