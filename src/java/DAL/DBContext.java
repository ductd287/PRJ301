/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    
    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)*/
    /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
     public Connection getConnection()throws Exception {        
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber +
                ";databaseName="+dbName;//+"; integratedSecurity=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");         
        return DriverManager.getConnection(url, userID, password);
//        return DriverManager.getConnection(url);
    }
     public void testConnection() throws Exception {
        try (Connection con = getConnection()) {
            if (con != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("Failed to connect to the database!");
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Not Found Error: " + ex.getMessage());
        }
    }
    
    /*Insert your other code right after this comment*/
    private final String serverName = "localhost";
    private final String dbName = "PRJ301_SE1762_FA23";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "123";
    
    public static void main(String[] args) throws Exception {
        DBContext db = new DBContext();
        db.testConnection();
    }   
}
