<%@page import="java.util.List"%>
<%@page import="br.com.ricardo.model.Card"%>
<%@page import="br.com.ricardo.model.User" %>
<%@page import="br.com.ricardo.model.Exercise" %>
<%@page import="br.com.ricardo.model.dao.impl.CardDAO" %>
<%@ page import="br.com.ricardo.model.dao.impl.UserDAO" %>

<%
    UserDAO userDAO = new UserDAO();
    List<User> allUsers = userDAO.findAll();
%>

<div class="page">
    <form action="/master" method="POST" class="formLogin">
        <h1>Athlete Cards</h1>
            <input type="hidden" name="ac" value="choseUserCard" />
        <label >Login</label>
            <select name="idAthleteCard" placeholder="ID">
                <% for(User user : allUsers) { %>
                    <% if ( user.isAthlete()) { %>
                        <option value="<%= user.getId() %>"> <%= user.getName() %> </option>
                    <% } %>
                <% } %>
            </select>

        <input type="submit" value="Search Cards" class="btn" />

        <%
            String msg = (String)request.getAttribute("msg");
        %>

        <% if (msg != null){  %>
            <div class="errorMessage"> <%= msg %> </div>
        <% } %>

    </form>

    <%
    CardDAO cardDAO = new CardDAO();
    User u = (User) session.getAttribute("userCard");
    if (u != null) {
        try {
            List<Card> userCards = cardDAO.findCardByUserId(u.getId());
            if (userCards != null && !userCards.isEmpty()) {
                int cardNumber = 1;
                for (Card userCard : userCards) {
    %>
                    <div class="card-container">
                        <h3 class="card-title">Card <%= cardNumber %> - <%= u.getName() %></h3>
                        <table class="card-table">
                            <tr>
                                <th>Exercise</th>
                                <th>Repetition</th>
                                <th>Weight</th>
                            </tr>
                            <%
                            for (Exercise exercise : userCard.getExercises()) {
                            %>
                                <tr>
                                    <td><%= exercise.getName() %></td>
                                    <td><%= exercise.getRepetition() %></td>
                                    <td><%= exercise.getWeight() %></td>
                                </tr>
                            <%
                            }
                            %>
                            <form action="/master" method="POST">
                                <input type="hidden" name="ac" value="deleteCard" />
                                <input type="hidden" name="cardId" value="<%= userCard.getId() %>">
                                <button type="submit" class="delete-button">Delete Card</button>
                            </form>
                        </table>
                    </div>
    <%
                    cardNumber++;
                }
            } else {
                out.println("<p class='no-cards'>No cards found for this user.</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p class='error-message'>Error retrieving cards: " + e.getMessage() + "</p>");
        }
    } else {
        out.println("<p class='session-message'>User not found in session.</p>");
    }
    %>
</div>
