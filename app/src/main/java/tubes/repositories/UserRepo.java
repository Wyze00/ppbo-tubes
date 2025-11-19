package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import tubes.models.User;
import tubes.models.enums.UserRole;
import tubes.util.Dialog;

public class UserRepo {
    private static final Connection connection = Database.connect();
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;

    public User findByUsername(String username) {
        
        query = "SELECT * FROM \"User\" WHERE username = ?";

        try {
            
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return convertResultSet();
            }

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return null;
    }

    public List<User> findAll() {
        return new ArrayList<User>();
    }

    public void insert(User user) {
        query = "INSERT INTO \"User\" (username, password, role) VALUES (?, ?, ?::\"UserRole\")";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setObject(3, user.getRole().getName(), Types.OTHER);
            statement.executeUpdate();

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void delete(User weapon) {}

    public void update(User weapon) {}

    public User convertResultSet() throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                UserRole.valueOf(resultSet.getString("role"))
        );
    }
}
