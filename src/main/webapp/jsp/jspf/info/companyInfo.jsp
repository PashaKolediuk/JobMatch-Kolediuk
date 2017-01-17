<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:if test="${param.fail != null}">
    <div>
        <div class="msg msg-warning msg-danger-text">
            <span class="glyphicon glyphicon-exclamation-sign"></span>
                ${failMessage}</div>
    </div>
</c:if>


<div class="container">
    <div class="row">

        <div class="company-info  col-md-10 col-md-offset-1">
            <c:set var="company" value="${requestScope.companyInfo}" scope="page"/>
            <c:set var="admin" value="${company.admin}" scope="page"/>

            <c:if test="${employee.status ne 'ADMIN'}">

                ${company_information}

                <br>

                <form action="<c:url value="/controller"/>" method="get">
                    <input type="hidden" name="command" value="get_list"/>
                    <input type="hidden" name="type" value="search"/>
                    <input type="hidden" name="page" value="1"/>
                    <input type="hidden" name="sort" value="date"/>
                    <input type="hidden" name="experienceFilter" value="0,100"/>
                    <input type="hidden" name="salaryFilter" value="0"/>
                    <input type="hidden" name="countryFilter" value=""/>
                    <input type="hidden" name="cityFilter" value=""/>
                    <input type="hidden" name="search" value="${company.companyName}">
                    <input type="submit" value="СПИСОК ВАКАНСИЙ КОМПАНИИ (EN)">
                </form>



                <table>
                    <tr>
                        <td class="vertical-section" rowspan="10">
                            <a href="<c:url value="/controller?command=get_info&type=company&idCompany=${company.idCompany}"/>">
                                <object data="${pageContext.request.contextPath}/upload/${company.idCompany}.png"
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
                                ${company.companyName}
                        </td>
                    </tr>
                    <tr>
                        <td>
                                ${company.country}, ${company.city}
                        </td>
                    </tr>
                    <tr>
                        <td>
                                ${company.website}
                        </td>
                    </tr>
                    <tr>
                        <td>
                                ${company.companyDescription}
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
                    <tr>
                        <td>
                                ${admin.fullName}
                        </td>
                    </tr>
                    <tr>
                        <td>
                                ${admin.email}
                        </td>
                    </tr>
                    <tr>
                        <td>
                                ${admin.phone}
                        </td>
                    </tr>
                    <tr>
                        <td>
                                ${admin.skype}
                        </td>
                    </tr>
                </table>

            </c:if>
            <c:if test="${employee.status eq 'ADMIN'}">
                <table>
                    <tr>
                        <td class="vertical-section">
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
                                <input type="file" name="file" size="50"/>
                                <br/>
                                <input type="submit" value="${change_logo}"/>
                            </form>
                        </td>
                        <td class="vertical-section">
                            <ul class="nav nav-tabs">
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
                                                <input type="text" class="form-control" name="companyName" id="companyName"
                                                       value="${company.companyName}">
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="website">${website}</label>
                                                <input type="text" class="form-control" name="website" id="website"
                                                       value="${company.website}">
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="country">${country}</label>
                                                <input type="text" class="form-control" name="country" id="country"
                                                       value="${company.country}">
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="city">${city}</label>
                                                <input type="text" class="form-control" name="city" id="city"
                                                       value="${company.city}">
                                            </div>

                                            <div class="form-group col-md-12">
                                                <label for="companyDescription">${company_description}</label>
                            <textarea class="form-control input-sm" name="companyDescription" id="companyDescription"
                                      rows="3">${company.companyDescription}</textarea>
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
                                                <input type="text" class="form-control"
                                                       name="fullName" id="fullName"
                                                       value="${employee.fullName}">
                                            </div>


                                            <div class="form-group col-md-6">
                                                <label for="email">${email}</label>
                                                <input type="email" class="form-control"
                                                       name="email" id="email"
                                                       value="${employee.email}">
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="phone">${phone}</label>
                                                <input type="text" class="form-control"
                                                       name="phone" id="phone"
                                                       value="${employee.phone}">
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="skype">Skype</label>
                                                <input type="text" class="form-control"
                                                       name="skype" id="skype"
                                                       value="${employee.skype}">
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
                                                <input type="password" class="form-control"
                                                       name="password" id="password"
                                                       placeholder="${password}">
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="confirmPassword">${confirm_password}</label>
                                                <input type="password" class="form-control"
                                                       name="confirmPassword"
                                                       id="confirmPassword"
                                                       placeholder="${confirm_password}">
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
                </table>



            </c:if>
        </div>
    </div>
</div>