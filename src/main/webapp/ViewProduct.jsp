<%-- 
    Document   : View Product
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
        <title>View & Update product</title>

        <%@include file = "component/common_css_js.jsp" %>
    </head>
    <body>
        <div class="container-fluid mt-3">    
            <div class="row mt-3 mx-2">

                <%
                    CategoryDAO c = new CategoryDAO(FactoryProvider.getfactory());
                    List<Category> categories = c.FetchCategoryList();

                    String[] bid = {};
                    String[] cid = {};

                    BrandDAO b = new BrandDAO(FactoryProvider.getfactory());
                    List<Brand> brands = b.FetchBrandList();


                %>

                <div class="col-md-2 bg-light">
                    <form action="CheckBoxEventHandler">
                        <input type="hidden" name="page" value="ViewProduct">
                        <!-- show brands -->
                        <div>
                            <button type="submit" class="btn custom-bg col-md-12">Apply Filter</button>
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
                                        <h5 class="card-title" ><%=product.getName()%></h5>
                                        <p class="card-text" ><%=Helper.get10words(product.getDescription())%></p>
                                    </div>

                                    <input type="hidden" name="ProductId" value="<%=product.getId()%>">
                                    <div class="card-footer">
                                        <button class="btn custom-bg" data-toggle="modal" data-target="#UpdateProductModal" onclick="update_product(<%=product.getId()%>, '<%=product.getName()%>', '<%=product.getDescription()%>',<%=product.getQuantity()%>,<%=product.getCost()%>,<%=product.getDiscount()%>, '<%=product.getCategory().getCategoryName()%>', '<%=product.getBrand().getBrandName()%>')"> Update Product Details</button>
                                        <button type="submit" class="btn custom-bg" name="remove" data-toggle="modal" data-target="#RemoveProductModal" onclick="remove_product(<%=product.getId() %>)">Remove Product </button>
                                        <button type="button" class="btn bg-light"><div style="font-weight: 900;">&#8377;<%=product.getPriceAfterDiscount()%>/- </div><span class="discount-label"> <%=product.getCost()%></span> ( <%=product.getDiscount()%>% off)</button>
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
        <!--add Product model to update -->


        <div class="modal fade" id="UpdateProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background: #cddc39">
                        <h5 class="modal-title" id="exampleModalLabel">Update Product</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="update-modal-body">

                        <form style="font-weight: bold" id="update-product" action="SaveDetails" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <input type="hidden" name="Operation" value="UpdateProduct">
                            </div>
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="ProductId">Product Id</label>
                                <input type="number" name="ProductId" readonly="readonly" class="form-control" id="ProductId" placeholder = "" >
                            </div>
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="ProductName">Product Name</label>
                                <input type="text" name="name" readonly="readonly" class="form-control" id="ProductName" placeholder = "" >
                            </div>
                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="ProductCategory">Category</label>
                                <input type="text" name="Category" readonly="readonly" class="form-control" id="ProductCategory" placeholder = "" >

                            </div>

                            <div class="form-group" style="margin-bottom:1px ; text-align: left">
                                <label for="ProductBrand">Brand</label>
                                <input type="text" name="Brand" readonly="readonly" class="form-control" id="ProductBrand" placeholder = "" >
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
                                <textarea name="description" readonly="readonly" class = "form-control" style="height: 100px " cols= "53" id="description" placeholder = "Add Description Here" ></textarea>
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
        <!--modal for removing product confirmation -->
        <div class="modal fade" id="RemoveProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">


                <div class="modal-content">
                    <div class="modal-header" style="background: #cddc39">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="Remove">
                        <div class="modal-body">
                            Are You sure, you want to remove this product?
                            <input type="hidden" name="Product" id="id">
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-Success">Yes</button>
                            <button type="button" class="btn btn-success" data-dismiss="modal">No</button>

                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>