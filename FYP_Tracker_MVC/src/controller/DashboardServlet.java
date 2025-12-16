package controller;

import model.EntityDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {

    private boolean requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth/login.jsp");
            return true;
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (requireLogin(req, resp)) return;

        try {
            req.setAttribute("totalUsers", EntityDAO.countUsers());
            req.setAttribute("totalRecords", EntityDAO.countEntities());
            req.setAttribute("statusSummary", EntityDAO.statusSummary());
            req.getRequestDispatcher("/dashboard/dashboard.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
