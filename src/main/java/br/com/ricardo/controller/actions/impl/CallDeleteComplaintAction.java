package br.com.ricardo.controller.actions.impl;

import br.com.ricardo.model.Complaint;
import br.com.ricardo.model.dao.impl.ComplaintDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CallDeleteComplaintAction implements br.com.ricardo.controller.actions.ICommanderAction{
    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Complaint complaint = new ComplaintDAO().findById(Integer.parseInt(req.getParameter("complaintID")));
        new ComplaintDAO().delete(complaint);
        new HomeAction().execute(req, resp);
    }
}
