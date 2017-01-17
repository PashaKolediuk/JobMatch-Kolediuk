package com.epam.jobmatch.command.util;

import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.controller.util.CommandEnum;

public class Request {

    public static final String GET_VACANCY_LIST = Page.CONTROLLER + "?" +
            Parameter.COMMAND + "=" + CommandEnum.GET_LIST.toString().toLowerCase() + "&" +
            Parameter.TYPE + "=" + TypeEnum.SEARCH.toString().toLowerCase() + "&" +
            Parameter.PAGE + "=1" + "&" +
            Parameter.SEARCH + "=" + "&" +
            Parameter.SORT + "=" + "date" + "&" +
            Parameter.EXPERIENCE_FILTER + "=0,100" + "&" +
            Parameter.SALARY_FILTER + "=0" + "&" +
            Parameter.COUNTRY_FILTER + "=" + "&" +
            Parameter.CITY_FILTER + "=";

    public static final String GET_VACANCY_LIST_BY_ID = Page.CONTROLLER + "?" +
            Parameter.COMMAND + "=" + CommandEnum.GET_LIST.toString().toLowerCase() + "&" +
            Parameter.TYPE + "=" + TypeEnum.VACANCY_BY_ID.toString().toLowerCase() + "&" +
            Parameter.PAGE + "=1" + "&" +
            Parameter.VACANCY_NAME + "=";

    public static final String GET_EMPLOYEE_LIST = Page.CONTROLLER + "?" +
            Parameter.COMMAND + "=" + CommandEnum.GET_LIST.toString().toLowerCase() + "&" +
            Parameter.TYPE + "=" + TypeEnum.EMPLOYEE.toString().toLowerCase() + "&" +
            Parameter.PAGE + "=1" + "&" +
            Parameter.FULL_NAME + "=" + "&" +
            Parameter.EMAIL + "=" + "&" +
            Parameter.PHONE + "=";

    public static final String GET_RESPOND_LIST_OF_APPLICANT = Page.CONTROLLER + "?" +
            Parameter.COMMAND + "=" + CommandEnum.GET_LIST.toString().toLowerCase() + "&" +
            Parameter.TYPE + "=" + TypeEnum.RESPOND_OF_APPLICANT.toString().toLowerCase() + "&" +
            Parameter.PAGE + "=1";

    public static final String GET_COMPANY_INFO_BY_ID = Page.CONTROLLER + "?" +
            Parameter.COMMAND + "=" + CommandEnum.GET_INFO.toString().toLowerCase() + "&" +
            Parameter.TYPE + "=" + TypeEnum.COMPANY.toString().toLowerCase() + "&" +
            Parameter.COMPANY_ID + "=";

    public static final String GET_RESPOND_INFO = Page.CONTROLLER + "?" +
            Parameter.COMMAND + "=" + CommandEnum.GET_INFO.toString().toLowerCase() + "&" +
            Parameter.TYPE + "=" + TypeEnum.RESPOND_OF_VACANCY.toString().toLowerCase() + "&" +
            Parameter.APPLICANT_ID + "=";

    public static final String GET_INDEX_INFO = Page.CONTROLLER + "?" +
            Parameter.COMMAND + "=" + CommandEnum.GET_INFO.toString().toLowerCase() + "&" +
            Parameter.TYPE + "=" + TypeEnum.INDEX.toString().toLowerCase() + "&" +
            Parameter.PAGE + "=" + TypeEnum.INDEX.toString().toLowerCase();

}
