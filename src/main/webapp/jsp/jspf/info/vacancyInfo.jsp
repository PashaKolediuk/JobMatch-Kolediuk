<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:if test="${param.fail != null}">
    <div>
        <div class="msg msg-warning msg-danger-text">
            <span class="glyphicon glyphicon-exclamation-sign"></span>
                ${failMessage}</div>
    </div>
</c:if>


<div class="container">
    <div class="row">
        <div class="company-info col-md-8 col-md-offset-2">
            <c:set var="vacancy" scope="page" value="${requestScope.vacancyInfo}"/>
            <c:set var="company" scope="page" value="${requestScope.companyInfo}"/>

            ${vacancy_details}

            <table>
                <tr>
                    <td class="vertical-section" rowspan="7">
                        <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">
                            <object data="${pageContext.request.contextPath}/upload/${company.idCompany}.png"
                                    alt="Company logo"
                                    width="300"
                                    height="300" type="image/png">
                                <img src="${pageContext.request.contextPath}/upload/no-image.png"
                                     width="300"
                                     height="300"
                                     alt="Company logo">
                            </object>
                        </a>
                    </td>
                    <td>
                        <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">${company.companyName}</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        ${vacancy.name}
                    </td>
                </tr>
                <tr>
                    <td>
                        ${vacancy.salary}$
                    </td>
                </tr>
                <tr>
                    <td>
                        ${vacancy.date}
                    </td>
                </tr>
                <tr>
                    <td>
                        ${vacancy.requiredExperience}
                    </td>
                </tr>
                <tr>
                    <td>
                        ${vacancy.requiredSkills}
                    </td>
                </tr>
                <tr>
                    <td>
                        ${vacancy.vacancyDescription}
                    </td>
                </tr>
            </table>
            <c:if test="${sessionScope.applicant != null}">
                <form action="<c:url value="/controller"/>" method="post">

                    <input type="hidden" name="command" value="sign_up"/>
                    <input type="hidden" name="type" value="respond"/>
                    <input type="hidden" name="idVacancy" value="${vacancy.idVacancy}"/>

                    <input type="submit" value="Apply">
                </form>
            </c:if>
        </div>
    </div>
</div>