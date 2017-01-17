package com.epam.jobmatch.dao;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;

public interface SignUpDAO {

	Employee companyRegistration(Company company) throws DAOException, MatchingException;
	Applicant applicantRegistration(Applicant applicant) throws DAOException, MatchingException;
	void employeeRegistration(Employee employee) throws DAOException, MatchingException;
	void vacancyRegistration(Vacancy vacancy) throws DAOException;
	void respondRegistration(int idApplicant, int idVacancy) throws DAOException, MatchingException;

}

