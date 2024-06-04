<%@ page import="java.util.List" %>
<%@ page import="br.com.ricardo.model.User" %>
<%@ page import="br.com.ricardo.model.dao.impl.UserDAO" %>
<%
    UserDAO userDAO = new UserDAO();
    List<User> allUsers = userDAO.findAll();
%>

<% if (allUsers != null && !allUsers.isEmpty()) { %>
    <div class="card-container">
        <table class="card-table">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Role</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <% for (User user : allUsers) { %>
                <% if (!user.isAdmin()) { %>
                    <tr>
                        <td><%= user.getId() %></td>
                        <td><%= user.getName() %></td>
                        <td><%= user.getLogin() %></td>
                        <td><%= user.getUserType() %></td>
                        <td>
                            <button onclick="openEditModal(<%= user.getId() %>)">Edit</button>
                        </td>
                        <td>
                            <form action="/master" method="post">
                                <input type="hidden" name="userId" value="<%= user.getId() %>" />
                                <input type="hidden" name="ac" value="deleteUser" />
                                <input type="submit" value="Delete" />
                            </form>
                        </td>
                    </tr>
                <% } %>
            <% } %>
        </table>
    </div>
<% } else { %>
    <p class='no-users'>No users found.</p>
<% } %>

<% for (User user : allUsers) { %>
    <% if (!user.isAdmin()) { %>
        <div id="editModal<%= user.getId() %>" class="edit-modal" style="display: none;">
            <div class="modal-content">
                <span class="close" onclick="closeEditModal(<%= user.getId() %>)">&times;</span>

                <form action="/master" method="post">

                    <input type="hidden" name="userId" value="<%= user.getId() %>" />
                    <input type="hidden" name="ac" value="editUser" />

                    <label for="name<%= user.getId() %>">Name:</label>
                    <input type="text" id="name<%= user.getId() %>" name="userName" value="<%= user.getName() %>" />

                    <label for="login<%= user.getId() %>">Login:</label>
                    <input type="text" id="login<%= user.getId() %>" name="userLogin" value="<%= user.getLogin() %>" />

                    <label for="userType<%= user.getId() %>">User Type:</label>
                    <select id="userType<%= user.getId() %>" name="userType">
                        <option value="INSTRUCTOR" <%= user.getUserType().equals("instructor") ? "selected" : "" %>>Instructor</option>
                        <option value="ATHLETE" <%= user.getUserType().equals("athlete") ? "selected" : "" %>>Athlete</option>
                    </select>

                    <input type="submit" value="Update" />
                </form>

            </div>
        </div>
    <% } %>
<% } %>

<%
    String msg = (String) request.getAttribute("msg");
%>
<% if (msg != null) { %>
    <div class="errorMessage"><%= msg %></div>
<% } %>

<script>
    function openEditModal(userId) {
        var modal = document.getElementById('editModal' + userId);
        modal.style.display = "block";
    }

    function closeEditModal(userId) {
        var modal = document.getElementById('editModal' + userId);
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target.className === 'edit-modal') {
            event.target.style.display = "none";
        }
    }
</script>
