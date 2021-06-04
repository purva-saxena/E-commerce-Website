<%-- 
    Document   : CheckOut
    Created on : 11-Jan-2021, 12:04:32 am
    Author     : hinas
--%>

<%@page import="com.mystore.mystore.entity.Product"%>
<%@page import="com.mystore.mystore.helper.FactoryProvider"%>
<%@page import="com.mystore.mystore.DAO.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("current-user");
    if (user == null) {
        session.setAttribute("message", "You are not logged in !! ");
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="component/common_css_js.jsp" %>
        <%@include file="component/navbar.jsp" %>
        <%@include file="component/common_modal.jsp"%>
    </head>
    <body onload="initialize_map();">
        <div class="container-fluid">
            <form action="OrderServlet">
                <div class="row mt-5">

                    <div class="col-md-7">
                        <!--card -->
                        <div class ="card">
                            <h3 class="text-center">Your Selected Items</h3>

                            <div class="card-body " >
                                <%                                String pid = request.getParameter("ProductId");
                                    if (pid == null) {

                                %>
                                <div class="cart-body">

                                </div>
                                <%} else {
                                    ProductDAO productDAO = new ProductDAO(FactoryProvider.getfactory());
                                    Product product = productDAO.fetchProductById(Integer.parseInt(pid));
                                    double x = product.getPriceAfterDiscount();
                                %>

                                <table class="table">
                                    <thead>
                                    <th>Product Name</th>
                                    <th>Cost</th>
                                    <th>Quantity</th>
                                    <th>Total Cost</th>
                                    </thead>
                                    <tr>
                                        <td><%=product.getName()%></td>
                                        <td><%=x%></td>
                                        <td><input type="text" name="Quantity" class="form-control no-border" id="Quantity" size=2 id="" value="<%=1%>" readonly="true"><button type="button" class="badge badge-pill badge-success btn-sm" style="border: none;" onclick='incrementSelectedItem(<%=x%>)'>+</button><button type="button" class="badge badge-pill badge-danger btn-sm" style="border: none;" onclick='decrementSelectedItem(<%=x%>)'>-</button></td>
                                        <td><input type="text" class="form-control no-border" id="totalCost" readonly="true" value="<%=x%>"></td>
                                    </tr>

                                </table>
                                <input type="hidden" name="ProductId" value="<%=product.getId()%>">
                                <%
                                    }
                                %>
                            </div>

                        </div>
                    </div>

                    <!--form details-->

                    <div class="col-md-5">
                        <div class ="card col-lg">
                            <h3 class="text-center">Detailes</h3>
                            <div class="card-body">
                                <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                    <label for="name">Name</label>
                                    <input value="<%=user.getName()%>" disabled="true" type="text" name="user_name" class="form-control" id="name">
                                </div>

                                <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                    <input type="hidden" name="userId" value="<%=user.getId()%>" class="form-control" id="userId">
                                </div>

                                <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                    <input type="hidden" name="cart-details" class="form-control" id="cart-details">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input value="<%=user.getEmail()%>" type="email" disabled="true" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                </div>

                                <div class="form-group" style="margin-bottom:2px ; text-align: left">
                                    <label for="phone">Phone Number</label>
                                    <input type="text" name="phoneNo" value="<%=user.getPhone()%>" class="form-control" id="phone" placeholder = "" >
                                </div>

                                <div class="form-group" style="margin-bottom:7px ; text-align: left">
                                    <label for="address">Shipping Address</label><br>
                                    <textarea class = "form-control" name="shippingAdd" style="height: 70px " cols= "53" id="shippingAdd" ><%=user.getAddress()%></textarea>
                                </div>


                                <div class="container text-center">
                                    <button name="button" value="order" onclick="initialize_map()" class="btn btn-outline-success">Order Now</button>
                                    <a href ="index.jsp" >Continue Shopping</a>
                                </div>


                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    </body>
</html>
