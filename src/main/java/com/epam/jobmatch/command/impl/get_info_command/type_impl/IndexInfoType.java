package com.epam.jobmatch.command.impl.get_info_command.type_impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.command.util.TypeEnum;
import com.epam.jobmatch.service.InfoListService;
import com.epam.jobmatch.service.InfoService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class IndexInfoType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        String page = request.getParameter(Parameter.PAGE);

        InfoListService infoListService = serviceFactory.getInfoListService();
        Map<Vacancy, Company> vacancyList = infoListService.getLatestVacancyList();
        request.setAttribute(Attribute.LATEST_VACANCY_LIST, vacancyList);

        ArrayList<Company> companyList = infoListService.getSearchingCompanyList();
        request.setAttribute(Attribute.SEARCHING_COMPANY_LIST, companyList);

        InfoService infoService = serviceFactory.getInfoService();
        ArrayList<Integer> statistics = infoService.statisticsInfo();
        request.setAttribute(Attribute.STATISTICS, statistics);

        return page.equals(TypeEnum.INDEX.toString().toLowerCase()) ? Page.INDEX : Page.MAIN;
    }
}
