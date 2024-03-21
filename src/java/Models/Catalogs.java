/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author thanh
 */
public class Catalogs {
    private int catalogID;
    private String name;
    private String description;

    // Constructors, getters, setters và các phương thức khác
    public Catalogs(){
        
    }

    public Catalogs(int catalogID, String name, String description) {
        this.catalogID = catalogID;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}

