package DAL;

import java.sql.*;
import java.sql.SQLException;
import java.util.Vector;
import Models.*;
import java.sql.PreparedStatement;

public class DAO {
    
    private static DAO instance = null;
    private Connection con;
    private BooksDAO booksDAO;
    private AddressDAO addressDAO;
    private CatalogsDAO catalogsDAO;
    private RatingsDAO ratingsDAO;
    private UsersDAO usersDAO; 
    private OrdersDAO ordersDAO; 
    private OrderDetailsDAO orderDetailsDAO; 
    private DBContext dbContext; 
    
    private Vector<Books> books;
    private Vector<Address> address;
    private Vector<Catalogs> catalogs;
    private Vector<OrderDetails> orderDetails;
    private Vector<Orders> orders;
    private Vector<Ratings> ratings;
    private Vector<Users> users;

    private DAO() {
        dbContext = new DBContext();
        try {
            con = dbContext.getConnection();
            booksDAO = new BooksDAO(con);
            catalogsDAO = new CatalogsDAO(con);
            ratingsDAO = new RatingsDAO(con);
            usersDAO = new UsersDAO(con); 
            ordersDAO = new OrdersDAO(con); 
            orderDetailsDAO = new OrderDetailsDAO(con); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }
    
    public void loadDB() {
        // Load Books
        books = new Vector<>();
        try {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Books_HE176281");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            books.add(new Books(
                rs.getInt("bookID"),
                rs.getInt("catalogID"),
                rs.getString("name"),
                rs.getString("author"),
                rs.getDouble("price"),
                rs.getDouble("InPrice"),
                rs.getInt("discount"),
                rs.getInt("stock"),
                rs.getInt("sold"),
                rs.getTimestamp("postedDate"),
                rs.getString("description"),
                rs.getString("imageLink"),
                rs.getInt("view")
            ));
        }
        } catch (Exception e) {
            System.out.println("Error at read Books: " + e.getMessage());
        }
        
        address = new Vector<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Address_HE176281");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            address.add(new Address(
                    rs.getInt("AddressID"),
                    rs.getInt("UserID"),
                    rs.getString("Address"),
                    rs.getString("Phone")
            ));
        }
        } catch (Exception e) {
            System.out.println("Error at read Books: " + e.getMessage());
        }
        

        // Load Catalogs
        catalogs = new Vector<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Catalogs_HE176281");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                catalogs.add(new Catalogs(
                    rs.getInt("catalogID"),
                    rs.getString("name"),
                    rs.getString("description")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error at read Catalogs: " + e.getMessage());
        }

        // Load OrderDetails
        orderDetails = new Vector<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM OrderDetails_HE176281");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orderDetails.add(new OrderDetails(
                    rs.getInt("orderDetailID"),
                    rs.getInt("orderID"),
                    rs.getInt("bookID"),
                    rs.getInt("quantity"),
                    rs.getDouble("discount")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error at read OrderDetails: " + e.getMessage());
        }

        // Load Orders
        orders = new Vector<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Orders_HE176281");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(new Orders(
                    rs.getInt("orderID"),
                    rs.getInt("customerID"),
                    rs.getInt("addressID"),
                    rs.getDate("orderDate"),
                    rs.getString("status")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error at read Orders: " + e.getMessage());
        }

        // Load Ratings
        ratings = new Vector<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Ratings_HE176281");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ratings.add(new Ratings(
                    rs.getInt("bookID"),
                    rs.getInt("userID"),
                    rs.getInt("star"),
                    rs.getString("description")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error at read Ratings: " + e.getMessage());
        }

        // Load Users
        users = new Vector<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Users_HE176281");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new Users(
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getBoolean("role"),
                    rs.getBoolean("active")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error at read Users: " + e.getMessage());
        }
    }

    public BooksDAO getBooksDAO() {
        return booksDAO;
    }

    public CatalogsDAO getCatalogsDAO() {
        return catalogsDAO;
    }

    public RatingsDAO getRatingsDAO() {
        return ratingsDAO;
    }

    public UsersDAO getUsersDAO() {
        return usersDAO;
    }

    public OrdersDAO getOrdersDAO() {
        return ordersDAO;
    }

    public OrderDetailsDAO getOrderDetailsDAO() {
        return orderDetailsDAO;
    }

    public Connection getConnection() {
        return con;
    }
}
