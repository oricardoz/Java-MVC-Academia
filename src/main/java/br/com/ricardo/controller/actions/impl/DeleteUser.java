package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.controller.actions.impl.CallViewAction;
import br.com.ricardo.model.Card;
import br.com.ricardo.model.Exercise;
import br.com.ricardo.model.dao.impl.CardDAO;
import br.com.ricardo.model.dao.impl.ExerciseDAO;
import br.com.ricardo.model.dao.impl.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class DeleteUser implements br.com.ricardo.controller.actions.ICommanderAction {
    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UserDAO userDAO = new UserDAO();
        CardDAO cardDAO = new CardDAO();
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        int userId = Integer.parseInt(req.getParameter("userId"));

        try {
            List<Card> userCards = cardDAO.findCardByUserId(userId);


            for (Card card : userCards) {

                List<Exercise> exercises = exerciseDAO.findAll();
                for (Exercise exercise : exercises) {
                    exerciseDAO.delete(exercise);
                }

                cardDAO.delete(card);
            }


            userDAO.delete(userDAO.findById(userId));
            req.setAttribute("ac", "users");
            new CallViewAction().execute(req, resp);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
