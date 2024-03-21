/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author thanh
 */
public class Address {
    private int addressID;
    private int userID;
    private String address;
    private String phone;

    public Address() {
    }

    public Address(int addressID, int userID, String address, String phone) {
        this.addressID = addressID;
        this.userID = userID;
        this.address = address;
        this.phone = phone;
    }

    public Address(int userID, String address, String phone) {
        this.userID = userID;
        this.address = address;
        this.phone = phone;
    }
    

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
