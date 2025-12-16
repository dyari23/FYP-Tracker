<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
  <div class="container">
    <h2>Login</h2>
    <% if ("1".equals(request.getParameter("error"))) { %>
      <div class="alert">Invalid email or password.</div>
    <% } %>
    <% if ("1".equals(request.getParameter("registered"))) { %>
      <div class="success">Registration successful. Please login.</div>
    <% } %>

    <form method="post" action="<%=request.getContextPath()%>/login">
      <label>Email</label>
      <input type="email" name="email" required>
      <label>Password</label>
      <input type="password" name="password" required>
      <button type="submit">Login</button>
    </form>

    <p>Don't have an account? <a href="register.jsp">Register</a></p>
  </div>
</body>
</html>
