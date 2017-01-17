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
import com.epam.jobmatch.dao.InfoDAO;
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

public class JDBCInfoDAO implements InfoDAO {

    private static final Logger log = LogManager.getLogger(JDBCInfoDAO.class.getName());

    private static final int NUMBER_OF_ROWS_PER_PAGE = 5;

    private static final String COMPANY_INFORMATION = "SELECT * FROM company WHERE idCompany = ?";
    private static final String ADMIN_INFORMATION = "SELECT * FROM employee WHERE idCompany = ? and status LIKE 'admin'";
    private static final String APPLICANT_INFORMATION = "SELECT * FROM applicant WHERE idApplicant = ?";
    private static final String VACANCY_INFORMATION = "SELECT * FROM vacancy WHERE idVacancy = ?";
    private static final String RESPOND_INFORMATION = "SELECT * FROM respond WHERE idApplicant = ? AND idVacancy = ?";
    private static final String STATISTICS_INFORMATION = "SELECT (select COUNT(*) from applicant) AS applicant_count,\n" +
            "  (select COUNT(*) from company) AS company_count,\n" +
            "  (select COUNT(*) from vacancy) AS vacancy_count;";
    /*private static final String RESULT_CREATION = "INSERT INTO result(idApplicant, idVacancy, stage) VALUES (?, ?, \"phone\")";*/


    private static final String SEARCH_VACANCY_COUNT = "SELECT COUNT(*) FROM vacancy INNER JOIN company ON vacancy.idCompany = company.idCompany\n" +
            " WHERE (vacancy.name LIKE CONCAT('%', ?, '%') OR vacancy.requiredSkills LIKE CONCAT('%', ?, '%')\n" +
            " OR vacancy.vacancyDescription LIKE CONCAT('%', ?, '%') OR company.companyName LIKE CONCAT('%', ?, '%'))\n" +
            " AND vacancy.requiredExperience BETWEEN ? and ? AND vacancy.salary >= ? AND company.country LIKE CONCAT('%', ?, '%')\n" +
            "  AND  company.city LIKE CONCAT('%', ?, '%')\n" +
            " ORDER by ";
    private static final String EMPLOYEE_LIST_COUNT = "SELECT COUNT(*) FROM employee INNER JOIN company ON employee.idCompany = company.idCompany\n" +
            " WHERE company.idCompany = ? AND employee.status LIKE 'hr' AND employee.fullName LIKE CONCAT('%', ?, '%') COLLATE utf8_general_ci" +
            " AND employee.email LIKE CONCAT('%', ?, '%') AND employee.phone LIKE CONCAT('%', ?, '%') ORDER BY fullName ";
    private static final String VACANCY_LIST_BY_ID_COUNT = "SELECT COUNT(*) FROM vacancy INNER JOIN company ON vacancy.idCompany = company.idCompany\n" +
            " WHERE company.idCompany = ? AND vacancy.name LIKE CONCAT('%', ?, '%') ORDER by vacancy.date DESC";
    private static final String RESPOND_LIST_FOR_VACANCY_COUNT = "SELECT COUNT(*) FROM respond INNER JOIN applicant ON respond.idApplicant = applicant.idApplicant" +
            " WHERE respond.idVacancy = ? AND lastName LIKE CONCAT('%', ?, '%') AND applicant.email LIKE CONCAT('%', ?, '%') AND applicant.phone LIKE CONCAT('%', ?, '%') " +
            " AND respond.stage LIKE CONCAT('%', ?, '%') AND respond.totalMark >= ? ORDER by ";
    private static final String RESPOND_LIST_FOR_APPLICANT_COUNT = "SELECT COUNT(*) FROM respond INNER JOIN vacancy ON respond.idVacancy = vacancy.idVacancy\n" +
            " WHERE respond.idApplicant = ? ORDER by respondDate DESC ";


    @Override
    public Company companyInfo(String idCompany) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Company company = null;
        Employee admin = null;

        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(COMPANY_INFORMATION);
            preparedStatement.setString(1, idCompany);
            resultSet = preparedStatement.executeQuery();
            company = getCompany(resultSet);

            preparedStatement = connection.prepareStatement(ADMIN_INFORMATION);
            preparedStatement.setString(1, idCompany);
            resultSet = preparedStatement.executeQuery();
            admin = getEmployee(resultSet);

            company.setAdmin(admin);
            return company;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting company information", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting company information", e);
            }
        }
    }

    @Override
    public Vacancy vacancyInfo(String idVacancy) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        Vacancy vacancy = null;

        try {
            connection = connectionPool.takeConnection();

            vacancy = getVacancy(idVacancy, connection);

            return vacancy;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting vacancy information", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting vacancy information", e);
            }
        }
    }

    @Override
    public Applicant applicantInfo(String idApplicant) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        Applicant applicant = null;

        try {
            connection = connectionPool.takeConnection();

            applicant = getApplicant(idApplicant, connection);

            return applicant;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting applicant information", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting applicant information", e);
            }
        }
    }

    @Override
    public Respond respondInfo(String idApplicant, String idVacancy) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        Respond respond = null;
        try {
            connection = connectionPool.takeConnection();

            respond = getRespond(idApplicant, idVacancy, connection);

            return respond;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting respond information", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting respond information", e);
            }
        }
    }


    @Override
    public ArrayList<Integer> statisticsInfo() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ArrayList<Integer> statistics = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(STATISTICS_INFORMATION);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            statistics.add(resultSet.getInt(Column.APPLICANT_COUNT));
            statistics.add(resultSet.getInt(Column.COMPANY_COUNT));
            statistics.add(resultSet.getInt(Column.VACANCY_COUNT));

            return statistics;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting information", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting statistics information", e);
            }
        }
    }


    @Override
    public int getSearchListPageCount(VacancyFilter vacancyFilter) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        int pageCount = 0;

        try {
            connection = connectionPool.takeConnection();

            String query = SEARCH_VACANCY_COUNT + vacancyFilter.getSort();

            ResultSet resultSet = executeSearchedVacancyCountQuery(query, vacancyFilter, connection);
            resultSet.next();
            pageCount = (int) Math.ceil((double) resultSet.getInt(1) / NUMBER_OF_ROWS_PER_PAGE);
            return pageCount;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting search list page count", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting search list page count", e);
            }
        }
    }

    @Override
    public int getEmployeeListPageCount(EmployeeFilter employeeFilter) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        int pageCount = 0;

        try {
            connection = connectionPool.takeConnection();

            ResultSet resultSet = executeEmployeeListQuery(EMPLOYEE_LIST_COUNT, employeeFilter, connection);
            resultSet.next();
            pageCount = (int) Math.ceil((double) resultSet.getInt(1) / NUMBER_OF_ROWS_PER_PAGE);
            return pageCount;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting employee list page count", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting employee list page count", e);
            }
        }
    }

    @Override
    public int getVacancyListByIdPageCount(int idCompany, String vacancyName) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        int pageCount = 0;

        try {
            connection = connectionPool.takeConnection();

            ResultSet resultSet = executeVacancyListByIdQuery(VACANCY_LIST_BY_ID_COUNT, idCompany, vacancyName, connection);
            resultSet.next();
            pageCount = (int) Math.ceil((double) resultSet.getInt(1) / NUMBER_OF_ROWS_PER_PAGE);
            return pageCount;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting vacancy list page count", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting vacancy list page count", e);
            }
        }
    }

    @Override
    public int getRespondListForVacancyPageCount(RespondFilter respondFilter) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        int pageCount = 0;

        try {
            connection = connectionPool.takeConnection();

            String query = RESPOND_LIST_FOR_VACANCY_COUNT + respondFilter.getSort();

            ResultSet resultSet = executeRespondListForVacancyQuery(query, respondFilter, connection);
            resultSet.next();
            pageCount = (int) Math.ceil((double) resultSet.getInt(1) / NUMBER_OF_ROWS_PER_PAGE);
            return pageCount;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting respond list page count", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting respond list page count", e);
            }
        }
    }

    @Override
    public int getRespondListForApplicantPageCount(int idApplicant) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        int pageCount = 0;

        try {
            connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(RESPOND_LIST_FOR_APPLICANT_COUNT);
            preparedStatement.setInt(1, idApplicant);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            pageCount = (int) Math.ceil((double) resultSet.getInt(1) / NUMBER_OF_ROWS_PER_PAGE);
            return pageCount;

        } catch (SQLException e) {
            throw new DAOException("SQLException during getting respond list page count", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after getting respond list page count", e);
            }
        }
    }


    private ResultSet executeSearchedVacancyCountQuery(String query, VacancyFilter vacancyFilter, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
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

    private ResultSet executeEmployeeListQuery(String query, EmployeeFilter employeeFilter, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, employeeFilter.getIdCompany());
        preparedStatement.setString(2, employeeFilter.getFullName());
        preparedStatement.setString(3, employeeFilter.getEmail());
        preparedStatement.setString(4, employeeFilter.getPhone());
        return preparedStatement.executeQuery();
    }

    private ResultSet executeVacancyListByIdQuery(String query, int idCompany, String vacancyName, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCompany);
        preparedStatement.setString(2, vacancyName);
        return preparedStatement.executeQuery();
    }

    private ResultSet executeRespondListForVacancyQuery(String query, RespondFilter respondFilter, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, respondFilter.getIdVacancy());
        preparedStatement.setString(2, respondFilter.getLastName());
        preparedStatement.setString(3, respondFilter.getEmail());
        preparedStatement.setString(4, respondFilter.getPhone());
        preparedStatement.setString(5, respondFilter.getStage());
        preparedStatement.setInt(6, respondFilter.getMinMark());
        return preparedStatement.executeQuery();
    }


    private Company getCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        resultSet.next();
        company.setIdCompany(resultSet.getInt(Column.ID_COMPANY));
        company.setCompanyName(resultSet.getString(Column.COMPANY_NAME));
        company.setCountry(resultSet.getString(Column.COUNTRY));
        company.setCity(resultSet.getString(Column.CITY));
        company.setCompanyDescription(resultSet.getString(Column.COMPANY_DESCRIPTION));
        company.setWebsite(resultSet.getString(Column.WEBSITE));
        return company;
    }

    private Employee getEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        resultSet.next();
        employee.setId(resultSet.getInt(Column.ID_EMPLOYEE));
        employee.setEmail(resultSet.getString(Column.EMAIL));
        employee.setPhone(resultSet.getString(Column.PHONE));
        employee.setSkype(resultSet.getString(Column.SKYPE));
        employee.setIdCompany(resultSet.getInt(Column.ID_COMPANY));
        employee.setFullName(resultSet.getString(Column.FULL_NAME));
        employee.setStatus(Status.valueOf(resultSet.getString(Column.STATUS).toUpperCase()));
        return employee;
    }

    private Applicant getApplicant(String idApplicant, Connection connection) throws SQLException {
        Applicant applicant = new Applicant();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(APPLICANT_INFORMATION);
            preparedStatement.setString(1, idApplicant);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
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
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during applicant information prepared statement closing", e);
            }
        }
        return applicant;
    }

    private Respond getRespond(String idApplicant, String idVacancy, Connection connection) throws SQLException {
        Respond respond = new Respond();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(RESPOND_INFORMATION);
            preparedStatement.setString(1, idApplicant);
            preparedStatement.setString(2, idVacancy);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            respond.setIdApplicant(Integer.parseInt(idApplicant));
            respond.setIdVacancy(Integer.parseInt(idVacancy));
            respond.setStage(Stage.valueOf(resultSet.getString(Column.STAGE).toUpperCase()));
            respond.setRespondDate(resultSet.getDate(Column.RESPOND_DATE));
            respond.setConversationDate(resultSet.getDate(Column.CONVERSATION_DATE));
            respond.setNote(resultSet.getString(Column.NOTE));
            respond.setMark(resultSet.getInt(Column.MARK));
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during company information prepared statement closing", e);
            }
        }
        return respond;
    }

    private Vacancy getVacancy(String idVacancy, Connection connection) throws SQLException {
        Vacancy vacancy = new Vacancy();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(VACANCY_INFORMATION);
            preparedStatement.setString(1, idVacancy);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            vacancy.setIdVacancy(Integer.parseInt(idVacancy));
            vacancy.setName(resultSet.getString(Column.VACANCY_NAME));
            vacancy.setIdCompany(resultSet.getInt(Column.ID_COMPANY));
            vacancy.setSalary(resultSet.getInt(Column.SALARY));
            vacancy.setDate(resultSet.getDate(Column.DATE));
            vacancy.setRequiredExperience(resultSet.getInt(Column.REQUIRED_EXPERIENCE));
            vacancy.setRequiredSkills(resultSet.getString(Column.REQUIRED_SKILLS));
            vacancy.setVacancyDescription(resultSet.getString(Column.VACANCY_DESCRIPTION));
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during vacancy information prepared statement closing", e);
            }
        }
        return vacancy;
    }

}
