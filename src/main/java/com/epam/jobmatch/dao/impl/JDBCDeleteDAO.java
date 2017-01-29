package com.epam.jobmatch.dao.impl;

import com.epam.jobmatch.dao.DeleteDAO;
import com.epam.jobmatch.dao.connection_pool.ConnectionPool;
import com.epam.jobmatch.dao.connection_pool.exception.ConnectionPoolException;
import com.epam.jobmatch.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDeleteDAO implements DeleteDAO {

    private static final Logger log = LogManager.getLogger(JDBCDeleteDAO.class.getName());

    private static final String DELETE_RESPOND_FOR_APPLICANT = "DELETE FROM respond WHERE idApplicant = ? AND idVacancy = ?";
    private static final String DELETE_VACANCY = "DELETE FROM vacancy WHERE idVacancy = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE idEmployee = ?";

    @Override
    public void deleteRespondForApplicant(int idApplicant, String idVacancy) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(DELETE_RESPOND_FOR_APPLICANT);
            preparedStatement.setInt(1, idApplicant);
            preparedStatement.setString(2, idVacancy);

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during respond deleting", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after respond deleting", e);
            }
        }
    }

    @Override
    public void deleteVacancy(String idVacancy) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(DELETE_VACANCY);
            preparedStatement.setString(1, idVacancy);

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during vacancy deleting", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after vacancy deleting", e);
            }
        }
    }

    @Override
    public void deleteEmployee(String idEmployee) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);
            preparedStatement.setString(1, idEmployee);

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error during taking connection from connection pool", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException during employee deleting", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during connection closing after employee deleting", e);
            }
        }
    }
}
