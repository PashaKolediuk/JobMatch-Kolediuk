<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${sessionScope.employee == null}">
    <c:redirect url="../index.jsp"/>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-12">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">${respond_list}</h3>
                        </div>
                        <div class="col col-xs-6 text-right">
                            <form action="<c:url value="/controller"/>" method="get">
                                <input type="hidden" name="command" value="get_list">
                                <input type="hidden" name="type" value="vacancy_by_id">
                                <input type="hidden" name="page" value="1">
                                <input type="hidden" name="vacancyName" value="">
                                <button type="submit" class="btn btn-labeled btn-info back-button">
                                    <i class="fa fa-long-arrow-left" aria-hidden="true"></i>
                                    ${back}
                                </button>
                            </form>
                            <button type="button" class="btn btn-labeled btn-info standart-button"
                                    data-toggle="collapse"
                                    data-target="#filter-panel">
                                <i class="fa fa-filter" aria-hidden="true"></i>
                                ${filter}
                            </button>
                        </div>
                        <div class="col col-xs-12">

                            <div id="filter-panel" class="collapse filter-panel">
                                <div class="panel panel-default filter">
                                    <div class="panel-body filter">
                                        <form class="form-inline " role="form">
                                            <input type="hidden" name="command" value="get_list">
                                            <input type="hidden" name="type" value="respond_of_vacancy">
                                            <input type="hidden" name="page" value="1">
                                            <input type="hidden" name="idVacancy" value="${param.idVacancy}">


                                            <div class="col-md-10 filter-row">
                                                <div class="col-md-3 col-md-offset-2" id="filterColumn1">

                                                    <div class="form-group">
                                                        <label for="lastNameFilter">${last_name}:</label>
                                                        <input type="text" class="form-control input-normal"
                                                               id="lastNameFilter"
                                                               name="lastName"
                                                               value="${param.lastName}">
                                                    </div>

                                                </div>
                                                <div class="col-md-3" id="filterColumn2">

                                                    <div class="form-group">
                                                        <label for="emailFilter">${email}:</label>
                                                        <input type="text" class="form-control input-normal"
                                                               id="emailFilter"
                                                               name="email"
                                                               value="${param.email}">
                                                    </div>

                                                </div>
                                                <div class="col-md-3" id="filterColumn3">

                                                    <div class="form-group">
                                                        <label for="phoneFilter">${phone}:</label>
                                                        <input type="text" class="form-control input-normal"
                                                               id="phoneFilter"
                                                               name="phone"
                                                               value="${param.phone}">
                                                    </div>

                                                </div>
                                                <div class="col-md-1">
                                                    <div class="form-group">

                                                        <button type="submit"
                                                                class="btn btn-default filter-col filter-button">
                                                            <span class="glyphicon glyphicon-record"></span>
                                                            ${filter}
                                                        </button>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-10 filter-row">
                                                <div class="col-md-3 col-md-offset-2" id="filterColumn4">

                                                    <div class="form-group">
                                                        <label for="markFilter">${min_mark}:</label>
                                                        <input type="number" class="form-control input-normal"
                                                               id="markFilter"
                                                               name="mark" value="${param.mark}">
                                                    </div>

                                                </div>
                                                <div class="col-md-2" id="filterColumn5">

                                                    <div class="form-group">
                                                        <label for="stageFilter">${stage}:</label>
                                                        <select class="form-control input-normal" id="stageFilter"
                                                                name="stage">
                                                            <c:if test="${param.stage eq ''}">
                                                                <option value="" selected>${no_matter}</option>
                                                            </c:if>
                                                            <c:if test="${param.stage ne ''}">
                                                                <option value="">${no_matter}</option>
                                                            </c:if>
                                                            <c:if test="${param.stage eq 'phone'}">
                                                                <option value="phone" selected>${phone_stage}</option>
                                                            </c:if>
                                                            <c:if test="${param.stage ne 'phone'}">
                                                                <option value="phone">${phone_stage}</option>
                                                            </c:if>
                                                            <c:if test="${param.stage eq 'interview'}">
                                                                <option value="interview"
                                                                        selected>${interview_stage}</option>
                                                            </c:if>
                                                            <c:if test="${param.stage ne 'interview'}">
                                                                <option value="interview">${interview_stage}</option>
                                                            </c:if>
                                                            <c:if test="${param.stage eq 'answer'}">
                                                                <option value="answer" selected>${answer_stage}</option>
                                                            </c:if>
                                                            <c:if test="${param.stage ne 'answer'}">
                                                                <option value="answer">${answer_stage}</option>
                                                            </c:if>
                                                        </select>
                                                    </div>

                                                </div>
                                                <div class="col-md-3 col-md-offset-1" id="filterColumn6">

                                                    <div class="form-group">
                                                        <label for="sort">${sort}:</label><br>
                                                        <select class="form-control input-normal" id="sort"
                                                                name="sort">
                                                            <c:if test="${param.sort eq 'respondDate desc'}">
                                                                <option value="respondDate desc"
                                                                        selected>${by_respond_date}</option>
                                                            </c:if>
                                                            <c:if test="${param.sort ne 'respondDate desc'}">
                                                                <option value="respondDate desc">${by_respond_date}</option>
                                                            </c:if>
                                                            <c:if test="${param.sort eq 'totalMark desc'}">
                                                                <option value="totalMark desc" selected>${by_mark_decrease}</option>
                                                            </c:if>
                                                            <c:if test="${param.sort ne 'totalMark desc'}">
                                                                <option value="totalMark desc">${by_mark_decrease}</option>
                                                            </c:if>
                                                            <c:if test="${param.sort eq 'totalMark'}">
                                                                <option value="totalMark" selected>${by_mark_increase}</option>
                                                            </c:if>
                                                            <c:if test="${param.sort ne 'totalMark'}">
                                                                <option value="totalMark">${by_mark_increase}</option>
                                                            </c:if>
                                                            <c:if test="${param.sort eq 'conversationDate desc'}">
                                                                <option value="conversationDate desc" selected>${by_meeting_date}</option>
                                                            </c:if>
                                                            <c:if test="${param.sort ne 'conversationDate desc'}">
                                                                <option value="conversationDate desc">${by_meeting_date}</option>
                                                            </c:if>
                                                        </select>
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


                <div class="panel-body">
                    <c:if test="${fn:length(requestScope.respondListOfVacancy) == 0}">
                        <div class="empty-list">
                                ${empty_list}
                        </div>
                    </c:if>
                    <c:if test="${fn:length(requestScope.respondListOfVacancy) > 0}">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                            <tr>
                                <th class="hidden-xs number col-md-1">#</th>
                                <th class="text-center col-md-2">${first_name}</th>
                                <th class="text-center col-md-2">${last_name}</th>
                                <th class="text-center col-md-2">${phone}</th>
                                <th class="text-center col-md-2">${email}</th>
                                <th class="text-center col-md-2">${stage}</th>
                                <th class="text-center col-md-1">${mark}</th>
                                <th class="text-center col-md-1"><em class="fa fa-cog"></em></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="pageCount" value="${requestScope.pageCount}"/>
                            <c:forEach var="respondItem" items="${requestScope.respondListOfVacancy}"
                                       varStatus="respondCount">
                                <c:set var="respond" scope="page" value="${respondItem.key}"/>
                                <c:set var="applicant" scope="page" value="${respondItem.value}"/>
                                <tr>
                                    <td class="hidden-xs text-center">${(param.page-1)*5+respondCount.count}.</td>
                                    <td class="text-center">${applicant.firstName}</td>
                                    <td class="text-center">${applicant.lastName}</td>
                                    <td class="text-center">${applicant.phone}</td>
                                    <td class="text-center">${applicant.email}</td>
                                    <td class="text-center">
                                        <c:if test="${respond.stage == 'PHONE'}">
                                            ${phone_stage}
                                        </c:if>
                                        <c:if test="${respond.stage == 'INTERVIEW'}">
                                            ${interview_stage}
                                        </c:if>
                                        <c:if test="${respond.stage == 'ANSWER'}">
                                            ${answer_stage}
                                        </c:if>
                                    </td>
                                    <td class="text-center">${respond.mark}</td>
                                    <td align="center">
                                        <div class="btn-group">
                                            <a class="btn btn-default btn-lg masterTooltip" title="${respond_details}"
                                               href="<c:url value="/controller?command=get_info&type=respond_of_vacancy&idApplicant=${respond.idApplicant}&idVacancy=${respond.idVacancy}"/>"><em
                                                    class="fa fa-pencil"></em></a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
                <nav class="text-center">
                    <ul class="pagination">
                        <c:if test="${pageCount != 1}">
                            <c:if test="${param.page != 1}">
                                <li>
                                    <a href="<c:url value="/controller?command=get_list&type=respond_of_vacancy&idVacancy=${param.idVacancy}&page=${param.page - 1}&lastName=${param.fullName}&email=${param.email}&phone=${param.phone}&stage=${param.stage}&mark=${param.mark}&sort=${param.sort}"/>">${previous}</a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${pageCount}" var="i">
                                <c:choose>
                                    <c:when test="${param.page eq i}">
                                        <li class="active"><a>${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="<c:url value="/controller?command=get_list&type=respond_of_vacancy&idVacancy=${param.idVacancy}&page=${i}&lastName=${param.fullName}&email=${param.email}&phone=${param.phone}&stage=${param.stage}&mark=${param.mark}&sort=${param.sort}"/>">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${param.page lt pageCount}">
                                <li>
                                    <a href="<c:url value="/controller?command=get_list&type=respond_of_vacancy&idVacancy=${param.idVacancy}&page=${param.page + 1}&lastName=${param.fullName}&email=${param.email}&phone=${param.phone}&stage=${param.stage}&mark=${param.mark}&sort=${param.sort}"/>">${next}</a>
                                </li>
                            </c:if>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>

    </div>
</div>
