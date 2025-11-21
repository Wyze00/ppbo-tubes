package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import tubes.models.Buff;
import tubes.models.enums.BuffType;
import tubes.models.enums.Rarity;
import tubes.util.Dialog;

public class BuffRepo {
    private static final Connection connection = Database.connect();
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;

    public Buff findById(int id){
        return null;
    }

    public List<Buff> findAll(){
        query = "SELECT * FROM \"Buff\"";
        
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Buff> buffs = new ArrayList<Buff>();

            while (resultSet.next()) {
                buffs.add(convertResultSet());
            }

            return buffs;

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return null;
    }

    public void insert(Buff buff) {
        query = "INSERT INTO \"Buff\" (name, \"buffType\", \"buffMultiplier\", rarity) VALUES (?, ?::\"BuffType\", ?, ?::\"Rarity\")";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, buff.getName());
            statement.setObject(2, buff.getType().getName(), Types.OTHER);
            statement.setInt(3, buff.getMultiplier());
            statement.setObject(4, buff.getRarity().getName(), Types.OTHER);

            statement.executeUpdate();
            Dialog.outputInformation("Buff " + buff.getName() + " inserted successfully to database.");

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void delete(Buff buff) {
        query = "DELETE FROM \"Buff\" WHERE id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, buff.getId());

            statement.executeUpdate();
            Dialog.outputInformation("Buff " + buff.getName() + " deleted successfully from database.");

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public List<Buff> findByRarity(Rarity rarity){
        query = "SELECT * FROM \"Buff\" WHERE rarity = ?::\"Rarity\"";

        try {
            
            statement = connection.prepareStatement(query);
            statement.setObject(1, rarity.getName(), Types.OTHER);
            resultSet = statement.executeQuery();
            List<Buff> buffs = new ArrayList<Buff>();

            while(resultSet.next()) {
                buffs.add(convertResultSet());
            }

            return buffs;
            
        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return null;
    }

    public Buff convertResultSet() throws SQLException {
        return new Buff(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            Rarity.valueOf(resultSet.getString("rarity")),
            BuffType.valueOf(resultSet.getString("buffType")),
            resultSet.getInt("buffMultiplier")
        );
    }
    
}
