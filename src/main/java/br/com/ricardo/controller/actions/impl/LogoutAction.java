package br.com.ricardo.controller.actions.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutAction implements br.com.ricardo.controller.actions.ICommanderAction{
    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        new HomeAction().execute(req, resp);
    }
}
