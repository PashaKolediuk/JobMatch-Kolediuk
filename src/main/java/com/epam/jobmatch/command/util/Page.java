package com.epam.jobmatch.command.util;


/**
 * Describes all pages to which request can be redirected
 */
public class Page {

    public static final String INDEX = "/index.jsp?";
    public static final String MAIN = "/jsp/main.jsp";
    public static final String CONTROLLER = "/controller";
    public static final String ERROR = "/jsp/error.jsp";

    public static final String COMPANY_REGISTRATION = "/jsp/registration/companyRegistration.jsp?";
    public static final String HR_REGISTRATION = "/jsp/registration/employeeRegistration.jsp?";
    public static final String VACANCY_REGISTRATION = "/jsp/registration/vacancyRegistration.jsp?";
    public static final String APPLICANT_REGISTRATION = "/jsp/registration/applicantRegistration.jsp?";

    public static final String APPLICANT_EDITING = "/jsp/editing/editingApplicantInfo.jsp?";
    public static final String EMPLOYEE_EDITING = "/jsp/editing/editingEmployeeInfo.jsp?";
}

