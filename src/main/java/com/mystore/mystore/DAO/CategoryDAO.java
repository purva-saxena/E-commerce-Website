package com.mystore.mystore.DAO;

import com.mystore.mystore.entity.Category;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Purva Saxena
 */
public class CategoryDAO {

    private final SessionFactory factory;

    public CategoryDAO(SessionFactory sessionfactory) {
        this.factory = sessionfactory;
    }

    // function to add category
    public void addCategory(Category category) {

        try {

            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(category);
            tx.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Method to fetch the list of all categories
    public List<Category> FetchCategoryList() {

        List<Category> categoryList;
        try ( Session session = this.factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Category");
            categoryList = (List) q.list();
            tx.commit();
        }

        return categoryList;

    }
    //method to check if the category already exists
    //return 1 if  category found else return 0

    public int FetchByName(String name) {

        try {
            Category category;
            try ( Session session = this.factory.openSession()) {
                Transaction tx = session.beginTransaction();
                Query q = session.createQuery("from Category where CategoryName =: e");
                q.setParameter("e", name);
                category = (Category) q.uniqueResult();
                tx.commit();
            }
            if (category == null) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    //fetch category by Id and return the object of class Category
    public Category FetchById(int cId) {
        Category category = null;
        try {

            try ( Session session = this.factory.openSession()) {
                category=session.get(Category.class, cId);
                session.close();
            }
        } catch (Exception e) {
        }
        return category;

    }
}
