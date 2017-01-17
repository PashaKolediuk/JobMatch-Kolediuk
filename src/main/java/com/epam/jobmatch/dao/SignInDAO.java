package com.epam.jobmatch.dao;

import com.epam.jobmatch.bean.entity.user.User;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;

public interface SignInDAO {

    User authorization(char[] password, String email) throws DAOException, MatchingException;

}
