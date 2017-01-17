package com.epam.jobmatch.command.impl.sign_up_command.type_impl;

import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.service.SignUpService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RespondSignUpType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;
        String requestPage = request.getHeader(Parameter.REFERER);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SignUpService signUpService = serviceFactory.getSignUpService();

        int userId = ((Applicant) request.getSession().getAttribute(Attribute.APPLICANT)).getId();
        int vacancyId = Integer.parseInt(request.getParameter(Parameter.VACANCY_ID));

        try {
            signUpService.respondRegistration(userId, vacancyId);

            pageToRedirect = requestPage.substring(requestPage.indexOf(Parameter.PROJECT_URI) + Parameter.PROJECT_URI.length());

        } catch (ValidationException e) {
            pageToRedirect = requestPage.substring(requestPage.indexOf(Parameter.PROJECT_URI) + Parameter.PROJECT_URI.length()) +
                    Parameter.SEPARATOR + Attribute.FAIL + e.getMessage();
        }
        return pageToRedirect;
    }
}
