<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">
    <div class="row">

        <div class="company-info  col-md-12 ">
            <c:set var="company" value="${requestScope.companyInfo}" scope="page"/>
            <c:set var="admin" value="${company.admin}" scope="page"/>


            <c:if test="${param.fail != null}">
                <div>
                    <div class="msg msg-warning msg-danger-text col-md-offset-4">
                        <span class="glyphicon glyphicon-exclamation-sign"></span>
                            ${failMessage}</div>
                </div>
            </c:if>

            <c:if test="${employee.status ne 'ADMIN'}">

                <div>
                    <div class="col-md-12">
                        <h3 class="info-header col-md-5">${company_information}</h3>
                        <form class="col-md-1 col-md-offset-4" action="<c:url value="/controller"/>" method="get">
                            <input type="hidden" name="command" value="get_list"/>
                            <input type="hidden" name="type" value="search"/>
                            <input type="hidden" name="page" value="1"/>
                            <input type="hidden" name="sort" value="date"/>
                            <input type="hidden" name="experienceFilter" value="0,100"/>
                            <input type="hidden" name="salaryFilter" value="0"/>
                            <input type="hidden" name="countryFilter" value=""/>
                            <input type="hidden" name="cityFilter" value=""/>
                            <input type="hidden" name="search" value="${company.companyName}">
                            <button type="submit" class="btn btn-labeled btn-primary back-button">
                                <i class="fa fa-list-ul" aria-hidden="true"></i>
                                    ${vacancy_list}
                            </button>
                        </form>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="col-md-4">
                        <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">
                            <object data="${pageContext.request.contextPath}/upload/${company.idCompany}.png"
                                    alt="Company logo"
                                    width="250"
                                    height="250" type="image/png">
                                <img src="${pageContext.request.contextPath}/upload/no-image.png"
                                     width="250"
                                     height="250"
                                     alt="Company logo">
                            </object>
                        </a>
                    </div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-12  open-sans-bold">
                                <h2>${company.companyName}</h2>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 open-sans-italic">
                                    ${company.country}, ${company.city}
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 ">
                                    ${company.website}
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 open-sans-bold">${company_description}:</div>
                                ${company.companyDescription}
                        </div>
                        <div class="row">
                                ${email}: ${admin.email}
                        </div>
                        <div class="row">
                                ${phone}: ${admin.phone}
                        </div>
                        <c:if test="${admin.skype ne ''}">
                            <div class="row">
                                Skype: ${admin.skype}
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:if>
            <c:if test="${employee.status eq 'ADMIN'}">
                <div class="col col-md-4 vertical-section">
                    <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${employee.idCompany}"/>">
                        <object data="${pageContext.request.contextPath}/upload/${employee.idCompany}.png"
                                alt="Company logo"
                                width="300"
                                height="300" type="image/png">
                            <img src="${pageContext.request.contextPath}/upload/no-image.png"
                                 width="300"
                                 height="300"
                                 alt="Company logo">
                        </object>
                    </a>
                    <form action="<c:url value="imageController"/>" method="post"
                          enctype="multipart/form-data">
                        <input type="hidden" name="command" value="image">
                        <input type="hidden" name="type" value="edit">
                        <div class="fileUpload btn btn-primary">
                            <span>${choose_file}</span>
                            <input type="file" name="file" class="upload" size="50" required/>
                        </div>
                        <input type="submit" class="btn btn-primary" value="${change_logo}"/>
                    </form>
                </div>
                <div class="col col-md-8 vertical-section">
                    <ul class="nav nav-tabs default-background">
                        <li class="active"><a data-toggle="tab"
                                              href="#company-details">
                                ${company_details}
                        </a></li>
                        <li><a data-toggle="tab"
                               href="#profile-details">
                                ${profile_details}
                        </a></li>
                        <li><a data-toggle="tab"
                               href="#password-details">
                                ${password}
                        </a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="company-details" class="tab-pane fade in active">
                            <form role="form" action="<c:url value="/controller"/>" method="post">

                                <input type="hidden" name="command" value="edit"/>
                                <input type="hidden" name="type" value="company"/>


                                <fieldset>
                                    <legend>${company_details}</legend>

                                    <div class="form-group col-md-6">
                                        <label for="companyName">${company_name}</label>
                                        <div class="input-group" data-validate="companyName">
                                            <input type="text" class="form-control" name="companyName"
                                                   id="companyName"
                                                   value="${company.companyName}" required>
                                                    <span class="input-group-addon danger masterTooltip"
                                                          title="${validation_company_name}">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                    </span>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="website">${website}</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" name="website" id="website"
                                                   value="${company.website}">
                                                    <span class="input-group-addon info"><span
                                                            class="glyphicon glyphicon-asterisk"></span></span>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="country">${country}</label>
                                        <div class="input-group" data-validate="name">
                                            <input type="text" class="form-control" name="country" id="country"
                                                   value="${company.country}" required>
                                                    <span class="input-group-addon danger masterTooltip"
                                                          title="${validation_name}">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                    </span>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="city">${city}</label>
                                        <div class="input-group" data-validate="name">
                                            <input type="text" class="form-control" name="city" id="city"
                                                   value="${company.city}" required>
                                                    <span class="input-group-addon danger masterTooltip"
                                                          title="${validation_name}">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                    </span>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-12">
                                        <label for="companyDescription">${company_description}</label>
                                        <div class="input-group">
                            <textarea class="form-control input-sm" name="companyDescription" id="companyDescription"
                                      rows="9">${company.companyDescription}</textarea>
                                                    <span class="input-group-addon info"><span
                                                            class="glyphicon glyphicon-asterisk"></span></span>
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
                        <div id="profile-details"
                             class="tab-pane fade">
                            <form role="form" action="<c:url value="/controller"/>"
                                  method="post">

                                <input type="hidden" name="command" value="edit"/>
                                <input type="hidden" name="type"
                                       value="employee_profile"/>
                                <input type="hidden" name="id" value="${employee.id}"/>
                                <input type="hidden" name="status" value="ADMIN"/>

                                <fieldset>
                                    <legend>${profile_details}</legend>

                                    <div class="form-group col-md-6">
                                        <label for="fullName">${full_name}</label>
                                        <div class="input-group" data-validate="name">
                                            <input type="text" class="form-control"
                                                   name="fullName" id="fullName"
                                                   value="${employee.fullName}" required>
                                                    <span class="input-group-addon danger masterTooltip"
                                                          title="${validation_name}">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                    </span>
                                        </div>
                                    </div>


                                    <div class="form-group col-md-6">
                                        <label for="email">${email}</label>
                                        <div class="input-group" data-validate="email">
                                            <input type="email" class="form-control"
                                                   name="email" id="email"
                                                   value="${employee.email}" required>
                                                    <span class="input-group-addon danger masterTooltip"
                                                          title="${validation_email}">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                    </span>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="phone">${phone}</label>
                                        <div class="input-group" data-validate="phone">
                                            <input type="text" class="form-control"
                                                   name="phone" id="phone"
                                                   value="${employee.phone}" required>
                                                    <span class="input-group-addon danger masterTooltip"
                                                          title="${validation_phone}">
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
                                        <button type="submit" class="btn btn-primary">
                                                ${edit}
                                        </button>
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div id="password-details"
                             class="tab-pane fade">
                            <form role="form" action="<c:url value="/controller"/>"
                                  method="post">

                                <input type="hidden" name="command" value="edit"/>
                                <input type="hidden" name="type"
                                       value="employee_password"/>
                                <input type="hidden" name="id" value="${employee.id}"/>
                                <input type="hidden" name="status" value="ADMIN"/>

                                <fieldset>

                                    <legend>${password}</legend>

                                    <div class="form-group col-md-6">
                                        <label for="password">${password}</label>
                                        <div class="input-group" data-validate="password">
                                            <input type="password" class="form-control"
                                                   name="password" id="password"
                                                   placeholder="${password}" required>
                                                    <span class="input-group-addon danger masterTooltip"
                                                          title="${validation_password}">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                    </span>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="confirmPassword">${confirm_password}</label>
                                        <div class="input-group" data-validate="confirmPassword">
                                            <input type="password" class="form-control"
                                                   name="confirmPassword"
                                                   id="confirmPassword"
                                                   placeholder="${confirm_password}" required>
                                                    <span class="input-group-addon danger masterTooltip"
                                                          title="${validation_confirm_password}">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                    </span>
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
                </div>
            </c:if>
        </div>
    </div>
</div>