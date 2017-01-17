package com.epam.jobmatch.service.impl;

import com.epam.jobmatch.dao.DeleteDAO;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.factory.DAOFactory;
import com.epam.jobmatch.service.DeleteService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.util.ValidationHandler;

public class Delete implements DeleteService {

    @Override
    public void respondForApplicantDeleting(int idApplicant, String idVacancy) throws ServiceException {
        try {
            ValidationHandler.intValidation(idApplicant);
            ValidationHandler.stringValidation(idVacancy);

            DAOFactory factory = DAOFactory.getInstance();
            DeleteDAO deleteDAO = factory.getDeleteDAO();

            deleteDAO.deleteRespondForApplicant(idApplicant, idVacancy);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void vacancyDeleting(String idVacancy) throws ServiceException {
        try {
            ValidationHandler.stringValidation(idVacancy);

            DAOFactory factory = DAOFactory.getInstance();
            DeleteDAO deleteDAO = factory.getDeleteDAO();

            deleteDAO.deleteVacancy(idVacancy);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void employeeDeleting(String idEmployee) throws ServiceException {
        try {
            ValidationHandler.stringValidation(idEmployee);

            DAOFactory factory = DAOFactory.getInstance();
            DeleteDAO deleteDAO = factory.getDeleteDAO();

            deleteDAO.deleteEmployee(idEmployee);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }
}
