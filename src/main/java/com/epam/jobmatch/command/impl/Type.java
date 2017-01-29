package com.epam.jobmatch.command.impl;

import com.epam.jobmatch.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Type {

    /**
     * Executes necessary type and prepares response to user.
     *
     * @param request the {@link javax.servlet.http.HttpServletRequest} object
     * @param response the {@link javax.servlet.http.HttpServletResponse} object
     *
     * @return path to redirect user
     *
     * @throws ServletException
     * @throws ServiceException
     * @throws IOException
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;
}
