<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${sessionScope.employee == null || sessionScope.employee.status != 'ADMIN'}">
    <c:redirect url="../index.jsp"/>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">${employee_list}</h3>


                        </div>
                        <div class="col col-xs-6 text-right">
                            <button type="button" class="btn btn-labeled btn-info standart-button"
                                    data-toggle="collapse"
                                    data-target="#filter-panel">
                                <i class="fa fa-filter" aria-hidden="true"></i>
                                ${filter}
                            </button>
                            <form action="<c:url value="/jsp/registration/employeeRegistration.jsp"/>">
                                <button type="submit" class="btn btn-labeled btn-info standart-button">
                                    <span class="btn-label"><i class="fa fa-plus"
                                                               aria-hidden="true"></i></span> ${add_new}</button>
                            </form>
                        </div>
                        <div class="col col-xs-12">

                            <div id="filter-panel" class="collapse filler filter-panel">
                                <div class="panel panel-default filter">
                                    <div class="panel-body filter centered">
                                        <form class="form-inline" role="form">
                                            <input type="hidden" name="command" value="get_list">
                                            <input type="hidden" name="type" value="employee">
                                            <input type="hidden" name="page" value="1">
                                            <div class="form-group">
                                                <label class="filter-col" for="fullNameFilter">${full_name}:</label>
                                                <input type="text" class="form-control input-sm" id="fullNameFilter"
                                                       name="fullName" value="${param.fullName}">
                                            </div>
                                            <div class="form-group">
                                                <label class="filter-col" for="emailFilter">${email}:</label>
                                                <input type="text" class="form-control input-sm" id="emailFilter"
                                                       name="email" value="${param.email}">
                                            </div>
                                            <div class="form-group">
                                                <label class="filter-col" for="phoneFilter">${phone}:</label>
                                                <input type="text" class="form-control input-sm" id="phoneFilter"
                                                       name="phone" value="${param.phone}">
                                            </div>
                                            <div class="form-group">

                                                <button type="submit" class="btn btn-default filter-col masterTooltip"
                                                        title="${filter}">
                                                    <span class="glyphicon glyphicon-record"></span>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>


                    </div>
                </div>
                <c:if test="${param.fail != null}">
                    <div>
                        <div class="msg msg-warning msg-danger-text col-md-offset-4">
                            <span class="glyphicon glyphicon-exclamation-sign"></span>
                                ${failMessage}</div>
                    </div>
                </c:if>

                <div class="panel-body">
                    <c:if test="${fn:length(requestScope.employeeList) == 0}">
                        <div class="empty-list">
                                ${empty_list}
                        </div>
                    </c:if>
                    <c:if test="${fn:length(requestScope.employeeList) > 0}">
                        <table class="table table-striped table-bordered table-list">
                            <thead class="table-head">
                            <tr>
                                <th class="text-center col-md-1">#</th>
                                <th class="text-center col-md-1">ID</th>
                                <th class="text-center col-md-3">${full_name}</th>
                                <th class="text-center col-md-3">${email}</th>
                                <th class="text-center col-md-2">${phone}</th>
                                <th class="text-center col-md-2"><em class="fa fa-cog"></em></th>
                            </tr>
                            </thead>

                            <tbody>

                            <c:set var="pageCount" value="${requestScope.pageCount}"/>
                            <c:forEach var="employee" items="${requestScope.employeeList}" varStatus="employeeCount">
                                <div class="panel-group" id="accordion${employeeCount.count}">
                                    <div class="panel panel-default no-border">
                                        <tr>
                                            <td class="text-center">${(param.page-1)*5+employeeCount.count}.</td>
                                            <td class="text-center">${employee.id}</td>
                                            <td class="text-center">${employee.fullName}</td>
                                            <td class="text-center">${employee.email}</td>
                                            <td class="text-center">${employee.phone}</td>
                                            <td align="center">
                                                <form action="<c:url value="/controller"/>" method="post">
                                                    <input type="hidden" name="command" value="delete">
                                                    <input type="hidden" name="type" value="employee">
                                                    <input type="hidden" name="id" value="${employee.id}">
                                                    <div class="btn-group">
                                                        <button type="submit" class="btn btn-danger masterTooltip"
                                                                data-toggle="confirmation"
                                                                data-placement="left"
                                                                data-singleton="true"
                                                                data-popout="true"
                                                                data-btn-ok-class="btn-xs btn-danger"
                                                                data-btn-ok-label="${yes}"
                                                                data-btn-cancel-label="${no}"
                                                                data-content="${are_you_sure}"
                                                                title="${delete}">
                                                            <em class="fa fa-trash"></em>
                                                        </button>
                                                        <button class="btn btn-primary masterTooltip"
                                                                title="${edit}" data-toggle="collapse"
                                                                data-parent="#accordion${employeeCount.count}"
                                                                href="#collapseTwo${employeeCount.count}">
                                                            <i class="fa fa-pencil-square-o"
                                                               aria-hidden="true"></i>
                                                        </button>
                                                    </div>
                                                </form>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="6" class="no-padding">

                                                <div id="collapseTwo${employeeCount.count}"
                                                     class="panel-collapse collapse employee-info">
                                                    <div class="panel-body">

                                                        <legend class="text-center">${profile_editing}</legend>

                                                        <ul class="nav nav-tabs">
                                                            <li class="active"><a data-toggle="tab"
                                                                                  href="#profile-details${employeeCount.count}">
                                                                    ${profile_details}
                                                            </a></li>
                                                            <li><a data-toggle="tab"
                                                                   href="#password-details${employeeCount.count}">
                                                                    ${password}
                                                            </a>
                                                            </li>
                                                        </ul>

                                                        <div class="tab-content">
                                                            <div id="profile-details${employeeCount.count}"
                                                                 class="tab-pane fade in active">
                                                                <form role="form" action="<c:url value="/controller"/>"
                                                                      method="post">

                                                                    <input type="hidden" name="command" value="edit"/>
                                                                    <input type="hidden" name="type"
                                                                           value="employee_profile"/>
                                                                    <input type="hidden" name="id"
                                                                           value="${employee.id}"/>
                                                                    <input type="hidden" name="status" value="HR"/>

                                                                    <fieldset>


                                                                        <div class="form-group col-md-6">
                                                                            <label for="fullName">${full_name}</label>
                                                                            <div class="input-group"
                                                                                 data-validate="name">
                                                                                <input type="text" class="form-control"
                                                                                       name="fullName" id="fullName"
                                                                                       value="${employee.fullName}"
                                                                                       required>
                                                                                <span class="input-group-addon danger masterTooltip" title="${validation_name}">
                                                                                    <span class="glyphicon glyphicon-remove"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>


                                                                        <div class="form-group col-md-6">
                                                                            <label for="email">${email}</label>
                                                                            <div class="input-group"
                                                                                 data-validate="email">
                                                                                <input type="email" class="form-control"
                                                                                       name="email" id="email"
                                                                                       value="${employee.email}"
                                                                                       required>
                                                                                <span class="input-group-addon danger masterTooltip" title="${validation_email}">
                                                                                    <span class="glyphicon glyphicon-remove"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>

                                                                        <div class="form-group col-md-6">
                                                                            <label for="phone">${phone}</label>
                                                                            <div class="input-group"
                                                                                 data-validate="phone">
                                                                                <input type="text" class="form-control"
                                                                                       name="phone" id="phone"
                                                                                       value="${employee.phone}"
                                                                                       required>
                                                                                <span class="input-group-addon danger masterTooltip" title="${validation_phone}">
                                                                                    <span class="glyphicon glyphicon-remove"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>

                                                                        <div class="form-group col-md-6">
                                                                            <label for="skype">Skype</label>
                                                                            <div class="input-group">
                                                                                <input type="text" class="form-control"
                                                                                       name="skype" id="skype"
                                                                                       value="${employee.skype}">
                                                                                <span class="input-group-addon info"><span
                                                                                        class="glyphicon glyphicon-asterisk"></span></span>
                                                                            </div>
                                                                        </div>

                                                                    </fieldset>

                                                                    <div class="form-group">
                                                                        <div class="col-md-12">
                                                                            <button type="submit"
                                                                                    class="btn btn-primary">
                                                                                    ${edit}
                                                                            </button>
                                                                        </div>
                                                                    </div>

                                                                </form>
                                                            </div>
                                                            <div id="password-details${employeeCount.count}"
                                                                 class="tab-pane fade">
                                                                <form role="form" action="<c:url value="/controller"/>"
                                                                      method="post">

                                                                    <input type="hidden" name="command" value="edit"/>
                                                                    <input type="hidden" name="type"
                                                                           value="employee_password"/>
                                                                    <input type="hidden" name="id"
                                                                           value="${employee.id}"/>
                                                                    <input type="hidden" name="status" value="HR"/>

                                                                    <fieldset>


                                                                        <div class="form-group col-md-6">
                                                                            <label for="password">${password}</label>
                                                                            <div class="input-group"
                                                                                 data-validate="password">
                                                                                <input type="password"
                                                                                       class="form-control"
                                                                                       name="password" id="password"
                                                                                       placeholder="${password}"
                                                                                       required>
                                                                                <span class="input-group-addon danger masterTooltip" title="${validation_password}">
                                                                                    <span class="glyphicon glyphicon-remove"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>

                                                                        <div class="form-group col-md-6">
                                                                            <label for="confirmPassword">${confirm_password}</label>
                                                                            <div class="input-group"
                                                                                 data-validate="confirmPassword">
                                                                                <input type="password"
                                                                                       class="form-control"
                                                                                       name="confirmPassword"
                                                                                       id="confirmPassword"
                                                                                       placeholder="${confirm_password}"
                                                                                       required>
                                                                                <span class="input-group-addon danger masterTooltip" title="${validation_confirm_password}">
                                                                                    <span class="glyphicon glyphicon-remove"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>


                                                                    </fieldset>

                                                                    <div class="form-group">
                                                                        <div class="col-md-12">
                                                                            <button type="submit"
                                                                                    class="btn btn-primary">
                                                                                    ${edit}
                                                                            </button>
                                                                        </div>
                                                                    </div>

                                                                </form>
                                                            </div>
                                                        </div>


                                                    </div>
                                                </div>
                                            </td>
                                        </tr>

                                    </div>
                                </div>
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
                                    <a href="<c:url value="/controller?command=get_list&type=employee&page=${param.page - 1}&fullName=${param.fullName}&email=${param.email}&phone=${param.phone}"/>">${previous}</a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${pageCount}" var="i">
                                <c:choose>
                                    <c:when test="${param.page eq i}">
                                        <li class="active"><a>${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="<c:url value="/controller?command=get_list&type=employee&page=${i}&fullName=${param.fullName}&email=${param.email}&phone=${param.phone}"/>">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${param.page lt pageCount}">
                                <li>
                                    <a href="<c:url value="/controller?command=get_list&type=employee&page=${param.page + 1}&fullName=${param.fullName}&email=${param.email}&phone=${param.phone}"/>">${next}</a>
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
