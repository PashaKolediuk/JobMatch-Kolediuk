package com.epam.jobmatch.command.impl.get_info_command.type_impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.service.InfoService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyInfoType implements Type {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InfoService infoService = serviceFactory.getInfoService();

        Company company = infoService.companyInfo(request.getParameter(Parameter.COMPANY_ID));
        request.setAttribute(Attribute.COMPANY_INFO, company);

        return Page.MAIN;
    }
}
