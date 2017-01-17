package com.epam.jobmatch.command.impl.edit_command.type_impl;

import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.service.EditService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplicantPasswordEditingType implements Type {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EditService editService = serviceFactory.getEditService();

        char[] password = request.getParameter(Parameter.PASSWORD).toCharArray();
        char[] confirmPassword = request.getParameter(Parameter.CONFIRM_PASSWORD).toCharArray();

        try {
            editService.applicantPasswordEditing(((Applicant) request.getSession().getAttribute(Attribute.APPLICANT)).getId(), password, confirmPassword);
            pageToRedirect = Page.APPLICANT_EDITING;
        } catch (ValidationException e) {
            pageToRedirect = Page.APPLICANT_EDITING + Attribute.FAIL + e.getMessage();
        }
        return pageToRedirect;
    }
}
