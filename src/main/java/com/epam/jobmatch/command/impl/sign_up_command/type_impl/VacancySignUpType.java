package com.epam.jobmatch.command.impl.sign_up_command.type_impl;

import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.command.util.Request;
import com.epam.jobmatch.service.SignUpService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

public class VacancySignUpType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SignUpService signUpService = serviceFactory.getSignUpService();

        Vacancy vacancy = getVacancyFromRequest(request);
        try {

            signUpService.vacancyRegistration(vacancy);

            pageToRedirect = Request.GET_VACANCY_LIST_BY_ID;

        } catch (ValidationException e) {
            pageToRedirect = Page.VACANCY_REGISTRATION + Attribute.FAIL + e.getMessage();
        }
        return pageToRedirect;
    }

    private Vacancy getVacancyFromRequest(HttpServletRequest request) {
        Vacancy vacancy = new Vacancy();
        vacancy.setIdCompany(((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getIdCompany());
        vacancy.setName(request.getParameter(Parameter.VACANCY_NAME));
        vacancy.setSalary(Integer.parseInt(request.getParameter(Parameter.SALARY).isEmpty() ?
                Parameter.DEFAULT_NUM : request.getParameter(Parameter.SALARY)));
        vacancy.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        vacancy.setRequiredExperience(Integer.parseInt(request.getParameter(Parameter.REQUIRED_EXPERIENCE).isEmpty() ?
                Parameter.DEFAULT_NUM : request.getParameter(Parameter.REQUIRED_EXPERIENCE)));
        vacancy.setRequiredSkills(request.getParameter(Parameter.REQUIRED_SKILLS));
        vacancy.setVacancyDescription(request.getParameter(Parameter.VACANCY_DESCRIPTION));
        return vacancy;
    }
}
