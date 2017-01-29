package com.epam.jobmatch.dao;

import com.epam.jobmatch.dao.exception.DAOException;

public interface SourceInit {

    /**
     * Initiate data source connection
     *
     * @throws DAOException
     */
    void initSource() throws DAOException;
    /**
     * Destroy data source connection
     *
     * @throws DAOException
     */
    void destroySource() throws DAOException;

}
