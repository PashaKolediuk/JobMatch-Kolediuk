package com.epam.jobmatch.command.impl;

import com.epam.jobmatch.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Type {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;
}
