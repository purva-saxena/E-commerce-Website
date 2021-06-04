package com.mystore.mystore.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Lovepreet Singh & Purva Saxena
 */

@Entity
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int Id;
    private String Name;
    
    @Column(length = 1000)
    private String Description;
    private float Cost;
    private int Quantity;
    private float Discount;
    private String Pic;

    //relationships
    
    @ManyToOne
    private Category category;
    
    @ManyToOne
    private Brand brand;
    
    @OneToMany(mappedBy = "product")
    private List<ProductInOrder> productInOrder;

    //getter & setter & constructer
    
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    
    
    public Product() {
    }

    public Product(String Name, String Description, float Cost, int Quantity, float Discount, String Pic) {
        this.Name = Name;
        this.Description = Description;
        this.Cost = Cost;
        this.Quantity = Quantity;
        this.Discount = Discount;
        this.Pic = Pic;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getCost() {
        return Cost;
    }

    public void setCost(float Cost) {
        this.Cost = Cost;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public float getDiscount() {
        return Discount;
    }

    public void setDiscount(float Discount) {
        this.Discount = Discount;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String Pic) {
        this.Pic = Pic;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public List<ProductInOrder> getProductInOrder() {
        return productInOrder;
    }

    public void setProductInOrder(List<ProductInOrder> productInOrder) {
        this.productInOrder = productInOrder;
    }

    @Override
    public String toString() {
        return "Product{" + "Id=" + Id + ", Name=" + Name + ", Description=" + Description + ", Cost=" + Cost + ", Quantity=" + Quantity + ", Discount=" + Discount + ", Pic=" + Pic + ", category=" + category + '}';
    }
    
    // calculate price after discount
    public double getPriceAfterDiscount(){
        double dis=0;
        
        dis = (this.getDiscount()/100.0)*(this.getCost());
        
        return(this.getCost()-dis);
    }
    
}
