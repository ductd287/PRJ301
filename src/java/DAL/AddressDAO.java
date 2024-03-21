/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import java.sql.*;
import Models.*;
import java.util.*;

public class AddressDAO {
     private Connection connection;
     
     public AddressDAO(Connection connection) {
        this.connection = connection;
    }
     
    private static final String SELECT_ADDRESSES_BY_USER = "SELECT * FROM Address_HE176281 WHERE userID = ?";
    private static final String INSERT_ADDRESS = "INSERT INTO Address_HE176281 (userID, address, phone) VALUES (?, ?, ?)";
     
    public List<Address> listAddresses(int userID) {
        List<Address> addresses = new ArrayList<>();
        // try-with-resources statement will auto close the connection.
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESSES_BY_USER);) {
            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int addressID = rs.getInt("addressID");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                addresses.add(new Address(addressID, userID, address, phone));
            }
        } catch (SQLException e) {
            // Handle exceptions
        }
        return addresses;
    }

    public void insertAddress(Address address) {
        // try-with-resources statement will auto close the connection.
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADDRESS)) {
            preparedStatement.setInt(1, address.getUserID());
            preparedStatement.setString(2, address.getAddress());
            preparedStatement.setString(3, address.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
        }
    }
     
     public void deleteAddress(int addressID) {
        String DELETE_ADDRESS_SQL = "DELETE FROM Address_HE176281 WHERE addressID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS_SQL)) {
            preparedStatement.setInt(1, addressID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
        }
    }
}
