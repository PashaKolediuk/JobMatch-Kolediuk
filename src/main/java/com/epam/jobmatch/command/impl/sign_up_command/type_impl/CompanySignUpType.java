package com.epam.jobmatch.command.impl.sign_up_command.type_impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.bean.entity.user.enumiration.Status;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.command.util.Request;
import com.epam.jobmatch.service.SignUpService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanySignUpType implements Type {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SignUpService signUpService = serviceFactory.getSignUpService();
        try {

            Employee admin = getAdminFromRequest(request);
            Company company = getCompanyFromRequest(request);
            company.setAdmin(admin);

            admin = signUpService.companyRegistration(company);
            request.getSession().setAttribute(Attribute.EMPLOYEE, admin);

            pageToRedirect = Request.GET_VACANCY_LIST_BY_ID;

        } catch (ValidationException e) {
            pageToRedirect = Page.COMPANY_REGISTRATION + Attribute.FAIL + e.getMessage();
        }
        return pageToRedirect;
    }

    private Employee getAdminFromRequest(HttpServletRequest request) {
        Employee admin = new Employee();
        admin.setPassword(request.getParameter(Parameter.PASSWORD).toCharArray());
        admin.setConfirmPassword(request.getParameter(Parameter.CONFIRM_PASSWORD).toCharArray());
        admin.setEmail(request.getParameter(Parameter.EMAIL));
        admin.setFullName(request.getParameter(Parameter.FULL_NAME));
        admin.setPhone(request.getParameter(Parameter.PHONE));
        admin.setSkype(request.getParameter(Parameter.SKYPE));
        admin.setStatus(Status.ADMIN);
        return admin;
    }

    private Company getCompanyFromRequest(HttpServletRequest request) {
        Company company = new Company();
        company.setCompanyName(request.getParameter(Parameter.COMPANY_NAME));
        company.setCountry(request.getParameter(Parameter.COUNTRY));
        company.setCity(request.getParameter(Parameter.CITY));
        company.setCompanyDescription(request.getParameter(Parameter.COMPANY_DESCRIPTION));
        company.setWebsite(request.getParameter(Parameter.WEBSITE));
        return company;
    }
}
