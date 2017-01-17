package com.epam.jobmatch.command.impl.image_command.type_impl;

import com.epam.jobmatch.bean.entity.user.Employee;
import com.epam.jobmatch.command.impl.Type;
import com.epam.jobmatch.command.util.Attribute;
import com.epam.jobmatch.command.util.Page;
import com.epam.jobmatch.command.util.Request;
import com.epam.jobmatch.service.EditService;
import com.epam.jobmatch.service.exception.ServiceException;
import com.epam.jobmatch.service.factory.ServiceFactory;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ImageEditingType implements Type {

    private static final String UPLOAD_DIR = "/upload/";
    private static final String IMAGE_FORMAT = ".png";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EditService editService = serviceFactory.getEditService();

        List<FileItem> itemList = (List<FileItem>) request.getAttribute(Attribute.ITEM_LIST);

        int idCompany = ((Employee) request.getSession().getAttribute(Attribute.EMPLOYEE)).getIdCompany();
        String path = request.getSession().getServletContext().getRealPath(UPLOAD_DIR + idCompany + IMAGE_FORMAT);

        for (FileItem item : itemList) {
            if (!item.isFormField()) {
                editService.imageEditing(item, path);
            }
        }
        return Request.GET_COMPANY_INFO_BY_ID + idCompany;
    }
}
