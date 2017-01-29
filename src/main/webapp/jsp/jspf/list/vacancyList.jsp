<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">
    <div class="row">
        <div class="vacancy-list col-md-8">

            <c:if test="${param.fail != null}">
                <div class="info message text-center">
                    <p><span class="glyphicon glyphicon-exclamation-sign"></span>
                            ${failMessage}</p>
                </div>
            </c:if>

            <c:if test="${fn:length(requestScope.vacancyList) == 0}">
                <div class="empty-list">
                        ${empty_list}
                </div>
            </c:if>
            <c:if test="${fn:length(requestScope.vacancyList) > 0}">
            <ul>
                <c:set var="pageCount" value="${requestScope.pageCount}"/>
                <c:forEach var="vacancyMapItem" items="${requestScope.vacancyList}">
                    <c:set var="vacancy" scope="page" value="${vacancyMapItem.key}"/>
                    <c:set var="company" scope="page" value="${vacancyMapItem.value}"/>
                    <li>
                        <table class="vacancy" id="${requestScope.pageCount}">
                            <tbody>
                            <tr>
                                <td class="company-image" rowspan="4">
                                    <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">
                                        <object data="${pageContext.request.contextPath}/upload/${company.idCompany}.png"
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

                                            <button type="submit" id="${requestScope.pageCount}apply"
                                                    class="btn btn-info ">${apply}</button>
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
        </c:if>
        <div class="vacancy-sort z-depth-3 col-md-6 ">
            <legend>${vacancy_filter}</legend>
            <form class="form-horizontal" role="form" action="<c:url value="/controller"/>" method="get">
                <input type="hidden" name="command" value="get_list"/>
                <input type="hidden" name="type" value="search"/>
                <input type="hidden" name="page" value="1"/>
                <input type="hidden" name="search" value="${param.search}">

                <div class="form-group">
                    <label for="sortFilter" class="control-label vacancy-sort-label col-md-4">${sort}:</label>
                    <div class="col-md-8">
                        <select name="sort" id="sortFilter" class="form-control">
                            <c:if test="${param.sort eq 'date desc'}">
                                <option value="date desc" selected>${by_opening_date}</option>
                            </c:if>
                            <c:if test="${param.sort ne 'date desc'}">
                                <option value="date desc">${by_opening_date}</option>
                            </c:if>
                            <c:if test="${param.sort eq 'salary desc'}">
                                <option value="salary desc" selected>${by_salary_decrease}</option>
                            </c:if>
                            <c:if test="${param.sort ne 'salary desc'}">
                                <option value="salary desc">${by_salary_decrease}</option>
                            </c:if>
                            <c:if test="${param.sort eq 'salary '}">
                                <option value="salary " selected>${by_salary_increase}</option>
                            </c:if>
                            <c:if test="${param.sort ne 'salary '}">
                                <option value="salary ">${by_salary_increase}</option>
                            </c:if>
                            <c:if test="${param.sort eq 'requiredExperience asc'}">
                                <option value="requiredExperience asc" selected>${by_experience_increase}</option>
                            </c:if>
                            <c:if test="${param.sort ne 'requiredExperience asc'}">
                                <option value="requiredExperience asc">${by_experience_increase}</option>
                            </c:if>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="experienceFilter"
                           class="control-label vacancy-sort-label col-md-4">${experience}:</label>
                    <div class="col-md-8">
                        <select name="experienceFilter" id="experienceFilter" class="form-control">
                            <c:if test="${param.experienceFilter eq '0,100'}">
                                <option value="0,100" selected>${no_matter}</option>
                            </c:if>
                            <c:if test="${param.experienceFilter ne '0,100'}">
                                <option value="0,100">${no_matter}</option>
                            </c:if>
                            <c:if test="${param.experienceFilter eq '0,0'}">
                                <option value="0,0" selected>${without_experience}</option>
                            </c:if>
                            <c:if test="${param.experienceFilter ne '0,0'}">
                                <option value="0,0">${without_experience}</option>
                            </c:if>
                            <c:if test="${param.experienceFilter eq '1,3'}">
                                <option value="1,3" selected>${one_three}</option>
                            </c:if>
                            <c:if test="${param.experienceFilter ne '1,3'}">
                                <option value="1,3">${one_three}</option>
                            </c:if>
                            <c:if test="${param.experienceFilter eq '3,6'}">
                                <option value="3,6" selected>${three_six}</option>
                            </c:if>
                            <c:if test="${param.experienceFilter ne '3,6'}">
                                <option value="3,6">${three_six}</option>
                            </c:if>
                            <c:if test="${param.experienceFilter eq '6,100'}">
                                <option value="6,100" selected>${six_and_more}</option>
                            </c:if>
                            <c:if test="${param.experienceFilter ne '6,100'}">
                                <option value="6,100">${six_and_more}</option>
                            </c:if>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="salaryFilter" class="control-label vacancy-sort-label col-md-4">${min_salary}:</label>
                    <div class="col-md-8">
                        <c:if test="${param.salaryFilter != null}">
                            <input type="number" min="0" class="form-control form-group-sm" name="salaryFilter"
                                   id="salaryFilter"
                                   value="${param.salaryFilter}">
                        </c:if>
                        <c:if test="${param.salaryFilter == null}">
                            <input type="number" min="0" class="form-control form-group-sm" name="salaryFilter"
                                   id="salaryFilter"
                                   value="0">
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <label for="countryFilter" class="control-label vacancy-sort-label col-md-4">${country}:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control input-sm" name="countryFilter" id="countryFilter"
                               value="${param.countryFilter}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="cityFilter" class="control-label vacancy-sort-label col-md-4"> ${city}:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control input-sm" name="cityFilter" id="cityFilter"
                               value="${param.cityFilter}">
                    </div>
                </div>

                <button type="submit" class="btn btn-labeled btn-primary sort-button ">
                    <i class="fa fa-filter" aria-hidden="true"></i>
                    ${filter}
                </button>
            </form>

        </div>

    </div>
</div>