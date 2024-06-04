package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.Enum.EUserType;
import br.com.ricardo.model.User;
import br.com.ricardo.model.dao.impl.UserDAO;
import br.com.ricardo.util.UtilService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CallSaveUserAction implements br.com.ricardo.controller.actions.ICommanderAction {
    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        String name = req.getParameter("nameField");
        String email = req.getParameter("emailField");
        String login = req.getParameter("loginField");
        String password = req.getParameter("passwordField");
        String passwordConfirm = req.getParameter("passwordField2");

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setLogin(login);
        u.setPassword(UtilService.md5(password));
        u.setCards(null);
        u.setUserType(EUserType.ATHLETE);


        UserDAO userDao = new UserDAO();
        List<User> userList = userDao.findAll();
        boolean loginExists = userList.stream()
                .anyMatch(existingUser  -> existingUser.getLogin().equalsIgnoreCase(login));

        if (name == null || name.isEmpty() || name.isBlank()) {
            req.setAttribute("ac", "register");
            req.setAttribute("msg", "Name is empty or blank");
            req.setAttribute("user", u);
            new CallViewAction().execute(req, resp);

        } else if (password.isEmpty() || password.isBlank() || !password.equals(passwordConfirm)) {
            req.setAttribute("ac", "register");
            req.setAttribute("msg", "Passwords are different or empty");
            req.setAttribute("user", u);
            new CallViewAction().execute(req, resp);

        } else if (loginExists){
            req.setAttribute("ac", "register");
            req.setAttribute("msg", "Login already exists. Please choose a different one.");
            req.setAttribute("user", u);
            new CallViewAction().execute(req, resp);
        } else {
            try {
                new UserDAO().insert(u);
                req.setAttribute("ac", "login");
                new CallViewAction().execute(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
