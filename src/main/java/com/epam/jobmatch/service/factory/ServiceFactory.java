package com.epam.jobmatch.service.factory;

import com.epam.jobmatch.service.*;
import com.epam.jobmatch.service.impl.*;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private SourceInitService sourceInitService = new DataBaseInit();
	private SignInService signInService = new SignIn();
	private SignUpService signUpService = new SignUp();
	private InfoService infoService = new Info();
	private EditService editService = new Editing();
	private InfoListService infoListService = new InfoList();
	private DeleteService deleteService = new Delete();

	private ServiceFactory(){}

	public static ServiceFactory getInstance(){
		return instance;
	}

	public SourceInitService getSourceInitService() {
		return sourceInitService;
	}

	public SignInService getSignInService() {
		return signInService;
	}

	public SignUpService getSignUpService() {
		return signUpService;
	}

	public InfoService getInfoService() {
		return infoService;
	}

	public EditService getEditService() {
		return editService;
	}

	public InfoListService getInfoListService() {
		return infoListService;
	}

	public DeleteService getDeleteService() {
		return deleteService;
	}
}
