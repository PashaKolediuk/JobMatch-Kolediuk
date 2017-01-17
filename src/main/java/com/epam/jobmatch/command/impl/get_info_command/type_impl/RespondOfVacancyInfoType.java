package com.epam.jobmatch.command.impl.get_info_command.type_impl;

import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.service.InfoService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RespondOfVacancyInfoType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InfoService infoService = serviceFactory.getInfoService();

        String idApplicant = request.getParameter(Parameter.APPLICANT_ID);
        String idVacancy = request.getParameter(Parameter.VACANCY_ID);

        Applicant applicant = infoService.applicantInfo(idApplicant);
        request.setAttribute(Attribute.APPLICANT_INFO, applicant);

        Respond respond = infoService.respondInfo(idApplicant, idVacancy);
        request.setAttribute(Attribute.RESPOND_INFO, respond);

        return Page.MAIN;
    }
}
