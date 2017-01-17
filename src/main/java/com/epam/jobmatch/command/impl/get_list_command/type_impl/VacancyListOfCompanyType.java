package com.epam.jobmatch.command.impl.get_list_command.type_impl;

import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Employee;
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
import java.util.ArrayList;

public class VacancyListOfCompanyType implements Type {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InfoListService infoListService = serviceFactory.getInfoListService();

        int idCompany = ((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getIdCompany();
        String page = request.getParameter(Parameter.PAGE);
        String vacancyName = request.getParameter(Parameter.VACANCY_NAME);

        ArrayList<Vacancy> vacancyList = infoListService.returnVacancyListById(idCompany, page, vacancyName);
        request.setAttribute(Attribute.VACANCY_LIST_BY_ID, vacancyList);

        InfoService infoService = serviceFactory.getInfoService();
        int pageCount = infoService.getVacancyListByIdPageCount(idCompany, vacancyName);
        request.setAttribute(Attribute.PAGE_COUNT, pageCount);

        return Page.MAIN;
    }
}
