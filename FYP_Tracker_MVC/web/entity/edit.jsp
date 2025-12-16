<%@ page import="model.Entity, model.EntityDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  if (session.getAttribute("user") == null) {
    response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
    return;
  }

  Entity e = null;
  try {
    int id = Integer.parseInt(request.getParameter("id"));
    e = EntityDAO.getById(id);
  } catch (Exception ex) {
    e = null;
  }
  if (e == null) {
%>
    <p>Record not found. <a href="<%=request.getContextPath()%>/entities">Back to list</a></p>
<%
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Entity</title>
  <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
  <div class="container">
    <div class="topbar">
      <h2>Edit Record</h2>
      <a class="btn" href="<%=request.getContextPath()%>/entities">Back</a>
    </div>

    <form method="post" action="<%=request.getContextPath()%>/entityUpdate">
      <input type="hidden" name="id" value="<%= e.getId() %>">
      <label>Title</label>
      <input type="text" name="title" value="<%= e.getTitle() %>" required>
      <label>Description</label>
      <textarea name="description" rows="4"><%= e.getDescription() == null ? "" : e.getDescription() %></textarea>
      <label>Status</label>
      <select name="status">
        <option <%= "New".equals(e.getStatus()) ? "selected" : "" %>>New</option>
        <option <%= "In Progress".equals(e.getStatus()) ? "selected" : "" %>>In Progress</option>
        <option <%= "Done".equals(e.getStatus()) ? "selected" : "" %>>Done</option>
      </select>
      <button type="submit">Update</button>
    </form>
  </div>
</body>
</html>
