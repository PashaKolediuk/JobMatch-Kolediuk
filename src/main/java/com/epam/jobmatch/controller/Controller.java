package com.epam.jobmatch.controller;

import com.epam.jobmatch.command.Command;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.controller.exception.CommandException;
import com.epam.jobmatch.controller.util.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class Controller extends HttpServlet {

    private static final Logger log = LogManager.getLogger(Controller.class.getName());

    private static final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToRedirect = null;
        try {
            pageToRedirect = processRequest(request, response);
        } catch (Exception e) {
            log.error("Unexpected error", e);
            pageToRedirect = Page.ERROR;
        }
        request.getRequestDispatcher(pageToRedirect).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToRedirect = null;
        try {
            pageToRedirect = processRequest(request, response);
        } catch (Exception e) {
            log.error("Unexpected error", e);
            pageToRedirect = Page.ERROR;
        }
        response.sendRedirect(response.encodeURL(request.getContextPath() + pageToRedirect));
    }

    private String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToRedirect = null;
        try {
            Command command = provider.getCommand(request.getParameter(Parameter.COMMAND));
            pageToRedirect = command.execute(request, response);
        } catch (CommandException e) {
            log.error("Error during matching command value", e);
            pageToRedirect = Page.ERROR;
        }
        return pageToRedirect;
    }
}
