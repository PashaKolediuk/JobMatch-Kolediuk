package com.epam.jobmatch.controller.util;

import com.epam.jobmatch.command.Command;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.controller.exception.CommandException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommandProvider {

    private static final String PROPERTY_FILE = "control_parameters.xml";
    private static final String SEPARATOR = ".";

    public Command getCommand(String commandName) throws CommandException {

        Properties properties = new Properties();
        Command commandObject = null;
        try (FileInputStream fileInputStream = new FileInputStream(getClass().getClassLoader().getResource(PROPERTY_FILE).getPath())) {

            properties.loadFromXML(fileInputStream);
            String className = properties.getProperty(Parameter.COMMAND + SEPARATOR + commandName);
            commandObject = (Command) Class.forName(className).newInstance();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            throw new CommandException(e);
        }
        return commandObject;
    }

}
