package br.com.ricardo.controller;

import br.com.ricardo.controller.actions.ICommanderAction;
import br.com.ricardo.controller.actions.impl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@WebServlet("/master")
public class MasterController extends HttpServlet {
    static HashMap<String, ICommanderAction> comandos = new HashMap<>();

    static {
        comandos.put(null,new HomeAction());
        comandos.put("login", new CallViewAction());
        comandos.put("register", new CallViewRegisterUserAction());
        comandos.put("saveUser", new CallSaveUserAction());
        comandos.put("verifyLogin", new VerifyLoginAction());
        comandos.put("logout", new LogoutAction());
        comandos.put("createCard", new CallViewRegisterCardAction());
        comandos.put("saveCard", new CallSaveCardAction());
        comandos.put("card", new CallViewAction());
        comandos.put("users", new CallViewAllUsers());
        comandos.put("deleteUser", new DeleteUser());
        comandos.put("editUser", new EditUser());
        comandos.put("choseUserCard", new CallShowCardAction());
        comandos.put("deleteCard", new DeleteCardAction());
        comandos.put("createComplaint", new CallViewAction());
        comandos.put("saveComplaint", new CallSaveComplaintAction());
        comandos.put("allComplaint", new CallViewAction());
        comandos.put("deleteComplaint", new CallDeleteComplaintAction());


    }
    private void resposta(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String ac = req.getParameter("ac");
            if (comandos.get(ac) == null){
                req.setAttribute("ac","Error");
                req.setAttribute("msg","Ação não encontrada!!!!");
                new CallViewAction().execute(req,resp);
            }else {
                comandos.get(ac).execute(req, resp);
            }

        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resposta(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resposta(req, resp);
    }
}


