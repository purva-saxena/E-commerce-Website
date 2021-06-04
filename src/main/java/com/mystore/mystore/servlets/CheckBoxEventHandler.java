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
@WebServlet(name = "CheckBoxEventHandler", urlPatterns = {"/CheckBoxEventHandler"})
public class CheckBoxEventHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String[] brands = request.getParameterValues("selectedBrand");
            String[] categories = request.getParameterValues("selectedCat");
            String s = request.getParameter("page");
            ProductDAO p = new ProductDAO(FactoryProvider.getfactory());
            List<Product> product = p.fetchProductByBrandAndCategoryBoth(brands, categories);

            if (s.equals("index")) {
                request.setAttribute("data", product);
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }else{
                request.setAttribute("data", product);
                RequestDispatcher rd = request.getRequestDispatcher("ViewProduct.jsp");
                rd.forward(request, response);
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
