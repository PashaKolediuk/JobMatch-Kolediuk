package com.epam.jobmatch.dao.impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.enumiration.Stage;
import com.epam.jobmatch.bean.entity.filter.EmployeeFilter;
import com.epam.jobmatch.bean.entity.filter.RespondFilter;
import com.epam.jobmatch.bean.entity.filter.VacancyFilter;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.bean.entity.user.enumiration.EnglishLevel;
import com.epam.jobmatch.bean.entity.user.enumiration.Status;
import com.epam.jobmatch.dao.InfoListDAO;
import com.epam.jobmatch.dao.connection_pool.ConnectionPool;
import com.epam.jobmatch.dao.connection_pool.exception.ConnectionPoolException;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.util.Column;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class JDBCInfoListDAO implements InfoListDAO {

    private static final Logger log = LogManager.getLogger(JDBCInfoListDAO.class.getName());

    private static final int NUMBER_OF_ROWS_PER_PAGE = 5;

    private static final String LIMIT_STATEMENT = " LIMIT ";
    private static final String LIMIT_SEPARATOR = ", ";

    private static final String SEARCH_VACANCY = "SELECT * FROM vacancy INNER JOIN company ON vacancy.idCompany = company.idCompany\n" +
            " WHERE (vacancy.name LIKE CONCAT('%', ?, '%') OR vacancy.requiredSkills LIKE CONCAT('%', ?, '%')\n" +
            " OR vacancy.vacancyDescription LIKE CONCAT('%', ?, '%') OR company.companyName LIKE CONCAT('%', ?, '%'))\n" +
            " AND vacancy.requiredExperience BETWEEN ? and ? AND vacancy.salary >= ? AND company.country LIKE CONCAT('%', ?, '%')\n" +
            "  AND  company.city LIKE CONCAT('%', ?, '%')\n" +
            " ORDER by ";

    private static final String EMPLOYEE_LIST = "SELECT * FROM employee INNER JOIN company ON employee.idCompany = company.idCompany\n" +
            " WHERE company.idCompany = ? AND employee.status LIKE 'hr' AND employee.fullName LIKE CONCAT('%', ?, '%') COLLATE utf8_general_ci" +
            " AND employee.email LIKE CONCAT('%', ?, '%') AND employee.phone LIKE CONCAT('%', ?, '%') ORDER BY fullName LIMIT ";
    private static final String VACANCY_LIST_BY_ID = "SELECT * FROM vacancy INNER JOIN company ON vacancy.idCompany = company.idCompany\n" +
            " WHERE company.idCompany = ? AND vacancy.name LIKE CONCAT('%', ?, '%') ORDER by vacancy.date DESC LIMIT ";

    private static final String RESPOND_LIST_FOR_VACANCY = "SELECT * FROM respond INNER JOIN applicant ON respond.idApplicant = applicant.idApplicant\n" +
            " WHERE respond.idVacancy = ? AND lastName LIKE CONCAT('%', ?, '%') COLLATE utf8_general_ci AND applicant.email LIKE CONCAT('%', ?, '%') AND applicant.phone LIKE CONCAT('%', ?, '%') " +
            " AND respond.stage LIKE CONCAT('%', ?, '%') AND respond.totalMark >= ? ORDER by ";
    private static final String RESPOND_LIST_FOR_APPLICANT = "SELECT * FROM respond INNER JOIN vacancy ON respond.idVacancy = vacancy.idVacancy\n" +
            " WHERE respond.idApplicant = ? ORDER by respondDate DESC LIMIT ";

    private static final String LATEST_VACANCY_LIST = "SELECT * FROM vacancy INNER JOIN company ON vacancy.idCompany = company.idCompany\n" +
            "ORDER by vacancy.date DESC LIMIT 0, 5";
    private static final String SEARCHING_COMPANY_LIST = "SELECT COUNT(respondDate) as repondCount, company.* FROM company INNER JOIN vacancy ON company.idCompany = vacancy.idCompany\n" +
            "INNER JOIN respond ON vacancy.idVacancy = respond.idVacancy\n" +
            "group by company.idCompany\n" +
            "ORDER BY repondCount DESC LIMIT 0, 5";

    @Override
    public Map<Vacancy, Company> searchResult(VacancyFilter vacancyFilter) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        StringBuilder query = new StringBuilder();
        ResultSet resultSet = null;

        Map<Vacancy, Company> vacancyMap = new LinkedHashMap<>();

        Vacancy vacancy = null;
        Company company = null;

        try {
            connection = connectionPool.takeConnection();

            query.append(SEARCH_VACANCY).append(vacancyFilter.getSort()).append(LIMIT_STATEMENT)
                    .append((vacancyFilter.getPage() - 1) * NUMBER_OF_ROWS_PER_PAGE).append(LIMIT_SEPARATOR)
                    .append(NUMBER_OF_ROWS_PER_PAGE);

            resultSet = executeSearchVacancyQuery(query, vacancyFilter, connection);

            while (resultSet.next()) {
                vacancy = setVacancy(resultSet);
                company = setCompany(resultSet);
                vacancyMap.put(vacancy, company);
            }

            return vacancyMap;

        } catch (SQLException e) {
            throw new DAOException("SQLException during searching", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after searching", e);
            }
        }
    }

    @Override
    public ArrayList<Employee> employeeList(EmployeeFilter employeeFilter) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        StringBuilder query = new StringBuilder();
        ResultSet resultSet = null;

        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee employee = null;

        try {
            connection = connectionPool.takeConnection();
            query.append(EMPLOYEE_LIST).append((employeeFilter.getPage() - 1) * NUMBER_OF_ROWS_PER_PAGE)
                    .append(LIMIT_SEPARATOR).append(NUMBER_OF_ROWS_PER_PAGE);

            resultSet = executeEmployeeListQuery(query, employeeFilter, connection);

            while (resultSet.next()) {
                employee = setEmployee(resultSet);
                employeeList.add(employee);
            }

            return employeeList;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting employee list", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting employee list", e);
            }
        }
    }

    @Override
    public ArrayList<Vacancy> vacancyListById(int idCompany, String page, String vacancyName) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        StringBuilder query = new StringBuilder();
        ResultSet resultSet = null;

        ArrayList<Vacancy> vacancyList = new ArrayList<>();
        Vacancy vacancy = null;

        try {
            connection = connectionPool.takeConnection();
            query.append(VACANCY_LIST_BY_ID).append((Integer.parseInt(page) - 1) * NUMBER_OF_ROWS_PER_PAGE)
                    .append(LIMIT_SEPARATOR).append(NUMBER_OF_ROWS_PER_PAGE);

            resultSet = executeVacancyListByIdQuery(query, idCompany, vacancyName, connection);

            while (resultSet.next()) {
                vacancy = setVacancy(resultSet);
                vacancyList.add(vacancy);
            }

            return vacancyList;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting vacancy list", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting vacancy list", e);
            }
        }
    }

    @Override
    public Map<Respond, Applicant> respondListForVacancy(RespondFilter respondFilter) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        StringBuilder query = new StringBuilder();
        ResultSet resultSet = null;

        Map<Respond, Applicant> respondMap = new LinkedHashMap<>();

        Respond respond = null;
        Applicant applicant = null;
        try {
            connection = connectionPool.takeConnection();
            query.append(RESPOND_LIST_FOR_VACANCY).append(respondFilter.getSort()).append(LIMIT_STATEMENT)
                    .append((respondFilter.getPage() - 1) * NUMBER_OF_ROWS_PER_PAGE).append(LIMIT_SEPARATOR)
                    .append(NUMBER_OF_ROWS_PER_PAGE);

            resultSet = executeRespondListForVacancyQuery(query, respondFilter, connection);

            while (resultSet.next()) {
                respond = setRespond(resultSet);
                applicant = setApplicant(resultSet);
                respondMap.put(respond, applicant);
            }

            return respondMap;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting respond list", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting respond list", e);
            }
        }
    }

    @Override
    public Map<Respond, Vacancy> respondListForApplicant(int idApplicant, String page) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        StringBuilder query = new StringBuilder();
        ResultSet resultSet = null;

        Map<Respond, Vacancy> respondMap = new LinkedHashMap<>();
        Respond respond = null;
        Vacancy vacancy = null;

        try {
            connection = connectionPool.takeConnection();
            query.append(RESPOND_LIST_FOR_APPLICANT).append((Integer.parseInt(page) - 1) * NUMBER_OF_ROWS_PER_PAGE)
                    .append(LIMIT_SEPARATOR).append(NUMBER_OF_ROWS_PER_PAGE);
            preparedStatement = connection.prepareStatement(String.valueOf(query));
            preparedStatement.setInt(1, idApplicant);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                respond = setRespond(resultSet);
                vacancy = setVacancy(resultSet);
                respondMap.put(respond, vacancy);
            }
            return respondMap;
        } catch (SQLException e) {
            throw new DAOException("SQLException during getting respond list", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting respond list", e);
            }
        }
    }

    @Override
    public Map<Vacancy, Company> latestVacancyList() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<Vacancy, Company> vacancyMap = new LinkedHashMap<>();
        Vacancy vacancy = null;
        Company company = null;

        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(LATEST_VACANCY_LIST);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vacancy = setVacancy(resultSet);
                company = setCompany(resultSet);
                vacancyMap.put(vacancy, company);
            }

            return vacancyMap;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting vacancy list", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting respond list", e);
            }
        }
    }

    @Override
    public ArrayList<Company> searchingCompanyList() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ArrayList<Company> companyList = new ArrayList<>();
        Company company = null;

        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(SEARCHING_COMPANY_LIST);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                company = setCompany(resultSet);
                companyList.add(company);
            }

            return companyList;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting company list", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting company list", e);
            }
        }
    }


    private ResultSet executeSearchVacancyQuery(StringBuilder query, VacancyFilter vacancyFilter, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
        preparedStatement.setString(1, vacancyFilter.getSearchString());
        preparedStatement.setString(2, vacancyFilter.getSearchString());
        preparedStatement.setString(3, vacancyFilter.getSearchString());
        preparedStatement.setString(4, vacancyFilter.getSearchString());
        preparedStatement.setInt(5, vacancyFilter.getMinExperienceFilter());
        preparedStatement.setInt(6, vacancyFilter.getMaxExperienceFilter());
        preparedStatement.setInt(7, vacancyFilter.getSalaryFilter());
        preparedStatement.setString(8, vacancyFilter.getCountryFilter());
        preparedStatement.setString(9, vacancyFilter.getCityFilter());
        return preparedStatement.executeQuery();
    }

    private ResultSet executeEmployeeListQuery(StringBuilder query, EmployeeFilter employeeFilter, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query));
        preparedStatement.setInt(1, employeeFilter.getIdCompany());
        preparedStatement.setString(2, employeeFilter.getFullName());
        preparedStatement.setString(3, employeeFilter.getEmail());
        preparedStatement.setString(4, employeeFilter.getPhone());
        return preparedStatement.executeQuery();
    }

    private ResultSet executeVacancyListByIdQuery(StringBuilder query, int idCompany, String vacancyName, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query));
        preparedStatement.setInt(1, idCompany);
        preparedStatement.setString(2, vacancyName);
        return preparedStatement.executeQuery();
    }

    private ResultSet executeRespondListForVacancyQuery(StringBuilder query, RespondFilter respondFilter, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query));
        preparedStatement.setInt(1, respondFilter.getIdVacancy());
        preparedStatement.setString(2, respondFilter.getLastName());
        preparedStatement.setString(3, respondFilter.getEmail());
        preparedStatement.setString(4, respondFilter.getPhone());
        preparedStatement.setString(5, respondFilter.getStage());
        preparedStatement.setInt(6, respondFilter.getMinMark());
        return preparedStatement.executeQuery();
    }


    private Vacancy setVacancy(ResultSet resultSet) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setIdVacancy(resultSet.getInt(Column.ID_VACANCY));
        vacancy.setName(resultSet.getString(Column.VACANCY_NAME));
        vacancy.setIdCompany(resultSet.getInt(Column.ID_COMPANY));
        vacancy.setSalary(resultSet.getInt(Column.SALARY));
        vacancy.setDate(resultSet.getDate(Column.DATE));
        vacancy.setRequiredExperience(resultSet.getInt(Column.REQUIRED_EXPERIENCE));
        vacancy.setRequiredSkills(resultSet.getString(Column.REQUIRED_SKILLS));
        vacancy.setVacancyDescription(resultSet.getString(Column.VACANCY_DESCRIPTION));
        return vacancy;
    }

    private Company setCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setIdCompany(resultSet.getInt(Column.ID_COMPANY));
        company.setCompanyName(resultSet.getString(Column.COMPANY_NAME));
        company.setCountry(resultSet.getString(Column.COUNTRY));
        company.setCity(resultSet.getString(Column.CITY));
        company.setCompanyDescription(resultSet.getString(Column.COMPANY_DESCRIPTION));
        company.setWebsite(resultSet.getString(Column.WEBSITE));
        return company;
    }

    private Employee setEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt(Column.ID_EMPLOYEE));
        employee.setEmail(resultSet.getString(Column.EMAIL));
        employee.setPhone(resultSet.getString(Column.PHONE));
        employee.setIdCompany(resultSet.getInt(Column.ID_COMPANY));
        employee.setFullName(resultSet.getString(Column.FULL_NAME));
        employee.setSkype(resultSet.getString(Column.SKYPE));
        employee.setStatus(Status.HR);
        return employee;
    }

    private Respond setRespond(ResultSet resultSet) throws SQLException {
        Respond respond = new Respond();
        respond.setIdApplicant(resultSet.getInt(Column.ID_APPLICANT));
        respond.setIdVacancy(resultSet.getInt(Column.ID_VACANCY));
        respond.setStage(Stage.valueOf(resultSet.getString(Column.STAGE).toUpperCase()));
        respond.setRespondDate(resultSet.getDate(Column.RESPOND_DATE));
        respond.setConversationDate(resultSet.getDate(Column.CONVERSATION_DATE));
        respond.setNote(resultSet.getString(Column.NOTE));
        respond.setMark(resultSet.getInt(Column.MARK));
        return respond;
    }

    private Applicant setApplicant(ResultSet resultSet) throws SQLException {
        Applicant applicant = new Applicant();
        applicant.setId(resultSet.getInt(Column.ID_APPLICANT));
        applicant.setEmail(resultSet.getString(Column.EMAIL));
        applicant.setPhone(resultSet.getString(Column.PHONE));
        applicant.setSkype(resultSet.getString(Column.SKYPE));
        applicant.setFirstName(resultSet.getString(Column.FIRST_NAME));
        applicant.setLastName(resultSet.getString(Column.LAST_NAME));
        applicant.setCountry(resultSet.getString(Column.COUNTRY));
        applicant.setCity(resultSet.getString(Column.CITY));
        applicant.setUniversity(resultSet.getString(Column.UNIVERSITY));
        applicant.setGraduationYear(resultSet.getInt(Column.GRADUATION_YEAR));
        applicant.setProfessionalSkills(resultSet.getString(Column.PROFESSIONAL_SKILLS));
        applicant.setEnglishLevel(EnglishLevel.valueOf(resultSet.getString(Column.ENGLISH_LEVEL).toUpperCase()));
        return applicant;
    }
}
