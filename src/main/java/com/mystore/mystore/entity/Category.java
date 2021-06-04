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

@Entity
public class Category {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoryId;
    
    @Column
    private String CategoryName;
    
    @Column (length = 500)
    private String CategoryDescription;

    @OneToMany (mappedBy = "category")
    List<Product> product = new ArrayList<>();
    

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
    public int getCategoryId() {
        return CategoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public void setCategoryDescription(String CategoryDescription) {
        this.CategoryDescription = CategoryDescription;
    }

    public Category(int CategoryId, String CategoryName, String CategoryDescription) {
        this.CategoryId = CategoryId;
        this.CategoryName = CategoryName;
        this.CategoryDescription = CategoryDescription;
    }

    public Category(String CategoryName, String CategoryDescription) {
        this.CategoryName = CategoryName;
        this.CategoryDescription = CategoryDescription;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" + "CategoryId=" + CategoryId + ", CategoryName=" + CategoryName + ", CategoryDescription=" + CategoryDescription + '}';
    }
    
}
