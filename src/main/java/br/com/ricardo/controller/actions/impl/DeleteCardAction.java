package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.Card;
import br.com.ricardo.model.Exercise;
import br.com.ricardo.model.dao.impl.CardDAO;
import br.com.ricardo.model.dao.impl.ExerciseDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class DeleteCardAction implements br.com.ricardo.controller.actions.ICommanderAction {

    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CardDAO cardDAO = new CardDAO();
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        int cardId = Integer.parseInt(req.getParameter("cardId"));

        try {
            Card card = cardDAO.findById(cardId);

            if (card != null) {
                List<Exercise> exercises = exerciseDAO.findAll();

                for (Exercise exercise : exercises) {
                    exerciseDAO.delete(exercise);
                }

                cardDAO.delete(card);
            }


            req.setAttribute("ac", "choseUserCard");
            new CallViewAction().execute(req, resp);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
