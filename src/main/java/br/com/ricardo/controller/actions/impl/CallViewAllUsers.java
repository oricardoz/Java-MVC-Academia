package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.Card;
import br.com.ricardo.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CallViewAllUsers implements br.com.ricardo.controller.actions.ICommanderAction{
    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setAttribute("card", new Card());
        User u = (User) req.getSession().getAttribute("user");

        RequestDispatcher rd;


        if(u.isAdmin()) {
            rd = req.getRequestDispatcher("template.jsp?pg=users");
        } else if (u.isInstructor()) {
            rd = req.getRequestDispatcher("template.jsp?pg=userInstructor");
        } else {
            rd = req.getRequestDispatcher("template.jsp?pg=home");
        }
        rd.forward(req,resp);
    }
}
