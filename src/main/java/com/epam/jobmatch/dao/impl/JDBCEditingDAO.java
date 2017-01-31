package com.epam.jobmatch.dao.impl;


import com.epam.jobmatch.bean.entity.Company;
import com.epam.jobmatch.bean.entity.Respond;
import com.epam.jobmatch.bean.entity.Vacancy;
import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.bean.entity.user.User;
import com.epam.jobmatch.dao.EditingDAO;
import com.epam.jobmatch.dao.connection_pool.ConnectionPool;
import com.epam.jobmatch.dao.connection_pool.exception.ConnectionPoolException;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;
import com.epam.jobmatch.dao.util.MatchMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCEditingDAO implements EditingDAO {

    private static final Logger log = LogManager.getLogger(JDBCEditingDAO.class.getName());

    private static final String COMPANY_EDITING = "UPDATE company SET companyName = ?, country = ?, city = ?, " +
            " companyDescription = ?, website = ? WHERE idCompany = ?";
    private static final String RESPOND_EDITING = "UPDATE respond SET stage = ?, conversationDate = ?, conversationNote = ?, " +
            "             totalMark = ? WHERE idApplicant = ? AND idVacancy = ?";

    private static final String APPLICANT_EDITING = "UPDATE applicant SET email = ?, firstName = ?, " +
            " lastName = ?, phone = ?, skype = ?, country = ?, city = ?, university = ?, graduationYear = ?, " +
            " englishLevel = ?, professionalSkills = ? WHERE idApplicant = ?";
    private static final String APPLICANT_PASSWORD_EDITING = "UPDATE applicant SET password = ? WHERE idApplicant = ?";

    private static final String EMPLOYEE_PROFILE_EDITING = "UPDATE employee SET fullName = ?, email = ?, " +
            " phone = ?, skype = ? WHERE idEmployee = ?";
    private static final String EMPLOYEE_PASSWORD_EDITING = "UPDATE employee SET password = ? WHERE idEmployee = ?";

    private static final String VACANCY_EDITING = "UPDATE vacancy SET name = ?, requiredExperience = ?, " +
            " requiredSkills = ?, vacancyDescription = ?, salary = ?, date = ? WHERE idVacancy = ?";

    private static final String COMPANY_MATCHING = "SELECT * FROM company WHERE (company.companyName LIKE ? OR company.website LIKE ?) AND company.idCompany NOT LIKE ?";
    private static final String USER_MATCHING = "SELECT applicant.idApplicant FROM applicant WHERE (applicant.email LIKE ? OR applicant.skype LIKE ?) AND applicant.idApplicant NOT LIKE ? " +
            " AND applicant.skype not LIKE '' " +
            " union SELECT employee.idEmployee FROM employee WHERE (employee.email LIKE ? OR employee.skype LIKE ?) AND employee.idEmployee NOT LIKE ?  AND employee.skype not LIKE ''";

    @Override
    public void editCompanyInfo(Company company) throws DAOException, MatchingException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            if (isCompanyExists(company, connection)) {
                throw new MatchingException(MatchMessage.COMPANY_EXISTS);
            }

            executeCompanyInfoEditing(company, connection);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during company editing", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after company editing", e);
            }
        }
    }

    @Override
    public void editRespondInfo(Respond newRespond) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            executeRespondInfoEditing(newRespond, connection);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during respond editing", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after respond editing", e);
            }
        }
    }

    @Override
    public void editApplicantProfileInfo(Applicant applicant) throws DAOException, MatchingException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            if (isUserExists(applicant, connection)) {
                throw new MatchingException(MatchMessage.USER_EXISTS);
            }

            executeApplicantEditing(applicant, connection);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during applicant editing", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after applicant editing", e);
            }
        }
    }

    @Override
    public void editApplicantPassword(int idApplicant, char[] encryptedPassword) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(APPLICANT_PASSWORD_EDITING);
            preparedStatement.setString(1, String.copyValueOf(encryptedPassword));
            preparedStatement.setInt(2, idApplicant);
            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during applicant editing", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after applicant password editing", e);
            }
        }
    }

    @Override
    public void editEmployeeProfileInfo(Employee employee) throws DAOException, MatchingException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            if (isUserExists(employee, connection)) {
                throw new MatchingException(MatchMessage.USER_EXISTS);
            }

            executeEmployeeProfileEditing(employee, connection);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during employee editing", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after employee editing", e);
            }
        }
    }

    @Override
    public void editEmployeePassword(String idEmployee, char[] encryptedPassword) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(EMPLOYEE_PASSWORD_EDITING);
            preparedStatement.setString(1, String.copyValueOf(encryptedPassword));
            preparedStatement.setString(2, idEmployee);
            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during employee editing", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after employee password editing", e);
            }
        }
    }

    @Override
    public void editVacancyInfo(Vacancy newVacancy) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {

            connection = connectionPool.takeConnection();

            executeVacancyEditing(newVacancy, connection);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during vacancy editing", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after vacancy editing", e);
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
            preparedStatement.setInt(3, company.getIdCompany());
            resultSet = preparedStatement.executeQuery();
            return resultSet.isBeforeFirst();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during matching prepared statement closing", e);
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
            preparedStatement.setInt(3, admin.getId());
            preparedStatement.setString(4, admin.getEmail());
            preparedStatement.setString(5, admin.getSkype());
            preparedStatement.setInt(6, admin.getId());
            resultSet = preparedStatement.executeQuery();
            return resultSet.isBeforeFirst();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during matching prepared statement closing", e);
            }
        }
    }


    private void executeVacancyEditing(Vacancy vacancy, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(VACANCY_EDITING);
            preparedStatement.setString(1, vacancy.getName());
            preparedStatement.setInt(2, vacancy.getRequiredExperience());
            preparedStatement.setString(3, vacancy.getRequiredSkills());
            preparedStatement.setString(4, vacancy.getVacancyDescription());
            preparedStatement.setInt(5, vacancy.getSalary());
            preparedStatement.setDate(6, vacancy.getDate());
            preparedStatement.setInt(7, vacancy.getIdVacancy());
            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during vacancy editing prepared statement closing", e);
            }
        }
    }

    private void executeEmployeeProfileEditing(Employee employee, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(EMPLOYEE_PROFILE_EDITING);
            preparedStatement.setString(1, employee.getFullName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getPhone());
            preparedStatement.setString(4, employee.getSkype().isEmpty() ?
                    null : employee.getSkype());
            preparedStatement.setInt(5, employee.getId());

            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during vacancy employee prepared statement closing", e);
            }
        }
    }

    private void executeApplicantEditing(Applicant applicant, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(APPLICANT_EDITING);
            preparedStatement.setString(1, applicant.getEmail());
            preparedStatement.setString(2, applicant.getFirstName());
            preparedStatement.setString(3, applicant.getLastName());
            preparedStatement.setString(4, applicant.getPhone());
            preparedStatement.setString(5, applicant.getSkype().isEmpty() ?
                    null : applicant.getSkype());
            preparedStatement.setString(6, applicant.getCountry());
            preparedStatement.setString(7, applicant.getCity());
            preparedStatement.setString(8, applicant.getUniversity());
            preparedStatement.setInt(9, applicant.getGraduationYear());
            preparedStatement.setString(10, applicant.getEnglishLevel().toString());
            preparedStatement.setString(11, applicant.getProfessionalSkills());
            preparedStatement.setInt(12, applicant.getId());
            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during applicant editing prepared statement closing", e);
            }
        }
    }

    private void executeCompanyInfoEditing(Company company, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(COMPANY_EDITING);
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getCountry());
            preparedStatement.setString(3, company.getCity());
            preparedStatement.setString(4, company.getCompanyDescription());
            preparedStatement.setString(5, company.getWebsite());
            preparedStatement.setInt(6, company.getIdCompany());
            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during editing prepared statement closing", e);
            }
        }
    }

    private void executeRespondInfoEditing(Respond newResult, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(RESPOND_EDITING);
            preparedStatement.setString(1, newResult.getStage().toString().toLowerCase());
            preparedStatement.setDate(2, newResult.getConversationDate());
            preparedStatement.setString(3, newResult.getNote());
            preparedStatement.setInt(4, newResult.getMark());
            preparedStatement.setInt(5, newResult.getIdApplicant());
            preparedStatement.setInt(6, newResult.getIdVacancy());
            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error during editing prepared statement closing", e);
            }
        }
    }

}
