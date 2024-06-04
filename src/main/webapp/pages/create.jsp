    <%@ page import="java.util.List" %>
    <%@ page import="br.com.ricardo.model.User" %>
    <%@ page import="br.com.ricardo.model.dao.impl.UserDAO" %>

    <%
    UserDAO userDAO = new UserDAO();
    List<User> allUsers = userDAO.findAll();
    %>
    <head>
        <link rel="stylesheet" href="../style.css">
    </head>

    <div class="card-container">
        <form action="/master" method="POST" class="card-form">

            <input type="hidden" name="ac" value="saveCard" />

            <div class="section">
            <h1>Create Card</h1>
            <p>Create your card with 8 exercises</p>
            </div>

            <div class="section">
            <label>Chose the athlete</label>
                <select name="idAthleteField" placeholder="ID" class="select-field">
                    <% for(User user : allUsers) { %>
                        <% if ( user.isAthlete()) { %>
                            <option value="<%= user.getId() %>"> <%= user.getName() %> </option>
                        <% } %>
                    <% } %>
                </select>
            </div>

            <div class="section">
                <label Exercise number 1</label>
                    <input class="input-field"  name="exercise1" placeholder="Exercise Name"/>
                    <input class="input-field"  name="repetition1"  placeholder="Exercise Repetiion"/>
                    <input class="input-field"  name="weight1"  placeholder="Exercise Weight"/>
            </div>

            <div class="section">
                <label Exercise number 2</label>
                    <input class="input-field"  name="exercise2"  placeholder="Exercise Name"/>
                    <input class="input-field"  name="repetition2"  placeholder="Exercise Repetiion"/>
                    <input class="input-field"   name="weight2"  placeholder="Exercise Weight"/>
            </div>

            <div class="section">
                <label >Exercise number 3</label>
                    <input class="input-field"  name="exercise3"  placeholder="Exercise Name"/>
                    <input class="input-field"  name="repetition3"  placeholder="Exercise Repetiion"/>
                    <input class="input-field"  name="weight3"  placeholder="Exercise Weight"/>
            </div>

            <div class="section">
                <label >Exercise number 4</label>
                    <input class="input-field"  name="exercise4"  placeholder="Exercise Name"/>
                    <input class="input-field"  name="repetition4"  placeholder="Exercise Repetiion"/>
                    <input class="input-field"  name="weight4"  placeholder="Exercise Weight"/>
            </div>

            <div class="section">
                <label >Exercise number 5</label>
                    <input class="input-field"  name="exercise5"  placeholder="Exercise Name"/>
                    <input class="input-field"  name="repetition5"  placeholder="Exercise Repetiion"/>
                    <input class="input-field"  name="weight5"  placeholder="Exercise Weight"/>
            </div>

            <div class="section">
                <label >Exercise number 6</label>
                    <input class="input-field"  name="exercise6"  placeholder="Exercise Name"/>
                    <input class="input-field"  name="repetition6"  placeholder="Exercise Repetiion"/>
                    <input class="input-field"  name="weight6"  placeholder="Exercise Weight"/>
            </div>


            <div class="section">
            <input type="submit" value="Create" class="submit-button" />
            </div>

                    <%
                                    String msg = (String)request.getAttribute("msg");
                                %>

                                <% if (msg != null){  %>
                                    <div class="errorMessage"> <%= msg %> </div>
                                <% } %>
        </form>

    </div>