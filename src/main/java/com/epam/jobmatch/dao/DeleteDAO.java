package com.epam.jobmatch.dao;

import com.epam.jobmatch.dao.exception.DAOException;

public interface DeleteDAO {

    void deleteRespondForApplicant(int idApplicant, String idVacancy) throws DAOException;
    void deleteVacancy(String idVacancy) throws DAOException;
    void deleteEmployee(String idEmployee) throws DAOException;

}
