/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystore.mystore.servlets;

import com.mystore.mystore.DAO.UserDAO;
import com.mystore.mystore.helper.FactoryProvider;
import com.mystore.mystore.helper.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;

/**
 *
 * @author hinas
 */
@WebServlet(name = "VerifyUserId", urlPatterns = {"/VerifyUserId"})
public class VerifyUserId extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String Operation = request.getParameter("Operation");

            if (Operation.equals("EnterEmail")) {

                String name = request.getParameter("username");
                String email = request.getParameter("useremail");

                SessionFactory factory = FactoryProvider.getfactory();
                UserDAO x = new UserDAO(factory);
                if (x.fetchId(email) == 1) {
                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "User Id already exists! Please try with another Id.");
                    response.sendRedirect("EnterEmail.jsp");
                    return;
                }

                SendEmail sm = new SendEmail();
                String code = sm.getRandom();
                String message = "Hello "+ name +" .Your Authentication code is: "+ code;
                String subject = "One time passwor(OTP)";
                boolean test = sm.sendEmail(email, message, subject);
                if (test) {
                    HttpSession session = request.getSession();
                    session.setAttribute("GeneratedCode", code);
                    session.setAttribute("email", email);
                    response.sendRedirect("EnterOTP.jsp");

                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("message", "OOPS something went wrong.. PLease try again.....");
                    response.sendRedirect("EnterEmail.jsp");
                }
            } else {
                String GeneratedCode = request.getParameter("generatedCode");
                String EnteredCode = request.getParameter("EnteredCode");
                String email = request.getParameter("email");
                HttpSession httpSession = request.getSession();
                if (GeneratedCode.equals(EnteredCode)) {
                    httpSession.setAttribute("Email", email);
                    response.sendRedirect("register.jsp");
                } else {
                    httpSession.setAttribute("message", "You have entered wrong OTP. Please Try Again ");
                    response.sendRedirect("EnterEmail.jsp");
                    return;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
