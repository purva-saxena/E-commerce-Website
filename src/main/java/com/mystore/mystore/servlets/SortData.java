package com.mystore.mystore.servlets;

import com.mystore.mystore.DAO.ProductDAO;
import com.mystore.mystore.entity.Product;
import com.mystore.mystore.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hinas
 */
@WebServlet(name = "SortData", urlPatterns = {"/SortData"})
public class SortData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            List<Product> productList = null;
            String var = request.getParameter("LowToHigh");
            ProductDAO x = new ProductDAO(FactoryProvider.getfactory());
            if(var!=null){
                productList = x.FetchProductInAscendingSortedOrder();
            }else {
                productList = x.FetchProductInDescendingSortedOrder();
            }
             
            
            
            request.setAttribute("data", productList);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
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
