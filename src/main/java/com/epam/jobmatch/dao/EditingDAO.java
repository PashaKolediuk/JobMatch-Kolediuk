package com.epam.jobmatch.dao;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;

public interface EditingDAO {

    /**
     * Edit company information
     *
     * @param company object, that contains new company information
     *
     * @throws DAOException
     * @throws MatchingException
     */
    void editCompanyInfo(Company company) throws DAOException, MatchingException;
    /**
     * Edit respond's information
     *
     * @param respond object, that contains new respond information
     *
     * @throws DAOException
     */
    void editRespondInfo(Respond respond) throws DAOException;

    /**
     * Edit applicant's profile information
     *
     * @param applicant object, that contains new applicant's profile information
     *
     * @throws DAOException
     * @throws MatchingException
     */
    void editApplicantProfileInfo(Applicant applicant) throws DAOException, MatchingException;
    /**
     * Edit applicant's password information
     *
     * @param idApplicant applicant's id
     * @param encryptedPassword new applicant's password (encrypted)
     *
     * @throws DAOException
     */
    void editApplicantPassword(int idApplicant, char[] encryptedPassword) throws DAOException;

    /**
     * Edit employee's profile information
     *
     * @param employee object, that contains new employee's profile information
     *
     * @throws DAOException
     * @throws MatchingException
     */
    void editEmployeeProfileInfo(Employee employee) throws DAOException, MatchingException;
    /**
     * Edit employee's password information
     *
     * @param idEmployee employee's id
     * @param encryptedPassword new applicant's password (encrypted)
     *
     * @throws DAOException
     */
    void editEmployeePassword(String idEmployee, char[] encryptedPassword) throws DAOException;

    /**
     * Edit vacancy information
     *
     * @param vacancy object, that contains new vacancy information
     *
     * @throws DAOException
     */
    void editVacancyInfo(Vacancy vacancy) throws DAOException;

}
