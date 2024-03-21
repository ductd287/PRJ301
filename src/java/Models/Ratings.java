/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author thanh
 */
public class Ratings {
    private int bookID;
    private int userID;
    private int star;
    private String description;

    // Constructors, getters, setters và các phương thức khác
    public Ratings(){
        
    }

    public Ratings(int bookID, int userID, int star, String description) {
        this.bookID = bookID;
        this.userID = userID;
        this.star = star;
        this.description = description;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    
}
