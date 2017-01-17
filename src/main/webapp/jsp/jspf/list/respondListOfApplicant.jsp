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
                        ${empty_list}
                    </c:if>
                    <c:if test="${fn:length(requestScope.respondListOfApplicant) > 0}">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                            <tr>
                                <th class="hidden-xs number">#</th>
                                <th>${vacancy_name}</th>
                                <th>${respond_date}</th>
                                <th class="text-center"><em class="fa fa-cog"></em></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="pageCount" value="${requestScope.pageCount}"/>
                            <c:forEach var="respondItem" items="${requestScope.respondListOfApplicant}"
                                       varStatus="respondCount">
                                <c:set var="respond" scope="page" value="${respondItem.key}"/>
                                <c:set var="vacancy" scope="page" value="${respondItem.value}"/>
                                <tr>
                                    <td class="hidden-xs">${(param.page-1)*5+respondCount.count}.</td>
                                    <td>${vacancy.name}</td>
                                    <td>${respond.respondDate}</td>
                                    <td align="center">
                                        <a class="btn btn-default"
                                           href="<c:url value="/controller?command=get_info&type=respond_of_applicant&idVacancy=${respond.idVacancy}"/>"><em
                                                class="fa fa-pencil"> ${respond_details}</em></a>
                                        <form action="<c:url value="/controller"/>" method="post">
                                            <input type="hidden" name="command" value="delete">
                                            <input type="hidden" name="type" value="respond_of_applicant">
                                            <input type="hidden" name="idVacancy" value="${respond.idVacancy}">

                                            <button type="submit" class="btn btn-danger"><em
                                                    class="fa fa-trash"> ${cancel}</em></button>
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
