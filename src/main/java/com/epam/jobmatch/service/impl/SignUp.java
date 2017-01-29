package com.epam.jobmatch.service.impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.dao.SignUpDAO;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;
import com.epam.jobmatch.dao.factory.DAOFactory;
import com.epam.jobmatch.service.SignUpService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.util.ValidationHandler;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.epam.jobmatch.service.util.Encryption.encryptedValue;

public class SignUp implements SignUpService {

    @Override
    public Employee companyRegistration(Company company) throws ServiceException, ValidationException {
        ValidationHandler.companyValidation(company);
        ValidationHandler.employeeValidation(company.getAdmin());

        DAOFactory factory = DAOFactory.getInstance();
        SignUpDAO signUpDAO = factory.getSignUpDAO();

        Employee admin = null;
        try {
            char[] encryptedPassword = encryptedValue(company.getAdmin().getPassword());
            Arrays.fill(company.getAdmin().getConfirmPassword(), '\u0000');
            company.getAdmin().setPassword(encryptedPassword);

            admin = signUpDAO.companyRegistration(company);

            Arrays.fill(company.getAdmin().getPassword(), '\u0000');

        } catch (DAOException | NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        } catch (MatchingException e) {
            throw new ValidationException(e.getMessage(), e);
        }
        return admin;
    }

    @Override
    public void employeeRegistration(Employee employee) throws ServiceException, ValidationException {
        ValidationHandler.employeeValidation(employee);

        DAOFactory factory = DAOFactory.getInstance();
        SignUpDAO signUpDAO = factory.getSignUpDAO();
        try {
            char[] encryptedPassword = encryptedValue(employee.getPassword());
            Arrays.fill(employee.getConfirmPassword(), '\u0000');
            employee.setPassword(encryptedPassword);

            signUpDAO.employeeRegistration(employee);

            Arrays.fill(employee.getPassword(), '\u0000');

        } catch (DAOException | NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        } catch (MatchingException e) {
            throw new ValidationException(e.getMessage(), e);
        }
    }

    @Override
    public void vacancyRegistration(Vacancy vacancy) throws ServiceException, ValidationException {
        ValidationHandler.vacancyValidation(vacancy);

        DAOFactory factory = DAOFactory.getInstance();
        SignUpDAO signUpDAO = factory.getSignUpDAO();
        try {
            signUpDAO.vacancyRegistration(vacancy);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Applicant applicantRegistration(Applicant applicant) throws ServiceException, ValidationException {
        ValidationHandler.applicantValidation(applicant);

        DAOFactory factory = DAOFactory.getInstance();
        SignUpDAO signUpDAO = factory.getSignUpDAO();

        Applicant newApplicant = null;
        try {
            char[] encryptedPassword = encryptedValue(applicant.getPassword());
            Arrays.fill(applicant.getConfirmPassword(), '\u0000');
            applicant.setPassword(encryptedPassword);

            newApplicant = signUpDAO.applicantRegistration(applicant);
            Arrays.fill(applicant.getPassword(), '\u0000');

        } catch (DAOException | NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        } catch (MatchingException e) {
            throw new ValidationException(e.getMessage(), e);
        }
        return newApplicant;
    }

    @Override
    public void respondRegistration(int userId, int vacancyId) throws ServiceException, ValidationException {
        try {
            ValidationHandler.intValidation(userId);
            ValidationHandler.intValidation(vacancyId);

            DAOFactory factory = DAOFactory.getInstance();
            SignUpDAO signUpDAO = factory.getSignUpDAO();

            signUpDAO.respondRegistration(userId, vacancyId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (MatchingException e) {
            throw new ValidationException(e.getMessage(), e);
        }
    }
}
