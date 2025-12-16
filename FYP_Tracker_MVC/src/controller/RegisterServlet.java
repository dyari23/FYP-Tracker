package controller;

import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterServlet extends HttpServlet {

    private static final Pattern EMAIL_RX = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        String error = null;
        if (fullName == null || fullName.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.isEmpty() ||
            confirm == null || confirm.isEmpty()) {
            error = "All fields are required.";
        } else if (!EMAIL_RX.matcher(email.trim()).matches()) {
            error = "Invalid email format.";
        } else if (password.length() < 6) {
            error = "Password must be at least 6 characters.";
        } else if (!password.equals(confirm)) {
            error = "Password confirmation does not match.";
        }

        try {
            if (error == null && UserDAO.emailExists(email)) {
                error = "Email already exists.";
            }

            if (error != null) {
                resp.sendRedirect(req.getContextPath() + "/auth/register.jsp?error=" + encode(error));
                return;
            }

            UserDAO.createUser(fullName, email, password);
            resp.sendRedirect(req.getContextPath() + "/auth/login.jsp?registered=1");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private String encode(String s) {
        return s.replace(" ", "%20");
    }
}
