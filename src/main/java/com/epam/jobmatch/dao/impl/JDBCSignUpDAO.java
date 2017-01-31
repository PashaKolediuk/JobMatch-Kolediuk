package com.epam.jobmatch.dao.impl;

import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.bean.entity.user.User;
import com.epam.jobmatch.bean.entity.user.enumiration.EnglishLevel;
import com.epam.jobmatch.bean.entity.user.enumiration.Status;
import com.epam.jobmatch.dao.SignUpDAO;
import com.epam.jobmatch.dao.connection_pool.ConnectionPool;
import com.epam.jobmatch.dao.connection_pool.exception.ConnectionPoolException;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;
import com.epam.jobmatch.dao.util.Column;
import com.epam.jobmatch.dao.util.MatchMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Calendar;

public class JDBCSignUpDAO implements SignUpDAO {

    private static final Logger log = LogManager.getLogger(JDBCSignUpDAO.class.getName());

    private static final String COMPANY_REGISTRATION = "INSERT INTO company(companyName, country, city, companyDescription, website) " +
            " VALUES (?, ?, ?, ?, ?)";
    private static final String ADMIN_REGISTRATION = "INSERT INTO employee(password, email, idCompany, fullName, phone, skype, status)" +
            " VALUES (?, ?, LAST_INSERT_ID(), ?, ?, ?, \"admin\")";
    private static final String EMPLOYEE_REGISTRATION = "INSERT INTO employee(password, email, idCompany, fullName, phone, skype, status)" +
            " VALUES (?, ?, ?, ?, ?, ?, \"hr\")";
    private static final String APPLICANT_REGISTRATION = "INSERT INTO applicant(email, password, firstName, lastName, phone, skype, country, city, university, graduationYear, englishLevel, professionalSkills) " +
            "VALUES (?, ?, ?, ?, ? , ?, ?, ?, ?, ? , ?, ?)";
    private static final String VACANCY_REGISTRATION = "INSERT INTO vacancy(name, idCompany, salary, date, requiredExperience, requiredSkills, vacancyDescription) " +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String RESPOND_REGISTRATION = "INSERT INTO respond(idApplicant, idVacancy, respondDate)" +
            " VALUES (?, ?, ?)";

    private static final String COMPANY_MATCHING = "SELECT * FROM company WHERE company.companyName LIKE ? OR company.website LIKE ?";
    private static final String USER_MATCHING = "SELECT applicant.idApplicant FROM applicant WHERE (applicant.email LIKE ? OR applicant.skype LIKE ?)  AND applicant.skype not LIKE '' " +
            "union SELECT employee.idEmployee FROM employee WHERE (employee.email LIKE ? OR employee.skype LIKE ?)  AND employee.skype not LIKE '' ";
    private static final String RESPOND_MATCHING = "SELECT * FROM respond WHERE respond.idApplicant LIKE ? AND respond.idVacancy LIKE ?";

    private static final String ADMIN_INFO = "SELECT * FROM employee WHERE idEmployee = LAST_INSERT_ID()";
    private static final String APPLICANT_INFO = "SELECT * FROM applicant WHERE idApplicant = LAST_INSERT_ID()";

    @Override
    public Employee companyRegistration(Company company) throws DAOException, MatchingException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        Employee admin = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            if (isCompanyExists(company, connection)) {
                throw new MatchingException(MatchMessage.COMPANY_EXISTS);
            }

            if (isUserExists(company.getAdmin(), connection)) {
                throw new MatchingException(MatchMessage.USER_EXISTS);
            }

            executeCompanyInsertion(company, connection);

            admin = getAdmin(connection);

            connection.commit();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Exception during transaction rolling back", ex);
            }
            throw new DAOException("SQLException during company registration", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after company registration", e);
            }
        }
        return admin;
    }

    @Override
    public void employeeRegistration(Employee employee) throws DAOException, MatchingException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            if (isUserExists(employee, connection)) {
                throw new MatchingException(MatchMessage.USER_EXISTS);
            }

            executeEmployeeInsertion(employee, connection);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during employee registration", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after employee registration", e);
            }
        }
    }

    @Override
    public Applicant applicantRegistration(Applicant applicant) throws DAOException, MatchingException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();

            if (isUserExists(applicant, connection)) {
                throw new MatchingException(MatchMessage.USER_EXISTS);
            }

            executeApplicantInsertion(applicant, connection);

            applicant = getApplicant(connection);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during applicant registration", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after applicant registration", e);
            }
        }
        return applicant;
    }

    @Override
    public void vacancyRegistration(Vacancy vacancy) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            executeVacancyInsertion(vacancy, connection);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during vacancy registration", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after vacancy registration", e);
            }
        }
    }

    @Override
    public void respondRegistration(int idApplicant, int idVacancy) throws DAOException, MatchingException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            if (isRespondExists(idApplicant, idVacancy, connection)) {
                throw new MatchingException(MatchMessage.RESPOND_EXISTS);
            }

            executeRespondInsertion(idApplicant, idVacancy, connection);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during respond registration", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after respond registration", e);
            }
        }
    }


    private boolean isCompanyExists(Company company, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(COMPANY_MATCHING);
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getWebsite());
            resultSet = preparedStatement.executeQuery();
            return resultSet.isBeforeFirst();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during registration prepared statement closing", e);
            }
        }
    }

    private boolean isUserExists(User admin, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(USER_MATCHING);
            preparedStatement.setString(1, admin.getEmail());
            preparedStatement.setString(2, admin.getSkype());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, admin.getSkype());
            resultSet = preparedStatement.executeQuery();
            return resultSet.isBeforeFirst();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during registration prepared statement closing", e);
            }
        }
    }

    private boolean isRespondExists(int idApplicant, int idVacancy, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(RESPOND_MATCHING);
            preparedStatement.setInt(1, idApplicant);
            preparedStatement.setInt(2, idVacancy);
            resultSet = preparedStatement.executeQuery();
            return resultSet.isBeforeFirst();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during registration prepared statement closing", e);
            }
        }
    }


    private void executeCompanyInsertion(Company company, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement(COMPANY_REGISTRATION);
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getCountry());
            preparedStatement.setString(3, company.getCity());
            preparedStatement.setString(4, company.getCompanyDescription());
            preparedStatement.setString(5, company.getWebsite());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ADMIN_REGISTRATION);
            preparedStatement.setString(1, String.copyValueOf(company.getAdmin().getPassword()));
            preparedStatement.setString(2, company.getAdmin().getEmail());
            preparedStatement.setString(3, company.getAdmin().getFullName());
            preparedStatement.setString(4, company.getAdmin().getPhone());
            preparedStatement.setString(5, company.getAdmin().getSkype().isEmpty() ?
                    null : company.getAdmin().getSkype());
            preparedStatement.executeUpdate();

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during prepared statement closing after company insertion", e);
            }
        }
    }

    private void executeEmployeeInsertion(Employee employee, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(EMPLOYEE_REGISTRATION);
            preparedStatement.setString(1, String.copyValueOf(employee.getPassword()));
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3, employee.getIdCompany());
            preparedStatement.setString(4, employee.getFullName());
            preparedStatement.setString(5, employee.getPhone());
            preparedStatement.setString(6, employee.getSkype().isEmpty() ?
                    null : employee.getSkype());
            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during prepared statement closing after employee insertion", e);
            }
        }
    }

    private void executeApplicantInsertion(Applicant applicant, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(APPLICANT_REGISTRATION);
            preparedStatement.setString(1, applicant.getEmail());
            preparedStatement.setString(2, String.copyValueOf(applicant.getPassword()));
            preparedStatement.setString(3, applicant.getFirstName());
            preparedStatement.setString(4, applicant.getLastName());
            preparedStatement.setString(5, applicant.getPhone());
            preparedStatement.setString(6, applicant.getSkype().isEmpty() ?
                    null : applicant.getSkype());
            preparedStatement.setString(7, applicant.getCountry());
            preparedStatement.setString(8, applicant.getCity());
            preparedStatement.setString(9, applicant.getUniversity());
            preparedStatement.setInt(10, applicant.getGraduationYear());
            preparedStatement.setString(11, applicant.getEnglishLevel().toString());
            preparedStatement.setString(12, applicant.getProfessionalSkills());
            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during prepared statement closing after applicant insertion", e);
            }
        }
    }

    private void executeVacancyInsertion(Vacancy vacancy, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(VACANCY_REGISTRATION);
            preparedStatement.setString(1, vacancy.getName());
            preparedStatement.setInt(2, vacancy.getIdCompany());
            preparedStatement.setInt(3, vacancy.getSalary());
            preparedStatement.setDate(4, vacancy.getDate());
            preparedStatement.setInt(5, vacancy.getRequiredExperience());
            preparedStatement.setString(6, vacancy.getRequiredSkills());
            preparedStatement.setString(7, vacancy.getVacancyDescription());
            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during prepared statement closing after vacancy insertion", e);
            }
        }
    }

    private void executeRespondInsertion(int idApplicant, int idVacancy, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(RESPOND_REGISTRATION);
            preparedStatement.setInt(1, idApplicant);
            preparedStatement.setInt(2, idVacancy);
            preparedStatement.setDate(3, new Date(Calendar.getInstance().getTime().getTime()));
            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during prepared statement closing after respond insertion", e);
            }
        }
    }

    private Employee getAdmin(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee employee = new Employee();
        try {

            preparedStatement = connection.prepareStatement(ADMIN_INFO);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            employee.setId(resultSet.getInt(Column.ID_EMPLOYEE));
            employee.setEmail(resultSet.getString(Column.EMAIL));
            employee.setPhone(resultSet.getString(Column.PHONE));
            employee.setSkype(resultSet.getString(Column.SKYPE));
            employee.setIdCompany(resultSet.getInt(Column.ID_COMPANY));
            employee.setFullName(resultSet.getString(Column.FULL_NAME));
            employee.setStatus(Status.valueOf(resultSet.getString(Column.STATUS).toUpperCase()));

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during prepared statement closing after company insertion", e);
            }
        }

        return employee;
    }

    private Applicant getApplicant(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Applicant applicant = new Applicant();
        try {

            preparedStatement = connection.prepareStatement(APPLICANT_INFO);
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
                log.error("Error during prepared statement closing after company insertion", e);
            }
        }

        return applicant;
    }
}
