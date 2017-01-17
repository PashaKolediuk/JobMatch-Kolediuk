package com.epam.jobmatch.service;

import com.epam.jobmatch.bean.entity.user.User;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.exception.ValidationException;

public interface SignInService {
    User authorization(char[] password, String email) throws ServiceException, ValidationException;
}