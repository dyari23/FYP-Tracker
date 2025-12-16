package controller;

import model.Entity;
import model.EntityDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class EntityCreateServlet extends HttpServlet {

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

        Entity e = new Entity();
        e.setTitle(req.getParameter("title"));
        e.setDescription(req.getParameter("description"));
        e.setStatus(req.getParameter("status"));

        try {
            if (e.getTitle() == null || e.getTitle().trim().isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/entity/create.jsp?error=Title%20is%20required");
                return;
            }
            if (e.getStatus() == null || e.getStatus().trim().isEmpty()) e.setStatus("New");

            EntityDAO.insert(e);
            resp.sendRedirect(req.getContextPath() + "/entities");
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
