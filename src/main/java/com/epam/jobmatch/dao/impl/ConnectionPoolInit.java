package com.epam.jobmatch.dao.impl;

import com.epam.jobmatch.dao.SourceInit;
import com.epam.jobmatch.dao.connection_pool.ConnectionPool;
import com.epam.jobmatch.dao.connection_pool.exception.ConnectionPoolException;
import com.epam.jobmatch.dao.exception.DAOException;

public class ConnectionPoolInit implements SourceInit {

    @Override
    public void initSource() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.initialize();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during connection pool initialization", e);
        }
    }

    @Override
    public void destroySource() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.clear();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during connection pool cleaning", e);
        }
    }
}
