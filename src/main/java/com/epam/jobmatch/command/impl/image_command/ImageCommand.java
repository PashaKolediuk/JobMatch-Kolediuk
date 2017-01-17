package com.epam.jobmatch.command.impl.image_command;

import com.epam.jobmatch.command.Command;
import com.epam.jobmatch.command.exception.TypeException;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Parameter;
import com.epam.jobmatch.command.util.TypeProvider;
import com.epam.jobmatch.service.exception.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ImageCommand implements Command {

    private static final Logger log = LogManager.getLogger(ImageCommand.class.getName());

    private static final TypeProvider provider = new TypeProvider();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToRedirect = null;
        try {
            List<FileItem> itemList = (List<FileItem>) request.getAttribute(Attribute.ITEM_LIST);

            String commandName = getCommandFromRequest(itemList);
            String typeName = getTypeFromRequest(itemList);

            Type type = provider.getType(commandName, typeName);
            pageToRedirect = type.execute(request, response);

        } catch (TypeException e) {
            log.error("Error during matching type value", e);
            pageToRedirect = Page.ERROR;
        } catch (ServiceException e) {
            log.error(e);
            pageToRedirect = Page.ERROR;
        }
        return pageToRedirect;
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

    private String getTypeFromRequest(List<FileItem> itemList) {
        String type = null;
        for (FileItem item : itemList) {
            if (item.isFormField() && item.getFieldName().equals(Parameter.TYPE)) {
                type = item.getString();
            }
        }
        return type;
    }
}
