package com.mystore.mystore.entity;

/**
 * @author Lovepreet Singh & Purva Saxena
 */
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;

    @Column
    private String name;
    
    @Column
    private String email;
    
    @Column
    private String password;

    @Column(length = 12)
    private String phone;
    
    @Column
    private String address;
    
    @Column
    private String pic;
    
    @Column
    private String gender;
    
    @Column
    private String User_type;

    @OneToMany (mappedBy = "user")
    List<MyOrder> myorder = new ArrayList<>();

    public int getId() {
        return UserId;
    }

    public void setId(int UserId) {
        this.UserId = UserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<MyOrder> getMyorder() {
        return myorder;
    }

    public void setMyorder(List<MyOrder> myorder) {
        this.myorder = myorder;
    }
    
        public String getUserType() {
        return User_type;
    }

    public void setUser_type(String User_type) {
        this.User_type = User_type;
    }



    public User(String name, String email, String password, String phone, String address, String pic, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.pic = pic;
        this.gender = gender;
        this.setUser_type("Normal");
    }
    
    public User(){
        
    }
    
    
    
    
}
