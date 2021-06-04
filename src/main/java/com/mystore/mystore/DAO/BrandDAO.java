/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystore.mystore.DAO;

import com.mystore.mystore.entity.Brand;
import java.util.ArrayList;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Purva Saxena
 */
public class BrandDAO {

    private final SessionFactory factory;

    public BrandDAO(SessionFactory sessionfactory) {
        this.factory = sessionfactory;
    }
    // function to add Brand
    public void addBrand(Brand brand) {

        try {

            try (Session session = this.factory.openSession()) {
                Transaction tx = session.beginTransaction();
                session.save(brand);
                tx.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    // Method to fetch the list of all brand
    
    public ArrayList<Brand> FetchBrandList(){
        
        ArrayList<Brand> BrandList = new ArrayList<Brand>();
        try (Session session = this.factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Brand");
            BrandList = (ArrayList<Brand>)q.list();
            tx.commit();
        }
        
        return BrandList;
        
    }
    //fetch Brand by name
    public int FetchByName(String name) {

        try {
            Brand brand;
            try (Session session = this.factory.openSession()) {
                Transaction tx = session.beginTransaction();
                Query q = session.createQuery("from Brand where BrandName =: e");
                q.setParameter("e", name);
                brand = (Brand)q.uniqueResult();
                tx.commit(); 
            }
            if(brand == null){
                return 0;
            }else{
                return 1;
            }
        } catch (Exception e) {
        } 
        return 0;
    }
    
    
    //fetch brand by Id and return the object of class Brand
    public Brand FetchById(int bId) {
        Brand brand = null;
        try {

            try ( Session session = this.factory.openSession()) {
                brand=session.get(Brand.class, bId);
                session.close();
            }
        } catch (Exception e) {
        }
        return brand;

    }
    
}
