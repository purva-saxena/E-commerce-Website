<%-- 
    Document   : index
    Created on : 07-Dec-2020, 4:39:46 pm
    Author     : Lovepreet Singh & Purva Saxena
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.mystore.mystore.entity.Brand"%>
<%@page import="com.mystore.mystore.DAO.BrandDAO"%>
<%@page import="com.mystore.mystore.helper.Helper"%>
<%@page import="com.mystore.mystore.entity.Category"%>
<%@page import="com.mystore.mystore.DAO.CategoryDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.mystore.mystore.entity.Product"%>
<%@page import="com.mystore.mystore.DAO.ProductDAO"%>
<%@page import="com.mystore.mystore.helper.FactoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Electronics Store</title>

        <%@include file = "component/common_css_js.jsp" %>
    </head>
    <body>
        <%@include file= "component/navbar.jsp" %>

        <%@include file="component/common_modal.jsp"%>
        <div class="container-fluid mt-3">    
            <div class="row mt-3 mx-2">

                <%                    CategoryDAO c = new CategoryDAO(FactoryProvider.getfactory());
                    List<Category> categories = c.FetchCategoryList();

                    BrandDAO b = new BrandDAO(FactoryProvider.getfactory());
                    List<Brand> brands = b.FetchBrandList();


                %>

                <div class="col-md-2 bg-light">
                    <form action="SortData" method="post">
                        <div class="dropdown">
                            <a class="nav-link dropdown-toggle col-md-12 custom-bg text-center"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Sort By Price
                            </a>
                            <div class="dropdown-menu col-md-12" aria-labelledby="navbarDropdown">

                                <button type="submit" name="LowToHigh" class="dropdown-item">Low to high</button>
                                <button type="submit" name="highToLow" class="dropdown-item">High to Low</button>

                            </div>

                        </div>

                    </form>
                    <form action="CheckBoxEventHandler" action="post">
                        <!-- show brands -->
                        <input type="hidden" name="page"  value="index">
                        <div>
                            <div>
                                <button type="submit" class="btn custom-bg col-md-12 mt-2">Apply Filter</button>
                            </div>
                            <h4>Brands</h4>
                            <table>
                                <%                            for (Brand brand : brands) {
                                %> 
                                <tr>
                                    <td>

                                        <input type="checkbox" style="width:20px;height:20px; margin-right: 2px" name="selectedBrand" value="<%=brand.getBrandId()%>"><%=brand.getBrandName()%>

                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                        </div>


                        <!-- show category -->
                        <div>

                            <h4>Category</h4>
                            <table>
                                <%
                                    for (Category category : categories) {
                                %>    
                                <tr>
                                    <td>
                                        <input type="checkbox" style="width:20px;height:20px; margin-right: 2px" name="selectedCat" value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                        </div>
                    </form>
                </div>

                <!-- show product -->
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="container-fluid">
                                <%@include file="component/message_component.jsp" %>
                            </div>
                            <div class="grid">

                                <%                                    List<Product> products = (ArrayList<Product>) request.getAttribute("data");
                                    if (products == null) {
                                        ProductDAO p = new ProductDAO(FactoryProvider.getfactory());
                                        products = p.fetchAllProduct();

                                    }
                                    if (products.size() == 0) {
                                        out.print("Sorry !! No  Product available");
                                    } else {
                                        for (Product product : products) {
                                %>
          
                                    <div class="card" >
                                        <div class="container text-center">
                                            <img class="card-img-top m-2" src="image/product/<%=product.getPic()%>" alt="Not Available" style="height: 150px; width: 150%; width: auto">
                                        </div>
                                        <div class="card-body">
                                            <h5 class="card-title"><%=product.getName()%></h5>
                                            <p class="card-text"><%=Helper.get10words(product.getDescription())%></p>
                                        </div>

                                        <div class="card-footer">
                                            <table>
                                                <tr>
                                                    <td><button class="btn custom-bg" name="actionButton" id="addToCart" value="addToCart" <%if (product.getQuantity() == 0) { %>disabled="true"<% }%> onclick="add_to_cart(<%=product.getId()%>, '<%=product.getName()%>', <%=product.getPriceAfterDiscount()%>)">Add to Cart</button></td>

                                                    <td><form action="CheckOut.jsp">
                                                            <input type="hidden" name="ProductId" value="<%=product.getId()%>">
                                                            <button class="btn custom-bg" name="actionButton" id="addToCart" value="buyNow" <%if (product.getQuantity() == 0) { %>disabled="true"<% }%> >Buy Now</button>
                                                        </form></td>
                                                    <td>
                                                        <button type="button" class="btn bg-light"><div style="font-weight: 900;">&#8377;<%=product.getPriceAfterDiscount()%>/- </div><span class="discount-label"> <%=product.getCost()%></span> ( <%=product.getDiscount()%>% off)</button>
                                                        <%
                                                            if (product.getQuantity() == 0) {
                                                        %>
                                                        <span style="font-weight: bold"> Not Available</span>
                                                        <%
                                                        } else {
                                                        %>
                                                        <span style="font-weight: bold"> In Stock</span>
                                                        <%
                                                            }
                                                        %>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                    </div>
                             
                                <%
                                        }
                                    }

                                %>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </body>
</html>