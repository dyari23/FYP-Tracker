<%@ page import="model.Entity" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  if (session.getAttribute("user") == null) {
    response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
    return;
  }
  Entity e = (Entity) request.getAttribute("entity");
  if (e == null) {
%>
  <p>Record not found. <a href="<%=request.getContextPath()%>/entities">Back</a></p>
<%
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Entity Details</title>
  <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
  <div class="container">
    <div class="topbar">
      <h2>Details</h2>
      <a class="btn" href="<%=request.getContextPath()%>/entities">Back</a>
    </div>

    <div class="detail">
      <p><b>ID:</b> <%= e.getId() %></p>
      <p><b>Title:</b> <%= e.getTitle() %></p>
      <p><b>Status:</b> <%= e.getStatus() %></p>
      <p><b>Description:</b> <%= e.getDescription() == null ? "" : e.getDescription() %></p>
    </div>
  </div>
</body>
</html>
