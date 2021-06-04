/**
 * @author Lovepreet Singh & Purva Saxena
 */

package com.mystore.mystore.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {
    
    public static SessionFactory factory ;
    
    public static SessionFactory getfactory()
    {
        try{
            if(factory==null)
            {
                factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                System.out.println(factory);
                return factory;
            }
        }catch(Exception ex){
            
            System.out.print("Initial SessionFactory creation failed.");
            ex.printStackTrace();
        }
        return factory;
    }
}