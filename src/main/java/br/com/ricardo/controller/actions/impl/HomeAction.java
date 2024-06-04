package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeAction implements br.com.ricardo.controller.actions.ICommanderAction{
    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        RequestDispatcher rd;

        if(u == null) {
            rd = req.getRequestDispatcher("template.jsp?pg=home");
        } else {
            rd = req.getRequestDispatcher("template.jsp?pg=homeLoged");
        }

        rd.forward(req, resp);

    }
}
