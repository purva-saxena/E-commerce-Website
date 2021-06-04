package com.mystore.mystore.servlets;

import com.mystore.mystore.DAO.OrderDAO;
import com.mystore.mystore.entity.ProductDetailesOfCart;
import com.mystore.mystore.helper.FactoryProvider;
import com.mystore.mystore.helper.JsonToJavaObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            String pid = request.getParameter("ProductId");
            int userId = Integer.parseInt(request.getParameter("userId"));
            String phoneNo = request.getParameter("phoneNo");
            String shippingAdd = request.getParameter("shippingAdd");
            OrderDAO orderDAO = new OrderDAO(FactoryProvider.getfactory());
            if (pid == null) {
                String str = request.getParameter("cart-details");
                JsonToJavaObject y = new JsonToJavaObject();
                List<ProductDetailesOfCart> productList = y.GetJavaObjects(str);
                orderDAO.manageListOfOrders(userId, phoneNo, shippingAdd, productList);
            }else{
                int quantity = Integer.parseInt(request.getParameter("Quantity"));
                orderDAO.manageOrder(userId, phoneNo, shippingAdd, Integer.parseInt(pid), quantity);
            }

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("message", "Your Order has been recorded. Check Your mail for further Information...");
            response.sendRedirect("index.jsp");

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
