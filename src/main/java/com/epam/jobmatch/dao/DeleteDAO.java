package com.epam.jobmatch.dao;

import com.epam.jobmatch.dao.exception.DAOException;

public interface DeleteDAO {
    /**
     * Delete applicant's respond.
     *
     * @param idApplicant current applicant id
     * @param idVacancy selected vacancy id for respond deleting
     *
     * @throws DAOException
     */
    void deleteRespondForApplicant(int idApplicant, String idVacancy) throws DAOException;
    /**
     * Delete vacancy
     *
     * @param idVacancy selected vacancy for deleting
     *
     * @throws DAOException
     */
    void deleteVacancy(String idVacancy) throws DAOException;
    /**
     * Delete employee
     *
     * @param idEmployee selected employee for deleting
     *
     * @throws DAOException
     */
    void deleteEmployee(String idEmployee) throws DAOException;

}
