package com.epam.jobmatch.command.impl.edit_command.type_impl;

import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Employee;
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
import java.sql.Date;
import java.util.Calendar;

public class VacancyInfoEditingType implements Type {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EditService editService = serviceFactory.getEditService();

        Vacancy newVacancy = getVacancyFromRequest(request);

        try {
            editService.vacancyEditing(newVacancy);

            pageToRedirect = Request.GET_VACANCY_LIST_BY_ID;

        } catch (ValidationException e) {
            pageToRedirect = Request.GET_VACANCY_LIST_BY_ID + Parameter.SEPARATOR + Attribute.FAIL + e.getMessage();
        }
        return pageToRedirect;
    }

    private Vacancy getVacancyFromRequest(HttpServletRequest request) {
        Vacancy vacancy = new Vacancy();
        vacancy.setIdVacancy(Integer.parseInt(request.getParameter(Parameter.VACANCY_ID)));
        vacancy.setIdCompany(((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getIdCompany());
        vacancy.setSalary(Integer.parseInt(request.getParameter(Parameter.SALARY)));
        vacancy.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        vacancy.setName(request.getParameter(Parameter.VACANCY_NAME));
        vacancy.setRequiredExperience(Integer.parseInt(request.getParameter(Parameter.REQUIRED_EXPERIENCE)));
        vacancy.setRequiredSkills(request.getParameter(Parameter.REQUIRED_SKILLS));
        vacancy.setVacancyDescription(request.getParameter(Parameter.VACANCY_DESCRIPTION));
        return vacancy;
    }

}
