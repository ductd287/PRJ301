/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author thanh
 */
public class Books {

    private int bookID;
    private int catalogID;
    private String name;
    private String author;
    private double price;

    private double inPrice;
    private int discount;
    private int stock;
    private int sold;
    private Date postedDate;
    private String description;
    private String imageLink;
    private int view;
    private String catalogName;
    private int quantity;

    public Books() {
    }

    public Books(int bookID, int catalogID, String name, String author, double price, double inPrice, int discount, int stock, int sold, Date postedDate, String description, String imageLink, int view) {
        this.bookID = bookID;
        this.catalogID = catalogID;
        this.name = name;
        this.author = author;
        this.price = price;
        this.inPrice = inPrice;
        this.discount = discount;
        this.stock = stock;
        this.sold = sold;
        this.postedDate = postedDate;
        this.description = description;
        this.imageLink = imageLink;
        this.view = view;
    }

    public Books(int bookID, int catalogID, String name, String author, double price, double inPrice, int discount, int stock, int sold, String description, String imageLink, int view) {
        this.bookID = bookID;
        this.catalogID = catalogID;
        this.name = name;
        this.author = author;
        this.price = price;
        this.inPrice = inPrice;
        this.discount = discount;
        this.stock = stock;
        this.sold = sold;
        this.description = description;
        this.imageLink = imageLink;
        this.view = view;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getCatalogID() {
        return catalogID;
    }

    public void setCatalogID(int catalogID) {
        this.catalogID = catalogID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
