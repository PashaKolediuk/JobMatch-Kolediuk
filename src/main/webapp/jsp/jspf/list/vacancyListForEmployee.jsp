<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                            <h3 class="panel-title">${vacancy_list}</h3>
                        </div>
                        <div class="common-div col col-xs-6 text-right">
                            <form action="<c:url value="/controller"/>"
                                  method="get" class="search">

                                <input type="hidden" name="command" value="get_list"/>
                                <input type="hidden" name="type" value="vacancy_by_id"/>
                                <input type="hidden" name="page" value="1"/>

                                <div id="custom-search-input" class="input-group">
                                    <input type="text" class="form-control input-sm" name="vacancyName"
                                           placeholder="${vacancy_name}" value="${param.vacancyName}">
                                <span class="input-group-btn">
                        <button class="btn btn-info btn-lg" type="submit">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </span>
                                </div>
                            </form>
                            <form action="<c:url value="/jsp/registration/vacancyRegistration.jsp"/>">
                                <button type="submit" class="btn btn-labeled btn-info standart-button">
                                    <span class="btn-label"><i class="fa fa-plus"
                                                               aria-hidden="true"></i></span> ${add_new}</button>
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
                        <div class="empty-list">
                                ${empty_list}
                        </div>
                    </c:if>
                    <c:if test="${fn:length(requestScope.vacancyListById) > 0}">
                        <table class="table table-striped table-bordered table-list">
                            <thead class="table-head">
                            <tr>
                                <th class="text-center col-md-1">#</th>
                                <th class="text-center col-md-1">ID</th>
                                <th class="text-center col-md-3">${vacancy_name}</th>
                                <th class="text-center col-md-2">${open_date}</th>
                                <th class="text-center col-md-1">${salary}</th>
                                <th class="text-center col-md-2">${required_experience}</th>
                                <th class="text-center col-md-2"><em class="fa fa-cog"></em></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="pageCount" value="${requestScope.pageCount}"/>
                            <c:forEach var="vacancy" items="${requestScope.vacancyListById}" varStatus="vacancyCount">
                            <div class="panel-group" id="accordion${vacancyCount.count}">
                                <div class="panel panel-default no-border">
                                    <tr>
                                        <td class="hidden-xs text-center ">${(param.page-1)*5+vacancyCount.count}.</td>
                                        <td class="text-center">${vacancy.idVacancy}</td>
                                        <td class="text-center">${vacancy.name}</td>
                                        <td class="text-center">${vacancy.date}</td>
                                        <td class="text-center">
                                            <c:if test="${vacancy.salary == 0}">
                                                -
                                            </c:if>
                                            <c:if test="${vacancy.salary != 0}">
                                                ${vacancy.salary}
                                            </c:if>
                                        </td>
                                        <td class="text-center">
                                            <c:if test="${vacancy.requiredExperience == 0}">
                                                -
                                            </c:if>
                                            <c:if test="${vacancy.requiredExperience != 0}">
                                                ${vacancy.requiredExperience}
                                            </c:if>
                                        </td>
                                        <td align="center">

                                            <form action="<c:url value="/controller"/>" method="post">
                                                <input type="hidden" name="command" value="delete">
                                                <input type="hidden" name="type" value="vacancy">
                                                <input type="hidden" name="idVacancy" value="${vacancy.idVacancy}">
                                                <div class="btn-group">
                                                    <a class="btn btn-default masterTooltip" title="${responds}"
                                                       href="<c:url value="/controller?command=get_list&type=respond_of_vacancy&idVacancy=${vacancy.idVacancy}&page=1&lastName=&email=&phone=&stage=&mark=0&sort=respondDate desc"/>">
                                                        <em class="fa fa-pencil"></em>
                                                    </a>
                                                    <button type="submit" class="btn btn-danger masterTooltip"
                                                            data-toggle="confirmation"
                                                            data-placement="left"
                                                            data-singleton="true"
                                                            data-popout="true"
                                                            data-btn-ok-class="btn-xs btn-danger"
                                                            data-btn-ok-label="${yes}"
                                                            data-btn-cancel-label="${no}"
                                                            data-content="${are_you_sure}"
                                                            title="${close}">
                                                        <em class="fa fa-trash"></em>
                                                    </button>
                                                    <button class="btn btn-primary masterTooltip" title="${edit}"
                                                            data-toggle="collapse"
                                                            data-parent="#accordion${vacancyCount.count}"
                                                            href="#collapseTwo${vacancyCount.count}">
                                                        <i class="fa fa-pencil-square-o"
                                                           aria-hidden="true"></i>
                                                    </button>
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
                                                                <div class="input-group">
                                                                    <input type="text" class="form-control"
                                                                           name="vacancyName" id="vacancyName"
                                                                           value="${vacancy.name}" required>
                                                                    <span class="input-group-addon danger"><span
                                                                            class="glyphicon glyphicon-remove"></span></span>
                                                                </div>
                                                            </div>

                                                            <div class="form-group col-md-3">
                                                                <label for="requiredExperience">${required_experience}</label>
                                                                <div class="input-group"
                                                                     data-validate="requiredExperience">
                                                                    <input type="number" class="form-control"
                                                                           name="requiredExperience"
                                                                           id="requiredExperience"
                                                                           value="${vacancy.requiredExperience}"
                                                                           required>
                                                                    <span class="input-group-addon danger masterTooltip" title="${validation_required_experience}">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                    </span>
                                                                </div>
                                                            </div>

                                                            <div class="form-group col-md-3">
                                                                <label for="salary">${salary}</label>
                                                                <div class="input-group" data-validate="salary">
                                                                    <input type="number" class="form-control"
                                                                           name="salary" id="salary"
                                                                           value="${vacancy.salary}" required>
                                                                    <span class="input-group-addon danger masterTooltip" title="${validation_salary}">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                    </span>
                                                                </div>
                                                            </div>

                                                            <div class="form-group col-md-12">
                                                                <label for="requiredSkills">${required_skills}</label>
                                                                <div class="input-group">
                            <textarea class="form-control input-sm" name="requiredSkills" id="requiredSkills"
                                      rows="6" required>${vacancy.requiredSkills}</textarea>
                                                                    <span class="input-group-addon danger"><span
                                                                            class="glyphicon glyphicon-remove"></span></span>
                                                                </div>
                                                            </div>

                                                            <div class="form-group col-md-12">
                                                                <label for="vacancyDescription">${vacancy_description}</label>
                                                                <div class="input-group">
                            <textarea class="form-control input-sm" name="vacancyDescription" id="vacancyDescription"
                                      rows="6" required>${vacancy.vacancyDescription}</textarea>
                                                                    <span class="input-group-addon danger"><span
                                                                            class="glyphicon glyphicon-remove"></span></span>
                                                                </div>
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
