package com.epam.jobmatch.controller.listener;

import com.epam.jobmatch.command.SourceInitCommand;
import com.epam.jobmatch.command.exception.SourceInitException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private static final Logger log = LogManager.getLogger(ContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            SourceInitCommand sourceInitCommand = SourceInitCommand.getInstance();
            sourceInitCommand.initSource();
        } catch (SourceInitException e) {
            log.error(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            SourceInitCommand sourceInitCommand = SourceInitCommand.getInstance();
            sourceInitCommand.destroySource();
        } catch (SourceInitException e) {
            log.error(e);
        }
    }
}
