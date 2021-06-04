package com.mystore.mystore.servlets;

import com.mystore.mystore.DAO.BrandDAO;
import com.mystore.mystore.DAO.CategoryDAO;
import com.mystore.mystore.DAO.ProductDAO;
import com.mystore.mystore.entity.Brand;
import com.mystore.mystore.entity.Category;
import com.mystore.mystore.entity.Product;
import com.mystore.mystore.helper.Capitalize;
import com.mystore.mystore.helper.FactoryProvider;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "SaveDetails", urlPatterns = {"/SaveDetails"})
public class SaveDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        System.out.print("@@@@@@@@");

        try ( PrintWriter out = response.getWriter()) {

            Capitalize c = new Capitalize();
            String Operation = request.getParameter("Operation");
            String Name = c.capitalize(request.getParameter("name"));
            String Description = request.getParameter("description");
            if (Operation.equals("addBrand")) {
                //verifying and saving details of a brand
                if (Name.isEmpty() || Description.isEmpty()) {

                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "please fill all the fields.");
                    response.sendRedirect("admin.jsp");
                    return;

                } else {
                    Brand brand = new Brand(Name, Description);
                    BrandDAO x = new BrandDAO(FactoryProvider.getfactory());
                    //check if  brand name already exist
                    if (x.FetchByName(Name) == 1) {
                        HttpSession httpsession = request.getSession();
                        httpsession.setAttribute("message", "Brand Already Exists");
                        response.sendRedirect("admin.jsp");
                        return;
                    }

                    x.addBrand(brand);

                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "Brand added successfully ! ");
                    response.sendRedirect("admin.jsp");
                    return;
                }
            } else if (Operation.equals("addProduct")) {

                String cost = request.getParameter("Cost");
                String discount = request.getParameter("Discount");
                String quantity = request.getParameter("Quantity");
                String cId = request.getParameter("CatId");
                String brandId = request.getParameter("BrandId");
                Part part = request.getPart("productPic");

                if (Name.isEmpty() || Description.isEmpty() || cost.isEmpty() || quantity.isEmpty() || discount.isEmpty() || brandId.isEmpty() || cId.isEmpty()) {

                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "please fill all the fields with valid values.");
                    response.sendRedirect("admin.jsp");
                    return;

                }
                if (Float.parseFloat(discount) >= 100) {
                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "Discount can not be more than 100!!");
                    response.sendRedirect("admin.jsp");
                    return;
                }

                Product product = new Product();
                product.setName(Name);
                product.setDescription(Description);
                product.setCost(Float.parseFloat(cost));
                product.setDiscount(Float.parseFloat(discount));
                product.setQuantity(Integer.parseInt(quantity));
                product.setPic(part.getSubmittedFileName());
                CategoryDAO x = new CategoryDAO(FactoryProvider.getfactory());
                product.setCategory(x.FetchById(Integer.parseInt(cId)));

                BrandDAO y = new BrandDAO(FactoryProvider.getfactory());
                product.setBrand(y.FetchById(Integer.parseInt(brandId)));

                ProductDAO z = new ProductDAO(FactoryProvider.getfactory());
                boolean bool = z.addProduct(product);

                //finding path to where we will save image
                //String path = request.getSession().getServletContext().getRealPath("image") + File.separator + "product" + File.separator + part.getSubmittedFileName();

                String path = "C:\\Users\\hinas\\Desktop\\Documents\\NetBeansProjects\\mystore\\src\\main\\webapp\\image\\product"+ File.separator + part.getSubmittedFileName();
                try {
                    //code for saving image into desired folder .......
                    FileOutputStream fos = new FileOutputStream(path);
                    InputStream is = part.getInputStream();

                    //reading data
                    //data variable is holding the picture
                    byte[] data = new byte[is.available()];
                    is.read(data);

                    //writing data
                    fos.write(data);
                    fos.close();
                } catch (Exception e) {
                }

                if (bool = true) {
                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "Product added successfully ! ");
                    response.sendRedirect("admin.jsp");
                    return;
                }
                HttpSession httpsession = request.getSession();
                httpsession.setAttribute("message", "Sorry, an error has ocuured ! ");
                response.sendRedirect("admin.jsp");
                return;

            } else if (Operation.equals("addCategory")) {

                //verifying and saving details of category
                if (Name.isEmpty() || Description.isEmpty()) {

                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "please fill all the fields.");
                    response.sendRedirect("admin.jsp");
                    return;

                } else {
                    Category category = new Category(Name, Description);
                    CategoryDAO x = new CategoryDAO(FactoryProvider.getfactory());

                    //check if category already exists
                    if (x.FetchByName(Name) == 1) {
                        HttpSession httpsession = request.getSession();
                        httpsession.setAttribute("message", "Category Already Exists");
                        response.sendRedirect("admin.jsp");
                        return;
                    }
                    x.addCategory(category);

                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "Category added successfully ! ");
                    response.sendRedirect("admin.jsp");
                    return;
                }
            } else if (Operation.equals("UpdateProduct")) {

                float cost = Float.parseFloat(request.getParameter("Cost"));
                float discount = Float.parseFloat(request.getParameter("Discount"));
                int quantity = Integer.parseInt(request.getParameter("Quantity"));

                int id = Integer.parseInt(request.getParameter("ProductId"));

                if (discount >= 100 || discount < 0 || cost < 0 || quantity < 0) {
                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "Please Enter the valid values");
                    response.sendRedirect("ViewProduct.jsp");
                    return;
                }

                ProductDAO x = new ProductDAO(FactoryProvider.getfactory());
                x.UpdateProduct(cost, quantity, discount, id);

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Product details are updated !");
                response.sendRedirect("ViewProduct.jsp");
                return;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
