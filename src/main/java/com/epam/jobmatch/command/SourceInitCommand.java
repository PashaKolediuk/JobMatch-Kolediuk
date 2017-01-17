package com.epam.jobmatch.command;

import com.epam.jobmatch.command.exception.SourceInitException;
import com.epam.jobmatch.service.SourceInitService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.factory.ServiceFactory;

public class SourceInitCommand {

    private static SourceInitCommand instance = null;

    private SourceInitCommand(){}

    public static synchronized SourceInitCommand getInstance() {
        if (instance == null)
            instance = new SourceInitCommand();
        return instance;
    }
    public void initSource() throws SourceInitException {
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            SourceInitService sourceInitService = serviceFactory.getSourceInitService();
            sourceInitService.initSource();
        } catch (ServiceException e) {
            throw new SourceInitException(e);
        }
    }

    public void destroySource() throws SourceInitException {
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            SourceInitService sourceInitService = serviceFactory.getSourceInitService();
            sourceInitService.destroySource();
        } catch (ServiceException e) {
            throw new SourceInitException(e);
        }
    }
}
