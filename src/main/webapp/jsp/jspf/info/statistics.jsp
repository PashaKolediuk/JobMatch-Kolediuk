<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">
    <div class="row">

        <div class="col-md-10 col-md-offset-1">

            <div class="col-md-6 ">
                <h3 class="text-center">${latest_vacancies}</h3>
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
                                                     alt="Company logo">
                                            </object>
                                        </a>
                                    </td>
                                    <td  class="top-company-name">
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

            <div class="col-md-6 ">
                <h3 class="text-center">${top_companies}</h3>
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
                                                     alt="Company logo">
                                            </object>
                                        </a>
                                    </td>
                                    <td  class="top-vacancy-name">
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