package com.epam.jobmatch.service.impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.dao.InfoListDAO;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.factory.DAOFactory;
import com.epam.jobmatch.service.InfoListService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.util.ValidationHandler;

import java.util.ArrayList;
import java.util.Map;

public class InfoList implements InfoListService {

    @Override
    public Map<Vacancy, Company> returnSearchResult(VacancyFilter vacancyFilter) throws ServiceException {
        try {
            ValidationHandler.vacancyFilterValidation(vacancyFilter);

            DAOFactory factory = DAOFactory.getInstance();
            InfoListDAO infoListDAO = factory.getInfoListDAO();

            return infoListDAO.searchResult(vacancyFilter);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Employee> returnEmployeeList(EmployeeFilter employeeFilter) throws ServiceException {
        try {
            ValidationHandler.employeeFilterValidation(employeeFilter);

            DAOFactory factory = DAOFactory.getInstance();
            InfoListDAO infoListDAO = factory.getInfoListDAO();

            return infoListDAO.employeeList(employeeFilter);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Vacancy> returnVacancyListById(int idCompany, String page, String vacancyName) throws ServiceException {
        try {
            ValidationHandler.intValidation(idCompany);
            ValidationHandler.stringValidation(page);
            ValidationHandler.stringValidation(vacancyName);

            DAOFactory factory = DAOFactory.getInstance();
            InfoListDAO infoListDAO = factory.getInfoListDAO();

            return infoListDAO.vacancyListById(idCompany, page, vacancyName);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Respond, Applicant> returnRespondListForVacancy(RespondFilter respondFilter) throws ServiceException {
        try {
            ValidationHandler.respondFilterValidation(respondFilter);

            DAOFactory factory = DAOFactory.getInstance();
            InfoListDAO infoListDAO = factory.getInfoListDAO();

            return infoListDAO.respondListForVacancy(respondFilter);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Respond, Vacancy> returnRespondListForApplicant(int idApplicant, String page) throws ServiceException {
        try {
            ValidationHandler.intValidation(idApplicant);
            ValidationHandler.stringValidation(page);

            DAOFactory factory = DAOFactory.getInstance();
            InfoListDAO infoListDAO = factory.getInfoListDAO();

            return infoListDAO.respondListForApplicant(idApplicant, page);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Vacancy, Company> getLatestVacancyList() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        InfoListDAO infoListDAO = factory.getInfoListDAO();
        try {
            return infoListDAO.latestVacancyList();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Company> getSearchingCompanyList() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        InfoListDAO infoListDAO = factory.getInfoListDAO();
        try {
            return infoListDAO.searchingCompanyList();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
