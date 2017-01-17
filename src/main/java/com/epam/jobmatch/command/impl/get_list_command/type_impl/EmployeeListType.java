package com.epam.jobmatch.command.impl.get_list_command.type_impl;

import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.service.InfoListService;
import com.epam.jobmatch.service.InfoService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeListType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InfoListService infoListService = serviceFactory.getInfoListService();

        EmployeeFilter employeeFilter = getEmployeeFilterFromRequest(request);

        ArrayList<Employee> employeeList = infoListService.returnEmployeeList(employeeFilter);
        request.setAttribute(Attribute.EMPLOYEE_LIST, employeeList);

        InfoService infoService = serviceFactory.getInfoService();
        int pageCount = infoService.getEmployeeListPageCount(employeeFilter);
        request.setAttribute(Attribute.PAGE_COUNT, pageCount);

        return Page.MAIN;
    }

    private EmployeeFilter getEmployeeFilterFromRequest(HttpServletRequest request) {
        EmployeeFilter employeeFilter = new EmployeeFilter();
        employeeFilter.setIdCompany(((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getIdCompany());
        employeeFilter.setPage(Integer.parseInt(request.getParameter(Parameter.PAGE)));
        employeeFilter.setFullName(request.getParameter(Parameter.FULL_NAME));
        employeeFilter.setEmail(request.getParameter(Parameter.EMAIL));
        employeeFilter.setPhone(request.getParameter(Parameter.PHONE));
        return employeeFilter;
    }
}
