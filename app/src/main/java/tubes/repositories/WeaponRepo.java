package tubes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import tubes.models.Weapon;
import tubes.models.enums.Element;
import tubes.models.enums.Rarity;
import tubes.util.Dialog;

public class WeaponRepo {
    private static final Connection connection = Database.connect();
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;
    
    public Weapon findById(int id){
        return null;
    }

    public List<Weapon> findAll(){
        query = "SELECT * FROM \"Weapon\"";

        try {
            
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Weapon> weapons = new ArrayList<Weapon>();

            while(resultSet.next()) {
                weapons.add(convertResultSet());
            }

            return weapons;
            
        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }

        return null;
    }

    public void insert(Weapon weapon) {
        query = "INSERT INTO \"Weapon\" (name, rarity, attack, mana, element) VALUES (?, ?::\"Rarity\", ?, ?, ?::\"Element\")";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, weapon.getName());
            statement.setObject(2, weapon.getRarity().getName(), Types.OTHER);
            statement.setInt(3, weapon.getAttack());
            statement.setInt(4, weapon.getMana());
            statement.setObject(5, weapon.getElement().getName(), Types.OTHER);

            statement.executeUpdate();

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void delete(Weapon weapon) {
        query = "DELETE FROM \"Weapon\" WHERE id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, weapon.getWeaponId());

            statement.executeUpdate();

        } catch (SQLException e) {
            Dialog.outputError(e.getMessage());
        }
    }

    public void update(Weapon weapon) {}

    public Weapon convertResultSet() throws SQLException {
        return new Weapon(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            Rarity.valueOf(resultSet.getString("rarity")),
            resultSet.getInt("attack"),
            resultSet.getInt("mana"),
            Element.valueOf(resultSet.getString("element"))
        );
    }
}
