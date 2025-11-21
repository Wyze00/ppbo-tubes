package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import tubes.models.Enemy;
import tubes.models.enums.Element;
import tubes.models.enums.EnemyType;
import tubes.util.Dialog;

public class EnemyRepo {
    private static final Connection connection = Database.connect();
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;

    public Enemy findById(int id) {
        return null;
    }

    public List<Enemy> findAll() {
        query = "SELECT * FROM \"Enemy\"";

        try {

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Enemy> enemies = new ArrayList<Enemy>();

            while (resultSet.next()) {
                enemies.add(convertResultSet());
            }

            return enemies;

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return null;
    }

    public void insert(Enemy enemy) {
        query = "INSERT INTO \"Enemy\" (name, \"enemyType\", hp, attack, defense, element) VALUES (?, ?::\"EnemyType\", ?, ?, ?, ?::\"Element\")";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, enemy.getName());
            statement.setObject(2, enemy.getType().getName(), Types.OTHER);
            statement.setInt(3, enemy.getHp());
            statement.setInt(4, enemy.getAttack());
            statement.setInt(5, enemy.getDefense());
            statement.setObject(6, enemy.getElement().getName(), Types.OTHER);

            statement.executeUpdate();
            Dialog.outputInformation("Enemy " + enemy.getName() + " inserted successfully to database.");

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void delete(Enemy enemy) {
        query = "DELETE FROM \"Enemy\" WHERE id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, enemy.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    

    public Enemy convertResultSet() throws SQLException {
        return new Enemy(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            EnemyType.valueOf(resultSet.getString("enemyType")),
            resultSet.getInt("hp"),
            0,
            resultSet.getInt("attack"),
            resultSet.getInt("defense"),
            Element.valueOf(resultSet.getString("element"))
        );
    }
}
