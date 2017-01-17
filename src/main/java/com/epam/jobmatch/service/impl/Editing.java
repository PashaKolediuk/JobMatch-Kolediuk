package com.epam.jobmatch.service.impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.dao.EditingDAO;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;
import com.epam.jobmatch.dao.factory.DAOFactory;
import com.epam.jobmatch.service.EditService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.util.ValidationHandler;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.epam.jobmatch.service.util.Encryption.encryptedValue;

public class Editing implements EditService {

    @Override
    public void companyInfoEditing(Company company) throws ServiceException, ValidationException {
        ValidationHandler.companyValidation(company);

        DAOFactory factory = DAOFactory.getInstance();
        EditingDAO editingDAO = factory.getEditingDAO();
        try {
            editingDAO.editCompanyInfo(company);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (MatchingException e) {
            throw new ValidationException(e.getMessage(), e);
        }
    }

    @Override
    public void respondInfoEditing(Respond newRespond) throws ServiceException, ValidationException {
        ValidationHandler.respondValidation(newRespond);

        DAOFactory factory = DAOFactory.getInstance();
        EditingDAO editingDAO = factory.getEditingDAO();
        try {
            editingDAO.editRespondInfo(newRespond);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void applicantProfileEditing(Applicant applicant) throws ServiceException, ValidationException {
        ValidationHandler.applicantProfileValidation(applicant);

        DAOFactory factory = DAOFactory.getInstance();
        EditingDAO editingDAO = factory.getEditingDAO();
        try {
            editingDAO.editApplicantProfileInfo(applicant);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (MatchingException e) {
            throw new ValidationException(e.getMessage(), e);
        }
    }

    @Override
    public void applicantPasswordEditing(int idApplicant, char[] password, char[] confirmPassword) throws ServiceException, ValidationException {
        ValidationHandler.passwordValidation(password, confirmPassword);
        try {
            ValidationHandler.intValidation(idApplicant);

            DAOFactory factory = DAOFactory.getInstance();
            EditingDAO editingDAO = factory.getEditingDAO();

            char[] encryptedPassword = encryptedValue(password);
            Arrays.fill(confirmPassword, '\u0000');

            editingDAO.editApplicantPassword(idApplicant, encryptedPassword);

            Arrays.fill(password, '\u0000');
        } catch (DAOException | NoSuchAlgorithmException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void employeeProfileEditing(Employee employee) throws ServiceException, ValidationException {
        ValidationHandler.employeeProfileValidation(employee);

        DAOFactory factory = DAOFactory.getInstance();
        EditingDAO editingDAO = factory.getEditingDAO();
        try {
            editingDAO.editEmployeeProfileInfo(employee);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (MatchingException e) {
            throw new ValidationException(e.getMessage(), e);
        }
    }

    @Override
    public void employeePasswordEditing(String idEmployee, char[] password, char[] confirmPassword) throws ServiceException, ValidationException {
        ValidationHandler.passwordValidation(password, confirmPassword);
        try {
            ValidationHandler.stringValidation(idEmployee);

            DAOFactory factory = DAOFactory.getInstance();
            EditingDAO editingDAO = factory.getEditingDAO();

            char[] encryptedPassword = encryptedValue(password);
            Arrays.fill(confirmPassword, '\u0000');

            editingDAO.editEmployeePassword(idEmployee, encryptedPassword);

            Arrays.fill(password, '\u0000');
        } catch (DAOException | NoSuchAlgorithmException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void vacancyEditing(Vacancy newVacancy) throws ServiceException, ValidationException {
        ValidationHandler.vacancyValidation(newVacancy);

        DAOFactory factory = DAOFactory.getInstance();
        EditingDAO editingDAO = factory.getEditingDAO();
        try {
            editingDAO.editVacancyInfo(newVacancy);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void imageEditing(FileItem item, String path) throws ServiceException {
        if (path == null || item == null) {
            throw new ServiceException("Invalidate image editing parameters");
        }

        File fileToUpload = new File(path);
        if (fileToUpload.exists()) {
            fileToUpload.delete();
        }
        try {
            fileToUpload.createNewFile();
            item.write(fileToUpload);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
