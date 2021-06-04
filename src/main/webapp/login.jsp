<%-- 
    Document   : login
    Created on : 16-Dec-2020, 10:50:33 pm
    Author     : Lovepreet Singh & Purva Saxena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Login</title>
        <%@include file="component/common_css_js.jsp" %>
        <%@include file="component/navbar.jsp" %>
    </head>
    <body>
        <div class="container">
            <center>
        <a href="index.jsp">
        <img class="card-img-top" src="image/mycarticon.jpg" alt="Card image cap" style="height: 100px; width: 150px; margin: 5px; margin-bottom: 10px">
        </a>
        <div class="row">
            <div class="col">
                <div class="card" style="width: 24rem; height: 22rem">
                    <%@include file="component/message_component.jsp" %>
                    <div class="card-body" style="padding-top: 2px">
                        <h3 class="text-center my-3">Login</h3>
                        
                        <form style="font-weight: bold" action="LoginServlet" method="post">
                            <div class="form-group" style="margin-bottom:2px; text-align: left">
                                <label for="email">User Email Id</label>
                                <input type="email" name="user_email" class="form-control" id="email" placeholder = "example@abc.com" >
                            </div>
                            <div class="form-group" style="margin-bottom:10px ; text-align: left">
                                <label for="password">Password</label>
                                <input type="password" name="user_pass" class="form-control" id="password" placeholder = "" >
                            </div>
                            <button type="submit" class="btn btn-success">Submit</button>
                            <hr>
                            <a href="EnterEmail.jsp" class="link" style="font-size: 15px; text-align: left">Create New Account</a>
                        </form>
                        
                    </div>
                    
                </div>
            </div>
        </div>
        </center>
        </div>
    </body>
</html>

