package com.epam.jobmatch.command.impl.edit_command.type_impl;

import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.enumiration.Stage;
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
import java.sql.Date;

public class RespondEditingType implements Type {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String pageToRedirect = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        EditService editService = serviceFactory.getEditService();
        Respond newRespond = getRespondFromRequest(request);

        try {
            editService.respondInfoEditing(newRespond);

            pageToRedirect = Request.GET_RESPOND_INFO + newRespond.getIdApplicant() + Parameter.SEPARATOR +
                    Parameter.VACANCY_ID + Parameter.EQUAL_MARK + newRespond.getIdVacancy();

        } catch (ValidationException e) {
            pageToRedirect = Request.GET_RESPOND_INFO + newRespond.getIdApplicant() + Parameter.SEPARATOR +
                    Parameter.VACANCY_ID + Parameter.EQUAL_MARK + newRespond.getIdVacancy() +
                    Parameter.SEPARATOR + Attribute.FAIL + e.getMessage();
        }
        return pageToRedirect;
    }

    private Respond getRespondFromRequest(HttpServletRequest request) {
        Respond respond = new Respond();
        respond.setIdApplicant(Integer.parseInt(request.getParameter(Parameter.APPLICANT_ID)));
        respond.setIdVacancy(Integer.parseInt(request.getParameter(Parameter.VACANCY_ID)));
        respond.setStage(Stage.valueOf(request.getParameter(Parameter.STAGE).toUpperCase()));
        respond.setConversationDate(Date.valueOf(request.getParameter(Parameter.CONVERSATION_DATE)));
        respond.setNote(request.getParameter(Parameter.NOTE));
        respond.setMark(Integer.parseInt(request.getParameter(Parameter.MARK)));
        return respond;
    }

}
