package com.epam.jobmatch.service;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;

public interface SignUpService {

    Employee companyRegistration(Company company) throws ServiceException, ValidationException;
    Applicant applicantRegistration(Applicant applicant) throws ServiceException, ValidationException;
    void employeeRegistration(Employee employee) throws ServiceException, ValidationException;
    void vacancyRegistration(Vacancy vacancy) throws ServiceException, ValidationException;
    void respondRegistration(int userId, int vacancyId) throws ServiceException, ValidationException;

}
