package com.epam.jobmatch.service;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;

public interface SignUpService {

    /**
     * Handle company registration
     *
     * @param company entered company information
     *
     * @return employee object
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    Employee companyRegistration(Company company) throws ServiceException, ValidationException;
    /**
     * Handle applicant registration
     *
     * @param applicant entered applicant information
     *
     * @return applicant object
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    Applicant applicantRegistration(Applicant applicant) throws ServiceException, ValidationException;
    /**
     * Handle employee registration
     *
     * @param employee entered employee information
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void employeeRegistration(Employee employee) throws ServiceException, ValidationException;
    /**
     * Handle vacancy registration
     *
     * @param vacancy entered vacancy information
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void vacancyRegistration(Vacancy vacancy) throws ServiceException, ValidationException;
    /**
     * Handle respond registration
     *
     * @param userId current applicant's id
     * @param vacancyId selected vacancy's id
     *
     * @throws ServiceException
     * @throws ValidationException
     */
    void respondRegistration(int userId, int vacancyId) throws ServiceException, ValidationException;

}
