package com.epam.jobmatch.service;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.service.exception.ServiceException;

import java.util.ArrayList;

public interface InfoService {

    /**
     * Return company's information
     *
     * @param idCompany company's id
     *
     * @return company object
     *
     * @throws ServiceException
     */
    Company companyInfo(String idCompany) throws ServiceException;
    /**
     * Return vacancy's information
     *
     * @param idVacancy vacancy's id
     *
     * @return vacancy object
     *
     * @throws ServiceException
     */
    Vacancy vacancyInfo(String idVacancy) throws ServiceException;
    /**
     * Return applicant's information
     *
     * @param idApplicant applicant's id
     *
     * @return applicant object
     *
     * @throws ServiceException
     */
    Applicant applicantInfo(String idApplicant) throws ServiceException;
    /**
     * Return respond's information
     *
     * @param idApplicant applicant's id
     * @param idVacancy vacancy's id
     *
     * @return respond object
     *
     * @throws ServiceException
     */
    Respond respondInfo(String idApplicant, String idVacancy) throws ServiceException;

    /**
     * Return statistics
     *
     * @return list of statistic information
     *
     * @throws ServiceException
     */
    ArrayList<Integer> statisticsInfo() throws ServiceException;

    /**
     * Return count of pages with search list
     *
     * @param vacancyFilter object, that contains search parameters
     *
     * @return count of pages
     *
     * @throws ServiceException
     */
    int getSearchListPageCount(VacancyFilter vacancyFilter) throws ServiceException;
    /**
     * Return count of pages with employee list
     *
     * @param employeeFilter object, that contains search parameters
     *
     * @return count of pages
     *
     * @throws ServiceException
     */
    int getEmployeeListPageCount(EmployeeFilter employeeFilter) throws ServiceException;
    /**
     * Return count of pages with full list of vacancies in company
     *
     * @param idCompany company's id
     * @param vacancyName vacancy's name for search
     *
     * @return count of pages
     *
     * @throws ServiceException
     */
    int getVacancyListByIdPageCount(int idCompany, String vacancyName) throws ServiceException;
    /**
     * Return count of pages with list of responds for selected vacancy
     *
     * @param respondFilter object, that contains search parameters
     *
     * @return count of pages
     *
     * @throws ServiceException
     */
    int getRespondListForVacancyPageCount(RespondFilter respondFilter) throws ServiceException;
    /**
     * Return count of pages with list of responds for applicant
     *
     * @param idApplicant applicant's id
     *
     * @return count of pages
     *
     * @throws ServiceException
     */
    int getRespondListForApplicantPageCount(int idApplicant) throws ServiceException;

}
