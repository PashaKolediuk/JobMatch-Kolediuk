package com.epam.jobmatch.service;

import com.epam.jobmatch.service.exception.ServiceException;

public interface DeleteService {

    void respondForApplicantDeleting(int idApplicant, String idVacancy) throws ServiceException;
    void vacancyDeleting(String idVacancy) throws ServiceException;
    void employeeDeleting(String idEmployee) throws ServiceException;

}
