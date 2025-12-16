<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register</title>
  <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
  <div class="container">
    <h2>Register</h2>
    <% if (request.getParameter("error") != null) { %>
      <div class="alert"><%= request.getParameter("error") %></div>
    <% } %>

    <form method="post" action="<%=request.getContextPath()%>/register">
      <label>Full Name</label>
      <input type="text" name="fullName" required>
      <label>Email</label>
      <input type="email" name="email" required>
      <label>Password (min 6 chars)</label>
      <input type="password" name="password" required>
      <label>Confirm Password</label>
      <input type="password" name="confirm" required>
      <button type="submit">Create Account</button>
    </form>

    <p>Already have an account? <a href="login.jsp">Login</a></p>
  </div>
</body>
</html>
