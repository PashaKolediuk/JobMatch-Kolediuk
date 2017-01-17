package com.epam.jobmatch.command.impl.sign_up_command.type_impl;

import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.enumiration.EnglishLevel;
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

public class ApplicantSignUpType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SignUpService signUpService = serviceFactory.getSignUpService();
        try {
            Applicant applicant = getApplicantFromRequest(request);

            applicant = signUpService.applicantRegistration(applicant);
            request.getSession().setAttribute(Attribute.APPLICANT, applicant);

            pageToRedirect = Request.GET_VACANCY_LIST;

        } catch (ValidationException e) {
            pageToRedirect = Page.APPLICANT_REGISTRATION + Attribute.FAIL + e.getMessage();
        }
        return pageToRedirect;
    }

    private Applicant getApplicantFromRequest(HttpServletRequest request) {
        Applicant applicant = new Applicant();
        applicant.setPassword(request.getParameter(Parameter.PASSWORD).toCharArray());
        applicant.setConfirmPassword(request.getParameter(Parameter.CONFIRM_PASSWORD).toCharArray());
        applicant.setEmail(request.getParameter(Parameter.EMAIL));
        applicant.setFirstName(request.getParameter(Parameter.FIRST_NAME));
        applicant.setLastName(request.getParameter(Parameter.LAST_NAME));
        applicant.setCountry(request.getParameter(Parameter.COUNTRY));
        applicant.setCity(request.getParameter(Parameter.CITY));
        applicant.setPhone(request.getParameter(Parameter.PHONE));
        applicant.setSkype(request.getParameter(Parameter.SKYPE));
        applicant.setUniversity(request.getParameter(Parameter.UNIVERSITY));
        applicant.setGraduationYear(Integer.parseInt(request.getParameter(Parameter.GRADUATION_YEAR)));
        applicant.setProfessionalSkills(request.getParameter(Parameter.PROFESSIONAL_SKILLS));
        applicant.setEnglishLevel(EnglishLevel.valueOf(request.getParameter(Parameter.ENGLISH_LEVEL)));
        return applicant;
    }
}
