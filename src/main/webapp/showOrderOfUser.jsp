<%-- 
    Document   : showOrderOfUser
    Created on : 05-Feb-2021, 12:31:00 pm
    Author     : hinas
--%>

<%@page import="com.mystore.mystore.helper.Helper"%>
<%@page import="com.mystore.mystore.DAO.ProductDAO"%>
<%@page import="com.mystore.mystore.entity.ProductInOrder"%>
<%@page import="com.mystore.mystore.entity.MyOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.mystore.mystore.helper.FactoryProvider"%>
<%@page import="com.mystore.mystore.DAO.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your order</title>
        <%@include file="component/common_css_js.jsp" %>
        <%@include file = "component/navbar.jsp" %>
    </head>
    <body>
        <%            OrderDAO orderDao = new OrderDAO(FactoryProvider.getfactory());
            List<MyOrder> myorders = orderDao.FetchOrderOfUser(user1.getId());
            if (myorders == null) {
        %>
        <div class="container">
            <h2>You Haven't ordered anything yet... </h2><a href="index.jsp">Order Now</a>
        </div>
        <%
                return;
            }
        %>
        <div class="container">
            <div class="row mt-5">
                <div class="col-md-12">
                    <div class="grid">
                        <%
                            for (MyOrder order : myorders) {
                                float totalAmount = 0;
                        %>
                        <div class="card" style="margin-bottom: 20px">
                            <div >Ordered On <span style="font-weight: bold"><%=order.getDate()%></span></div>
                            <div > Delivered to <%=order.getDeliveryAddress()%>
                            </div>
                            <%
                                List<ProductInOrder> pios = orderDao.FetchProductInOrder(order.getOrderId());
                                for (ProductInOrder pio : pios) {
                                    totalAmount += (pio.getPaidAmount() * pio.getQuantity());
                            %>

                            <div class="card" >
                                <div class="container">
                                    <img class="card-img-top m-2" src="image/product/<%=pio.getProduct().getPic()%>" alt="Not Available" style="height: 150px; width: 150%; width: auto">
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title" ><%=pio.getProduct().getName()%></h5>
                                    <p class="card-text" ><%=Helper.get10words(pio.getProduct().getDescription())%></p>
                                </div>

                                <div class="card-footer">
                                    <button type="button" class="btn bg-light"><div style="font-weight: 900;">&#8377;<%=pio.getPaidAmount()%>/- </div></button>
                                    <button type="button" class="btn bg-light"><div style="font-weight: 900;">Quantity : <%=pio.getQuantity()%> </div></button>
                                </div>

                            </div>

                            <%
                                }
                            %>
                            <div>
                                Total Amount paid :  <span style="font-weight: bold"><%=totalAmount%></span>
                            </div>
                        </div>

                        <%
                            }
                        %>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
