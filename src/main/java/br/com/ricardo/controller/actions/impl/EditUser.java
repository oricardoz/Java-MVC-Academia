package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.Enum.EUserType;
import br.com.ricardo.model.User;
import br.com.ricardo.model.dao.impl.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class EditUser implements br.com.ricardo.controller.actions.ICommanderAction {
    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            UserDAO userDAO = new UserDAO();
            User u = userDAO.findById(userId);
            String userLoginFind = u.getLogin();

            String userName = req.getParameter("userName");
            String userLogin = req.getParameter("userLogin");
            String userTypeParam = req.getParameter("userType");

            if( !userLoginFind.equalsIgnoreCase(userLogin)){
                UserDAO userDao = new UserDAO();
                List<User> userList = userDao.findAll();
                boolean loginExists = userList.stream()
                        .anyMatch(existingUser -> existingUser.getLogin().equalsIgnoreCase(userLogin));

                if (loginExists){
                    req.setAttribute("ac", "users");
                    req.setAttribute("msg", "Login already exists. Please choose a different one.");
                    new CallViewAction().execute(req, resp);
                }
            }

            if (userName != null && userLogin != null && userTypeParam != null) {
                EUserType userType = EUserType.valueOf(userTypeParam);
                u.setName(userName);
                u.setLogin(userLogin);
                u.setUserType(userType);

                userDAO.change(u);
                req.setAttribute("ac", "users");
                new CallViewAction().execute(req, resp);
            } else {
                req.setAttribute("msg", "Login ou Senha Incorreta");
                req.setAttribute("ac", "users");
                new CallViewAction().execute(req, resp);
            }
        }  catch (Exception e) {
            req.setAttribute("msg", "Login ou Senha Incorreta");
            req.setAttribute("ac", "users");
            new CallViewAction().execute(req, resp);
        }
    }
}
