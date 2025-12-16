package controller;

import model.User;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User u = UserDAO.login(email, password);
            if (u != null) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", u);
                resp.sendRedirect(req.getContextPath() + "/dashboard");
            } else {
                resp.sendRedirect(req.getContextPath() + "/auth/login.jsp?error=1");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
