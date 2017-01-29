package com.epam.jobmatch.service;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Map;

public interface InfoListService {

    /**
     * Return search result
     *
     * @param vacancyFilter object, that contains search parameters
     *
     * @return map of vacancies and companies
     *
     * @throws ServiceException
     */
    Map<Vacancy, Company> returnSearchResult(VacancyFilter vacancyFilter) throws ServiceException;

    /**
     * Return full list of employees in company
     *
     * @param employeeFilter object, that contains search parameters
     *
     * @return list of employees
     *
     * @throws ServiceException
     */
    ArrayList<Employee> returnEmployeeList(EmployeeFilter employeeFilter) throws ServiceException;
    /**
     * Return full list of vacancies in company
     *
     * @param idCompany company's id
     * @param page page with information to get
     * @param vacancyName vacancy's name for search
     *
     * @return list of vacancies
     *
     * @throws ServiceException
     */
    ArrayList<Vacancy> returnVacancyListById(int idCompany, String page, String vacancyName) throws ServiceException;

    /**
     * Return list of responds for selected vacancy
     *
     * @param respondFilter object, that contains search parameters
     *
     * @return map of responds and applicants
     *
     * @throws ServiceException
     */
    Map<Respond, Applicant> returnRespondListForVacancy(RespondFilter respondFilter) throws ServiceException;
    /**
     * Return list of responds for applicant
     *
     * @param idApplicant applicant's id
     * @param page page with information to get
     *
     * @return map of responds and vacancies
     *
     * @throws ServiceException
     */
    Map<Respond, Vacancy> returnRespondListForApplicant(int idApplicant, String page) throws ServiceException;

    /**
     * Return list of latest vacancies
     *
     * @return map of vacancies and companies
     *
     * @throws ServiceException
     */
    Map<Vacancy,Company> getLatestVacancyList() throws ServiceException;
    /**
     * Return list companies with the biggest count of responds
     *
     * @return list of companies
     *
     * @throws ServiceException
     */
    ArrayList<Company> getSearchingCompanyList() throws ServiceException;
}
