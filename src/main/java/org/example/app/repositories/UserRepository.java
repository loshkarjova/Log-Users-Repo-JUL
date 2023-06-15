package org.example.app.repositories;

import lombok.AllArgsConstructor;
import org.example.app.database.DBConn;
import org.example.app.entities.User;
import org.example.app.entities.UserDto;
import org.example.app.utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
public class UserRepository {
    private static final Logger LOGGER = Logger.getLogger(UserRepository.class.getName());

    private final Connection connection = DBConn.connect();

    public String createUser(User user) {
        String sql = "INSERT INTO " + Constants.TABLE_USERS + "(name, phone, email) VALUES (?,?,?);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            return Constants.DATA_INSERT_MSG;
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            LOGGER.log(Level.WARNING, Constants.LOG_DB_ERROR_MSG);
            return e.getMessage();
        }
    }

    public String deleteUser(int id) {
        String sql = "DELETE FROM " + Constants.TABLE_USERS + " WHERE id=?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return Constants.DATA_DELETE_MSG;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, Constants.LOG_DB_ERROR_MSG);
            return e.getMessage();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            List<User> users = new ArrayList<>();
            String sql = "SELECT id, name, phone, email FROM " + Constants.TABLE_USERS + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
            return users;

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, Constants.LOG_DB_ERROR_MSG);
            return Collections.emptyList();
        }

    }

    public String updateUser(UserDto user) {
        String sql = "UPDATE " + Constants.TABLE_USERS + " SET phone = ?, email = ? WHERE id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getPhone());
            ps.setString(2, user.getEmail());
            ps.setInt(3, Integer.parseInt(user.getId()));
            ps.executeUpdate();
            return Constants.DATA_UPDATE_MSG;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, Constants.LOG_DB_ERROR_MSG);
            return e.getMessage();
        }
    }

    public boolean isIdExists(int id) {
        try {
            String query = "SELECT * FROM " + Constants.TABLE_USERS + " WHERE id = ?;";
            PreparedStatement pst = DBConn.connect().prepareStatement(query);
            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
