package com.epam.jobmatch.service.impl;

import com.epam.jobmatch.bean.entity.user.User;
import com.epam.jobmatch.dao.SignInDAO;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;
import com.epam.jobmatch.dao.factory.DAOFactory;
import com.epam.jobmatch.service.SignInService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;
import com.epam.jobmatch.service.util.ValidationHandler;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.epam.jobmatch.service.util.Encryption.encryptedValue;

public class SignIn implements SignInService {

    @Override
    public User authorization(char[] password, String email) throws ServiceException, ValidationException {
        try {
            if (password == null) {
                throw new ServiceException("Invalid password for authorization");
            }
            ValidationHandler.stringValidation(email);

            DAOFactory factory = DAOFactory.getInstance();
            SignInDAO signInDAO = factory.getSignInDAO();

            char[] encryptedPassword = encryptedValue(password);

            User user = signInDAO.authorization(encryptedPassword, email);

            Arrays.fill(encryptedPassword, '\u0000');

            return user;
        } catch (DAOException | NoSuchAlgorithmException | ValidationException e) {
            throw new ServiceException(e);
        } catch (MatchingException e) {
            throw new ValidationException(e.getMessage(), e);
        }
    }
}
