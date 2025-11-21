package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import tubes.models.Potion;
import tubes.models.enums.PotionType;
import tubes.models.enums.Rarity;
import tubes.util.Dialog;

public class PotionRepo {
    private static final Connection connection = Database.connect();
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;
    
    public Potion findById(int id){
        return null;
    }

    public List<Potion> findAll(){
        query = "SELECT * FROM \"Potion\"";
        
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Potion> potions = new ArrayList<Potion>();

            while (resultSet.next()) {
                potions.add(convertResultSet());
            }

            return potions;

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return new ArrayList<Potion>();
    }

    public void insert(Potion potion) {
        query = "INSERT INTO \"Potion\" (name, \"potionType\", \"potionEffect\", rarity) VALUES (?, ?::\"PotionType\", ?, ?::\"Rarity\")";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, potion.getName());
            statement.setObject(2, potion.getPotionType().getName(), Types.OTHER);
            statement.setInt(3, potion.getPotionEffect());
            statement.setObject(4, potion.getRarity().getName(), Types.OTHER);

            statement.executeUpdate();
            Dialog.outputInformation("Potion " + potion.getName() + " inserted successfully to database.");

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void delete(Potion potion) {
        query = "DELETE FROM \"Potion\" WHERE id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, potion.getPotionId());

            statement.executeUpdate();
            Dialog.outputInformation("Potion " + potion.getName() + " deleted successfully from database.");

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public List<Potion> findByRarity(Rarity rarity){
        query = "SELECT * FROM \"Potion\" WHERE rarity = ?::\"Rarity\"";

        try {
            
            statement = connection.prepareStatement(query);
            statement.setObject(1, rarity.getName(), Types.OTHER);
            resultSet = statement.executeQuery();
            List<Potion> potions = new ArrayList<Potion>();

            while(resultSet.next()) {
                potions.add(convertResultSet());
            }

            return potions;
            
        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return null;
    }

    public Potion convertResultSet() throws SQLException {
        return new Potion(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            Rarity.valueOf(resultSet.getString("rarity")),
            PotionType.valueOf(resultSet.getString("potionType")),
            resultSet.getInt("potionEffect")
        );
    }
}