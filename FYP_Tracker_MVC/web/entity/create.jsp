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
  <title>Create Entity</title>
  <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
  <div class="container">
    <div class="topbar">
      <h2>Create Record</h2>
      <a class="btn" href="<%=request.getContextPath()%>/entities">Back</a>
    </div>

    <% if (request.getParameter("error") != null) { %>
      <div class="alert"><%= request.getParameter("error") %></div>
    <% } %>

    <form method="post" action="<%=request.getContextPath()%>/entityCreate">
      <label>Title *</label>
      <input type="text" name="title" required>
      <label>Description</label>
      <textarea name="description" rows="4"></textarea>
      <label>Status</label>
      <select name="status">
        <option>New</option>
        <option>In Progress</option>
        <option>Done</option>
      </select>
      <button type="submit">Save</button>
    </form>
  </div>
</body>
</html>
