package com.greenswap.servlet;

import com.greenswap.dao.SwapItemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClaimItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = parseId(request.getParameter("id"));
            SwapItemDAO dao = new SwapItemDAO();

            if (!dao.itemExists(id)) {
                request.setAttribute("errorMessage", "The item you selected no longer exists.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            boolean updated = dao.markClaimed(id);
            if (!updated) {
                request.setAttribute("errorMessage", "The item was already claimed or could not be updated.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            response.sendRedirect(request.getContextPath() + "/list-items");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Could not mark item as claimed: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private int parseId(String value) {
        return Integer.parseInt(value);
    }
}
