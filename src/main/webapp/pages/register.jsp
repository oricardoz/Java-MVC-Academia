<%@page import="br.com.ricardo.model.User" %>

<%
    User u = (User) request.getAttribute("user");
%>

<div class="page">
    <form action="/master" method="POST" class="formLogin">
        <input type="hidden" name="ac" value="saveUser" />

        <h1>Register</h1>
        <p>Enter your registration details in the field below.</p>

        <label >Name</label>
        <input type="text" placeholder="Type your name" name="nameField" required value="<%= u.getName() %>" />

        <label >Login</label>
        <input type="text" placeholder="Type your username" name="loginField" required value="<%= u.getLogin() %>" />

        <label >E-mail</label>
        <input type="email" placeholder="Type your e-mail" name="emailField" required value="<%= u.getEmail() %>" />

        <label >Password</label>
        <input type="password" placeholder="Type your password" name="passwordField" required />

        <label >Password</label>
        <input type="password" placeholder="Type your password again" name="passwordField2" required />

        <input type="submit" value="Register" class="btn" />

        <%
            String msg = (String) request.getAttribute("msg");
        %>
        <% if (msg != null) { %>
            <div class="errorMessage"><%= msg %></div>
        <% } %>
    </form>
</div>
