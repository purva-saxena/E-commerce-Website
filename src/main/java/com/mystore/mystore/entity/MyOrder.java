package com.mystore.mystore.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author hinas
 */
        
@Entity
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column
    private Date date;

    @Column
    private String deliveryAddress;

    @Column
    private String phoneNo;
    
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "myorder")
    private List<ProductInOrder> ProductInOrder;

    
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<ProductInOrder> getProductInOrder() {
        return ProductInOrder;
    }

    public void setProductInOrder(List<ProductInOrder> ProductInOrder) {
        this.ProductInOrder = ProductInOrder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public MyOrder(String shippinggAdd, String phone) {
        this.deliveryAddress = shippinggAdd;
        this.phoneNo = phone;
        this.date = new Date();
    }

    public MyOrder() {

    }

}
