package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tubes.models.Difficulty;
import tubes.util.Dialog;

public class DifficultyRepo {
    private static final Connection connection = Database.connect();
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;

    public Difficulty findById(int id){
        query = "SELECT * FROM \"Difficulty\" WHERE id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return convertResultSet();
            }

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
        return null;
    }

    public List<Difficulty> findAll(){
        query = "SELECT * FROM \"Difficulty\"";
        List<Difficulty> difficulties = new ArrayList<>();

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                difficulties.add(convertResultSet());
            }
            return difficulties;
            
        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return difficulties;
    }

    public void insert(Difficulty diff) {
        query = "INSERT INTO \"Difficulty\" (name, \"baseHp\", \"baseMana\", \"baseAttack\", \"baseDefense\") VALUES (?, ?, ?, ?, ?)";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, diff.getName());
            statement.setInt(2, diff.getBaseHp());
            statement.setInt(3, diff.getBaseMana());
            statement.setInt(4, diff.getBaseAttack());
            statement.setInt(5, diff.getBaseDefense());

            statement.executeUpdate();
            Dialog.outputInformation("Difficulty " + diff.getName() + " inserted successfully to database.");

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void delete(Difficulty diff) {
        query = "DELETE FROM \"Difficulty\" WHERE id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, diff.getId());

            statement.executeUpdate();
            Dialog.outputInformation("Difficulty " + diff.getName() + " deleted successfully from database.");

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void update(Difficulty diff) {}

    private Difficulty convertResultSet() throws SQLException {
        return new Difficulty(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("baseHp"),
            resultSet.getInt("baseMana"),
            resultSet.getInt("baseAttack"),
            resultSet.getInt("baseDefense")
        );
    }
}