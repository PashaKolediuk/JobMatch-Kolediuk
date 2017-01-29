package com.epam.jobmatch.command.impl.edit_command.type_impl;

import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.bean.entity.user.enumiration.Status;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.command.util.Request;
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

        Status editorStatus = ((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getStatus();
        Status statusForEditing = Status.valueOf(request.getParameter(Parameter.STATUS));

        try {
            editService.employeePasswordEditing(request.getParameter(Parameter.ID), password, confirmPassword);

            pageToRedirect = getSuccessRequest(editorStatus, statusForEditing, idCompany);

        } catch (ValidationException e) {

            pageToRedirect = getFailRequest(editorStatus, statusForEditing, idCompany, e.getMessage());

        }
        return pageToRedirect;
    }

    private String getSuccessRequest(Status editorStatus, Status statusForEditing, int idCompany) {
        String pageToRedirect = null;
        switch (editorStatus) {
            case HR:
                pageToRedirect = Request.GET_VACANCY_LIST_BY_ID;
                break;
            case ADMIN:
                if (statusForEditing == Status.ADMIN) {
                    pageToRedirect = Request.GET_COMPANY_INFO_BY_ID + idCompany;
                } else {
                    pageToRedirect = Request.GET_EMPLOYEE_LIST;
                }
                break;
        }
        return pageToRedirect;
    }

    private String getFailRequest(Status editorStatus, Status statusForEditing, int idCompany, String message) {
        String pageToRedirect = null;
        switch (editorStatus) {
            case HR:
                pageToRedirect = Page.EMPLOYEE_EDITING + Attribute.FAIL + message;
                break;
            case ADMIN:
                if (statusForEditing == Status.ADMIN) {
                    pageToRedirect = Request.GET_COMPANY_INFO_BY_ID + idCompany + Parameter.SEPARATOR +
                            Attribute.FAIL + message;
                } else {
                    pageToRedirect = Request.GET_EMPLOYEE_LIST + Parameter.SEPARATOR + Attribute.FAIL + message;
                }
                break;
        }
        return pageToRedirect;
    }

}
