package controller;

import model.EntityDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class EntityDeleteServlet extends HttpServlet {

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
            int id = Integer.parseInt(req.getParameter("id"));
            EntityDAO.delete(id);
            resp.sendRedirect(req.getContextPath() + "/entities");
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
