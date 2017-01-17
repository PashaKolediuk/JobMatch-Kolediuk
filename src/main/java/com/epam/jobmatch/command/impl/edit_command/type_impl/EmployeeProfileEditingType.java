package com.epam.jobmatch.command.impl.edit_command.type_impl;

import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.bean.entity.user.enumiration.Status;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.*;
import com.epam.jobmatch.controller.util.CommandEnum;
import com.epam.jobmatch.service.EditService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeProfileEditingType implements Type {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EditService editService = serviceFactory.getEditService();

        Employee employee = getEmployeeFromRequest(request);
        try {
            editService.employeeProfileEditing(employee);

            switch (((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getStatus()) {
                case HR:
                    request.getSession().setAttribute(Attribute.EMPLOYEE, employee);
                    pageToRedirect = Request.GET_VACANCY_LIST_BY_ID;
                    break;
                case ADMIN:
                    if (employee.getStatus() == Status.ADMIN) {
                        request.getSession().setAttribute(Attribute.EMPLOYEE, employee);
                        pageToRedirect = Request.GET_COMPANY_INFO_BY_ID + employee.getIdCompany();
                    } else {
                        pageToRedirect = Request.GET_EMPLOYEE_LIST;
                    }
                    break;
            }

        } catch (ValidationException e) {

            switch (((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getStatus()) {
                case HR:
                    pageToRedirect = Page.EMPLOYEE_EDITING + Attribute.FAIL + e.getMessage();
                    break;
                case ADMIN:
                    if (employee.getStatus() == Status.ADMIN) {
                        pageToRedirect = Request.GET_COMPANY_INFO_BY_ID + employee.getIdCompany() + Parameter.SEPARATOR +
                                Attribute.FAIL + e.getMessage();
                    } else {
                        pageToRedirect = Request.GET_EMPLOYEE_LIST + Parameter.SEPARATOR + Attribute.FAIL + e.getMessage();
                    }
                    break;
            }

        }
        return pageToRedirect;
    }

    private Employee getEmployeeFromRequest(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(request.getParameter(Parameter.ID)));
        employee.setIdCompany(((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getIdCompany());
        employee.setEmail(request.getParameter(Parameter.EMAIL));
        employee.setFullName(request.getParameter(Parameter.FULL_NAME));
        employee.setPhone(request.getParameter(Parameter.PHONE));
        employee.setSkype(request.getParameter(Parameter.SKYPE));
        employee.setStatus(Status.valueOf(request.getParameter(Parameter.STATUS)));
        return employee;
    }

}
