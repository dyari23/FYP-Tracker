package controller;

import model.EntityDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class EntityListServlet extends HttpServlet {

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

        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        try {
            if ("details".equals(action) && idStr != null) {
                int id = Integer.parseInt(idStr);
                req.setAttribute("entity", model.EntityDAO.getById(id));
                req.getRequestDispatcher("/entity/details.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("entities", EntityDAO.getAll());
            req.getRequestDispatcher("/entity/list.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
