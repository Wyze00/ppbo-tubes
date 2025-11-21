package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import tubes.models.Skill;
import tubes.models.enums.Element;
import tubes.models.enums.Rarity;
import tubes.util.Dialog;

public class SkillRepo {
    private static final Connection connection = Database.connect();
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;
    public Skill findById(int id){
        return null;
    }

    public List<Skill> findAll(){
        query = "SELECT * FROM \"Skill\"";
        
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Skill> skills = new ArrayList<Skill>();

            while (resultSet.next()) {
                skills.add(convertResultSet());
            }

            return skills;

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return null;
    }

    public void insert(Skill skill) {
        query = "INSERT INTO \"Skill\" (name, attack, mana, cooldown, element, rarity) VALUES (?, ?, ?, ?, ?::\"Element\", ?::\"Rarity\")";
    
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, skill.getName());
            statement.setInt(2, skill.getAttack());
            statement.setInt(3, skill.getManaCost()); 
            statement.setInt(4, skill.getCooldown());
            statement.setObject(5, skill.getElement().getName(), Types.OTHER);
            statement.setObject(6, skill.getRarity().getName(), Types.OTHER);

            statement.executeUpdate();
            Dialog.outputInformation("Skill " + skill.getName() + " inserted successfully to database.");

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void delete(Skill skill) {
        query = "DELETE FROM \"Skill\" WHERE id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, skill.getSkillId());

            statement.executeUpdate();
            Dialog.outputInformation("Skill " + skill.getName() + " deleted successfully from database.");

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public Skill findDefaultByElement(Element element){
        query = "SELECT * FROM \"Skill\" WHERE element = ?::\"Element\" AND rarity = 'COMMON'::\"Rarity\" LIMIT 1";

        try {
            statement = connection.prepareStatement(query);
            statement.setObject(1, element.getName(), Types.OTHER);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return convertResultSet();
            }

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
        
        return null;
    }

    public List<Skill> findByRarity(Rarity rarity){
        query = "SELECT * FROM \"Skill\" WHERE rarity = ?::\"Rarity\"";

        try {
            
            statement = connection.prepareStatement(query);
            statement.setObject(1, rarity.getName(), Types.OTHER);
            resultSet = statement.executeQuery();
            List<Skill> skills = new ArrayList<Skill>();

            while(resultSet.next()) {
                skills.add(convertResultSet());
            }

            return skills;
            
        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return null;
    }

    public Skill convertResultSet() throws SQLException {
        return new Skill(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            Rarity.valueOf(resultSet.getString("rarity")),
            resultSet.getInt("attack"),
            resultSet.getInt("mana"),
            resultSet.getInt("cooldown"),
            Element.valueOf(resultSet.getString("element"))
        );
    }
}
