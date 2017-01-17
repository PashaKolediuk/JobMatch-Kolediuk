package com.epam.jobmatch.command.impl.get_list_command.type_impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
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

public class SearchListType implements Type {

    private static final String EXPERIENCE_SEPARATOR = ",";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InfoListService infoListService = serviceFactory.getInfoListService();

        VacancyFilter vacancyFilter = getVacancyFilterFromRequest(request);

        Map<Vacancy, Company> vacancyList = infoListService.returnSearchResult(vacancyFilter);
        request.setAttribute(Attribute.VACANCY_LIST, vacancyList);

        InfoService infoService = serviceFactory.getInfoService();
        int pageCount = infoService.getSearchListPageCount(vacancyFilter);
        request.setAttribute(Attribute.PAGE_COUNT, pageCount);

        return Page.MAIN;
    }

    private VacancyFilter getVacancyFilterFromRequest(HttpServletRequest request) {
        VacancyFilter vacancyFilter = new VacancyFilter();
        vacancyFilter.setSearchString(request.getParameter(Parameter.SEARCH));
        vacancyFilter.setPage(Integer.parseInt(request.getParameter(Parameter.PAGE)));
        vacancyFilter.setSort(request.getParameter(Parameter.SORT));
        String experienceFilter = request.getParameter(Parameter.EXPERIENCE_FILTER);
        String[] minMaxExperienceValue = experienceFilter.split(EXPERIENCE_SEPARATOR);
        vacancyFilter.setMinExperienceFilter(Integer.parseInt(minMaxExperienceValue[0]));
        vacancyFilter.setMaxExperienceFilter(Integer.parseInt(minMaxExperienceValue[1]));
        vacancyFilter.setSalaryFilter(Integer.parseInt(request.getParameter(Parameter.SALARY_FILTER).isEmpty() ?
                Parameter.DEFAULT_NUM : request.getParameter(Parameter.SALARY_FILTER)));
        vacancyFilter.setCountryFilter(request.getParameter(Parameter.COUNTRY_FILTER));
        vacancyFilter.setCityFilter(request.getParameter(Parameter.CITY_FILTER));
        return vacancyFilter;
    }
}
