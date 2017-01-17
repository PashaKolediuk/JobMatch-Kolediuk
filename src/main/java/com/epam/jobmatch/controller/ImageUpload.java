package com.epam.jobmatch.controller;

import com.epam.jobmatch.command.Command;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.controller.exception.CommandException;
import com.epam.jobmatch.controller.util.CommandProvider;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class ImageUpload extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ImageUpload.class.getName());

    private static final CommandProvider provider = new CommandProvider();



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToRedirect = null;
        try {
            pageToRedirect = processRequest(request, response);
        } catch (Exception e) {
            log.error("Unexpected error", e);
            pageToRedirect = Page.ERROR;
        }
        response.sendRedirect(response.encodeURL(request.getContextPath() + pageToRedirect));
    }

    private String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToRedirect = null;
        try {
            List<FileItem> itemList = getFileItemListFromRequest(request);
            request.setAttribute(Attribute.ITEM_LIST, itemList);

            String commandName = getCommandFromRequest(itemList);
            Command command = provider.getCommand(commandName);

            pageToRedirect = command.execute(request, response);
        } catch (CommandException | FileUploadException e) {
            log.error("Error during matching command value", e);
            pageToRedirect = Page.ERROR;
        }
        return pageToRedirect;
    }

    private List<FileItem> getFileItemListFromRequest(HttpServletRequest request) throws FileUploadException {
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        return upload.parseRequest(request);
    }

    private String getCommandFromRequest(List<FileItem> itemList) {
        String command = null;
        for (FileItem item : itemList) {
            if (item.isFormField() && item.getFieldName().equals(Parameter.COMMAND)) {
                command = item.getString();
            }
        }
        return command;
    }

}
