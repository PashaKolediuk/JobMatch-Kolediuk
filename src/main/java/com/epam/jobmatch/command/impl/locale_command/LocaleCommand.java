package com.epam.jobmatch.command.impl.locale_command;

import com.epam.jobmatch.command.Command;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(Attribute.LOCAL, request.getParameter(Parameter.LOCAL));
        String pageToRedirect = request.getHeader(Parameter.REFERER);
        return pageToRedirect.substring(pageToRedirect.indexOf(Parameter.PROJECT_URI) + Parameter.PROJECT_URI.length());
    }
}
