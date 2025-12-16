package controller;

import model.Entity;
import model.EntityDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class EntityUpdateServlet extends HttpServlet {

    private boolean requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth/login.jsp");
            return true;
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (requireLogin(req, resp)) return;

        try {
            Entity e = new Entity();
            e.setId(Integer.parseInt(req.getParameter("id")));
            e.setTitle(req.getParameter("title"));
            e.setDescription(req.getParameter("description"));
            e.setStatus(req.getParameter("status"));

            EntityDAO.update(e);
            resp.sendRedirect(req.getContextPath() + "/entities");
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
