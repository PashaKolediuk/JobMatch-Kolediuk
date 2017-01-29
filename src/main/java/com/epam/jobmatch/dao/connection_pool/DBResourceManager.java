package com.epam.jobmatch.dao.connection_pool;

import java.util.ResourceBundle;

class DBResourceManager {

	private static final DBResourceManager instance = new DBResourceManager();

	private static final String DB_PROPERTIES = "db";

	private ResourceBundle bundle = ResourceBundle.getBundle(DB_PROPERTIES);
	
	public static DBResourceManager getInstance() {
		return instance;
	}
	
	String getValue(String key) {
		return bundle.getString(key);
	}
	
}
