
<%@page import="com.mystore.mystore.entity.User"%>
<%
    User user1 = (User) session.getAttribute("current-user");

%>


<nav class="navbar navbar-expand-lg navbar-light bg-light custom-bg">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">MyCart</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                
            </ul>
            <form class="form-inline my-2 my-lg-0" autocomplete="off" action="Search">
                <input class="form-control mr-sm-2 autocomplete" type="search" placeholder="Search" id="search" name="search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
            
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#" data-toggle="modal" data-target="#cart"><i class="fa fa-cart-plus" style="font-size: 30px"></i> <span  class="ml-0 cart-items" style="font-size: 15px; font-weight: bold">( 0 )</span></a>
                </li>
                <%                if (user1 == null) {
                %>
                <li class="nav-item active">
                    <a class="nav-link" href="login.jsp">Login <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="EnterEmail.jsp">Sign up <span class="sr-only">(current)</span></a>
                </li>

                <%
                } else {
                %>
                <li class="nav-item active">
                    <a class="nav-link" href="customerPanel.jsp"><%=user1.getName()%><span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="LogoutServlet">Log Out<span class="sr-only">(current)</span></a>
                </li>
                <%
                    }
                %>

            </ul>
        </div>
    </div>
</nav>