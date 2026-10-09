package com.greenswap.servlet;

import com.greenswap.dao.SwapItemDAO;
import com.greenswap.model.SwapItem;
import com.greenswap.util.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String itemName = trim(request.getParameter("itemName"));
        String category = trim(request.getParameter("category"));
        String description = trim(request.getParameter("description"));
        String itemCondition = trim(request.getParameter("itemCondition"));
        String contactEmail = trim(request.getParameter("contactEmail"));

        if (!isValidSubmission(itemName, category, description, itemCondition, contactEmail)) {
            request.setAttribute("errorMessage",
                    "Please complete all fields with valid values before posting an item.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        SwapItem item = new SwapItem();
        item.setItemName(itemName);
        item.setCategory(category);
        item.setDescription(description);
        item.setItemCondition(itemCondition);
        item.setContactEmail(contactEmail);
        item.setStatus("Available");

        try {
            new SwapItemDAO().addItem(item);
            response.sendRedirect(request.getContextPath() + "/list-items");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Could not add item: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private boolean isValidSubmission(String itemName, String category, String description, String itemCondition,
            String contactEmail) {
        return !InputValidator.isBlank(itemName)
                && !InputValidator.isBlank(category)
                && !InputValidator.isBlank(description)
                && !InputValidator.isBlank(itemCondition)
                && InputValidator.isValidEmail(contactEmail)
                && InputValidator.isSafeText(itemName)
                && InputValidator.isSafeText(category)
                && InputValidator.isSafeText(description)
                && InputValidator.isSafeText(itemCondition)
                && InputValidator.isSafeText(contactEmail);
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
