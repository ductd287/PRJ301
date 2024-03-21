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

public class UsersDAO {

    private Connection connection;
    private List<Users> allUsers;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy thông tin người dùng bằng Username
    public Users getUserByUsername(String username) {
        Users user = null;
        try {
            String sql = "SELECT * FROM users_HE176281 WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new Users();
                user.setUserID(resultSet.getInt("UserID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPassword(resultSet.getString("Password"));
                user.setName(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPhone(resultSet.getString("Phone"));
                user.setRole(resultSet.getBoolean("Role"));
                user.setActive(resultSet.getBoolean("Active"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users getUserByUsernameOrMail(String username) {
        Users user = null;
        try {
            String sql = "SELECT * FROM users_HE176281 WHERE (Username = ? or Mail = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new Users();
                user.setUserID(resultSet.getInt("UserID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPassword(resultSet.getString("Password"));
                user.setName(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPhone(resultSet.getString("Phone"));
                user.setRole(resultSet.getBoolean("Role"));
                user.setActive(resultSet.getBoolean("Active"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Thêm một người dùng mới
    public void addUser(String username, String password, String name, String email, String phone, boolean role, boolean active) {
        try {
            String sql = "INSERT INTO users_HE176281 (Username, Password, Name, Email, Phone, Role, Active) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, email);
            statement.setString(5, phone);
            statement.setBoolean(6, role);
            statement.setBoolean(7, active);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username, String password, String name, String email, boolean role, boolean active) {
        try {
            String sql = "INSERT INTO users_HE176281 (Username, Password, Name, Email, role, active) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, email);
            statement.setBoolean(5, role);
            statement.setBoolean(6, active);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra xem username đã tồn tại chưa
    public boolean isUsernameExists(String username) {
        try {
            String sql = "SELECT COUNT(*) FROM users_HE176281 WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra xem email đã tồn tại chưa
    public boolean isEmailExists(String email) {
        try {
            String sql = "SELECT COUNT(*) FROM users_HE176281 WHERE Email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUserOrEmailExists(String email) {
        try {
            String sql = "SELECT COUNT(*) FROM users_HE176281 WHERE (Email = ? or Username = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getUserIDByEmailOrUsername(String emailOrUsername) {
        int userId = 0;
        try {
            String sql = "SELECT UserID FROM users_HE176281 WHERE Email = ? OR Username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, emailOrUsername);
            statement.setString(2, emailOrUsername);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public List<Users> getAllStudents() {
        List<Users> students = new ArrayList<>();

        try {

            String sql = "SELECT * FROM users_HE176281 ";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Users user = new Users();
                user.setUserID(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setName(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                user.setPhone(resultSet.getString(6));
                user.setRole(resultSet.getBoolean(7));
                user.setActive(resultSet.getBoolean(8));
                // Assuming isStudent flag is not part of the Users model

                students.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public boolean deleteUserByUsername(String username) {
        try {
            String sql = "DELETE FROM Users_HE176281 WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateUser(String username, String password, String name, String email, String phone, boolean role, boolean active) {
        try {
            String sql = "UPDATE users_HE176281 SET Password = ?, Name = ?, Email = ?, Phone = ?, Role = ?, Active = ? WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setBoolean(5, role);
            statement.setBoolean(6, active);
            statement.setString(7, username);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int UserID, String name, String email) {
        try {
            String sql = "UPDATE users_HE176281 SET  Name = ?, Email = ? WHERE UserID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, UserID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activateUser(int UserID, String phone) {
        try {
            String sql = "UPDATE users_HE176281 SET  Phone = ?, Active = 1 WHERE UserID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
            statement.setInt(2, UserID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int UserID, String pass) {
        try {
            String sql = "UPDATE users_HE176281 SET Password = ? WHERE UserID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pass);
            statement.setInt(2, UserID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
