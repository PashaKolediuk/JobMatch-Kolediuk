package com.epam.jobmatch.service;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import org.apache.commons.fileupload.FileItem;

public interface EditService {

    /**
     * Edit company information
     *
     * @param company object, that contains new company information
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void companyInfoEditing(Company company) throws ServiceException, ValidationException;
    /**
     * Edit respond's information
     *
     * @param respond object, that contains new respond information
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void respondInfoEditing(Respond respond) throws ServiceException, ValidationException;

    /**
     * Edit applicant's profile information
     *
     * @param applicant object, that contains new applicant's profile information
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void applicantProfileEditing(Applicant applicant) throws ServiceException, ValidationException;
    /**
     * Edit applicant's password information
     *
     * @param id applicant's id
     * @param password new applicant's password
     * @param confirmPassword applicant's confirmed password
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void applicantPasswordEditing(int id, char[] password, char[] confirmPassword) throws ServiceException, ValidationException;

    /**
     * Edit employee's profile information
     *
     * @param employee object, that contains new employee's profile information
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void employeeProfileEditing(Employee employee) throws ServiceException, ValidationException;
    /**
     * Edit employee's password information
     *
     * @param idEmployee employee's id
     * @param password new employee's password
     * @param confirmPassword employee's confirmed password
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void employeePasswordEditing(String idEmployee, char[] password, char[] confirmPassword) throws ServiceException, ValidationException;

    /**
     * Edit vacancy information
     *
     * @param vacancy object, that contains new vacancy information
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void vacancyEditing(Vacancy vacancy)  throws ServiceException, ValidationException;
    /**
     * Edit image
     *
     * @param item new image
     * @param path path to store image
     *
     * @throws ServiceException
     */
    void imageEditing(FileItem item, String path) throws ServiceException;
}
