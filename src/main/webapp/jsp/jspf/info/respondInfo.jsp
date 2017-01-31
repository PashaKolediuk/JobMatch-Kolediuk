<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${sessionScope.employee == null && sessionScope.applicant == null}">
    <c:redirect url="../index.jsp"/>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
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
                    <c:set var="vacancy" value="${requestScope.vacancyInfo}"/>
                    <div class="company-info">
                        <div class="col-md-12">
                            <h3 class="info-header col-md-5">${vacancy_details}</h3>
                            <form class="col-md-1 col-md-offset-6" action="<c:url value="/controller"/>" method="get">
                                <input type="hidden" name="command" value="get_list">
                                <input type="hidden" name="type" value="respond_of_applicant">
                                <input type="hidden" name="page" value="1">
                                <button type="submit" class="btn btn-labeled btn-info back-button">
                                    <i class="fa fa-long-arrow-left" aria-hidden="true"></i>
                                        ${back}
                                </button>
                            </form>
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
                                    <div class="col-md-12  open-sans-bold">
                                            ${vacancy.name}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12 open-sans-bold">
                                            ${salary}: <span class="open-sans-italic">${vacancy.salary}$</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12 open-sans-bold">
                                            ${required_experience}: <span
                                            class="open-sans-italic">${vacancy.requiredExperience}</span>
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

                        <div class="col-md-12 text-center company-info">
                            <h3 class="info-header">${respond_details}</h3>
                            <div class="row">
                                <span class=" open-sans-bold">${stage}:</span>
                                <c:if test="${respond.stage == 'PHONE'}">
                                    ${phone_stage}
                                </c:if>
                                <c:if test="${respond.stage == 'INTERVIEW'}">
                                    ${interview_stage}
                                </c:if>
                                <c:if test="${respond.stage == 'ANSWER'}">
                                    ${answer_stage}
                                </c:if>
                            </div>
                            <div class="row">
                                <span class=" open-sans-bold">${respond_date}:</span> ${respond.respondDate}
                            </div>
                            <div class="row">
                                <span class=" open-sans-bold">${conversation_date}:</span>
                                <c:if test="${respond.conversationDate == null}">
                                    ${no_conversation}
                                </c:if>
                                <c:if test="${respond.conversationDate != null}">
                                    ${respond.conversationDate}
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${employee != null}">

                    <c:set var="applicantInfo" value="${requestScope.applicantInfo}"/>
                    <div class="company-info">
                        <div class="col-md-12">
                            <h3 class="info-header col-md-5">${applicant_details}</h3>
                            <form class="col-md-1 col-md-offset-6" action="<c:url value="/controller"/>" method="get">
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
                                <button type="submit" class="btn btn-labeled btn-info back-button">
                                    <i class="fa fa-long-arrow-left" aria-hidden="true"></i>
                                        ${back}
                                </button>
                            </form>
                        </div>

                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-12 open-sans-bold">
                                    <h3>${applicantInfo.firstName} ${applicantInfo.lastName}</h3>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <span class="open-sans-bold">${city}, ${country}:</span>
                                                ${applicantInfo.city}, ${applicantInfo.country}
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <span class="open-sans-bold">${university}:</span>
                                                ${applicantInfo.university}, ${applicantInfo.graduationYear}
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <span class="open-sans-bold">${english_level}:</span>
                                                ${applicantInfo.englishLevel}
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <span class="open-sans-bold">${email}:</span>
                                                ${applicantInfo.email}
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <span class="open-sans-bold">${phone}:</span>
                                                ${applicantInfo.phone}
                                        </div>
                                    </div>
                                    <c:if test="${applicantInfo.skype ne ''}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <span class="open-sans-bold">Skype:</span>
                                                    ${applicantInfo.skype}
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${applicantInfo.professionalSkills ne ''}">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="col-md-12 open-sans-bold">${professional_skills}:</div>
                                            ${applicantInfo.professionalSkills}
                                    </div>
                                </div>
                            </c:if>
                            <div class="row">
                                <div class="col-md-4 col-md-offset-8">
                                    <span class="open-sans-bold">${respond_date}:</span>
                                        ${respond.respondDate}
                                </div>
                            </div>
                        </div>
                    </div>

                    <form action="<c:url value="/controller"/>" method="post">

                        <input type="hidden" name="command" value="edit"/>
                        <input type="hidden" name="type" value="result"/>
                        <input type="hidden" name="idApplicant" value="${applicantInfo.id}"/>
                        <input type="hidden" name="idVacancy" value="${respond.idVacancy}"/>

                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="stage">${stage}</label>
                                    <select class="form-control input-normal" name="stage" id="stage">
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
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="conversationDate">${conversation_date}</label>
                                    <div class="input-group">
                                        <c:if test="${respond.conversationDate != null}">
                                            <input type="date" id="conversationDate" name="conversationDate"
                                                   class="form-control input-normal"
                                                   value="${respond.conversationDate}" required>
                                        </c:if>
                                        <c:if test="${respond.conversationDate == null}">
                                            <input type="date" id="conversationDate" name="conversationDate"
                                                   class="form-control input-normal" required>
                                        </c:if>
                                        <span class="input-group-addon danger"><span
                                                class="glyphicon glyphicon-remove"></span></span>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="mark">${mark}</label>
                                    <div class="input-group" data-validate="mark">
                                        <c:if test="${respond.mark != null}">
                                            <input type="number" id="mark" name="mark" value="${respond.mark}"
                                                   class="form-control input-normal" required>
                                        </c:if>
                                        <c:if test="${respond.mark == null}">
                                            <input type="number" id="mark" name="mark"
                                                   class="form-control input-normal" required>
                                        </c:if>
                                        <span class="input-group-addon danger masterTooltip" title="${validation_mark}">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <button type="submit" class="btn btn-primary standart-button">
                                        ${edit_result}
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label for="note">${respond_note}</label>
                                    <c:if test="${respond.note != null}">
                                        <textarea id="note" name="note" class="form-control input-normal"
                                                  rows="5">${respond.note}</textarea>
                                    </c:if>
                                    <c:if test="${respond.note == null}">
                                        <textarea id="note" name="note" class="form-control input-normal"
                                                  rows="5"></textarea>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                    </form>
                </c:if>
            </c:if>

        </div>
    </div>
</div>