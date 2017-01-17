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

public class EmployeePasswordEditingType implements Type {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EditService editService = serviceFactory.getEditService();

        char[] password = request.getParameter(Parameter.PASSWORD).toCharArray();
        char[] confirmPassword = request.getParameter(Parameter.CONFIRM_PASSWORD).toCharArray();
        int idCompany = ((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getIdCompany();
        try {
            editService.employeePasswordEditing(request.getParameter(Parameter.ID), password, confirmPassword);

            switch (((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getStatus()) {
                case HR:
                    pageToRedirect = Request.GET_VACANCY_LIST_BY_ID;
                    break;
                case ADMIN:
                    if (Status.valueOf(request.getParameter(Parameter.STATUS)) == Status.ADMIN) {
                        pageToRedirect = Request.GET_COMPANY_INFO_BY_ID + idCompany;
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
                    if (Status.valueOf(request.getParameter(Parameter.STATUS)) == Status.ADMIN) {
                        pageToRedirect = Request.GET_COMPANY_INFO_BY_ID + idCompany + Parameter.SEPARATOR +
                                Attribute.FAIL + e.getMessage();
                    } else {
                        pageToRedirect = Request.GET_EMPLOYEE_LIST + Parameter.SEPARATOR + Attribute.FAIL + e.getMessage();
                    }
                    break;
            }

        }
        return pageToRedirect;
    }

}
