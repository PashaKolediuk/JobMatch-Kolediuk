package com.epam.jobmatch.command.impl.delete_command.type_impl;

import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.command.util.Request;
import com.epam.jobmatch.command.util.TypeEnum;
import com.epam.jobmatch.controller.util.CommandEnum;
import com.epam.jobmatch.service.DeleteService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VacancyDeleteType implements Type {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DeleteService deleteService = serviceFactory.getDeleteService();

        deleteService.vacancyDeleting(request.getParameter(Parameter.VACANCY_ID));
        return Request.GET_VACANCY_LIST_BY_ID;
    }

}
