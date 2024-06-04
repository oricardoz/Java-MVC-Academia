<div class="page">
    <form action="/master" method="POST" class="formLogin">
        <h1>Login</h1>
        <input type="hidden" name="ac" value="verifyLogin" />
        <p>Enter your login details in the field below.</p>
        
        <label>Login</label>
        <input  placeholder="Type your login"  name="cpLogin" />
        
        <label >Password</label>
        <input type="password" placeholder="Type your password" name="cpPassword" />

        <input type="submit" value="Login" class="btn" />

        <%
            String msg = (String) request.getAttribute("msg");
        %>

        <% if (msg != null) { %>
            <div class="errorMessage"><%= msg %></div>
        <% } %>
    </form>
</div>
