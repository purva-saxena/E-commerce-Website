<%-- 
    Document   : BrandCategory
    Created on : 11-Jan-2021, 11:47:58 pm
    Author     : hinas
--%>

<%@page import="com.mystore.mystore.helper.FactoryProvider"%>
<%@page import="com.mystore.mystore.DAO.BrandDAO"%>
<%@page import="com.mystore.mystore.entity.Brand"%>
<%@page import="java.util.List"%>
<%@page import="com.mystore.mystore.DAO.CategoryDAO"%>
<%@page import="com.mystore.mystore.entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brand & Categories</title>
    </head>
    <body>
        <%@include file = "component/common_css_js.jsp" %>
       <%    
            CategoryDAO c = new CategoryDAO(FactoryProvider.getfactory());
            List<Category> categoryList = c.FetchCategoryList();

            BrandDAO b = new BrandDAO(FactoryProvider.getfactory());
            List<Brand> BrandList = b.FetchBrandList();
        %>
        <div class="container-fluid mt-3">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <h3 class="text-center" style="font-weight: bold">Brand List</h3>
                        <table class="table table-hover">
                            <thead>
                            <th>Brand Id</th>
                            <th>Brand Name</th>
                            <th>Description</th>
                            </thead>
                            <%
                                for (Brand brand : BrandList) {
                            %>
                            <tr>
                                <td><%= brand.getBrandId()%></td>
                                <td><%= brand.getBrandName()%></td>
                                <td><%= brand.getBrandDescription()%></td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <h3 class="text-center" style="font-weight: bold">Category List</h3>
                        <table class="table-hover table">
                            <thead>
                            <th>Category Id</th>
                            <th>Category Name</th>
                            <th>Description</th>
                            </thead>
                            <%
                                for (Category category : categoryList) {
                            %>
                            <tr>
                                <td><%= category.getCategoryId()%></td>
                                <td><%= category.getCategoryName()%></td>
                                <td><%= category.getCategoryDescription()%></td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
