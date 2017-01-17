<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${sessionScope.employee == null}">
    <c:redirect url="../index.jsp"/>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">${vacancy_list}</h3>
                            <form action="<c:url value="/controller"/>"
                                  method="get">

                                <input type="hidden" name="command" value="get_list"/>
                                <input type="hidden" name="type" value="vacancy_by_id"/>
                                <input type="hidden" name="page" value="1"/>


                                <input type="text" name="vacancyName">
                                <input type="submit" value="${search}">
                            </form>
                        </div>
                        <div class="col col-xs-6 text-right">
                            <form action="<c:url value="/jsp/registration/vacancyRegistration.jsp"/>">
                                <button type="submit" class="btn btn-sm btn-info btn-create">${add_new}</button>
                            </form>
                        </div>
                    </div>
                </div>

                <c:if test="${param.fail != null}">
                    <div>
                        <div class="msg msg-warning msg-danger-text">
                            <span class="glyphicon glyphicon-exclamation-sign"></span>
                                ${failMessage}</div>
                    </div>
                </c:if>


                <div class="panel-body">
                    <c:if test="${fn:length(requestScope.vacancyListById) == 0}">
                        ${empty_list}
                    </c:if>
                    <c:if test="${fn:length(requestScope.vacancyListById) > 0}">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                            <tr>
                                <th class="hidden-xs number">#</th>
                                <th class="id">ID</th>
                                <th>${vacancy_name}</th>
                                <th>${open_date}</th>
                                <th>${salary}</th>
                                <th>${required_experience}</th>
                                <th class="text-center"><em class="fa fa-cog"></em></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="pageCount" value="${requestScope.pageCount}"/>
                            <c:forEach var="vacancy" items="${requestScope.vacancyListById}" varStatus="vacancyCount">
                            <div class="panel-group" id="accordion${vacancyCount.count}">
                                <div class="panel panel-default no-border">
                                    <tr>
                                        <td class="hidden-xs">${(param.page-1)*5+vacancyCount.count}.</td>
                                        <td>${vacancy.idVacancy}</td>
                                        <td>${vacancy.name}</td>
                                        <td>${vacancy.date}</td>
                                        <td>${vacancy.salary}</td>
                                        <td>${vacancy.requiredExperience}</td>
                                        <td align="center">
                                            <a class="btn btn-default"
                                               href="<c:url value="/controller?command=get_list&type=respond_of_vacancy&idVacancy=${vacancy.idVacancy}&page=1&lastName=&email=&phone=&stage=&mark=0&sort=respondDate desc"/>"><em
                                                    class="fa fa-pencil">${responds}</em></a>
                                            <form action="<c:url value="/controller"/>" method="post">
                                                <input type="hidden" name="command" value="delete">
                                                <input type="hidden" name="type" value="vacancy">
                                                <input type="hidden" name="idVacancy" value="${vacancy.idVacancy}">

                                                <button type="submit" class="btn btn-danger"><em class="fa fa-trash">
                                                        ${close}
                                                </em></button>
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a data-toggle="collapse"
                                                           data-parent="#accordion${vacancyCount.count}"
                                                           href="#collapseTwo${vacancyCount.count}">
                                                            <button class="btn btn-primary">${edit}</button>

                                                        </a>
                                                    </h4>
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="7" class="no-padding">

                                            <div id="collapseTwo${vacancyCount.count}" class="panel-collapse collapse">
                                                <div class="panel-body ">


                                                    <form role="form" action="<c:url value="/controller"/>"
                                                          method="post">

                                                        <input type="hidden" name="command" value="edit"/>
                                                        <input type="hidden" name="type" value="vacancy"/>
                                                        <input type="hidden" name="idVacancy"
                                                               value="${vacancy.idVacancy}"/>

                                                        <fieldset>
                                                            <legend>${vacancy_details}</legend>

                                                            <div class="form-group col-md-6">
                                                                <label for="vacancyName">${vacancy_name}</label>
                                                                <input type="text" class="form-control"
                                                                       name="vacancyName" id="vacancyName"
                                                                       value="${vacancy.name}">
                                                            </div>

                                                            <div class="form-group col-md-3">
                                                                <label for="requiredExperience">${required_experience}</label>
                                                                <input type="number" class="form-control"
                                                                       name="requiredExperience" id="requiredExperience"
                                                                       value="${vacancy.requiredExperience}">
                                                            </div>

                                                            <div class="form-group col-md-3">
                                                                <label for="salary">${salary}</label>
                                                                <input type="number" class="form-control"
                                                                       name="salary" id="salary"
                                                                       value="${vacancy.salary}">
                                                            </div>

                                                            <div class="form-group col-md-12">
                                                                <label for="requiredSkills">${required_skills}</label>
                            <textarea class="form-control input-sm" name="requiredSkills" id="requiredSkills"
                                      rows="3">${vacancy.requiredSkills}</textarea>
                                                            </div>

                                                            <div class="form-group col-md-12">
                                                                <label for="vacancyDescription">${vacancy_description}</label>
                            <textarea class="form-control input-sm" name="vacancyDescription" id="vacancyDescription"
                                      rows="3">${vacancy.vacancyDescription}</textarea>
                                                            </div>

                                                        </fieldset>

                                                        <div class="form-group">
                                                            <div class="col-md-12">
                                                                <button type="submit" class="btn btn-primary">
                                                                        ${edit}
                                                                </button>
                                                            </div>
                                                        </div>

                                                    </form>

                                                </div>
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
                                    <a href="<c:url value="/controller?command=get_list&type=vacancy_by_id&page=${param.page - 1}&vacancyName=${param.vacancyName}"/>">${previous}</a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${pageCount}" var="i">
                                <c:choose>
                                    <c:when test="${param.page eq i}">
                                        <li class="active"><a>${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="<c:url value="/controller?command=get_list&type=vacancy_by_id&page=${i}&vacancyName=${param.vacancyName}"/>">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${param.page lt pageCount}">
                                <li>
                                    <a href="<c:url value="/controller?command=get_list&type=vacancy_by_id&page=${param.page + 1}&vacancyName=${param.vacancyName}"/>">${next}</a>
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
