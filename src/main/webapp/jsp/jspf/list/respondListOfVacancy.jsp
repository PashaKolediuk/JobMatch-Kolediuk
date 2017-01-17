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
                            <form action="<c:url value="/controller"/>" method="get">
                                <input type="hidden" name="command" value="get_list">
                                <input type="hidden" name="type" value="vacancy_by_id">
                                <input type="hidden" name="page" value="1">
                                <input type="hidden" name="vacancyName" value="">
                                <button type="submit">${back}</button>
                            </form>
                        </div>
                        <div class="col col-xs-6 text-right">
                            <button type="button" class="btn btn-primary" data-toggle="collapse"
                                    data-target="#filter-panel">
                                <span class="glyphicon glyphicon-cog"></span>
                                ${filter}
                            </button>
                        </div>
                        <div class="col col-xs-12">

                            <div id="filter-panel" class="collapse filter-panel">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <form class="form-inline" role="form">
                                            <input type="hidden" name="command" value="get_list">
                                            <input type="hidden" name="type" value="respond_of_vacancy">
                                            <input type="hidden" name="page" value="1">
                                            <input type="hidden" name="idVacancy" value="${param.idVacancy}">

                                            <div class="form-group">
                                                <label class="filter-col" for="lastNameFilter">${last_name}:</label>
                                                <input type="text" class="form-control input-sm" id="lastNameFilter"
                                                       name="lastName">
                                            </div> <!-- form group [rows] -->
                                            <div class="form-group">
                                                <label class="filter-col" for="emailFilter">${email}:</label>
                                                <input type="text" class="form-control input-sm" id="emailFilter"
                                                       name="email">
                                            </div><!-- form group [search] -->
                                            <div class="form-group">
                                                <label class="filter-col" for="phoneFilter">${phone}:</label>
                                                <input type="text" class="form-control input-sm" id="phoneFilter"
                                                       name="phone">
                                            </div> <!-- form group [order by] -->
                                            <div class="form-group">
                                                <label class="filter-col" for="stageFilter">${stage}:</label>
                                                <select class="form-control input-sm" id="stageFilter"
                                                        name="stage">
                                                    <option value="phone" selected>${phone_stage}</option>
                                                    <option value="interview">${interview_stage}</option>
                                                    <option value="answer">${answer_stage}</option>
                                                </select>
                                            </div> <!-- form group [order by] -->
                                            <div class="form-group">
                                                <label class="filter-col" for="markFilter">${min_mark}:</label>
                                                <input type="number" class="form-control input-sm" id="markFilter"
                                                       name="mark" value="0">
                                            </div> <!-- form group [order by] -->
                                            <div class="form-group">
                                                <input type="radio" name="sort" value="respondDate desc"
                                                       checked>${by_respond_date}<br>
                                                <input type="radio" name="sort"
                                                       value="totalMark desc">${by_mark_decrease}<br>
                                                <input type="radio" name="sort"
                                                       value="totalMark ">${by_mark_increase}<br>
                                                <input type="radio" name="sort"
                                                       value="conversationDate desc">${by_meeting_date}<br>
                                            </div>
                                            <div class="form-group">

                                                <button type="submit" class="btn btn-default filter-col">
                                                    <span class="glyphicon glyphicon-record"></span>
                                                    ${filter}
                                                </button>
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
                        ${empty_list}
                    </c:if>
                    <c:if test="${fn:length(requestScope.respondListOfVacancy) > 0}">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                            <tr>
                                <th class="hidden-xs number">#</th>
                                <th>${first_name}</th>
                                <th>${last_name}</th>
                                <th>${phone}</th>
                                <th>${email}</th>
                                <th>${respond_date}</th>
                                <th>${stage}</th>
                                <th>${mark}</th>
                                <th class="text-center"><em class="fa fa-cog"></em></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="pageCount" value="${requestScope.pageCount}"/>
                            <c:forEach var="respondItem" items="${requestScope.respondListOfVacancy}"
                                       varStatus="respondCount">
                                <c:set var="respond" scope="page" value="${respondItem.key}"/>
                                <c:set var="applicant" scope="page" value="${respondItem.value}"/>
                                <tr>
                                    <td class="hidden-xs">${(param.page-1)*5+respondCount.count}.</td>
                                    <td>${applicant.firstName}</td>
                                    <td>${applicant.lastName}</td>
                                    <td>${applicant.phone}</td>
                                    <td>${applicant.email}</td>
                                    <td>${respond.respondDate}</td>
                                    <td>${respond.stage}</td>
                                    <td>${respond.mark}</td>
                                    <td align="center">
                                        <a class="btn btn-default"
                                           href="<c:url value="/controller?command=get_info&type=respond_of_vacancy&idApplicant=${respond.idApplicant}&idVacancy=${respond.idVacancy}"/>"><em
                                                class="fa fa-pencil">${respond_details}</em></a>
                                        <a class="btn btn-danger"><em class="fa fa-trash"></em></a>
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
</div>
