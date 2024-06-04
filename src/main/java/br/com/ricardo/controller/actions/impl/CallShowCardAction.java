package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.User;
import br.com.ricardo.model.dao.impl.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CallShowCardAction implements br.com.ricardo.controller.actions.ICommanderAction{
    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("idAthleteCard");

        if(id == null || id.isEmpty() || id.isBlank()) {
            req.setAttribute("ac", "choseUserCard");
            new CallViewAction().execute(req, resp);
        } else {
            try{
                UserDAO userDAO = new UserDAO();
                User u = userDAO.findById(Integer.valueOf(id));
                req.getSession().setAttribute("userCard", u);
                req.setAttribute("ac", "choseUserCard");
                new CallViewAction().execute(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }


    }
}
