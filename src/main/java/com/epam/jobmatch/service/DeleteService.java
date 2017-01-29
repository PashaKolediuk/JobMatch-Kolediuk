package com.epam.jobmatch.service;

import com.epam.jobmatch.service.exception.ServiceException;

public interface DeleteService {
    /**
     * Delete applicant's respond.
     *
     * @param idApplicant current applicant id
     * @param idVacancy selected vacancy id for respond deleting
     *
     * @throws ServiceException
     */
    void respondForApplicantDeleting(int idApplicant, String idVacancy) throws ServiceException;

    /**
     * Delete vacancy
     *
     * @param idVacancy selected vacancy for deleting
     *
     * @throws ServiceException
     */
    void vacancyDeleting(String idVacancy) throws ServiceException;

    /**
     * Delete employee
     *
     * @param idEmployee selected employee for deleting
     *
     * @throws ServiceException
     */
    void employeeDeleting(String idEmployee) throws ServiceException;

}
