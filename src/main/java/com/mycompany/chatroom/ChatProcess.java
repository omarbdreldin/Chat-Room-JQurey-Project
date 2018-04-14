/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatroom;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Omar
 */
@WebServlet(name = "ChatProcess", urlPatterns = {"/ChatProcess"})
public class ChatProcess extends HttpServlet {
    
    private static ArrayList<Messege> messeges = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        
        String name = req.getParameter("name");
        String text = req.getParameter("messege");
        
        Messege messege = new Messege(name, text, new Date().getTime());
        
        messeges.add(messege);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.

        int size = Integer.parseInt(req.getParameter("size"));
        
        resp.setContentType("application/json");
        
        System.out.println("in doGet");
        
        try (PrintWriter out = resp.getWriter()) {
            out.print(new Gson().toJson(messeges.stream().skip(size).toArray()));
        }
    }

}
        