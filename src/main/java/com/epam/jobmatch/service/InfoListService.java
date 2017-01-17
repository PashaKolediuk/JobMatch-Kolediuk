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

    Map<Vacancy, Company> returnSearchResult(VacancyFilter vacancyFilter) throws ServiceException;

    ArrayList<Employee> returnEmployeeList(EmployeeFilter employeeFilter) throws ServiceException;
    ArrayList<Vacancy> returnVacancyListById(int idCompany, String page, String vacancyName) throws ServiceException;

    Map<Respond, Applicant> returnRespondListForVacancy(RespondFilter respondFilter) throws ServiceException;
    Map<Respond, Vacancy> returnRespondListForApplicant(int idApplicant, String page) throws ServiceException;

    Map<Vacancy,Company> getLatestVacancyList() throws ServiceException;
    ArrayList<Company> getSearchingCompanyList() throws ServiceException;
}
