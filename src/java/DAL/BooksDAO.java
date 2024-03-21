/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.*;
import Models.*;
import java.util.*;

public class BooksDAO {

    private Connection connection;
    private List<Books> allBooks;
    private List<Books> newBooks;
    private List<Books> soldBooks;
    private List<Books> viewBooks;
    private List<Books> allNewBooks;
    private List<Books> allSoldBooks;
    private List<Books> allViewBooks;
    private List<Books> allBooksCatalog;

    public BooksDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Books> getNewBooksList() {
        return newBooks;
    }

    public List<Books> getSoldBooksList() {
        return soldBooks;
    }

    public List<Books> getAllBooksList() {
        return allBooks;
    }

    public List<Books> getViewBookList() {
        return viewBooks;
    }

    public List<Books> getAllNewBooks() {
        return allNewBooks;
    }

    public List<Books> getAllSoldBooks() {
        return allSoldBooks;
    }

    public List<Books> getAllViewBooks() {
        return allViewBooks;
    }

    public List<Books> getAllBooksCatalog() {
        return allBooksCatalog;
    }

    public Books getBookById(int bookId) throws SQLException {
        Books bookDetail = null;
        String sql = "SELECT b.*, c.name FROM books_HE176281 b "
                + "LEFT JOIN catalogs_HE176281 c ON b.CatalogID = c.CatalogID "
                + "WHERE b.bookid = ?";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            try ( ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    bookDetail = new Books();

                    // Set thông tin từ bảng books_HE176281
                    bookDetail.setBookID(rs.getInt("BookID"));
                    bookDetail.setCatalogID(rs.getInt("CatalogID"));
                    bookDetail.setName(rs.getString(3));
                    bookDetail.setAuthor(rs.getString("Author"));
                    bookDetail.setPrice(rs.getDouble("Price"));
                    bookDetail.setInPrice(rs.getDouble("InPrice"));
                    bookDetail.setDiscount(rs.getInt("Discount"));
                    bookDetail.setStock(rs.getInt("Stock"));
                    bookDetail.setSold(rs.getInt("Sold"));
                    bookDetail.setPostedDate(rs.getTimestamp("PostedDate"));
                    bookDetail.setDescription(rs.getString("Description"));
                    bookDetail.setImageLink(rs.getString("ImageLink"));
                    bookDetail.setView(rs.getInt("View"));

                    // Set thông tin từ bảng catalog_HE176281
                    bookDetail.setCatalogName(rs.getString(14));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return bookDetail;
    }

    // Lấy danh sách tất cả sách
    public void getAllBooks() throws SQLException {
        allBooks = new Vector<>();
        String sql = "SELECT * FROM Books_HE176281";
        try ( PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setCatalogID(rs.getInt("CatalogID"));
                book.setName(rs.getString("Name"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setInPrice(rs.getDouble("InPrice"));
                book.setDiscount(rs.getInt("Discount"));
                book.setStock(rs.getInt("Stock"));
                book.setSold(rs.getInt("Sold"));
                book.setPostedDate(rs.getTimestamp("PostedDate"));
                book.setDescription(rs.getString("Description"));
                book.setImageLink(rs.getString("ImageLink"));
                book.setView(rs.getInt("View"));
                allBooks.add(book);
            }
        }
    }

    public void getAllBooksByCatalog(int catalogId) throws SQLException {
        allBooksCatalog = new ArrayList<>();
        String sql = "SELECT b.*, c.name FROM books_HE176281 b "
                + "LEFT JOIN catalogs_HE176281 c ON b.CatalogID = c.CatalogID "
                + "WHERE b.CatalogID = ?";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, catalogId);
            try ( ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Books book = new Books();

                    // Set thông tin từ bảng books_HE176281
                    book.setBookID(rs.getInt("BookID"));
                    book.setCatalogID(rs.getInt("CatalogID"));
                    book.setName(rs.getString(3));
                    book.setAuthor(rs.getString("Author"));
                    book.setPrice(rs.getDouble("Price"));
                    book.setDiscount(rs.getInt("Discount"));
                    book.setStock(rs.getInt("Stock"));
                    book.setSold(rs.getInt("Sold"));
                    book.setPostedDate(rs.getTimestamp("PostedDate"));
                    book.setDescription(rs.getString("Description"));
                    book.setImageLink(rs.getString("ImageLink"));
                    book.setView(rs.getInt("View"));

                    // Set thông tin từ bảng catalog_HE176281
                    book.setCatalogName(rs.getString(13));

                    allBooksCatalog.add(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    // Thêm sách mới
    public void addBook(Books book) throws SQLException {
        String sqlAdd = "INSERT INTO Books_HE176281 (CatalogID, Name, Author, Price, Discount, Stock, Sold, Description, ImageLink, [View], InPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement stmt = connection.prepareStatement(sqlAdd)) {
            stmt.setInt(1, book.getCatalogID());
            stmt.setString(2, book.getName());
            stmt.setString(3, book.getAuthor());
            stmt.setDouble(4, book.getPrice());
            stmt.setDouble(11, book.getInPrice());
            stmt.setInt(5, book.getDiscount());
            stmt.setInt(6, book.getStock());
            stmt.setInt(7, book.getSold());
            stmt.setString(8, book.getDescription());
            stmt.setString(9, book.getImageLink());
            stmt.setInt(10, book.getView());
            stmt.executeUpdate();
        }
    }

    // Cập nhật thông tin sách
    public void updateBook(Books book) throws SQLException {
        String sqlUpdate = "UPDATE Books_HE176281 SET CatalogID=?, Name=?, Author=?, Price=?, Discount=?, Stock=?, Sold=?, Description=?, ImageLink=?, [View]=?, InPrice=? WHERE BookID=?";
        try ( PreparedStatement stmt = connection.prepareStatement(sqlUpdate)) {
            stmt.setInt(1, book.getCatalogID());
            stmt.setString(2, book.getName());
            stmt.setString(3, book.getAuthor());
            stmt.setDouble(4, book.getPrice());
            stmt.setInt(5, book.getDiscount());
            stmt.setInt(6, book.getStock());
            stmt.setInt(7, book.getSold());
            stmt.setString(8, book.getDescription());
            stmt.setString(9, book.getImageLink());
            stmt.setInt(10, book.getView());
            stmt.setDouble(11, book.getInPrice());
            stmt.setInt(12, book.getBookID());
            stmt.executeUpdate();
        }
    }
    
    // Lấy ra n mẫu sách mới đăng bán
    public void getNewlyPostedBooks() throws SQLException {
        newBooks = new ArrayList<>(); // Khởi tạo danh sách mới
        String sql = "SELECT TOP 4 * FROM Books_HE176281 ORDER BY PostedDate DESC;";
        try ( PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setCatalogID(rs.getInt("CatalogID"));
                book.setName(rs.getString("Name"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setInPrice(rs.getDouble("InPrice"));
                book.setDiscount(rs.getInt("Discount"));
                book.setStock(rs.getInt("Stock"));
                book.setSold(rs.getInt("Sold"));
                book.setPostedDate(rs.getTimestamp("PostedDate"));
                book.setDescription(rs.getString("Description"));
                book.setImageLink(rs.getString("ImageLink"));
                book.setView(rs.getInt("View"));
                newBooks.add(book);
            }
        }
    }

    public void getAllNewlyPostedBooks() throws SQLException {
        allNewBooks = new ArrayList<>(); // Khởi tạo danh sách mới
        String sql = "SELECT * FROM Books_HE176281 ORDER BY PostedDate DESC;";
        try ( PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setCatalogID(rs.getInt("CatalogID"));
                book.setName(rs.getString("Name"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setInPrice(rs.getDouble("InPrice"));
                book.setDiscount(rs.getInt("Discount"));
                book.setStock(rs.getInt("Stock"));
                book.setSold(rs.getInt("Sold"));
                book.setPostedDate(rs.getTimestamp("PostedDate"));
                book.setDescription(rs.getString("Description"));
                book.setImageLink(rs.getString("ImageLink"));
                book.setView(rs.getInt("View"));
                allNewBooks.add(book);
            }
        }
    }

    public void getMostSoldBooks() throws SQLException {
        soldBooks = new ArrayList<>(); // Khởi tạo danh sách mới
        String sql = "SELECT TOP 4 * FROM Books_HE176281 ORDER BY sold DESC;";
        try ( PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setCatalogID(rs.getInt("CatalogID"));
                book.setName(rs.getString("Name"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setInPrice(rs.getDouble("InPrice"));
                book.setDiscount(rs.getInt("Discount"));
                book.setStock(rs.getInt("Stock"));
                book.setSold(rs.getInt("Sold"));
                book.setPostedDate(rs.getTimestamp("PostedDate"));
                book.setDescription(rs.getString("Description"));
                book.setImageLink(rs.getString("ImageLink"));
                book.setView(rs.getInt("View"));
                soldBooks.add(book);
            }
        }
    }

    public void getAllMostSoldBooks() throws SQLException {
        allSoldBooks = new ArrayList<>(); // Khởi tạo danh sách mới
        String sql = "SELECT * FROM Books_HE176281 ORDER BY sold DESC;";
        try ( PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setCatalogID(rs.getInt("CatalogID"));
                book.setName(rs.getString("Name"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setInPrice(rs.getDouble("InPrice"));
                book.setDiscount(rs.getInt("Discount"));
                book.setStock(rs.getInt("Stock"));
                book.setSold(rs.getInt("Sold"));
                book.setPostedDate(rs.getTimestamp("PostedDate"));
                book.setDescription(rs.getString("Description"));
                book.setImageLink(rs.getString("ImageLink"));
                book.setView(rs.getInt("View"));
                allSoldBooks.add(book);
            }
        }
    }

    public void getMostViewBooks() throws SQLException {
        viewBooks = new ArrayList<>(); // Khởi tạo danh sách mới
        String sql = "SELECT TOP 4 * FROM Books_HE176281 ORDER BY [view] DESC;";
        try ( PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setCatalogID(rs.getInt("CatalogID"));
                book.setName(rs.getString("Name"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setInPrice(rs.getDouble("InPrice"));
                book.setDiscount(rs.getInt("Discount"));
                book.setStock(rs.getInt("Stock"));
                book.setSold(rs.getInt("Sold"));
                book.setPostedDate(rs.getTimestamp("PostedDate"));
                book.setDescription(rs.getString("Description"));
                book.setImageLink(rs.getString("ImageLink"));
                book.setView(rs.getInt("View"));
                viewBooks.add(book);
            }
        }
    }

    public void getAllViewSoldBooks() throws SQLException {
        allViewBooks = new ArrayList<>(); // Khởi tạo danh sách mới
        String sql = "SELECT * FROM Books_HE176281 ORDER BY [view] DESC;";
        try ( PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setCatalogID(rs.getInt("CatalogID"));
                book.setName(rs.getString("Name"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setInPrice(rs.getDouble("InPrice"));
                book.setDiscount(rs.getInt("Discount"));
                book.setStock(rs.getInt("Stock"));
                book.setSold(rs.getInt("Sold"));
                book.setPostedDate(rs.getTimestamp("PostedDate"));
                book.setDescription(rs.getString("Description"));
                book.setImageLink(rs.getString("ImageLink"));
                book.setView(rs.getInt("View"));
                allViewBooks.add(book);
            }
        }
    }

    // Xóa sách
    public void deleteBook(int bookID) throws SQLException {
        String sql = "DELETE FROM Books_HE176281 WHERE BookID=?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bookID);
            stmt.executeUpdate();
        }
    }

    public boolean isBookExists(int bookID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Books_HE176281 WHERE BookID = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bookID);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public List<Books> searchInfoBookBySearchForm(String searchForm) throws SQLException {
        List<Books> searchedBooks = new ArrayList<>();
        String sql = "SELECT * FROM Books_HE176281 WHERE Name LIKE ? ORDER BY Name";

        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Setting the search string with wildcard characters for partial matching
            stmt.setString(1, "%" + searchForm + "%");

            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Books book = new Books();
                    book.setBookID(rs.getInt("BookID"));
                    book.setCatalogID(rs.getInt("CatalogID"));
                    book.setName(rs.getString("Name"));
                    book.setAuthor(rs.getString("Author"));
                    book.setPrice(rs.getDouble("Price"));
                    book.setInPrice(rs.getDouble("InPrice"));
                    book.setDiscount(rs.getInt("Discount"));
                    book.setStock(rs.getInt("Stock"));
                    book.setSold(rs.getInt("Sold"));
                    book.setPostedDate(rs.getTimestamp("PostedDate"));
                    book.setDescription(rs.getString("Description"));
                    book.setImageLink(rs.getString("ImageLink"));
                    book.setView(rs.getInt("View"));
                    searchedBooks.add(book);
                }
            }
        }
        return searchedBooks;
    }

}
