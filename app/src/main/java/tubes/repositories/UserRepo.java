package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        query = "SELECT * FROM users WHERE username = ?";

        try {
            
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    UserRole.valueOf(resultSet.getString("role"))
                );

                return user;
            }

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return null;
    }

    public List<User> findAll() {
        return new ArrayList<User>();
    }

    public void insert(User weapon) {}

    public void delete(User weapon) {}

    public void update(User weapon) {}
}
