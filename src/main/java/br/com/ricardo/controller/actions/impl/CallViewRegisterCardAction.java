package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.Card;
import br.com.ricardo.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallViewRegisterCardAction implements br.com.ricardo.controller.actions.ICommanderAction{
    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("card", new Card());
        User u = (User) req.getSession().getAttribute("user");

        RequestDispatcher rd;


        if(u.isInstructor() || u.isAdmin()) {
            rd = req.getRequestDispatcher("template.jsp?pg=create");
        } else {
            rd = req.getRequestDispatcher("template.jsp?pg=home");
        }
        rd.forward(req,resp);

    }
}
