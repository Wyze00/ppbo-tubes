package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import tubes.models.PlayerState;
import tubes.models.enums.Element;
import tubes.util.Dialog;

public class PlayerStateRepo {
    private static final Connection connection = Database.connect();
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;
    
    public PlayerState findByUsername(String username){
        query = "SELECT * FROM \"PlayerState\" WHERE username = ?";

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

    public void insert(PlayerState playerState) {

        query = "INSERT INTO \"PlayerState\" (username, hp, \"currentHp\", mana, \"currentMana\", defense, attack, element, \"currentStage\", \"weaponId\", \"skillId\") VALUES (?, ?, ?, ?, ?, ?, ?, ?::\"Element\", ?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, playerState.getUsername());
            statement.setInt(2, playerState.getHp());
            statement.setInt(3, playerState.getCurrentHp());
            statement.setInt(4, playerState.getMana());
            statement.setInt(5, playerState.getCurrentMana());
            statement.setInt(6, playerState.getDefense());
            statement.setInt(7, playerState.getAttack());
            statement.setObject(8, playerState.getElement().name(), Types.OTHER);
            statement.setInt(9, playerState.getCurrentStage());
            statement.setInt(10, playerState.getWeaponId());
            statement.setInt(11, playerState.getSkillId());

            statement.executeUpdate();

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void update(PlayerState playerState) {
        query = "UPDATE \"PlayerState\" SET hp = ?, \"currentHp\" = ?, mana = ?, \"currentMana\" = ?, defense = ?, attack = ?, element = ?::\"Element\", \"currentStage\" = ?, \"weaponId\" = ?, \"skillId\" = ? WHERE username = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, playerState.getHp());
            statement.setInt(2, playerState.getCurrentHp());
            statement.setInt(3, playerState.getMana());
            statement.setInt(4, playerState.getCurrentMana());
            statement.setInt(5, playerState.getDefense());
            statement.setInt(6, playerState.getAttack());
            statement.setObject(7, playerState.getElement().name(), Types.OTHER);
            statement.setInt(8, playerState.getCurrentStage());
            statement.setInt(9, playerState.getWeaponId());
            statement.setInt(10, playerState.getSkillId());
            statement.setString(11, playerState.getUsername());

            statement.executeUpdate();

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void delete(String username) {   
        query = "DELETE FROM \"PlayerState\" WHERE username = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            statement.executeUpdate();

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public PlayerState convertResultSet() throws SQLException {

        return new PlayerState(
            resultSet.getString("username"),
            resultSet.getInt("hp"),
            resultSet.getInt("currentHp"),
            resultSet.getInt("mana"),
            resultSet.getInt("currentMana"),
            resultSet.getInt("defense"),
            resultSet.getInt("attack"),
            Element.valueOf(resultSet.getString("element")),
            resultSet.getInt("currentStage"),
            resultSet.getInt("weaponId"),
            resultSet.getInt("skillId")
        );
    }
}
