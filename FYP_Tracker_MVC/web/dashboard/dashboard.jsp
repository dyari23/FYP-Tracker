<%@ page import="java.util.*" %>
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
  <title>Dashboard</title>
  <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
  <div class="container">
    <div class="topbar">
      <h2>Dashboard</h2>
      <a class="btn" href="<%=request.getContextPath()%>/logout">Logout</a>
    </div>

    <div class="kpis">
      <div class="card">
        <div class="label">Total Users</div>
        <div class="value"><%= request.getAttribute("totalUsers") %></div>
      </div>
      <div class="card">
        <div class="label">Total Records</div>
        <div class="value"><%= request.getAttribute("totalRecords") %></div>
      </div>
    </div>

    <h3>Status Summary</h3>
    <table>
      <tr><th>Status</th><th>Count</th></tr>
      <%
        List<String[]> rows = (List<String[]>) request.getAttribute("statusSummary");
        if (rows != null) {
          for (String[] r : rows) {
      %>
        <tr><td><%= r[0] %></td><td><%= r[1] %></td></tr>
      <%
          }
        }
      %>
    </table>

    <div style="margin-top:16px;">
      <a class="btn" href="<%=request.getContextPath()%>/entities">Go to Entity Management</a>
      <a class="btn" href="<%=request.getContextPath()%>/entity/create.jsp">Create New Record</a>
    </div>
  </div>
</body>
</html>
