<%-- 
    Document   : showAllUser
    Created on : 11-Jan-2021, 10:44:11 pm
    Author     : hinas
--%>

<%@page import="java.util.List"%>
<%@page import="com.mystore.mystore.helper.FactoryProvider"%>
<%@page import="com.mystore.mystore.DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User currentUser = (User) session.getAttribute("current-user");
    if (currentUser == null) {
        session.setAttribute("message", "You are not logged in !! ");
        response.sendRedirect("login.jsp");
        return;
    } else {
        if (currentUser.getUserType().equals("Normal")) {
            session.setAttribute("message", "You are not allowed to access this page.");
            response.sendRedirect("index.jsp");
            return;
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Detailes</title>
    </head>
    <body>
        <%@include file = "component/common_css_js.jsp" %>
        <%@include file= "component/navbar.jsp" %>
        <%@include file="component/common_modal.jsp"%>
        <%            UserDAO u = new UserDAO(FactoryProvider.getfactory());
            List<User> userList = u.fetchAllUser();

        %>
        <div class="container-fluid mt-3">
            <div class="row ">
                <div>
                    <table class="table table-hover table-dark ml-4 table-responsive col-md-12">
                        <thead>
                            <tr>
                                <th scope="col">User Id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Address</th>
                                <th scope="col">Email</th>
                                <th scope="col">Cart Item</th>
                                <th scope="col">Purchased Items</th>
                                
                            </tr>
                        </thead>

                        <%   
                            for (User user : userList) {
                        %>
                        <tr>
                            <td><%= user.getId()%></td>
                            <td><%= user.getName()%></td>
                            <td><%= user.getPhone()%></td>
                            <td><%= user.getAddress()%></td>
                            <td><%= user.getEmail() %></td>
                            <td><button class="btn-outline-success">View Cart Item</button></td>
                            <td><button class="btn-outline-success">View Purchased Item</button></td>
                        </tr>
                        <%
                            }
                        %>

                    </table>
                </div>
            </div>
        </div>

    </body>
</html>
