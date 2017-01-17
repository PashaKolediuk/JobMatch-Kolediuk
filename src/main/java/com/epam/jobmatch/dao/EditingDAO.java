package com.epam.jobmatch.dao;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;

public interface EditingDAO {

    void editCompanyInfo(Company company) throws DAOException, MatchingException;
    void editRespondInfo(Respond newRespond) throws DAOException;

    void editApplicantProfileInfo(Applicant applicant) throws DAOException, MatchingException;
    void editApplicantPassword(int idApplicant, char[] encryptedPassword) throws DAOException;

    void editEmployeeProfileInfo(Employee employee) throws DAOException, MatchingException;
    void editEmployeePassword(String idEmployee, char[] encryptedPassword) throws DAOException;

    void editVacancyInfo(Vacancy newVacancy) throws DAOException;

}
