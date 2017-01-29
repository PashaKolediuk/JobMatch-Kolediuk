package com.epam.jobmatch.command.impl.sign_in_command;

import com.epam.jobmatch.bean.entity.user.User;
import com.epam.jobmatch.command.Command;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.command.util.Request;
import com.epam.jobmatch.service.SignInService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand implements Command {

    private static final Logger log = LogManager.getLogger(SignInCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToRedirect = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SignInService signInService = serviceFactory.getSignInService();

        String email = request.getParameter(Parameter.EMAIL);
        char[] password = request.getParameter(Parameter.PASSWORD).toCharArray();

        try {
            User user = signInService.authorization(password, email);
            switch (user.getRole()) {
                case APPLICANT:
                    request.getSession().setAttribute(Attribute.APPLICANT, user);
                    pageToRedirect = Request.GET_VACANCY_LIST;
                    break;
                case EMPLOYEE:
                    request.getSession().setAttribute(Attribute.EMPLOYEE, user);
                    pageToRedirect = Request.GET_VACANCY_LIST_BY_ID;
            }
        } catch (ValidationException e) {
            pageToRedirect = Request.GET_INDEX_INFO + Parameter.SEPARATOR + Attribute.FAIL + e.getMessage();
        } catch (ServiceException e) {
            log.error(e);
            pageToRedirect = Page.ERROR;
        }
        return pageToRedirect;
    }

}
