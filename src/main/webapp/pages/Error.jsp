<h1>Something unexpected happened</h1>

<%
    String msg = (String) request.getAttribute("msg");
%>

<% if (msg != null) { %>
    <div class="errorMessage"><%= msg %></div>
<% } %>
