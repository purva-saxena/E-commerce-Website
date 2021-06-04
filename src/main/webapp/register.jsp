<%-- 
    Document   : register
    Created on : 10-Dec-2020, 5:55:34 pm
    Author     : Lovepreet Singh & Purva Saxena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
        <%@include file="component/common_css_js.jsp" %>
        <%@include file="component/navbar.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <center>
                <a href="index.jsp">
                    <img class="card-img-top" src="image/mycarticon.jpg" alt="Card image cap" style="height: 80px; width: 130px">
                </a>
                <div class="row">
                    <div class="col">
                        <div class="card" style="width: 24rem; height: 32rem">

                            <%@include file="component/message_component.jsp" %>

                            <div class="card-body" style="padding-top: 1px">
                                <h3 class="text-center my-3">Create Account</h3>

                                <form style="font-weight: bold" action="RegisterServlet" method="post">
                                    <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                        <label for="name">User Name</label>
                                        <input type="text" name="user_name" class="form-control" id="name" placeholder = "Enter your name">
                                    </div>
                                    <div class="form-group" style="margin-bottom:1px; text-align: left">
                                        <label for="email">User Email</label>
                                        <input type="email" name="user_email" readonly="readonly" value="<%=session.getAttribute("Email")%>" class="form-control" id="email" >
                                    </div>
                                    <div class="form-group mt-2" style="margin-bottom:1px; text-align: left">
                                        
                                        <div class="form-check form-check-inline">
                                            Gender
                                        </div>
                                        
                                        
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="gender" value="male">
                                            <label class="form-check-label" for="gender">Male</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="gender" value="female">
                                            <label class="form-check-label" for="gender">Female</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="gender" value="other">
                                            <label class="form-check-label" for="gender">Other</label>
                                        </div>
                                    </div>

                                    <div class="form-group mt-2" style="margin-bottom:1px ; text-align: left">
                                        <label for="password">Password</label>
                                        <input type="password" name="user_pass" class="form-control" id="password" placeholder = "Atleast 8 character" >
                                    </div>
                                    <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                        <label for="phone">Phone Number</label>
                                        <input type="number" name="user_phone" class="form-control" id="phone" placeholder = "" >
                                    </div>
                                    <div class="form-group" style="margin-bottom:2px ; text-align: left">
                                        <label for="address">Address</label><br>
                                        <textarea name="user_address" class = "form-control" style="height: 70px " cols= "53" id="address" placeholder = "Enter your address" ></textarea>
                                    </div>

                                    <div class="container text-center" >
                                        <button class="btn btn-outline-success" style="margin-top: 3px">Register</button>
                                        <button class="btn btn-outline-warning" style="margin-top: 3px">Reset</button>
                                    </div>

                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </center>
        </div>
    </body>
</html>
