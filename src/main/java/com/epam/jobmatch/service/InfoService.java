package com.epam.jobmatch.service;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.service.exception.ServiceException;

import java.util.ArrayList;

public interface InfoService {

    Company companyInfo(String idCompany) throws ServiceException;
    Vacancy vacancyInfo(String idVacancy) throws ServiceException;
    Applicant applicantInfo(String idApplicant) throws ServiceException;
    Respond respondInfo(String idApplicant, String idVacancy) throws ServiceException;

    ArrayList<Integer> statisticsInfo() throws ServiceException;

    int getSearchListPageCount(VacancyFilter vacancyFilter) throws ServiceException;
    int getEmployeeListPageCount(EmployeeFilter employeeFilter) throws ServiceException;
    int getVacancyListByIdPageCount(int idCompany, String vacancyName) throws ServiceException;
    int getRespondListForVacancyPageCount(RespondFilter respondFilter) throws ServiceException;
    int getRespondListForApplicantPageCount(int idApplicant) throws ServiceException;

}
