package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.*;

public class OrdersDAO {

    private Connection connection;

    public OrdersDAO(Connection connection) {
        this.connection = connection;
    }

    // Thêm một đơn hàng mới
    public void addOrder(Orders order) throws SQLException {
        String sql = "INSERT INTO Orders_HE176281 (UserID, OrderDate, Status) VALUES (?, ?, ?)";
        try ( PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUserID());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setString(3, order.getStatus());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setOrderID(generatedKeys.getInt(1));
            }
        }
    }

    // Cập nhật thông tin đơn hàng
    public void updateOrder(Orders order) throws SQLException {
        String sql = "UPDATE Orders_HE176281 SET UserID=?, OrderDate=?, Status=?, AddressID = ? WHERE OrderID=?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getUserID());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setString(3, order.getStatus());
            stmt.setInt(5, order.getOrderID());
            stmt.setInt(4, order.getAddressID());
            stmt.executeUpdate();
        }
    }

    // Xóa đơn hàng
    public void deleteOrder(int orderID) throws SQLException {
        String sql = "DELETE FROM Orders_HE176281 WHERE OrderID=?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderID);
            stmt.executeUpdate();
        }
    }

    // Lấy danh sách đơn hàng theo trạng thái
    public List<Orders> getOrdersByStatus(String status) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT * FROM Orders_HE176281 WHERE Status = ?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Orders order = new Orders();
                    order.setOrderID(resultSet.getInt("OrderID"));
                    order.setUserID(resultSet.getInt("UserID"));
                    order.setOrderDate(resultSet.getDate("OrderDate"));
                    order.setStatus(resultSet.getString("Status"));
                    ordersList.add(order);
                }
            }
        }
        return ordersList;
    }
// Lấy danh sách đơn hàng theo trạng thái

    public Orders getOrdersByID(int OrderID) throws SQLException {
        Orders order = new Orders();
        String sql = "SELECT * FROM Orders_HE176281 WHERE OrderID = ?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, OrderID);
            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    order.setOrderID(resultSet.getInt("OrderID"));
                    order.setUserID(resultSet.getInt("UserID"));
                    order.setOrderDate(resultSet.getDate("OrderDate"));
                    order.setStatus(resultSet.getString("Status"));
                }
            }
        }
        return order;
    }

    public List<Orders> getOrdersByStatus(int UserID, String status1, String status2) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT * FROM Orders_HE176281 WHERE userID = ? and (Status = ? or Status = ?)";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, UserID);
            statement.setString(2, status1);
            statement.setString(3, status2);
            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Orders order = new Orders();
                    order.setOrderID(resultSet.getInt("OrderID"));
                    order.setUserID(resultSet.getInt("UserID"));
                    order.setOrderDate(resultSet.getDate("OrderDate"));
                    order.setStatus(resultSet.getString("Status"));
                    ordersList.add(order);
                }
            }
        }
        return ordersList;
    }

    public List<Books> getBooksInCartByUserId(int userId) throws SQLException {
        List<Books> books = new ArrayList<>();
        String sql = "SELECT b.*, od.quantity FROM Books_HE176281 b "
                + "INNER JOIN OrderDetails_HE176281 od ON b.BookID = od.bookID "
                + "INNER JOIN Orders_HE176281 o ON od.orderID = o.OrderID "
                + "WHERE o.UserID = ? AND o.Status = 'cart'";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            Books bookDetail = null;
            try ( ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    bookDetail = new Books();

                    // Set thông tin từ bảng books_HE176281
                    bookDetail.setBookID(rs.getInt("BookID"));
                    bookDetail.setCatalogID(rs.getInt("CatalogID"));
                    bookDetail.setName(rs.getString("Name"));
                    bookDetail.setPrice(rs.getDouble("Price"));
                    bookDetail.setDiscount(rs.getInt("Discount"));
                    bookDetail.setStock(rs.getInt("Stock"));
                    bookDetail.setSold(rs.getInt("Sold"));
                    bookDetail.setPostedDate(rs.getTimestamp("PostedDate"));
                    bookDetail.setDescription(rs.getString("Description"));
                    bookDetail.setImageLink(rs.getString("ImageLink"));
                    bookDetail.setView(rs.getInt("View"));
                    bookDetail.setQuantity(rs.getInt("quantity"));

                    books.add(bookDetail);
                }
            }
        }
        return books;
    }

    // Method to check for an existing cart or create a new one
    public int checkOrCreateCart(int userID) throws SQLException {
        // Check if the user already has a cart
        String checkCartSql = "SELECT OrderID FROM Orders_HE176281 WHERE UserID = ? AND Status = 'cart'";
        try ( PreparedStatement checkStmt = connection.prepareStatement(checkCartSql)) {
            checkStmt.setInt(1, userID);
            try ( ResultSet resultSet = checkStmt.executeQuery()) {
                if (resultSet.next()) {
                    // Return existing cart OrderID
                    return resultSet.getInt("OrderID");
                }
            }
        }

        // If no cart exists, create a new one
        String createCartSql = "INSERT INTO Orders_HE176281 (UserID, OrderDate, Status) VALUES (?, CURRENT_TIMESTAMP, 'cart')";
        try ( PreparedStatement createStmt = connection.prepareStatement(createCartSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            createStmt.setInt(1, userID);
            createStmt.executeUpdate();

            try ( ResultSet generatedKeys = createStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Return new cart OrderID
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating cart failed, no ID obtained.");
                }
            }
        }
    }

    public void updateOrderStatusByUserId(int userID, String newStatus) throws SQLException {
        String sql = "UPDATE Orders_HE176281 SET Status = ? WHERE UserID = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, userID);
            stmt.executeUpdate();
        }
    }

    public int findCartOrderID(int userID) throws SQLException {
        String sql = "SELECT OrderID FROM Orders_HE176281 WHERE UserID = ? AND Status = 'cart'";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("OrderID");
                } else {
                    return -1; // Trả về -1 nếu không tìm thấy giỏ hàng
                }
            }
        }
    }

    public void deleteBookFromCart(int userID, int bookID) throws SQLException {
        int orderID = findCartOrderID(userID);
        if (orderID == -1) {
            throw new SQLException("Không tìm thấy giỏ hàng cho người dùng này.");
        }

        String sql = "DELETE FROM OrderDetails_HE176281 WHERE orderID = ? AND bookID = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderID);
            stmt.setInt(2, bookID);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Xóa sách khỏi giỏ hàng không thành công.");
            }
        }
    }

    public List<Orders> getOrdersInfoByStatus(int UserID, String status1, String status2) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT o.OrderID, o.UserID, o.OrderDate, o.Status, o.AddressID, "
                + "SUM(od.quantity) as TotalQuantity, SUM(od.quantity * b.price * (1 - (od.discount/100))) as TotalPrice "
                + "FROM Orders_HE176281 o "
                + "JOIN OrderDetails_HE176281 od ON o.OrderID = od.orderID "
                + "JOIN Books_HE176281 b ON od.bookID = b.BookID "
                + "WHERE o.UserID = ? AND (o.Status = ? OR o.Status = ?) "
                + "GROUP BY o.OrderID, o.UserID, o.OrderDate, o.Status, o.AddressID";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, UserID);
            statement.setString(2, status1);
            statement.setString(3, status2);
            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Orders order = new Orders();
                    order.setOrderID(resultSet.getInt("OrderID"));
                    order.setUserID(resultSet.getInt("UserID"));
                    order.setOrderDate(resultSet.getDate("OrderDate"));
                    order.setStatus(resultSet.getString("Status"));
                    order.setAddressID(resultSet.getInt("AddressID"));
                    // Set total quantity and total price
                    order.setQuantity(resultSet.getInt("TotalQuantity"));
                    order.setPrice(resultSet.getDouble("TotalPrice"));
                    ordersList.add(order);
                }
            }
        }
        return ordersList;
    }

    public double calculateTotal(List<OrderDetails> orderDetailsList) {
        double total = 0.0;
        for (OrderDetails detail : orderDetailsList) {
            double priceAfterDiscount = detail.getPrice() * (1 - (detail.getDiscount() / 100.0));
            total += priceAfterDiscount * detail.getQuantity();
        }
        return total;
    }
}
