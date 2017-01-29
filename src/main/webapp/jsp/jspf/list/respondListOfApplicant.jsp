<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${sessionScope.applicant == null}">
    <c:redirect url="../index.jsp"/>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">${respond_list}</h3>
                        </div>
                    </div>
                </div>


                <div class="panel-body">
                    <c:if test="${fn:length(requestScope.respondListOfApplicant) == 0}">
                        <div class="empty-list">
                                ${empty_list}
                        </div>
                    </c:if>
                    <c:if test="${fn:length(requestScope.respondListOfApplicant) > 0}">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                            <tr>
                                <th class="text-center col-md-1">#</th>
                                <th class="text-center col-md-4">${vacancy_name}</th>
                                <th class="text-center col-md-2">${respond_date}</th>
                                <th class="text-center col-md-3">${stage}</th>
                                <th class="text-center col-md-2"><em class="fa fa-cog"></em></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="pageCount" value="${requestScope.pageCount}"/>
                            <c:forEach var="respondItem" items="${requestScope.respondListOfApplicant}"
                                       varStatus="respondCount">
                                <c:set var="respond" scope="page" value="${respondItem.key}"/>
                                <c:set var="vacancy" scope="page" value="${respondItem.value}"/>
                                <tr>
                                    <td class="text-center">${(param.page-1)*5+respondCount.count}.</td>
                                    <td class="text-center">${vacancy.name}</td>
                                    <td class="text-center">${respond.respondDate}</td>
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
                                    <td align="center">
                                        <form action="<c:url value="/controller"/>" method="post">
                                            <input type="hidden" name="command" value="delete">
                                            <input type="hidden" name="type" value="respond_of_applicant">
                                            <input type="hidden" name="idVacancy" value="${respond.idVacancy}">
                                            <div class="btn-group">
                                                <a class="btn btn-default masterTooltip" title="${respond_details}"
                                                   href="<c:url value="/controller?command=get_info&type=respond_of_applicant&idVacancy=${respond.idVacancy}"/>">
                                                    <em class="fa fa-pencil"></em></a>
                                                <button type="submit" class="btn btn-danger masterTooltip"
                                                        data-toggle="confirmation"
                                                        data-placement="left"
                                                        data-singleton="true"
                                                        data-popout="true"
                                                        data-btn-ok-class="btn-xs btn-danger"
                                                        data-btn-ok-label="${yes}"
                                                        data-btn-cancel-label="${no}"
                                                        data-content="${are_you_sure}"
                                                        title="${cancel}">
                                                    <em class="fa fa-trash"></em></button>
                                            </div>
                                        </form>
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
                                    <a href="<c:url value="/controller?command=get_list&type=respond_of_applicant&page=${param.page - 1}"/>">${previous}</a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${pageCount}" var="i">
                                <c:choose>
                                    <c:when test="${param.page eq i}">
                                        <li class="active"><a>${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="<c:url value="/controller?command=get_list&type=respond_of_applicant&page=${i}"/>">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${param.page lt pageCount}">
                                <li>
                                    <a href="<c:url value="/controller?command=get_list&type=respond_of_applicant&page=${param.page + 1}"/>">${next}</a>
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
