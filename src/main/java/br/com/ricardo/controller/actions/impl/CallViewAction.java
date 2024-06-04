package br.com.ricardo.controller.actions.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallViewAction implements br.com.ricardo.controller.actions.ICommanderAction{

    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ac = (String) req.getAttribute("ac");
        ac = (ac == null)? req.getParameter("ac"): ac;

        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?pg="+ac);

        rd.forward(req, resp);
    }
}
