package com.epam.jobmatch.service.util;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.bean.entity.user.enumiration.EnglishLevel;
import com.epam.jobmatch.service.exception.ValidationException;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ValidationHandler {

    private static final Pattern NAME = Pattern.compile("^[a-zA-Z][a-zA-Z\\s-]{2,30}");
    private static final Pattern EMAIL = Pattern.compile("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$");
    private static final Pattern PASSWORD = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,30}$");
    private static final Pattern PHONE = Pattern.compile("^(\\+)[0-9]{12}");
    private static final Pattern SKYPE = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9_.-]{3,30}");

    public static void applicantValidation(Applicant applicant) throws ValidationException {
        applicantProfileValidation(applicant);
        passwordValidation(applicant.getPassword(), applicant.getConfirmPassword());
    }

    public static void applicantProfileValidation(Applicant applicant) throws ValidationException {
        if (!NAME.matcher(applicant.getFirstName()).matches() || applicant.getFirstName() == null) {
            throw new ValidationException(InvalidMessage.INVALID_FIRST_NAME);
        }
        if (!NAME.matcher(applicant.getLastName()).matches() || applicant.getFirstName() == null) {
            throw new ValidationException(InvalidMessage.INVALID_LAST_NAME);
        }
        if (!EMAIL.matcher(applicant.getEmail()).matches() || applicant.getEmail() == null) {
            throw new ValidationException(InvalidMessage.INVALID_EMAIL);
        }
        if (!PHONE.matcher(applicant.getPhone()).matches() || applicant.getPhone() == null) {
            throw new ValidationException(InvalidMessage.INVALID_PHONE);
        }
        if ((!SKYPE.matcher(applicant.getSkype()).matches() && !applicant.getSkype().isEmpty()) ||
                applicant.getSkype() == null) {
            throw new ValidationException(InvalidMessage.INVALID_SKYPE);
        }
        if (!NAME.matcher(applicant.getCountry()).matches() || applicant.getCountry() == null) {
            throw new ValidationException(InvalidMessage.INVALID_COUNTRY);
        }
        if (!NAME.matcher(applicant.getCity()).matches() || applicant.getCity() == null) {
            throw new ValidationException(InvalidMessage.INVALID_CITY);
        }
        if (!NAME.matcher(applicant.getUniversity()).matches() || applicant.getUniversity() == null) {
            throw new ValidationException(InvalidMessage.INVALID_UNIVERSITY);
        }
        if (applicant.getGraduationYear() < 1960 || applicant.getGraduationYear() > 2020) {
            throw new ValidationException(InvalidMessage.INVALID_GRADUATION_YEAR);
        }
        if (applicant.getEnglishLevel() == null) {
            throw new ValidationException(InvalidMessage.INVALID_ENGLISH_LEVEL);
        }
    }

    public static void employeeValidation(Employee employee) throws ValidationException {
        employeeProfileValidation(employee);
        passwordValidation(employee.getPassword(), employee.getConfirmPassword());
    }

    public static void employeeProfileValidation(Employee employee) throws ValidationException {
        if (!NAME.matcher(employee.getFullName()).matches() || employee.getFullName() == null) {
            throw new ValidationException(InvalidMessage.INVALID_FULL_NAME);
        }
        if (!EMAIL.matcher(employee.getEmail()).matches() || employee.getEmail() == null) {
            throw new ValidationException(InvalidMessage.INVALID_EMAIL);
        }
        if (!PHONE.matcher(employee.getPhone()).matches() || employee.getPhone() == null) {
            throw new ValidationException(InvalidMessage.INVALID_PHONE);
        }
        if ((!SKYPE.matcher(employee.getSkype()).matches() && !employee.getSkype().isEmpty()) ||
                employee.getSkype() == null) {
            throw new ValidationException(InvalidMessage.INVALID_SKYPE);
        }
    }

    public static void companyValidation(Company company) throws ValidationException {
        // TODO validation
    }

    public static void vacancyValidation(Vacancy vacancy) throws ValidationException {
        // TODO validation
    }

    public static void respondValidation(Respond respond) throws ValidationException {

    }



    public static void passwordValidation(char[] password, char[] confirmPassword) throws ValidationException {
        if (!PASSWORD.matcher(CharBuffer.wrap(password)).matches()) {
            throw new ValidationException(InvalidMessage.INVALID_PASSWORD);
        }
        if (!Arrays.equals(password, confirmPassword)) {
            throw new ValidationException(InvalidMessage.INVALID_CONFIRM_PASSWORD);
        }
    }


    public static void vacancyFilterValidation(VacancyFilter vacancyFilter) throws ValidationException {
        if (vacancyFilter.getSearchString() == null) {
            throw new ValidationException("Invalid search string of vacancy filter");
        }
        if (vacancyFilter.getPage() <= 0) {
            throw new ValidationException("Invalid page of vacancy filter");
        }
        if (vacancyFilter.getSort() == null) {
            throw new ValidationException("Invalid sort of vacancy filter");
        }
        if (vacancyFilter.getMinExperienceFilter() < 0) {
            throw new ValidationException("Invalid min experience of vacancy filter");
        }
        if (vacancyFilter.getMaxExperienceFilter() < 0) {
            throw new ValidationException("Invalid max experience of vacancy filter");
        }
        if (vacancyFilter.getSalaryFilter() < 0) {
            throw new ValidationException("Invalid page of vacancy filter");
        }
        if (vacancyFilter.getCountryFilter() == null) {
            throw new ValidationException("Invalid country of vacancy filter");
        }
        if (vacancyFilter.getCityFilter() == null) {
            throw new ValidationException("Invalid city of vacancy filter");
        }
    }

    public static void employeeFilterValidation(EmployeeFilter employeeFilter) throws ValidationException {
        if (employeeFilter.getIdCompany() <= 0) {
            throw new ValidationException("Invalid company id of employee filter");
        }
        if (employeeFilter.getPage() <= 0) {
            throw new ValidationException("Invalid page of employee filter");
        }
        if (employeeFilter.getFullName() == null) {
            throw new ValidationException("Invalid full name of employee filter");
        }
        if (employeeFilter.getEmail() == null) {
            throw new ValidationException("Invalid email of employee filter");
        }
        if (employeeFilter.getPhone() == null) {
            throw new ValidationException("Invalid phone of employee filter");
        }
    }

    public static void respondFilterValidation(RespondFilter respondFilter) throws ValidationException {
        if (respondFilter.getIdVacancy() <= 0) {
            throw new ValidationException("Invalid vacancy id of respond filter");
        }
        if (respondFilter.getPage() <= 0) {
            throw new ValidationException("Invalid page of respond filter");
        }
        if (respondFilter.getLastName() == null) {
            throw new ValidationException("Invalid last name of respond filter");
        }
        if (respondFilter.getEmail() == null) {
            throw new ValidationException("Invalid email of respond filter");
        }
        if (respondFilter.getPhone() == null) {
            throw new ValidationException("Invalid phone of respond filter");
        }
        if (respondFilter.getStage() == null) {
            throw new ValidationException("Invalid stage of respond filter");
        }
        if (respondFilter.getMinMark() < 0) {
            throw new ValidationException("Invalid min mark of respond filter");
        }
        if (respondFilter.getSort() == null) {
            throw new ValidationException("Invalid sort of respond filter");
        }
    }


    public static void stringValidation(String string) throws ValidationException {
        if (string == null) {
            throw new ValidationException("Invalid string parameter");
        }
    }

    public static void intValidation(int count) throws ValidationException {
        if (count <= 0) {
            throw new ValidationException("Invalid int parameter");
        }
    }


}
