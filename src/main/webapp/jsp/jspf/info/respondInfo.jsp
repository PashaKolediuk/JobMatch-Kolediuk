<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${sessionScope.employee == null && sessionScope.applicant == null}">
    <c:redirect url="../index.jsp"/>
</c:if>

<div class="container">
    <div class="row">
        <div class="company-info col-md-8">
            <c:if test="${param.fail != null}">
                <div>
                    <div class="msg msg-warning msg-danger-text">
                        <span class="glyphicon glyphicon-exclamation-sign"></span>
                            ${failMessage}</div>
                </div>
            </c:if>


            <c:if test="${requestScope.respondInfo != null}">
                <c:set var="respond" value="${requestScope.respondInfo}" scope="page"/>
                <c:if test="${applicant != null}">
                    <form action="<c:url value="/controller"/>" method="get">
                        <input type="hidden" name="command" value="get_list">
                        <input type="hidden" name="type" value="respond_of_applicant">
                        <input type="hidden" name="page" value="1">
                        <button type="submit">${back}</button>
                    </form>
                    <c:set var="vacancy" value="${requestScope.vacancyInfo}"/>

                    ${vacancy_details}
                    <table>
                        <tr>
                            <td rowspan="6">
                                <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${vacancy.idCompany}"/>">
                                    <object data="${pageContext.request.contextPath}/upload/${vacancy.idCompany}.png"
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

                    ${stage}:
                    <c:if test="${respond.stage == 'PHONE'}">
                        ${phone_stage}
                    </c:if>
                    <c:if test="${respond.stage == 'INTERVIEW'}">
                        ${interview_stage}
                    </c:if>
                    <c:if test="${respond.stage == 'ANSWER'}">
                        ${answer_stage}
                    </c:if>
                    <br>
                    ${respond_date}: ${respond.respondDate}
                    <br>
                    ${conversation_date}:
                    <c:if test="${respond.conversationDate == null}">
                        ${no_conversation}
                    </c:if>
                    <c:if test="${respond.conversationDate != null}">
                        ${respond.conversationDate}
                    </c:if>

                </c:if>

                <c:if test="${employee != null}">
                    <form action="<c:url value="/controller"/>" method="get">
                        <input type="hidden" name="command" value="get_list">
                        <input type="hidden" name="type" value="respond_of_vacancy">
                        <input type="hidden" name="page" value="1">
                        <input type="hidden" name="idVacancy" value="${respond.idVacancy}">
                        <input type="hidden" name="lastName" value="">
                        <input type="hidden" name="email" value="">
                        <input type="hidden" name="phone" value="">
                        <input type="hidden" name="stage" value="">
                        <input type="hidden" name="mark" value="0">
                        <input type="hidden" name="sort" value="respondDate desc">
                        <button type="submit">${back}</button>
                    </form>

                    <c:set var="applicantInfo" value="${requestScope.applicantInfo}"/>
                    <br>
                    Данные о соискателе (EN)
                    ${applicantInfo.firstName} ${applicantInfo.lastName}
                    <br>
                    ${applicantInfo.city}, ${applicantInfo.country}
                    <br>
                    ${applicantInfo.university}, ${applicantInfo.graduationYear}
                    <br>
                    ${applicantInfo.englishLevel}
                    <br>
                    ${applicantInfo.professionalSkills}
                    <br>
                    ${applicantInfo.email}
                    <br>
                    ${applicantInfo.phone}
                    <br>
                    ${applicantInfo.skype}
                    <br>

                    <form action="<c:url value="/controller"/>" method="post">

                        <input type="hidden" name="command" value="edit"/>
                        <input type="hidden" name="type" value="result"/>
                        <input type="hidden" name="idApplicant" value="${applicantInfo.id}"/>
                        <input type="hidden" name="idVacancy" value="${respond.idVacancy}"/>

                        <label for="stage">${stage}</label>
                        <select class="form-control input-sm" name="stage" id="stage">
                            <c:if test="${respond.stage == 'PHONE'}">
                                <option value="phone" selected>${phone_stage}</option>
                            </c:if>
                            <c:if test="${respond.stage != 'PHONE'}">
                                <option value="phone">${phone_stage}</option>
                            </c:if>
                            <c:if test="${respond.stage == 'INTERVIEW'}">
                                <option value="interview" selected>${interview_stage}</option>
                            </c:if>
                            <c:if test="${respond.stage != 'INTERVIEW'}">
                                <option value="interview">${interview_stage}</option>
                            </c:if>
                            <c:if test="${respond.stage == 'ANSWER'}">
                                <option value="answer" selected>${answer_stage}</option>
                            </c:if>
                            <c:if test="${respond.stage != 'ANSWER'}">
                                <option value="answer">${answer_stage}</option>
                            </c:if>
                        </select>

                        <label for="conversationDate">Дата последней беседы (en)</label>
                        <c:if test="${respond.conversationDate != null}">
                            <input type="date" id="conversationDate" name="conversationDate"
                                   value="${respond.conversationDate}">
                        </c:if>
                        <c:if test="${respond.conversationDate == null}">
                            <input type="date" id="conversationDate" name="conversationDate">
                        </c:if>

                        <br>

                        <label for="note">Пометки о соискателе (en)</label>
                        <c:if test="${respond.note != null}">
                            <textarea id="note" name="note">${respond.note}</textarea>
                        </c:if>
                        <c:if test="${respond.note == null}">
                            <textarea id="note" name="note"></textarea>
                        </c:if>

                        <br>


                        <label for="mark">Общая оценка (en)</label>
                        <c:if test="${respond.mark != null}">
                            <input type="number" id="mark" name="mark" value="${respond.mark}">
                        </c:if>
                        <c:if test="${respond.mark == null}">
                            <input type="number" id="mark" name="mark">
                        </c:if>

                        <br>


                        <button type="submit" class="btn btn-primary">
                            Редактировать результат
                        </button>
                    </form>
                </c:if>
            </c:if>

        </div>
    </div>
</div>