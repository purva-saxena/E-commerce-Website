<%--
    Document   : admin
    Created on : 21-Dec-2020, 9:31:45 pm
    Author     : Lovepreet Singh & Purva Saxena
--%>
<%@page import="com.mystore.mystore.entity.Product"%>
<%@page import="com.mystore.mystore.DAO.ProductDAO"%>
<%@page import="com.mystore.mystore.DAO.UserDAO"%>
<%@page import="com.mystore.mystore.entity.Brand"%>
<%@page import="com.mystore.mystore.DAO.BrandDAO"%>
<%@page import="com.mystore.mystore.entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.mystore.mystore.helper.FactoryProvider"%>
<%@page import="com.mystore.mystore.DAO.CategoryDAO"%>
<%@page import="com.mystore.mystore.entity.User"%>
<%
    User user = (User) session.getAttribute("current-user");
    if (user == null) {
        session.setAttribute("message", "You are not logged in !! ");
        response.sendRedirect("login.jsp");
        return;
    } else {
        if (user.getUserType().equals("Normal")) {
            session.setAttribute("message", "You are not allowed to access this page.");
            response.sendRedirect("login.jsp");
            return;
        }
    }
%>
<%
    CategoryDAO c = new CategoryDAO(FactoryProvider.getfactory());
    List<Category> categoryList = c.FetchCategoryList();

    BrandDAO b = new BrandDAO(FactoryProvider.getfactory());
    List<Brand> BrandList = b.FetchBrandList();
%>

<%@include file="component/common_modal.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>
        <%@include file="component/common_css_js.jsp" %>
        <%@include file="component/navbar.jsp" %>

    </head>
    <body>
        <div class="container admin">
            <div class="container-fluid">
                <%@include file="component/message_component.jsp" %>
            </div>
            <div class="row mt-3 text-center">

                <a class="col-md-4" href="showAllUser.jsp">
                    <div class="card">
                        <div class="card-body">
                            <div class="container">
                                <img style="max-width: 80px; max-height: 80px" class= "img-fluid" src="image/teamwork.png">   
                            </div>
                            <%                                UserDAO u = new UserDAO(FactoryProvider.getfactory());
                                List<User> userList = u.fetchAllUser();
                            %>
                            <h6><%=userList.size()%></h6>
                            <h3 style="font-style: var">USERS</h3>
                        </div>
                    </div>
                </a>
                <a class="col-md-4" href="BrandCategory.jsp">
                    <div class="card">
                        <div class="card-body">
                            <div class="container">
                                <img style="max-width: 80px; max-height: 80px" class= "img-fluid" src="image/list.png">   
                            </div>
                            <h5 style="font-weight: bold">BRANDS (<%=BrandList.size()%>)   &   </h5>
                            <h5 style="font-weight: bold">CATEGORIES (<%=categoryList.size()%>)</h5>
                        </div>
                    </div>
                </a>
                <a class="col-md-4" href="ViewProduct.jsp">
                    <div class="card">
                        <div class="card-body">
                            <div class="container">
                                <img style="max-width: 80px; max-height: 80px" class= "img-fluid" src="image/product.png">   
                            </div>
                            <%
                                ProductDAO p = new ProductDAO(FactoryProvider.getfactory());
                                List<Product> productList = p.fetchAllProduct();

                            %>
                            <h6><%=productList.size()%></h6>
                            <h3 style="font-style: var">PRODUCT</h3>
                        </div>
                    </div>
                </a>
            </div>
            <div class="row mt-3 text-center">
                <div class="col-md-4">
                    <div class="card" data-toggle = "modal" data-target="#addCategoryModal">
                        <div class="card-body">
                            <div class="container">
                                <img style="max-width: 80px; max-height: 80px" class= "img-fluid" src="image/add category.png">   
                            </div>
                            <h6> </h6>
                            <h3 style="font-style: var">ADD CATEGORIES</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card" data-toggle = "modal" data-target="#addProductModal">
                        <div class="card-body">
                            <div class="container">
                                <img style="max-width: 80px; max-height: 80px" class= "img-fluid" src="image/add product.png">   
                            </div>
                            <h6> </h6>
                            <h3 style="font-style: var">ADD PRODUCTS</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card" data-toggle = "modal" data-target="#addBrandModal">
                        <div class="card-body">
                            <div class="container">
                                <img style="max-width: 80px; max-height: 80px" class= "img-fluid" src="image/brand-engagement.png">   
                            </div>
                            <h6> </h6>
                            <h3 style="font-style: var">ADD BRANDS</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--add category model -->

        <div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background: #cddc39">
                        <h5 class="modal-title" id="exampleModalLabel">Add Category</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form style="font-weight: bold" action="SaveDetails" method="post">
                            <div class="form-group">
                                <input type="hidden" name="Operation" value="addCategory">
                            </div>
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="CategoryName">Category Name</label>
                                <input type="text" name="name" class="form-control" id="CategoryName" placeholder = "" >
                            </div>

                            <div class="form-group" style="margin-bottom:2px ; text-align: left">
                                <label for="description">Description</label><br>
                                <textarea name="description" class = "form-control" style="height: 150px " cols= "53" id="description" placeholder = "Add Description Here" ></textarea>
                            </div>
                            <div class="container text-right" >
                                <button class="btn btn-success" style="margin-top: 3px">Add Category</button>
                                <button type="button" class="btn btn-warning" style="margin-top: 3px" data-dismiss="modal" aria-label="Close">Cancel</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        <!--add Product model -->


        <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background: #cddc39">
                        <h5 class="modal-title" id="exampleModalLabel">Add Product</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form style="font-weight: bold" action="SaveDetails" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <input type="hidden" name="Operation" value="addProduct">
                            </div>
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="ProductName">Product Name</label>
                                <input type="text" name="name" class="form-control" id="ProductName" placeholder = "" >
                            </div>
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="CatId">Select Category</label>

                                <select name="CatId" class="form-control">
                                    <%                                        for (Category category : categoryList) {

                                    %>
                                    <option value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="BrandId">Select Brand</label>

                                <select name="BrandId" class="form-control">
                                    <%
                                        for (Brand brand : BrandList) {

                                    %>

                                    <option value="<%=brand.getBrandId()%>"><%=brand.getBrandName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="Cost">Cost</label>
                                <input type="Number" step="0.01" name="Cost" class="form-control" id="Cost" placeholder = "" >
                            </div>

                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="Discount">Discount</label>
                                <input type="Number" step="0.01" name="Discount" class="form-control" id="Discount" placeholder = "" >
                            </div>

                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="Quantity">Quantity</label>
                                <input type="Number" name="Quantity" class="form-control" id="Quantity" placeholder = "" >
                            </div>
                            <div class="form-group" style="margin-bottom:2px ; text-align: left">
                                <label for="description">Description</label><br>
                                <textarea name="description" class = "form-control" style="height: 100px " cols= "53" id="description" placeholder = "Add Description Here" ></textarea>
                            </div>

                            <!-- product image file -->
                            <div class="form-group">
                                <label for="productPic">Add Picture for the product</label><br>
                                <input type="file" id = "productPic" name="productPic" required="true">
                            </div>

                            <div class="container text-right" >
                                <button class="btn btn-success" style="margin-top: 3px">Add Product</button>
                                <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close" style="margin-top: 3px">Cancel</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>


        <!--add Brand model -->
        <div class="modal fade" id="addBrandModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background: #cddc39">
                        <h5 class="modal-title" id="exampleModalLabel">Add Brand</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form style="font-weight: bold" action="SaveDetails" method="post">
                            <div class="form-group">
                                <input type="hidden" name="Operation" value="addBrand">
                            </div>
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="BrandName">Brand Name</label>
                                <input type="text" name="name" class="form-control" id="BrandName" placeholder = "" >
                            </div>
                            <div class="form-group" style="margin-bottom:2px ; text-align: left">
                                <label for="description">Description</label><br>
                                <textarea name="description" class = "form-control" style="height: 150px " cols= "53" id="description" placeholder = "Add Description Here" ></textarea>
                            </div>

                            <div class="container text-right" >
                                <button class="btn btn-success" style="margin-top: 3px">Add Brand</button>
                                <button type="button" class="btn btn-warning "  data-dismiss="modal" aria-label="Close"  style="margin-top: 3px">Cancel</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>

    </body>
</html>
