package com.mystore.mystore.servlets;

import com.mystore.mystore.DAO.UserDAO;
import com.mystore.mystore.entity.User;
import com.mystore.mystore.helper.Capitalize;
import com.mystore.mystore.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;

/**
 *
 * @author Lovepreet Singh & Purva Saxena 
 */
public class RegisterServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                Capitalize c = new Capitalize();
                String name = c.capitalize(request.getParameter("user_name"));
                String email = request.getParameter("user_email");
                String address = c.capitalize(request.getParameter("user_address"));
                String phone = request.getParameter("user_phone");
                String pass = request.getParameter("user_pass");
                String gender = request.getParameter("gender");
                
                // validation
                
                SessionFactory factory = FactoryProvider.getfactory();
                UserDAO x = new UserDAO(factory);
                
                if(name.isEmpty() ||email.isEmpty()|| address.isEmpty()|| phone.isEmpty()|| pass.isEmpty()){
                        HttpSession httpsession = request.getSession();
                        httpsession.setAttribute("message", "Please fill all the fields");
                        response.sendRedirect("register.jsp");
                        return;
                    }
                
                //creating User object to store data
          
                    User user = new User(name, email, pass, address, "default.jpg", phone, gender);
                    
                    x.addUser(user);
                    
                    /*
                    Session HibernateSession = FactoryProvider.getfactory().openSession();
                    Transaction tx = HibernateSession.beginTransaction();
                    
                    HibernateSession.save(user);
                    
                    tx.commit();
                    HibernateSession.close();
                    */
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("message","Registration Successful !! ");
                    response.sendRedirect("index.jsp");
                    return ;
                    
            } catch (Exception e) {
                e.printStackTrace();
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
