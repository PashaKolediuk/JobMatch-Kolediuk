package com.epam.jobmatch.service.impl;

import com.epam.jobmatch.dao.SourceInit;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.factory.DAOFactory;
import com.epam.jobmatch.service.SourceInitService;
import com.epam.jobmatch.service.exception.ServiceException;

public class DataBaseInit implements SourceInitService {

    @Override
    public void initSource() throws ServiceException {
        try {
            DAOFactory factory = DAOFactory.getInstance();
            SourceInit sourceInit = factory.getSourceInit();
            sourceInit.initSource();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void destroySource() throws ServiceException {
        try {
            DAOFactory factory = DAOFactory.getInstance();
            SourceInit sourceInit = factory.getSourceInit();
            sourceInit.destroySource();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
