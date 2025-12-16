<%@ page import="java.util.*,model.Entity" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  if (session.getAttribute("user") == null) {
    response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Entity List</title>
  <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
  <div class="container">
    <div class="topbar">
      <h2>Entity Records</h2>
      <div>
        <a class="btn" href="<%=request.getContextPath()%>/dashboard">Dashboard</a>
        <a class="btn" href="<%=request.getContextPath()%>/logout">Logout</a>
      </div>
    </div>

    <div style="margin: 12px 0;">
      <a class="btn" href="create.jsp">+ Create New</a>
    </div>

    <table>
      <tr>
        <th>ID</th><th>Title</th><th>Status</th><th>Actions</th>
      </tr>
      <%
        List<Entity> list = (List<Entity>) request.getAttribute("entities");
        if (list != null) {
          for (Entity e : list) {
      %>
        <tr>
          <td><%= e.getId() %></td>
          <td><%= e.getTitle() %></td>
          <td><%= e.getStatus() %></td>
          <td>
            <a href="<%=request.getContextPath()%>/entities?action=details&id=<%=e.getId()%>">Details</a>
            |
            <a href="edit.jsp?id=<%=e.getId()%>">Edit</a>
            |
            <a href="<%=request.getContextPath()%>/entityDelete?id=<%=e.getId()%>"
               onclick="return confirm('Delete this record?');">Delete</a>
          </td>
        </tr>
      <%
          }
        }
      %>
    </table>
  </div>
</body>
</html>
