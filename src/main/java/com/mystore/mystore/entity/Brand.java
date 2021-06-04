package com.mystore.mystore.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Lovepreet Singh & Purva Saxena
 */

@Entity(name = "Brand")
public class Brand {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BrandId;
    
    @Column
    private String BrandName;

    @Column (length = 500)
    private String BrandDescription;

    @OneToMany (mappedBy = "brand")
    List<Product> product = new ArrayList<>();
    

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }
    public int getBrandId() {
        return BrandId;
    }

    public String getBrandName() {
        return BrandName;
    }

    public String getBrandDescription() {
        return BrandDescription;
    }

    public void setBrandId(int BrandId) {
        this.BrandId = BrandId;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public void setBrandDescription(String BrandDescription) {
        this.BrandDescription = BrandDescription;
    }

    public Brand(int BrandId, String BrandName, String BrandDescription) {
        this.BrandId = BrandId;
        this.BrandName = BrandName;
        this.BrandDescription = BrandDescription;
    }

    public Brand(String BrandName, String BrandDescription) {
        this.BrandName = BrandName;
        this.BrandDescription = BrandDescription;
    }

    public Brand() {
    }

    @Override
    public String toString() {
        return "Brand{" + "BrandId=" + BrandId + ", BrandName=" + BrandName + ", BrandDescription=" + BrandDescription + '}';
    }
    
}
