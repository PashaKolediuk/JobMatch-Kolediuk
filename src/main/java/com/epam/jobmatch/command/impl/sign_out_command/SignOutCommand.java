package com.epam.jobmatch.command.impl.sign_out_command;

import com.epam.jobmatch.command.Command;
import com.epam.jobmatch.command.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        return Page.INDEX;
    }
}
