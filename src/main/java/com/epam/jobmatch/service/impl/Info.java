package com.epam.jobmatch.service.impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.dao.InfoDAO;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.factory.DAOFactory;
import com.epam.jobmatch.service.InfoService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.util.ValidationHandler;

import java.util.ArrayList;

public class Info implements InfoService {

    @Override
    public Company companyInfo(String idCompany) throws ServiceException {
        try {
            ValidationHandler.stringValidation(idCompany);

            DAOFactory factory = DAOFactory.getInstance();
            InfoDAO infoDAO = factory.getInfoDAO();

            return infoDAO.companyInfo(idCompany);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Vacancy vacancyInfo(String idVacancy) throws ServiceException {
        try {
            ValidationHandler.stringValidation(idVacancy);

            DAOFactory factory = DAOFactory.getInstance();
            InfoDAO infoDAO = factory.getInfoDAO();

            return infoDAO.vacancyInfo(idVacancy);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Applicant applicantInfo(String idApplicant) throws ServiceException {
        try {
            ValidationHandler.stringValidation(idApplicant);

            DAOFactory factory = DAOFactory.getInstance();
            InfoDAO infoDAO = factory.getInfoDAO();

            return infoDAO.applicantInfo(idApplicant);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Respond respondInfo(String idApplicant, String idVacancy) throws ServiceException {
        try {
            ValidationHandler.stringValidation(idApplicant);
            ValidationHandler.stringValidation(idVacancy);

            DAOFactory factory = DAOFactory.getInstance();
            InfoDAO infoDAO = factory.getInfoDAO();

            return infoDAO.respondInfo(idApplicant, idVacancy);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public ArrayList<Integer> statisticsInfo() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        InfoDAO infoDAO = factory.getInfoDAO();
        try {
            return infoDAO.statisticsInfo();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public int getSearchListPageCount(VacancyFilter vacancyFilter) throws ServiceException {
        try {
            ValidationHandler.vacancyFilterValidation(vacancyFilter);

            DAOFactory factory = DAOFactory.getInstance();
            InfoDAO infoDAO = factory.getInfoDAO();

            return infoDAO.getSearchListPageCount(vacancyFilter);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getEmployeeListPageCount(EmployeeFilter employeeFilter) throws ServiceException {
        try {
            ValidationHandler.employeeFilterValidation(employeeFilter);

            DAOFactory factory = DAOFactory.getInstance();
            InfoDAO infoDAO = factory.getInfoDAO();

            return infoDAO.getEmployeeListPageCount(employeeFilter);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getVacancyListByIdPageCount(int idCompany, String vacancyName) throws ServiceException {
        try {
            ValidationHandler.intValidation(idCompany);
            ValidationHandler.stringValidation(vacancyName);

            DAOFactory factory = DAOFactory.getInstance();
            InfoDAO infoDAO = factory.getInfoDAO();

            return infoDAO.getVacancyListByIdPageCount(idCompany, vacancyName);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getRespondListForVacancyPageCount(RespondFilter respondFilter) throws ServiceException {
        try {
            ValidationHandler.respondFilterValidation(respondFilter);

            DAOFactory factory = DAOFactory.getInstance();
            InfoDAO infoDAO = factory.getInfoDAO();

            return infoDAO.getRespondListForVacancyPageCount(respondFilter);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getRespondListForApplicantPageCount(int idApplicant) throws ServiceException {
        try {
            ValidationHandler.intValidation(idApplicant);

            DAOFactory factory = DAOFactory.getInstance();
            InfoDAO infoDAO = factory.getInfoDAO();

            return infoDAO.getRespondListForApplicantPageCount(idApplicant);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

}
