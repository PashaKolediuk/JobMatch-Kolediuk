package com.epam.jobmatch.command.impl.get_list_command.type_impl;

import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.service.InfoListService;
import com.epam.jobmatch.service.InfoService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RespondListOfApplicantType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InfoListService infoListService = serviceFactory.getInfoListService();

        int idApplicant = ((Applicant)request.getSession().getAttribute(Attribute.APPLICANT)).getId();
        String page = request.getParameter(Parameter.PAGE);

        Map<Respond, Vacancy> respondList = infoListService.returnRespondListForApplicant(idApplicant, page);
        request.setAttribute(Attribute.RESPOND_LIST_OF_APPLICANT, respondList);

        InfoService infoService = serviceFactory.getInfoService();
        int pageCount = infoService.getRespondListForApplicantPageCount(idApplicant);
        request.setAttribute(Attribute.PAGE_COUNT, pageCount);

        return Page.MAIN;
    }
}
