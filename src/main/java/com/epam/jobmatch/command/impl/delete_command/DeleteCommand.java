package com.epam.jobmatch.command.impl.delete_command;

import com.epam.jobmatch.command.Command;
import com.epam.jobmatch.command.exception.TypeException;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.command.util.TypeProvider;
import com.epam.jobmatch.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommand implements Command {

    private static final Logger log = LogManager.getLogger(DeleteCommand.class.getName());

    private static final TypeProvider provider = new TypeProvider();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToRedirect = null;
        try {
            Type type = provider.getType(request.getParameter(Parameter.COMMAND), request.getParameter(Parameter.TYPE));
            pageToRedirect = type.execute(request, response);
        } catch (TypeException e) {
            log.error("Error during matching type value", e);
            pageToRedirect = Page.ERROR;
        } catch (ServiceException e) {
            log.error(e);
            pageToRedirect = Page.ERROR;
        }
        return pageToRedirect;
    }
}
