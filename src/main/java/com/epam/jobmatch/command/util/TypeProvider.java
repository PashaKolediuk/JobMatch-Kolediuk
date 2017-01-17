package com.epam.jobmatch.command.util;

import com.epam.jobmatch.command.exception.TypeException;
import com.epam.jobmatch.command.impl.Type;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TypeProvider {

	private static final String PROPERTY_FILE = "control_parameters.xml";
	private static final String SEPARATOR = ".";

	public Type getType(String commandName, String typeName) throws TypeException {

		Properties properties = new Properties();
		Type typeObject = null;
		try(FileInputStream fileInputStream = new FileInputStream(getClass().getClassLoader().getResource(PROPERTY_FILE).getPath())) {

			properties.loadFromXML(fileInputStream);
			String className = properties.getProperty(Parameter.TYPE + SEPARATOR + commandName + SEPARATOR + typeName);
			typeObject = (Type) Class.forName(className).newInstance();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			throw new TypeException(e);
		}
		return typeObject;
	}

}
