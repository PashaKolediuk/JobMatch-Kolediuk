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

    void companyInfoEditing(Company company) throws ServiceException, ValidationException;
    void respondInfoEditing(Respond newRespond) throws ServiceException, ValidationException;

    void applicantProfileEditing(Applicant applicant) throws ServiceException, ValidationException;
    void applicantPasswordEditing(int id, char[] password, char[] confirmPassword) throws ServiceException, ValidationException;

    void employeeProfileEditing(Employee employee) throws ServiceException, ValidationException;
    void employeePasswordEditing(String idEmployee, char[] password, char[] confirmPassword) throws ServiceException, ValidationException;

    void vacancyEditing(Vacancy newVacancy)  throws ServiceException, ValidationException;
    void imageEditing(FileItem item, String path) throws ServiceException;
}
