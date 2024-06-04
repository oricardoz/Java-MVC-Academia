<%@page import="java.util.List"%>
<%@page import="br.com.ricardo.model.Card"%>
<%@page import="br.com.ricardo.model.User" %>
<%@page import="br.com.ricardo.model.Exercise" %>
<%@page import="br.com.ricardo.model.dao.impl.CardDAO" %>
<%
CardDAO cardDAO = new CardDAO();
User u = (User) session.getAttribute("user");
if (u != null) {
    try {
        List<Card> userCards = cardDAO.findCardByUserId(u.getId());
        if (userCards != null && !userCards.isEmpty()) {
            int cardNumber = 1;
            for (Card userCard : userCards) {
%>
                <div class="card-container">
                    <h3 class="card-title">Card <%= cardNumber %></h3>
                    <table class="card-table">
                        <tr>
                            <th>Exercise</th>
                            <th>Repetition</th>
                            <th>Weight</th>
                            <th>Status</th>
                        </tr>
                        <%
                        for (Exercise exercise : userCard.getExercises()) {
                        %>
                            <tr>
                                <td><%= exercise.getName() %></td>
                                <td><%= exercise.getRepetition() %></td>
                                <td><%= exercise.getWeight() %></td>
                                <td>
                                    <input type="checkbox" name="completed<%= exercise.getId() %>" id="completed<%= exercise.getId() %>" />
                                    <label for="completed<%= exercise.getId() %>">Completed</label>
                                </td>
                            </tr>
                        <%
                        }
                        %>
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

