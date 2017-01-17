package com.epam.jobmatch.dao;

import com.epam.jobmatch.dao.exception.DAOException;

public interface SourceInit {

    void initSource() throws DAOException;
    void destroySource() throws DAOException;

}
