package com.epam.jobmatch.service;

import com.epam.jobmatch.service.exception.ServiceException;

public interface SourceInitService {
    void initSource() throws ServiceException;
    void destroySource() throws ServiceException;
}
