package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntityDAO {

    public static void insert(Entity e) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO entity_records(title, description, status) VALUES(?,?,?)"
        );
        ps.setString(1, e.getTitle());
        ps.setString(2, e.getDescription());
        ps.setString(3, e.getStatus());
        ps.executeUpdate();
        ps.close(); con.close();
    }

    public static List<Entity> getAll() throws Exception {
        List<Entity> list = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery(
            "SELECT * FROM entity_records ORDER BY id DESC"
        );
        while (rs.next()) {
            Entity e = new Entity();
            e.setId(rs.getInt("id"));
            e.setTitle(rs.getString("title"));
            e.setDescription(rs.getString("description"));
            e.setStatus(rs.getString("status"));
            list.add(e);
        }
        rs.close(); con.close();
        return list;
    }

    public static Entity getById(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM entity_records WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Entity e = null;
        if (rs.next()) {
            e = new Entity();
            e.setId(rs.getInt("id"));
            e.setTitle(rs.getString("title"));
            e.setDescription(rs.getString("description"));
            e.setStatus(rs.getString("status"));
        }
        rs.close(); ps.close(); con.close();
        return e;
    }

    public static void update(Entity e) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE entity_records SET title=?, description=?, status=? WHERE id=?"
        );
        ps.setString(1, e.getTitle());
        ps.setString(2, e.getDescription());
        ps.setString(3, e.getStatus());
        ps.setInt(4, e.getId());
        ps.executeUpdate();
        ps.close(); con.close();
    }

    public static void delete(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM entity_records WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close(); con.close();
    }

    public static int countUsers() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(*) c FROM users");
        rs.next();
        int c = rs.getInt("c");
        rs.close(); con.close();
        return c;
    }

    public static int countEntities() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(*) c FROM entity_records");
        rs.next();
        int c = rs.getInt("c");
        rs.close(); con.close();
        return c;
    }

    public static List<String[]> statusSummary() throws Exception {
        List<String[]> rows = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery(
            "SELECT status, COUNT(*) cnt FROM entity_records GROUP BY status ORDER BY cnt DESC"
        );
        while (rs.next()) {
            rows.add(new String[]{ rs.getString("status"), String.valueOf(rs.getInt("cnt")) });
        }
        rs.close(); con.close();
        return rows;
    }
}
