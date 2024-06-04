package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.Complaint;
import br.com.ricardo.model.User;
import br.com.ricardo.model.dao.impl.ComplaintDAO;
import br.com.ricardo.model.dao.impl.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CallSaveComplaintAction implements br.com.ricardo.controller.actions.ICommanderAction{
    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String nameComplaint = req.getParameter("nameComplaint");
        String description =  req.getParameter("description");
        String userId = req.getParameter("userId");

        User u = new UserDAO().findById(Integer.valueOf(userId));

        if(nameComplaint == null || nameComplaint.isEmpty() || nameComplaint.isBlank()) {
            req.setAttribute("ac", "createComplaint");
            req.setAttribute("msg", "Something is empty or blank");

            new CallViewAction().execute(req, resp);
        } else if(description == null || description.isEmpty() || description.isBlank()) {
            req.setAttribute("ac", "createComplaint");
            req.setAttribute("msg", "Something is empty or blank");

            new CallViewAction().execute(req, resp);
        } else {
            Complaint c = new Complaint();
            c.setText(description);
            c.setTopic(nameComplaint);
            c.setUser(u);

            try {
                new ComplaintDAO().insert(c);
                new HomeAction().execute(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }
}
