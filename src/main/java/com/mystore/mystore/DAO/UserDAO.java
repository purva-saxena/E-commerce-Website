package com.mystore.mystore.DAO;

import org.hibernate.SessionFactory;
import com.mystore.mystore.entity.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * @author Lovepreet Singh & Purva Saxena
 */
public class UserDAO {

    private SessionFactory factory;

    public UserDAO(SessionFactory factory) {
        this.factory = factory;
    }
    // check if the entered user id already exists

    public int fetchId(String email) {
        User user = null;
        try {
            String query = "from User where email =: e";
            Session session = this.factory.openSession();
            Transaction t = session.beginTransaction();

            Query q = session.createQuery(query);
            q.setParameter("e", email);
            user = (User) q.uniqueResult();
            t.commit();
            session.close();
            if (user == null) {
                return 0;
            } else {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    //fetch user by ID
    public User FetchUserById(int UserId) {
        User user = null;
        try {

            try ( Session session = this.factory.openSession()) {
                user = session.get(User.class, UserId);
                session.close();
            }
        } catch (Exception e) {
        }
        return user;

    }

    // fetch user data by email and password
    public User getUserByEmailAndPass(String email, String pass) {
        User user = null;
        try {
            String query = "from User where email =: e and password=: p";
            Session session = this.factory.openSession();
            Transaction t = session.beginTransaction();
            Query q = session.createQuery(query);
            q.setParameter("e", email);
            q.setParameter("p", pass);
            user = (User) q.uniqueResult();
            t.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    //fetch all the user
    public List<User> fetchAllUser() {
        List<User> user = null;
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from User");
            user = q.list();

            tx.commit();
            session.close();
            return user;

        } catch (Exception e) {
        }

        return user;
    }

    public void addUser(User user) {
        Session HibernateSession = this.factory.openSession();
        Transaction tx = HibernateSession.beginTransaction();

        HibernateSession.save(user);

        tx.commit();
        HibernateSession.close();
    }

    public void updateUserProfile(int id, String phone, String add) {

        try {
            Session HibernateSession = this.factory.openSession();
            Transaction tx = HibernateSession.beginTransaction();

            Query query = HibernateSession.createQuery("update User as u set u.phone=:a, u.address=:b where u.User.userid=:e");
            query.setParameter("a", phone);
            query.setParameter("b", add);
            query.setParameter("e", id);

            query.executeUpdate();

            tx.commit();
            HibernateSession.close();
        } catch (Exception e) {

        }
    }

}
