package com.epam.jobmatch.command.impl.delete_command.type_impl;

import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.*;
import com.epam.jobmatch.controller.util.CommandEnum;
import com.epam.jobmatch.service.DeleteService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RespondOfApplicantDeleteType implements Type {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DeleteService deleteService = serviceFactory.getDeleteService();

        int idApplicant = ((Applicant)request.getSession().getAttribute(Attribute.APPLICANT)).getId();
        String idVacancy = request.getParameter(Parameter.VACANCY_ID);

        deleteService.respondForApplicantDeleting(idApplicant, idVacancy);
        return Request.GET_RESPOND_LIST_OF_APPLICANT;
    }

}
