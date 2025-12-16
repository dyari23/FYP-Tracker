package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public static boolean emailExists(String email) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT id FROM users WHERE email=?");
        ps.setString(1, email.trim().toLowerCase());
        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();
        rs.close(); ps.close(); con.close();
        return exists;
    }

    public static boolean createUser(String fullName, String email, String password) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO users(full_name,email,password) VALUES(?,?,?)"
        );
        ps.setString(1, fullName.trim());
        ps.setString(2, email.trim().toLowerCase());
        ps.setString(3, password);
        int rows = ps.executeUpdate();
        ps.close(); con.close();
        return rows > 0;
    }

    public static User login(String email, String password) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM users WHERE email=? AND password=?"
        );
        ps.setString(1, email.trim().toLowerCase());
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        User u = null;
        if (rs.next()) {
            u = new User();
            u.setId(rs.getInt("id"));
            u.setFullName(rs.getString("full_name"));
            u.setEmail(rs.getString("email"));
        }
        rs.close(); ps.close(); con.close();
        return u;
    }
}
