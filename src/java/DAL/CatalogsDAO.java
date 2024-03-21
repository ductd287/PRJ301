/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author thanh
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.*;

public class CatalogsDAO {

    private Connection connection;

    public CatalogsDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy danh sách tất cả các catalogs
    public List<Catalogs> getAllCatalogs() {
        List<Catalogs> catalogsList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM catalogs_HE176281";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Catalogs catalog = new Catalogs();
                catalog.setCatalogID(resultSet.getInt("CatalogID"));
                catalog.setName(resultSet.getString("Name"));
                catalog.setDescription(resultSet.getString("Description"));
                catalogsList.add(catalog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogsList;
    }

    public String getCatalogNameById(int catalogId) {
        try {
            String sql = "SELECT Name FROM catalogs_HE176281 WHERE CatalogID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, catalogId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy tên catalog với ID tương ứng
    }

    // Thêm một catalog mới
    public void addCatalog(Catalogs catalog) {
        try {
            String sql = "INSERT INTO catalogs_HE176281 (CatalogID, Name, Description) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, catalog.getCatalogID());
            statement.setString(2, catalog.getName());
            statement.setString(3, catalog.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Các phương thức khác cho việc truy vấn dữ liệu từ bảng Catalogs
    // ...
}
