<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1 company-info">
            <c:set var="vacancy" scope="page" value="${requestScope.vacancyInfo}"/>
            <c:set var="company" scope="page" value="${requestScope.companyInfo}"/>

            <c:if test="${param.fail != null}">
                <div class="info message text-center">
                    <p><span class="glyphicon glyphicon-exclamation-sign"></span>
                            ${failMessage}</p>
                </div>
            </c:if>

            <div>
                <div class="col-md-12 ">
                    <h3 class="info-header col-md-5 ">${vacancy_details}</h3>
                    <c:if test="${sessionScope.applicant != null}">
                        <form class="col-md-1 col-md-offset-10 apply-button" action="<c:url value="/controller"/>"
                              method="post">

                            <input type="hidden" name="command" value="sign_up"/>
                            <input type="hidden" name="type" value="respond"/>
                            <input type="hidden" name="idVacancy" value="${vacancy.idVacancy}"/>

                            <button type="submit" class="btn btn-info">${apply}</button>
                        </form>
                    </c:if>
                </div>

                <div class="col-md-12">
                    <div class="col-md-4">
                        <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${vacancy.idCompany}"/>">
                            <object data="${pageContext.request.contextPath}/upload/${vacancy.idCompany}.png"
                                    alt="Company logo"
                                    width="200"
                                    height="200" type="image/png">
                                <img src="${pageContext.request.contextPath}/upload/no-image.png"
                                     width="200"
                                     height="200"
                                     alt="Company logo">
                            </object>
                        </a>
                    </div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-12 open-sans-bold">
                                <h2>${vacancy.name}</h2>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 open-sans-bold">
                                ${salary}: <span class="open-sans-italic">
                                <c:if test="${vacancy.salary == 0}">
                                    -
                                </c:if>
                                    <c:if test="${vacancy.salary > 0}">
                                        ${vacancy.salary}$
                                    </c:if>
                                    </span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 open-sans-bold">
                                ${required_experience}:
                                    <span class="open-sans-italic">
                                    <c:if test="${vacancy.requiredExperience == 0}">
                                        -
                                    </c:if>
                                    <c:if test="${vacancy.requiredExperience > 0}">
                                        ${vacancy.requiredExperience}
                                    </c:if>
                                </span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 open-sans-bold">${required_skills}:</div>
                            ${vacancy.requiredSkills}
                        </div>
                        <div class="row">
                            <div class="col-md-12 open-sans-bold">${vacancy_description}:</div>
                            ${vacancy.vacancyDescription}
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>