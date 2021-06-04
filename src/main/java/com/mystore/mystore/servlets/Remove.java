package com.mystore.mystore.servlets;

import com.mystore.mystore.DAO.ProductDAO;
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
@WebServlet(name = "Remove", urlPatterns = {"/Remove"})
public class Remove extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            ProductDAO x = new ProductDAO(FactoryProvider.getfactory());

            try {
                x.removeProduct(Integer.parseInt(request.getParameter("Product")));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("message", "Product has been removed !");
            response.sendRedirect("ViewProduct.jsp");
            return;

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
