package com.epam.jobmatch.service;

import com.epam.jobmatch.service.exception.ServiceException;

public interface SourceInitService {

    /**
     * Initiate data source connection
     *
     * @throws ServiceException
     */
    void initSource() throws ServiceException;
    /**
     * Destroy data source connection
     *
     * @throws ServiceException
     */
    void destroySource() throws ServiceException;
}
