package com.epam.jobmatch.command.impl.get_list_command.type_impl;

import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
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

public class RespondListOfVacancyType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InfoListService infoListService = serviceFactory.getInfoListService();

        RespondFilter respondFilter = getRespondFilterFromRequest(request);

        Map<Respond, Applicant> respondList = infoListService.returnRespondListForVacancy(respondFilter);
        request.setAttribute(Attribute.RESPOND_LIST_OF_VACANCY, respondList);

        InfoService infoService = serviceFactory.getInfoService();
        int pageCount = infoService.getRespondListForVacancyPageCount(respondFilter);
        request.setAttribute(Attribute.PAGE_COUNT, pageCount);

        return Page.MAIN;
    }

    private RespondFilter getRespondFilterFromRequest(HttpServletRequest request) {
        RespondFilter respondFilter = new RespondFilter();
        respondFilter.setIdVacancy(Integer.parseInt(request.getParameter(Parameter.VACANCY_ID)));
        respondFilter.setPage(Integer.parseInt(request.getParameter(Parameter.PAGE)));
        respondFilter.setLastName(request.getParameter(Parameter.LAST_NAME));
        respondFilter.setEmail(request.getParameter(Parameter.EMAIL));
        respondFilter.setPhone(request.getParameter(Parameter.PHONE));
        respondFilter.setStage(request.getParameter(Parameter.STAGE).toLowerCase());
        respondFilter.setMinMark(Integer.parseInt(request.getParameter(Parameter.MARK).isEmpty() ?
                Parameter.DEFAULT_NUM : request.getParameter(Parameter.MARK)));
        respondFilter.setSort(request.getParameter(Parameter.SORT));
        return respondFilter;
    }
}
