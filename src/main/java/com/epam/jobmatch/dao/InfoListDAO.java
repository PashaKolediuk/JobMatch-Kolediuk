package com.epam.jobmatch.dao;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.Map;

public interface InfoListDAO {

    /**
     * Return search result
     *
     * @param vacancyFilter object, that contains search parameters
     *
     * @return map of vacancies and companies
     *
     * @throws DAOException
     */
    Map<Vacancy, Company> searchResult(VacancyFilter vacancyFilter) throws DAOException;

    /**
     * Return full list of employees in company
     *
     * @param employeeFilter object, that contains search parameters
     *
     * @return list of employees
     *
     * @throws DAOException
     */
    ArrayList<Employee> employeeList(EmployeeFilter employeeFilter) throws DAOException;
    /**
     * Return full list of vacancies in company
     *
     * @param idCompany company's id
     * @param page page with information to get
     * @param vacancyName vacancy's name for search
     *
     * @return list of vacancies
     *
     * @throws DAOException
     */
    ArrayList<Vacancy> vacancyListById(int idCompany, String page, String vacancyName) throws DAOException;

    /**
     * Return list of responds for selected vacancy
     *
     * @param respondFilter object, that contains search parameters
     *
     * @return map of responds and applicants
     *
     * @throws DAOException
     */
    Map<Respond, Applicant> respondListForVacancy(RespondFilter respondFilter) throws DAOException;
    /**
     * Return list of responds for applicant
     *
     * @param idApplicant applicant's id
     * @param page page with information to get
     *
     * @return map of responds and vacancies
     *
     * @throws DAOException
     */
    Map<Respond, Vacancy> respondListForApplicant(int idApplicant, String page) throws DAOException;

    /**
     * Return list of latest vacancies
     *
     * @return map of vacancies and companies
     *
     * @throws DAOException
     */
    Map<Vacancy,Company> latestVacancyList() throws DAOException;
    /**
     * Return list companies with the biggest count of responds
     *
     * @return list of companies
     *
     * @throws DAOException
     */
    ArrayList<Company> searchingCompanyList() throws DAOException;
}
