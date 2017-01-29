package com.epam.jobmatch.dao;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;

public interface SignUpDAO {

	/**
	 * Handle company registration
	 *
	 * @param company entered company information
	 *
	 * @return employee object
	 *
	 * @throws DAOException
	 * @throws MatchingException
	 */
	Employee companyRegistration(Company company) throws DAOException, MatchingException;
	/**
	 * Handle applicant registration
	 *
	 * @param applicant entered applicant information
	 *
	 * @return applicant object
	 *
	 * @throws DAOException
	 * @throws MatchingException
	 */
	Applicant applicantRegistration(Applicant applicant) throws DAOException, MatchingException;
	/**
	 * Handle employee registration
	 *
	 * @param employee entered employee information
	 *
	 * @throws DAOException
	 * @throws MatchingException
	 */
	void employeeRegistration(Employee employee) throws DAOException, MatchingException;
	/**
	 * Handle vacancy registration
	 *
	 * @param vacancy entered vacancy information
	 *
	 * @throws DAOException
	 */
	void vacancyRegistration(Vacancy vacancy) throws DAOException;
	/**
	 * Handle respond registration
	 *
	 * @param idApplicant current applicant's id
	 * @param idVacancy selected vacancy's id
	 *
	 * @throws DAOException
	 * @throws MatchingException
	 */
	void respondRegistration(int idApplicant, int idVacancy) throws DAOException, MatchingException;

}

