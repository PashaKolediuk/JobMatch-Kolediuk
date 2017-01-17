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

    Company companyInfo(String idCompany) throws DAOException;
    Vacancy vacancyInfo(String idVacancy) throws DAOException;
    Applicant applicantInfo(String idApplicant) throws DAOException;
    Respond respondInfo(String idApplicant, String idVacancy) throws DAOException;

    ArrayList<Integer> statisticsInfo() throws DAOException;

    int getSearchListPageCount(VacancyFilter vacancyFilter) throws DAOException;
    int getEmployeeListPageCount(EmployeeFilter employeeFilter) throws DAOException;
    int getVacancyListByIdPageCount(int idCompany, String vacancyName) throws DAOException;
    int getRespondListForVacancyPageCount(RespondFilter respondFilter) throws DAOException;
    int getRespondListForApplicantPageCount(int idApplicant) throws DAOException;


}
