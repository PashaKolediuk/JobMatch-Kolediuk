package com.epam.jobmatch.command.impl.sign_up_command.type_impl;

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

public class EmployeeSignUpType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SignUpService signUpService = serviceFactory.getSignUpService();
        try {
            Employee employee = getEmployeeFromRequest(request);

            signUpService.employeeRegistration(employee);

            pageToRedirect = Request.GET_EMPLOYEE_LIST;

        } catch (ValidationException e) {
            pageToRedirect = Page.HR_REGISTRATION + Attribute.FAIL + e.getMessage();
        }
        return pageToRedirect;
    }

    private Employee getEmployeeFromRequest(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setIdCompany(((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getIdCompany());
        employee.setConfirmPassword(request.getParameter(Parameter.CONFIRM_PASSWORD).toCharArray());
        employee.setPassword(request.getParameter(Parameter.PASSWORD).toCharArray());
        employee.setEmail(request.getParameter(Parameter.EMAIL));
        employee.setFullName(request.getParameter(Parameter.FULL_NAME));
        employee.setPhone(request.getParameter(Parameter.PHONE));
        employee.setSkype(request.getParameter(Parameter.SKYPE));
        employee.setStatus(Status.HR);
        return employee;
    }
}
