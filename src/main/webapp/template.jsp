<!DOCTYPE html>

<%@page import="br.com.ricardo.model.User" %>

<html lang="en">

<head>
  <meta charset="utf-8" />
  <title>FitnessZ Gym</title>
  <meta name="title" content="Web site" />
  <link id="theme" rel="stylesheet" type="text/css" href="index.css" title="theme" />
</head>

<body>
  <header class="header">
  <% User u = (User)session.getAttribute("user");  %>

    <nav class="nav">
        <a href="/" class="logo">
            FitnessZ Gym
        </a>
        <ul class="nav-list">
        <% if (u == null){ %>
            <li>
                <a href="/master" class="sobre-mim-link">Home</a>
            </li>
            <li>
                <a href="/master?ac=login" class="sobre-mim-link">Login</a>
            </li>
            <li>
                <a href="/master?ac=register" class="procedimentos-link">Register</a>
            </li>
            <li>
                <a href="/master"class="contato-link">Help</a>
            </li>

            <% }else{ %>
            <li>
                <a href="/master" class="sobre-mim-link">Home</a>
            </li>
             <li>
                <a href="/master?ac=logout" class="sobre-mim-link">Logout</a>
            </li>
            <% } %>
            <% if (u != null && u.isInstructor() ){ %>
            <li>
                <a href="/master?ac=choseUserCard" class="sobre-mim-link">Edit Cards</a>
            </li>
              <li>
                  <a href="/master?ac=createCard" class="sobre-mim-link">Create Card</a>
              </li>
              <li>
                  <a href="/master?ac=users" class="sobre-mim-link">All Users</a>
              </li>
            <% }%>
            <% if (u != null && u.isAdmin() ){ %>
              <li>
                   <a href="/master?ac=users" class="sobre-mim-link">All Users</a>
              </li>
              <li>
                  <a href="/master?ac=allComplaint" class="sobre-mim-link">Complaints about gym</a>
              </li>
            <% }%>
            <% if ( u != null && u. isAthlete() ){ %>
                <li>
                    <a href="/master?ac=card"class="contato-link">Card</a>
                </li>
                <li>
                    <a href="/master?ac=createComplaint"class="contato-link">Complaints</a>
                </li>
            <% } %>
        </ul>
    </nav>
</header>

  <div id="wrapper">
    <div class="row">
      <div id="container">
        <div id="page">
          <div id="main">
            <section class="home">
              <% String pg=request.getParameter("pg"); pg=(pg==null)? "home" : pg; pg="/pages/" +pg+".jsp"; %>
                <jsp:include page="<%= pg %>" />
            </section>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html> 