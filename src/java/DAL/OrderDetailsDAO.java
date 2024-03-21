package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.*;

public class OrderDetailsDAO {
    private Connection connection;

    public OrderDetailsDAO(Connection connection) {
        this.connection = connection;
    }

    // Thêm một chi tiết đơn hàng mới
    public void addOrderDetail(OrderDetails orderDetail) throws SQLException {
        String sql = "INSERT INTO OrderDetails_HE176281 (orderID, bookID, quantity, discount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orderDetail.getOrderID());
            stmt.setInt(2, orderDetail.getBookID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getDiscount());
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderDetail.setOrderDetailID(generatedKeys.getInt(1));
            }
        }
    }

    // Sửa thông tin một chi tiết đơn hàng
    public void updateOrderDetail(OrderDetails orderDetail) throws SQLException {
        String sql = "UPDATE OrderDetails_HE176281 SET orderID=?, bookID=?, quantity=?, discount=? WHERE orderDetailID=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderDetail.getOrderID());
            stmt.setInt(2, orderDetail.getBookID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getDiscount());
            stmt.setInt(5, orderDetail.getOrderDetailID());
            stmt.executeUpdate();
        }
    }

    // Xóa một chi tiết đơn hàng
    public void deleteOrderDetail(int orderDetailID) throws SQLException {
        String sql = "DELETE FROM OrderDetails_HE176281 WHERE orderDetailID=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderDetailID);
            stmt.executeUpdate();
        }
    }

public List<OrderDetails> getOrderDetailsByOrderID(int orderID) throws SQLException {
    List<OrderDetails> orderDetailsList = new ArrayList<>();
    String sql = "SELECT od.orderDetailID, od.orderID, od.bookID, od.quantity, od.discount, " +
                 "b.price, b.name " +  // Thêm cột name từ bảng Books_HE176281
                 "FROM OrderDetails_HE176281 od " +
                 "JOIN Books_HE176281 b ON od.bookID = b.bookID " +
                 "WHERE od.orderID = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, orderID);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setOrderDetailID(resultSet.getInt("orderDetailID"));
                orderDetail.setOrderID(resultSet.getInt("orderID"));
                orderDetail.setBookID(resultSet.getInt("bookID"));
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setDiscount(resultSet.getDouble("discount"));
                orderDetail.setPrice(resultSet.getDouble("price"));
                orderDetail.setName(resultSet.getString("name"));  // Lấy tên sách
                // Các thuộc tính khác của orderDetail...
                orderDetailsList.add(orderDetail);
            }
        }
    }
    return orderDetailsList;
}

public OrderDetails getOrderDetailByBookIdAndOrderId(int bookId, int orderId) throws SQLException {
    String sql = "SELECT * FROM OrderDetails_HE176281 WHERE bookID = ? AND orderID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, bookId);
        stmt.setInt(2, orderId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setOrderDetailID(rs.getInt("orderDetailID"));
                orderDetail.setOrderID(rs.getInt("orderID"));
                orderDetail.setBookID(rs.getInt("bookID"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setDiscount(rs.getDouble("discount"));
                // Không cần set price ở đây vì nó đã được lấy từ bảng Books
                return orderDetail;
            }
        }
    }
    return null;
}

public void updateOrderDetailQuantity(int orderDetailID, int quantity) throws SQLException {
        String sql = "UPDATE OrderDetails_HE176281 SET quantity=? WHERE orderDetailID=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, orderDetailID);
            stmt.executeUpdate();
        }
    }

    public List<Integer> getOrderDetailIdsByUserIdAndStatus(int userId, String status) throws SQLException {
        List<Integer> orderDetailIds = new ArrayList<>();
        String sql = "SELECT od.orderDetailID FROM OrderDetails_HE176281 od " +
                     "JOIN Orders_HE176281 o ON od.orderID = o.orderID " +
                     "WHERE o.userID = ? AND o.status = ? " +
                     "ORDER BY od.orderDetailID ASC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, status);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    orderDetailIds.add(resultSet.getInt("orderDetailID"));
                }
            }
        }
        return orderDetailIds;
    }



}
