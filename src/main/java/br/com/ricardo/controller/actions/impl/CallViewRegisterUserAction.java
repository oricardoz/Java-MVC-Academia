package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.controller.actions.ICommanderAction;
import br.com.ricardo.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallViewRegisterUserAction implements ICommanderAction {
    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", new User());
        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?pg=register");

        rd.forward(req, resp);
    }
}
