package com.epam.jobmatch.dao.factory;

import com.epam.jobmatch.dao.*;
import com.epam.jobmatch.dao.impl.*;

public class DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactory();

	private SourceInit sourceInit = new ConnectionPoolInit();
	private SignUpDAO signUpDAO = new JDBCSignUpDAO();
	private SignInDAO signInDAO = new JDBCSignInDAO();
	private InfoDAO infoDAO = new JDBCInfoDAO();
	private EditingDAO editingDAO = new JDBCEditingDAO();
	private InfoListDAO infoListDAO = new JDBCInfoListDAO();
	private DeleteDAO deleteDAO = new JDBCDeleteDAO();

	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return INSTANCE;
	}

	public SourceInit getSourceInit() {
		return sourceInit;
	}

	public SignUpDAO getSignUpDAO() {
		return signUpDAO;
	}

	public SignInDAO getSignInDAO() {
		return signInDAO;
	}

	public InfoDAO getInfoDAO() {
		return infoDAO;
	}

	public EditingDAO getEditingDAO() {
		return editingDAO;
	}

	public InfoListDAO getInfoListDAO() {
		return infoListDAO;
	}

	public DeleteDAO getDeleteDAO() {
		return deleteDAO;
	}
}
