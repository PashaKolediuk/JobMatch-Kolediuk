package com.epam.jobmatch.command.impl.edit_command.type_impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.command.util.Request;
import com.epam.jobmatch.service.EditService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyInfoEditingType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EditService editService = serviceFactory.getEditService();

        Company companyForEditing = getCompanyFromRequest(request);
        try {
            editService.companyInfoEditing(companyForEditing);
            pageToRedirect = Request.GET_COMPANY_INFO_BY_ID + companyForEditing.getIdCompany();
        } catch (ValidationException e) {
            pageToRedirect = Request.GET_COMPANY_INFO_BY_ID + companyForEditing.getIdCompany() +
                     Parameter.SEPARATOR + Attribute.FAIL + e.getMessage();
        }
        return pageToRedirect;
    }

    private Company getCompanyFromRequest(HttpServletRequest request) {
        Company companyForEditing = new Company();
        companyForEditing.setIdCompany(((Employee)request.getSession().getAttribute(Attribute.EMPLOYEE)).getIdCompany());
        companyForEditing.setCompanyName(request.getParameter(Parameter.COMPANY_NAME));
        companyForEditing.setCountry(request.getParameter(Parameter.COUNTRY));
        companyForEditing.setCity(request.getParameter(Parameter.CITY));
        companyForEditing.setCompanyDescription(request.getParameter(Parameter.COMPANY_DESCRIPTION));
        companyForEditing.setWebsite(request.getParameter(Parameter.WEBSITE));
        return companyForEditing;
    }

}

