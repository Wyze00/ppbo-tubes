package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tubes.models.Boss;
import tubes.models.Enemy;
import tubes.models.enums.Element;
import tubes.models.enums.EnemyType;
import tubes.util.Dialog;

public class BossRepo {
    private static final Connection connection = Database.connect();
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;

    public Boss findById(int id){
        return null;
    }

    public List<Boss> findAll(){
        query = "SELECT * FROM \"Boss\"";
        try {
            
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Boss> bosses = new ArrayList<Boss>();

            while(resultSet.next()) {
                bosses.add(convertResultSet());
            }

            return bosses;
            
        } catch (Exception e) {
            Dialog.outputError(e.getMessage());
        }
        return null;
    }

    public void insert(Boss boss) {
        query = "INSERT INTO \"Boss\" (name, \"enemyType\", hp, attack, defense, element, \"skillId\") VALUES (?, ?::\"EnemyType\", ?, ?, ?, ?::\"Element\", ?)";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, boss.getName());
            statement.setObject(2, boss.getType().getName());
            statement.setInt(3, boss.getHp());
            statement.setInt(4, boss.getAttack());
            statement.setInt(5, boss.getDefense());
            statement.setObject(6, boss.getElement().getName());
            statement.setInt(7, boss.getSkillId());

            statement.executeUpdate();
            Dialog.outputInformation("Boss " + boss.getName() + " inserted successfully to database.");
        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void delete(Boss boss) {
        query = "DELETE FROM \"Boss\" WHERE id = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, boss.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

   public Boss convertResultSet() throws SQLException {
        return new Boss(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            EnemyType.valueOf(resultSet.getString("enemyType")),
            resultSet.getInt("hp"),
            0,
            resultSet.getInt("attack"),
            resultSet.getInt("defense"),
            Element.valueOf(resultSet.getString("element")), 
            resultSet.getInt("skillId")
        );
    }
    
}
