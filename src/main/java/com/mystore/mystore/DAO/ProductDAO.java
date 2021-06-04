package com.mystore.mystore.DAO;

import com.mystore.mystore.entity.Product;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author hinas
 */
public class ProductDAO {

    private SessionFactory factory;

    public ProductDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public boolean addProduct(Product product) {

        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(product);

            tx.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Product> fetchAllProduct() {

        List<Product> products = null;
        try {
            try ( Session session = this.factory.openSession()) {
                Query query = session.createQuery("from Product");
                products = (List<Product>) query.list();
            }
            return products;

        } catch (Exception e) {

        }
        return products;
    }

    public List<Product> fetchProductByCategory(List<Integer> cid) {

        List<Product> products = null;
        try {
            try ( Session session = this.factory.openSession()) {
                Query query = session.createQuery("from Product as p where p.category.CategoryId in (:e)");
                query.setParameter("e", cid);
                products = (List<Product>) query.list();
            }
            return products;

        } catch (Exception e) {

        }
        return products;
    }

    public Product fetchProductById(int id) {

        Product product = null;
        try {
            try ( Session session = this.factory.openSession()) {
                Query query = session.createQuery("from Product where Id=:e");
                query.setParameter("e", id);
                product = (Product) query.uniqueResult();
            }
            return product;

        } catch (Exception e) {

        }
        return product;
    }
    
    
    
    public List<Product> fetchProductByBrand(List<Integer> bid) {

        List<Product> products = null;
        try {
            try ( Session session = this.factory.openSession()) {
                Query query = session.createQuery("from Product as p where p.brand.BrandId in (:e)");
                query.setParameter("e", bid);
                products = (List<Product>) query.list();
            }
            return products;

        } catch (Exception e) {

        }
        return products;
    }

    public List<Product> fetchProductByBrandAndCategoryBoth(String[] bid, String[] cid) {

        List<Product> products = null;
        try {
            List<Integer> brand = new ArrayList<Integer>();
            List<Integer> category = new ArrayList<Integer>();

            if (bid == null && cid == null) {
                products = fetchAllProduct();
                return products;
            } else if (bid == null) {

                for (String i : cid) {
                    category.add(Integer.parseInt(i));
                }

                products = fetchProductByCategory(category);
                return products;

            } else if (cid == null) {
                for (String i : bid) {
                    brand.add(Integer.parseInt(i));
                }

                products = fetchProductByBrand(brand);
                return products;
            } else {
                Session session = this.factory.openSession();
                Transaction tx = session.beginTransaction();
                for (String i : cid) {
                    category.add(Integer.parseInt(i));
                }
                for (String i : bid) {
                    brand.add(Integer.parseInt(i));
                }
                Query query = session.createQuery("from Product as p where p.brand.BrandId in (:b) and p.category.CategoryId in (:c)");
                query.setParameter("b", brand);
                query.setParameter("c", category);

                products = (List<Product>) query.list();
                tx.commit();
                session.close();
                return products;
            }
        } catch (Exception e) {
        }
        return products;
    }

    public List<Product> FetchProductInAscendingSortedOrder() {

        List<Product> productList = null;
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Product order by Cost asc");
            productList = q.list();

            tx.commit();
            session.close();

            return productList;
        } catch (Exception e) {
        }

        return productList;
    }

    public List<Product> FetchProductInDescendingSortedOrder() {

        List<Product> productList = null;
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Product order by Cost desc");
            productList = q.list();

            tx.commit();
            session.close();

            return productList;
        } catch (Exception e) {
        }

        return productList;
    }

    public void UpdateProduct(float cost, int quantity, float discount, int id) {
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("update Product set Cost=:a, Quantity=:b, Discount=:c where Id=:d ");
            q.setParameter("a", cost);
            q.setParameter("b", quantity);
            q.setParameter("c", discount);
            q.setParameter("d", id);
            q.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
        }
    }

    public void removeProduct(int id) {
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("delete from Product where Id=: a");
            q.setParameter("a", id);
            q.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
        }
    }

    public List<Product> SearchProduct(String searchString) {
        List<Product> product = null;
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Product as p where p.Name like :e or p.Description like : e ");
            q.setParameter("e", '%' + searchString + '%');
            product = q.list();
            tx.commit();
            session.close();
            return product;
        } catch (Exception e) {

        }
        return product;
    }
}
