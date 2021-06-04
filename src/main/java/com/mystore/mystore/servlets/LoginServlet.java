package com.mystore.mystore.servlets;

import com.mystore.mystore.DAO.UserDAO;
import com.mystore.mystore.entity.User;
import com.mystore.mystore.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Lovepreet Singh &  Purva Saxena
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try{
                String email = request.getParameter("user_email");
                String pass = request.getParameter("user_pass");
                
                // Autheticating User
                SessionFactory factory = FactoryProvider.getfactory();
                
                UserDAO x = new UserDAO(factory);
                User user = x.getUserByEmailAndPass(email,pass);
                
                HttpSession httpSession = request.getSession();
                if(user==null){
                    httpSession.setAttribute("message","Wrong email or Password !! ");
                    response.sendRedirect("login.jsp");
                    return ;
                }
                else{
                    httpSession.setAttribute("current-user", user);
                    if(user.getUserType().equals("Admin")){
                        response.sendRedirect("admin.jsp");
                    }else if(user.getUserType().equals("Normal")){
                        response.sendRedirect("index.jsp");
                    }else{
                        out.print("User Not Identified");
                    }
                }
                
            }catch( Exception ex){
                ex.printStackTrace();
            }
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
