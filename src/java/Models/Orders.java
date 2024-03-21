/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

public class Orders {

    private int orderID;
    private int userID;
    private int AddressID;
    private Date orderDate;
    private String status;
    private int quantity;
    private double discount;
    private String address;
    private double price;
    private Date time;

    public Orders() {

    }

    public Orders(int orderID, int userID, int AddressID, Date orderDate, String status) {
        this.orderID = orderID;
        this.userID = userID;
        this.AddressID = AddressID;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Orders(int orderID, int userID, int AddressID, String status) {
        this.orderID = orderID;
        this.userID = userID;
        this.AddressID = AddressID;
        this.status = status;
    }
    

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int AddressID) {
        this.AddressID = AddressID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    

}
