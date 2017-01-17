package com.epam.jobmatch.dao.impl;

import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.bean.entity.user.User;
import com.epam.jobmatch.bean.entity.user.enumiration.EnglishLevel;
import com.epam.jobmatch.bean.entity.user.enumiration.Status;
import com.epam.jobmatch.dao.SignInDAO;
import com.epam.jobmatch.dao.connection_pool.ConnectionPool;
import com.epam.jobmatch.dao.connection_pool.exception.ConnectionPoolException;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;
import com.epam.jobmatch.dao.util.Column;
import com.epam.jobmatch.dao.util.MatchMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCSignInDAO implements SignInDAO {

    private static final Logger log = LogManager.getLogger(JDBCSignInDAO.class.getName());

    private static final String USER_AUTH = "SELECT * FROM applicant WHERE email LIKE ? AND password LIKE ?";
    private static final String EMPLOYEE_AUTH = "SELECT * FROM employee WHERE email LIKE ? AND password LIKE ?";

    @Override
    public User authorization(char[] password, String email) throws DAOException, MatchingException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();

            // Searching user among applicants in DB
            preparedStatement = connection.prepareStatement(USER_AUTH);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, String.copyValueOf(password));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                return applicantData(resultSet);
            }

            // Searching user among employees in DB
            preparedStatement = connection.prepareStatement(EMPLOYEE_AUTH);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, String.copyValueOf(password));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                return employeeData(resultSet);
            }

            // There is no such user
            throw new MatchingException(MatchMessage.INCORRECT_EMAIL_PASSWORD);

        } catch (SQLException e) {
            throw new DAOException("SQLException during authorization", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during taking connection from connection pool", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after authorization", e);
            }
        }
    }

    private Applicant applicantData(ResultSet resultSet) throws SQLException {
        Applicant applicant = new Applicant();
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
        return applicant;
    }

    private Employee employeeData(ResultSet resultSet) throws SQLException {
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
}
