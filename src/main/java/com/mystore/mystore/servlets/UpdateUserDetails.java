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

/**
 *
 * @author hinas
 */
@WebServlet(name = "UpdateUserDetails", urlPatterns = {"/UpdateUserDetails"})
public class UpdateUserDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            String address = request.getParameter("address");
            String Phone = request.getParameter("PhoneNo");
            int id = Integer.parseInt(request.getParameter("UserId"));
            UserDAO userdao = new UserDAO(FactoryProvider.getfactory());
            User user =userdao.FetchUserById(id);
            String mess;
            if(!(user.getAddress().equals(address)&& user.getPhone().equals(Phone))){
                userdao.updateUserProfile(id, Phone, address);
                mess = "Your Profile has been updated";
            }else{
                mess = "No change has been made !!";
            }
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("message", mess);
            response.sendRedirect("customerPanel.jsp");
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
