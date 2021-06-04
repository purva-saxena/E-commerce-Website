package com.mystore.mystore.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author hinas
 */
@Entity
public class ProductInOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private int quantity;
    
    @Column
    private float paidAmount;

    public float getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(float paidAmount) {
        this.paidAmount = paidAmount;
    }

    public MyOrder getMyorder() {
        return myorder;
    }

    public void setMyorder(MyOrder myorder) {
        this.myorder = myorder;
    }
    
    @ManyToOne
    private Product product;

    @ManyToOne
    private MyOrder myorder;

    
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrder(MyOrder order) {
        this.myorder = order;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public MyOrder getOrder() {
        return myorder;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductInOrder(Product product, MyOrder order, int quantity) {
        this.product = product;
        this.myorder = order;
        this.quantity = quantity;
        setPaidAmount((float)product.getPriceAfterDiscount());
    }

    public ProductInOrder(int quantity) {
        this.quantity = quantity;
    }
    
    public ProductInOrder(){
        
    }

}
