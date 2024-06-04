<%@page import="java.util.List"%>
<%@page import="br.com.ricardo.model.User" %>
<%@page import="br.com.ricardo.model.dao.impl.UserDAO" %>
<%
    UserDAO userDAO = new UserDAO();
    List<User> allUsers = userDAO.findAll();
    if (allUsers != null && !allUsers.isEmpty()) {
%>

<div class="card-container">
    <table class="card-table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Role</th>
        </tr>
        <%
            for (User user : allUsers) {
                if (!user.isAdmin()) {
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getLogin() %></td>
            <td><%= user.getUserType() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
<%
    } else {
        out.println("<p class='no-users'>No users found.</p>");
    }
%>
