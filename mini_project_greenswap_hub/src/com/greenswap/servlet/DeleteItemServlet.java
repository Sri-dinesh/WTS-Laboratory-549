package com.greenswap.servlet;

import com.greenswap.dao.SwapItemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            SwapItemDAO dao = new SwapItemDAO();

            if (!dao.itemExists(id)) {
                request.setAttribute("errorMessage", "The item you tried to delete was not found.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            dao.deleteById(id);
            response.sendRedirect(request.getContextPath() + "/list-items");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Could not delete item: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
