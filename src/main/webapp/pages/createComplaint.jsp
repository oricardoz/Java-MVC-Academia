<%@page import="br.com.ricardo.model.User" %>
<%
    User u = (User) session.getAttribute("user");
%>

<head>
    <link rel="stylesheet" href="../style.css">
</head>

<body>
<div class="complaint-form">
    <h2>Form about Complaint</h2>
    <form action="/master" method="POST">

        <input type="hidden" name="ac" value="saveComplaint" />
        <input type="hidden" name="userId" value="<%= u.getId() %>" />

        <label for="tema">Theme about your complaint:</label> <br>
        <input type="text" id="tema" name="nameComplaint" placeholder="Ex: Problem on order" class="complaint-input" required>

        <br><br>

        <label for="descricao">Describe your complaint</label><br>
        <textarea id="descricao" name="description" rows="5" placeholder="Describe your problems in details" class="complaint-textarea" required></textarea>
        <br><br>
        <center>
        <input type="submit" value="Submit" class="complaint-submit">
        </center>
    </form>
</div>
</body>
