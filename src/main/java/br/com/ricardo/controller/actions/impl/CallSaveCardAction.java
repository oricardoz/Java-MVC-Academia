package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.Card;
import br.com.ricardo.model.Exercise;
import br.com.ricardo.model.User;
import br.com.ricardo.model.dao.impl.CardDAO;
import br.com.ricardo.model.dao.impl.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallSaveCardAction implements br.com.ricardo.controller.actions.ICommanderAction{
    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = new User();
        String idUserString = req.getParameter("idAthleteField");

        if (idUserString != null && !idUserString.isEmpty()) {
            try {
                int idUser = Integer.parseInt(idUserString);
                u = new UserDAO().findById(idUser);
                System.out.println(u.getName());

            } catch (Exception e) {
                req.setAttribute("ac", "create");
                req.setAttribute("msg", "User not founded");
                new CallViewAction().execute(req, resp);

            }
        } else {
            req.setAttribute("ac", "create");
            req.setAttribute("msg", "User id can not be empty");
            new CallViewAction().execute(req, resp);
        }

        Card c = new Card();
        c.setDate(Date.from(Instant.now()));
        List<Exercise> exercises = new ArrayList<>();


        for (int i = 1; i <= 6; i++) {
            String exerciseName = req.getParameter("exercise" + i);
            String repetitionStr = req.getParameter("repetition" + i);
            String weightStr = req.getParameter("weight" + i);

            if (exerciseName == null || exerciseName.isEmpty()) {
                req.setAttribute("ac", "create");
                req.setAttribute("msg", "Exercise name cannot be empty.");
                new CallViewAction().execute(req, resp);
                continue;
            }

            if( u == null ) {
                req.setAttribute("ac", "create");
                req.setAttribute("msg", "User not founded");
                new CallViewAction().execute(req, resp);
                continue;
            }

            int repetition;
            double weight;

            try {
                repetition = Integer.parseInt(repetitionStr);
                weight = Double.parseDouble(weightStr);
            } catch (NumberFormatException e) {
                req.setAttribute("ac", "create");
                req.setAttribute("msg", "Repetition and weight must be numeric values. ");
                new CallViewAction().execute(req, resp);
                continue;
            }

            if (repetition <= 0 || weight <= 0) {
                req.setAttribute("ac", "create");
                req.setAttribute("msg", "Repetition and weight must be greater than zero. ");
                new CallViewAction().execute(req, resp);
                continue;
            }

            Exercise exercise = new Exercise();
            exercise.setName(exerciseName);
            exercise.setRepetition(repetition);
            exercise.setWeight(weight);
            exercise.setCard(c);
            exercises.add(exercise);

            c.setUser(u);
            c.setExercises(exercises);

        }

        try {
            new CardDAO().insert(c);
            new HomeAction().execute(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
