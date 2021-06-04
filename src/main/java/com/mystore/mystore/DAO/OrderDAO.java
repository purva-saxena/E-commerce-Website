package com.mystore.mystore.DAO;

import com.mystore.mystore.entity.MyOrder;
import com.mystore.mystore.entity.ProductDetailesOfCart;
import com.mystore.mystore.entity.ProductInOrder;
import com.mystore.mystore.entity.User;
import com.mystore.mystore.helper.FactoryProvider;
import com.mystore.mystore.helper.SendEmail;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author hinas
 */
public class OrderDAO {

    SessionFactory sessionFactory;

    public OrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void manageOrder(int userId, String phoneNo, String shippingAdd, int productId, int Quantity) {
        MyOrder myOrder = new MyOrder();
        myOrder.setDate();
        myOrder.setDeliveryAddress(shippingAdd);
        myOrder.setPhoneNo(phoneNo);
        UserDAO userDao = new UserDAO(FactoryProvider.getfactory());
        User user = userDao.FetchUserById(userId);
        myOrder.setUser(user);
        addOrder(myOrder);

        ProductDAO productDAO = new ProductDAO(FactoryProvider.getfactory());

        ProductInOrder p = new ProductInOrder(productDAO.fetchProductById(productId), myOrder, Quantity);
        AddProductInOrder(p);
        sendMail(user.getName(), user.getEmail());
    }

    public void manageListOfOrders(int userId, String phoneNo, String shippingAdd, List<ProductDetailesOfCart> productList) {

        MyOrder myOrder = new MyOrder();
        myOrder.setDate();
        myOrder.setDeliveryAddress(shippingAdd);
        myOrder.setPhoneNo(phoneNo);
        UserDAO userDao = new UserDAO(FactoryProvider.getfactory());
        User user = userDao.FetchUserById(userId);
        myOrder.setUser(user);
        addOrder(myOrder);

        ProductDAO productDAO = new ProductDAO(FactoryProvider.getfactory());

        for (ProductDetailesOfCart i : productList) {
            ProductInOrder p = new ProductInOrder(productDAO.fetchProductById(i.getProductId()), myOrder, i.getProductQuantity());
            AddProductInOrder(p);
        }

        sendMail(user.getName(), user.getEmail());

    }

    public void sendMail(String name, String email) {
        String message = "Hello " + name + " .Your order ";

        message = message + " has been recorded. It will be delivered to you within next 7 days." + "\n" + "HAPPY SHOPPING";

        SendEmail sendEmail = new SendEmail();
        sendEmail.sendEmail(email, message, "Your MyCart Order Details");
    }

    public void addOrder(MyOrder myorder) {

        try {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(myorder);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddProductInOrder(ProductInOrder p) {
        try {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(p);
            tx.commit();
            session.close();
        } catch (Exception e) {
        }
    }

    public List<MyOrder> FetchOrderOfUser(int UserId) {

        List<MyOrder> myOrders = null;
        try {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from MyOrder as o where o.user.UserId=:e");
            q.setParameter("e", UserId);

            myOrders = q.list();

            tx.commit();
            session.close();
            return myOrders;

        } catch (Exception e) {
        }

        return myOrders;
    }

    public List<ProductInOrder> FetchProductInOrder(int orderId) {
        List<ProductInOrder> productInOrders = null;
        try {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from ProductInOrder as pio where pio.myorder.orderId =: e");
            query.setParameter("e", orderId);
            productInOrders = query.list();
            tx.commit();
            session.close();
            return productInOrders;
        } catch (Exception e) {
        }

        return productInOrders;
    }

}
