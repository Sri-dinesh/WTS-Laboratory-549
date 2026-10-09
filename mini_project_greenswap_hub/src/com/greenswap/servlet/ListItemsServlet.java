package com.greenswap.servlet;

import com.greenswap.dao.SwapItemDAO;
import com.greenswap.model.SwapItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<SwapItem> items = new SwapItemDAO().findAll();
            request.setAttribute("items", items);
            request.getRequestDispatcher("list-items.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Could not load items: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
