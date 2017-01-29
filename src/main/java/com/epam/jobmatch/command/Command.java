package com.epam.jobmatch.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command{

	/**
	 * Executes necessary command and prepares response to user.
	 *
	 * @param request the {@link javax.servlet.http.HttpServletRequest} object
	 * @param response the {@link javax.servlet.http.HttpServletResponse} object
	 *
	 * @return path to redirect user
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
