package com.epam.jobmatch.dao;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.dao.exception.DAOException;

import java.util.ArrayList;

public interface InfoDAO {

    /**
     * Return company's information
     *
     * @param idCompany company's id
     *
     * @return company object
     *
     * @throws DAOException
     */
    Company companyInfo(String idCompany) throws DAOException;
    /**
     * Return vacancy's information
     *
     * @param idVacancy vacancy's id
     *
     * @return vacancy object
     *
     * @throws DAOException
     */
    Vacancy vacancyInfo(String idVacancy) throws DAOException;
    /**
     * Return applicant's information
     *
     * @param idApplicant applicant's id
     *
     * @return applicant object
     *
     * @throws DAOException
     */
    Applicant applicantInfo(String idApplicant) throws DAOException;
    /**
     * Return respond's information
     *
     * @param idApplicant applicant's id
     * @param idVacancy vacancy's id
     *
     * @return respond object
     *
     * @throws DAOException
     */
    Respond respondInfo(String idApplicant, String idVacancy) throws DAOException;

    /**
     * Return statistics
     *
     * @return list of statistic information
     *
     * @throws DAOException
     */
    ArrayList<Integer> statisticsInfo() throws DAOException;

    /**
     * Return count of pages with search list
     *
     * @param vacancyFilter object, that contains search parameters
     *
     * @return count of pages
     *
     * @throws DAOException
     */
    int getSearchListPageCount(VacancyFilter vacancyFilter) throws DAOException;
    /**
     * Return count of pages with employee list
     *
     * @param employeeFilter object, that contains search parameters
     *
     * @return count of pages
     *
     * @throws DAOException
     */
    int getEmployeeListPageCount(EmployeeFilter employeeFilter) throws DAOException;
    /**
     * Return count of pages with full list of vacancies in company
     *
     * @param idCompany company's id
     * @param vacancyName vacancy's name for search
     *
     * @return count of pages
     *
     * @throws DAOException
     */
    int getVacancyListByIdPageCount(int idCompany, String vacancyName) throws DAOException;
    /**
     * Return count of pages with list of responds for selected vacancy
     *
     * @param respondFilter object, that contains search parameters
     *
     * @return count of pages
     *
     * @throws DAOException
     */
    int getRespondListForVacancyPageCount(RespondFilter respondFilter) throws DAOException;
    /**
     * Return count of pages with list of responds for applicant
     *
     * @param idApplicant applicant's id
     *
     * @return count of pages
     *
     * @throws DAOException
     */
    int getRespondListForApplicantPageCount(int idApplicant) throws DAOException;


}
