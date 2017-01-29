<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">
    <div class="row">

        <div class="col-md-10 col-md-offset-1 z-depth-3 statistic-column">

            <div class="col-md-6 first-static-column">
                <h3 class="text-center open-sans-bold">${latest_vacancies}</h3>
                <ul class="statistics">
                    <c:forEach var="vacancyItem" items="${requestScope.latestVacancyList}" varStatus="vacancyCount">
                        <c:set var="vacancy" scope="page" value="${vacancyItem.key}"/>
                        <c:set var="company" scope="page" value="${vacancyItem.value}"/>
                        <li>
                            <table class="statistics-table">
                                <tr>
                                    <td class="top-company-image" rowspan="3">
                                        <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">
                                            <object data="${pageContext.request.contextPath}/upload/${company.idCompany}.png"
                                                    alt="Company logo"
                                                    width="70"
                                                    height="70" type="image/png">
                                                <img src="${pageContext.request.contextPath}/upload/no-image.png"
                                                     width="70"
                                                     height="70"
                                                     alt="Company logo">
                                            </object>
                                        </a>
                                    </td>
                                    <td class="top-company-name">
                                        <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">${company.companyName}</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="top-vacancy-name">
                                        <a href="<c:url value="/controller?command=get_info&type=vacancy&idVacancy=${vacancy.idVacancy}"/>"> ${vacancy.name}</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="top-company-address">
                                            ${company.city}, ${company.country}
                                        <span class="vacancy-date">${vacancy.date}</span>
                                    </td>
                                </tr>
                            </table>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-6 second-static-column">
                <h3 class="text-center open-sans-bold">${top_companies}</h3>
                <ul class="statistics">
                    <c:forEach var="company" items="${requestScope.searchingCompanyList}" varStatus="companyCount">
                        <li>
                            <table class="statistics-table">
                                <tr>
                                    <td class="top-company-image" rowspan="3">
                                        <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">
                                            <object data="${pageContext.request.contextPath}/upload/${company.idCompany}.png"
                                                    alt="Company logo"
                                                    width="70"
                                                    height="70" type="image/png">
                                                <img src="${pageContext.request.contextPath}/upload/no-image.png"
                                                     width="70"
                                                     height="70"
                                                     alt="Company logo">
                                            </object>
                                        </a>
                                    </td>
                                    <td class="top-vacancy-name">
                                        <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">${company.companyName}</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="top-company-address">
                                            ${company.city}, ${company.country}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="top-company-address">
                                            ${company.website}
                                    </td>
                                </tr>
                            </table>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </div>
    </div>
</div>
<div class="container">
    <div class="row">

        <div class="col-md-8 col-md-offset-2">

            <div class="row">
                <div class="col-md-4 col-sm-6">
                    <div class="serviceBox">
                        <div class="service-icon">
                            <i class="fa fa-users"></i>
                        </div>
                        <div class="service-content">
                            <h3 class="title">${total_applicants}: ${requestScope.statistics[0]}</h3>
                            <p class="description">
                                ${total_applicants_text}
                            </p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 col-sm-6">
                    <div class="serviceBox">
                        <div class="service-icon">
                            <i class="fa fa-building"></i>
                        </div>
                        <div class="service-content">
                            <h3 class="title">${total_companies}: ${requestScope.statistics[1]}</h3>
                            <p class="description">
                                ${total_companies_text}
                            </p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 col-sm-6">
                    <div class="serviceBox">
                        <div class="service-icon">
                            <i class="fa fa-briefcase"></i>
                        </div>
                        <div class="service-content">
                            <h3 class="title">${total_vacancies}: ${requestScope.statistics[2]}</h3>
                            <p class="description">
                                ${total_vacancies_text}
                            </p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>
<div class="floating">
    <footer class="col-md-12" id="footer">
        <div class="footer-layout">
            <div class="contact-us col-md-4 col-md-offset-2 text-right">
                <h3 class="col-md-4 open-sans-bold">${contact_us}</h3>
                <ul class="roboto-black">
                    <li>${phone}: +375292577796</li>
                    <li>${email}: kolediuk@gmail.com</li>
                    <li>Skype: pashakolediuk</li>
                </ul>
            </div>
            <div class="col-md-4">
                <div class="row">
                    <div class="join-us col col-md-12 text-left">
                        <h3 class="open-sans-bold">${join_us}:</h3>
                        <ul>
                            <li><a href="https://www.linkedin.com"><i class="fa fa-twitter-square"
                                                                      aria-hidden="true"></i></a></li>
                            <li><a href="https://twitter.com"><i class="fa fa-linkedin-square"
                                                                 aria-hidden="true"></i></a></li>
                            <li><a href="https://plus.google.com"><i class="fa fa-google-plus-square"
                                                                     aria-hidden="true"></i></a></li>
                            <li><a href="https://www.facebook.com"><i class="fa fa-facebook-square"
                                                                      aria-hidden="true"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="copyrights col col-md-12">
                        <small class="roboto-black">&copy; Copyright 2017, Designed and Developed by Pavel Kolediuk</small>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</div>