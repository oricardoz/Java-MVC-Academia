<%@ page import="java.util.List" %>
<%@ page import="br.com.ricardo.model.Complaint" %>
<%@ page import="br.com.ricardo.model.dao.impl.ComplaintDAO" %>

<%
    ComplaintDAO complaintDAO = new ComplaintDAO();
    List<Complaint> allComplaints = complaintDAO.findAll();
%>

<head>
    <link rel="stylesheet" href="../style.css">
</head>

<h1>Complaints</h1>

<div class="complaints-container">
    <% if (allComplaints != null && !allComplaints.isEmpty()) { %>
    <% for (Complaint complaint : allComplaints) { %>
    <% int complaintID = complaint.getId(); %>
    <center>
    <div class="complaint">
        <h2>Topic</h2>
        <p><%= complaint.getTopic()%></p>
        <br>
        <h2>Description</h2>
        <p><%= complaint.getText()%></p>
        <br>
        <h2>User name</h2>
        <p><%= complaint.getUser().getName()%></p>
        <br>
        <a href="#" onclick="deleteComplaint(<%= complaintID %>)" class="delete-complaint-btn">Delete Complaint</a>

    </div>
    </center>
    <% } %>
    <% } else { %>
    <p class="no-complaints">No Complaints found.</p>
    <% } %>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/delete.js"></script>

<style>
    .delete-complaint-btn {
        display: inline-block;
        padding: 0.5rem 1rem;
        border: 1px solid #ddd;
        border-radius: 4px;
        text-decoration: none;
        color: #333;
        font-weight: bold;
        cursor: pointer;
        transition: background-color 0.2s ease-in-out;
    }

    .delete-complaint-btn:hover {
        background-color: #eee;
    }
</style>


