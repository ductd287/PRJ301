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

public class RatingsDAO {

    private Connection connection;

    public RatingsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Ratings> getRatingsByBookId(int bookId) throws SQLException {
        List<Ratings> ratings = new ArrayList<>();
        String sql = "SELECT * FROM ratings_HE176281 WHERE bookID = ?";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);

            try ( ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Ratings rating = new Ratings();
                    // rating.setRatingID(rs.getInt("ratingID")); // Loại bỏ dòng này
                    rating.setBookID(rs.getInt("bookID"));
                    rating.setUserID(rs.getInt("userID"));
                    rating.setStar(rs.getInt("star"));
                    rating.setDescription(rs.getString("description"));
                    ratings.add(rating);
                }
            }
        }
        return ratings;
    }

    public double getStarAvg(int bookId) throws SQLException {
        String sql = "SELECT CAST(AVG(CAST(star AS FLOAT)) AS DOUBLE PRECISION) AS avg_star "
                + "FROM ratings_HE176281 "
                + "WHERE bookID = ?;";
        double avgStars = 0;

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);

            try ( ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    avgStars = rs.getDouble("avg_star");
                }
            }
        }
        return avgStars;
    }

    public void addRating(Ratings rating) throws SQLException {
        String sql = "INSERT INTO ratings_HE176281(bookID, userID, star, description) VALUES (?, ?, ?, ?)";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rating.getBookID());
            statement.setInt(2, rating.getUserID());
            statement.setInt(3, rating.getStar());
            statement.setString(4, rating.getDescription());
            statement.executeUpdate();
        }
    }

    public void deleteRating(int bookId, int userId) throws SQLException {
        String sql = "DELETE FROM ratings_HE176281 WHERE bookID=? AND userID=?";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        }
    }

    public boolean isRatingExists(int bookId, int userId) throws SQLException {
        String sql = "SELECT COUNT(1) FROM ratings_HE176281 WHERE bookID = ? AND userID = ?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.setInt(2, userId);

            try ( ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}
