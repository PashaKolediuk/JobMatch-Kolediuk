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

    Map<Vacancy, Company> searchResult(VacancyFilter vacancyFilter) throws DAOException;

    ArrayList<Employee> employeeList(EmployeeFilter employeeFilter) throws DAOException;
    ArrayList<Vacancy> vacancyListById(int idCompany, String page, String vacancyName) throws DAOException;

    Map<Respond, Applicant> respondListForVacancy(RespondFilter respondFilter) throws DAOException;
    Map<Respond, Vacancy> respondListForApplicant(int idApplicant, String page) throws DAOException;

    Map<Vacancy,Company> latestVacancyList() throws DAOException;
    ArrayList<Company> searchingCompanyList() throws DAOException;
}
