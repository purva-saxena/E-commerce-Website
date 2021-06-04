<%-- 
    Document   : customerPanel
    Created on : 21-Dec-2020, 9:33:07 pm
    Author     : hinas
--%>
<%@page import="com.mystore.mystore.entity.User"%>
<%
    User user = (User) session.getAttribute("current-user");
    if (user == null) {
        session.setAttribute("message", "You are not logged in !! ");
        response.sendRedirect("login.jsp");
        return;
    } else if (user.getUserType().equals("Admin")) {
        response.sendRedirect("admin.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Panel</title>
        <%@include file="component/common_css_js.jsp" %>
        <%@include file = "component/navbar.jsp" %>
    </head>

    <body>
        <div class="container">
            <h3 style="margin-top:4px">Your Account</h3>
            <div class="container-fluid mt-3">
                <%@include file="component/message_component.jsp" %>
            </div>
            <div class="row mt-3 text-center">
                <a class="col-md-4" href="showOrderOfUser.jsp">
                    <div class="card">
                        <div class="card-body">
                            <div class="container">
                                <img style="max-width: 80px; max-height: 80px" class= "img-fluid" src="image/shopping-bag.png">   
                            </div>
                            <h3 style="font-style: var">Your Orders</h3>
                        </div>
                    </div>
                </a>
                <a class="col-md-4" href="#">
                    <div class="card">
                        <div class="card-body">
                            <div class="container">
                                <img style="max-width: 80px; max-height: 80px" class= "img-fluid" src="image/teamwork.png">   
                            </div>
                            <h3 style="font-style: var">Login & Security</h3>
                        </div>
                    </div>
                </a>
                <div class="col-md-4">
                    <div class="card" data-toggle = "modal" data-target="#EditUserDetails" onclick="editUser(<%=user1.getId() %>,'<%=user1.getName() %>','<%=user1.getEmail() %>' ,'<%=user1.getGender() %>', '<%=user1.getAddress() %>' , '<%=user1.getPhone() %>' )">
                        <div class="card-body">
                            <div class="container">
                                <img style="max-width: 80px; max-height: 80px" class= "img-fluid" src="image/add category.png">   
                            </div>
                            <h6> </h6>
                            <h3 style="font-style: var">Edit Your Profile</h3>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        
        
        <!--Edit User Details Modal -->


        <div class="modal fade" id="EditUserDetails" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background: #cddc39">
                        <h5 class="modal-title" id="exampleModalLabel">Edit Profile</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="update-modal-body">

                        <form style="font-weight: bold" id="update-product" action="UpdateUserDetails" method="post" >
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <input type="hidden" name="UserId"  class="form-control" id="UserId" placeholder="" >
                            </div>
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="name">Name</label>
                                <input type="text" name="name" readonly="readonly" class="form-control" id="name" placeholder = "" >
                            </div>
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="email">Email</label>
                                <input type="text" name="email" readonly="readonly" class="form-control" id="email" placeholder = "" >

                            </div>

                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="gender">Gender</label>
                                <input type="text" name="gender" readonly="readonly" class="form-control" id="gender" placeholder = "" >
                            </div>

                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="PhoneNo">Phone Number</label>
                                <input type="text" name="PhoneNo" class="form-control" id="PhoneNo" placeholder = "" >
                            </div>
                            <div class="form-group" style="margin-bottom:2px ; text-align: left">
                                <label for="address">Address</label><br>
                                <textarea name="address" class = "form-control" style="height: 100px " cols= "53" id="address" placeholder = "" ></textarea>
                            </div>

                            <div class="container text-right" >
                                <button class="btn btn-success" style="margin-top: 3px">Update</button>
                                <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close" style="margin-top: 3px">Cancel</button>
                            </div>
                            
                        </form>
                    </div>

                </div>
            </div>
        </div>
        
    </body>
</html>
