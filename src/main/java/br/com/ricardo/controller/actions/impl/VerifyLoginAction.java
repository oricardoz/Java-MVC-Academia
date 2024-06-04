package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.User;
import br.com.ricardo.model.dao.impl.UserDAO;
import br.com.ricardo.util.UtilService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class VerifyLoginAction implements br.com.ricardo.controller.actions.ICommanderAction{
    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            User u = new UserDAO().findByLoginAndPassword(req.getParameter("cpLogin"),
                    UtilService.md5(req.getParameter("cpPassword")));

            if(u == null) {
                req.setAttribute("msg", "Login or password are wrong");
                req.setAttribute("ac", "login");
                new CallViewAction().execute(req, resp);
            } else {
                req.getSession().setAttribute("user", u);
                new HomeAction().execute(req, resp);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
