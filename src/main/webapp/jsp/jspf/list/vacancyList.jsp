<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">
    <div class="row">
        <c:if test="${fn:length(requestScope.vacancyList) == 0}">
            <div class="empty-list">
                    ${empty_list}
            </div>
        </c:if>
        <div class="vacancy-list col-md-8">
            <c:if test="${param.fail != null}">
                <div>
                    <div class="msg msg-warning msg-danger-text">
                        <span class="glyphicon glyphicon-exclamation-sign"></span>
                            ${failMessage}</div>
                </div>
            </c:if>
            <c:if test="${fn:length(requestScope.vacancyList) > 0}">
            <ul>
                <c:set var="pageCount" value="${requestScope.pageCount}"/>
                <c:forEach var="vacancyMapItem" items="${requestScope.vacancyList}">
                    <c:set var="vacancy" scope="page" value="${vacancyMapItem.key}"/>
                    <c:set var="company" scope="page" value="${vacancyMapItem.value}"/>
                    <li>
                        <table>
                            <tbody>
                            <tr>
                                <td class="company-image" rowspan="4">
                                    <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">
                                        <object data="${pageContext.request.contextPath}/upload/${company.idCompany}.png"
                                                alt="Company logo"
                                                width="100"
                                                height="100" type="image/png">
                                            <img src="${pageContext.request.contextPath}/upload/no-image.png"
                                                 alt="Company logo">
                                        </object>
                                    </a>
                                </td>
                                <td class="company-name">
                                    <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">${company.companyName}</a>
                                </td>
                                <td class="vacancy-salary" rowspan="3">
                                    <c:if test="${vacancy.salary > 0}">
                                        ${vacancy.salary}$
                                    </c:if>
                                </td>
                                <td class="apply-button" rowspan="3">
                                    <c:if test="${sessionScope.applicant != null}">
                                        <form action="<c:url value="/controller"/>" method="post">

                                            <input type="hidden" name="command" value="sign_up"/>
                                            <input type="hidden" name="type" value="respond"/>
                                            <input type="hidden" name="idVacancy" value="${vacancy.idVacancy}"/>

                                            <input type="submit" value="Apply">
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="vacancy-name">
                                    <a href="<c:url value="/controller?command=get_info&type=vacancy&idVacancy=${vacancy.idVacancy}"/>">${vacancy.name}</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="company-address">
                                        ${company.city}, ${company.country}
                                            <span class="vacancy-date">${vacancy.date}</span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <div class="vacancy-description">
                                        <div class="vacancy-description-wrapper">
                                                ${vacancy.vacancyDescription}
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </li>
                </c:forEach>
            </ul>
            <nav class="text-center">
                <ul class="pagination">
                    <c:if test="${pageCount != 1}">
                        <c:if test="${param.page != 1}">
                            <li>
                                <a href="<c:url value="/controller?command=get_list&type=search&page=${param.page - 1}&search=${param.search}&sort=${param.sort}&experienceFilter=${param.experienceFilter}&salaryFilter=${param.salaryFilter}&countryFilter=${param.countryFilter}&cityFilter=${param.cityFilter}"/>">${previous}</a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${pageCount}" var="i">
                            <c:choose>
                                <c:when test="${param.page eq i}">
                                    <li class="active"><a>${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="<c:url value="/controller?command=get_list&type=search&page=${i}&search=${param.search}&sort=${param.sort}&experienceFilter=${param.experienceFilter}&salaryFilter=${param.salaryFilter}&countryFilter=${param.countryFilter}&cityFilter=${param.cityFilter}"/>">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${param.page lt pageCount}">
                            <li>
                                <a href="<c:url value="/controller?command=get_list&type=search&page=${param.page + 1}&search=${param.search}&sort=${param.sort}&experienceFilter=${param.experienceFilter}&salaryFilter=${param.salaryFilter}&countryFilter=${param.countryFilter}&cityFilter=${param.cityFilter}"/>">${next}</a>
                            </li>
                        </c:if>
                    </c:if>
                </ul>
            </nav>
        </div>
        <div class="vacancy-sort z-depth-3 col-md-4 ">
            <form role="form" action="<c:url value="/controller"/>" method="get">
                <input type="hidden" name="command" value="get_list"/>
                <input type="hidden" name="type" value="search"/>
                <input type="hidden" name="page" value="1"/>
                <input type="hidden" name="search" value="${param.search}">

                <label>${sort}</label>
                <c:if test="${param.sort eq 'date desc'}">
                    <div class="radio">
                        <label>
                            <input type="radio" name="sort" value="date desc"
                                   checked>
                                ${by_opening_date}
                        </label>
                    </div>
                </c:if>
                <c:if test="${param.sort ne 'date desc'}">
                    <div class="radio">
                        <label><input type="radio" name="sort" value="date desc">${by_opening_date}
                        </label>
                    </div>
                </c:if>
                <c:if test="${param.sort eq 'salary desc'}">
                    <div class="radio">
                        <label><input type="radio" name="sort" value="salary desc"
                                      checked>${by_salary_decrease}</label>
                    </div>
                </c:if>
                <c:if test="${param.sort ne 'salary desc'}">
                    <div class="radio">
                        <label><input type="radio" name="sort" value="salary desc">${by_salary_decrease}
                        </label>
                    </div>
                </c:if>
                <c:if test="${param.sort eq 'salary '}">
                    <div class="radio">
                        <label><input type="radio" name="sort" value="salary "
                                      checked>${by_salary_increase}
                        </label>
                    </div>
                </c:if>
                <c:if test="${param.sort ne 'salary '}">
                    <div class="radio">
                        <label><input type="radio" name="sort" value="salary ">${by_salary_increase}
                        </label>
                    </div>
                </c:if>
                <c:if test="${param.sort eq 'requiredExperience asc'}">
                    <div class="radio">
                        <label><input type="radio" name="sort" value="requiredExperience asc"
                                      checked>${by_experience_increase}</label>
                    </div>
                </c:if>
                <c:if test="${param.sort ne 'requiredExperience asc'}">
                    <div class="radio">
                        <label><input type="radio" name="sort"
                                      value="requiredExperience asc">${by_experience_increase}</label>
                    </div>
                </c:if>

                <label>${experience}</label>
                <c:if test="${param.experienceFilter eq '0,100'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="0,100" checked>${no_matter}</label>
                    </div>
                </c:if>
                <c:if test="${param.experienceFilter ne '0,100'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="0,100">${no_matter}</label>
                    </div>
                </c:if>
                <c:if test="${param.experienceFilter eq '0,0'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="0,0" checked>${without_experience}
                        </label>
                    </div>
                </c:if>
                <c:if test="${param.experienceFilter ne '0,0'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="0,0">${without_experience}</label>
                    </div>
                </c:if>
                <c:if test="${param.experienceFilter eq '1,3'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="1,3" checked>${one_three}</label>
                    </div>
                </c:if>
                <c:if test="${param.experienceFilter ne '1,3'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="1,3">${one_three}</label>
                    </div>
                </c:if>
                <c:if test="${param.experienceFilter eq '3,6'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="3,6" checked>${three_six}</label>
                    </div>
                </c:if>
                <c:if test="${param.experienceFilter ne '3,6'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="3,6">${three_six}</label>
                    </div>
                </c:if>
                <c:if test="${param.experienceFilter eq '6,100'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="6,100" checked>${six_and_more}</label>
                    </div>
                </c:if>
                <c:if test="${param.experienceFilter ne '6,100'}">
                    <div class="radio">
                        <label><input type="radio" name="experienceFilter" value="6,100">${six_and_more}</label>
                    </div>
                </c:if>

                <div class="form-group form-inline">
                    <label for="salaryFilter">${min_salary}:</label>
                    <c:if test="${param.salaryFilter != null}">
                        <input type="number" class="form-control form-group-sm" name="salaryFilter" id="salaryFilter"
                               value="${param.salaryFilter}">
                    </c:if>
                    <c:if test="${param.salaryFilter == null}">
                        <input type="number" class="form-control form-group-sm" name="salaryFilter" id="salaryFilter"
                               value="0">
                    </c:if>
                </div>

                <div class="form-group form-inline">
                    <label for="countryFilter">${country}:</label>
                    <input type="text" class="form-control input-sm" name="countryFilter" id="countryFilter"
                           value="${param.countryFilter}">
                </div>

                <div class="form-group form-inline">
                    <label for="cityFilter"> ${city}:</label>
                    <input type="text" class="form-control input-sm" name="cityFilter" id="cityFilter"
                           value="${param.cityFilter}">
                </div>

                <input type="submit" value="${filter}">
            </form>

        </div>
        </c:if>
    </div>
</div>
</div>