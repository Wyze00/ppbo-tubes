package tubes.repositories;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import tubes.models.Weapon;

public class WeaponRepo {
    private Connection connection = Database.connect();
    
    public Weapon findById(int id){
        return null;
    }

    public List<Weapon> findAll(){
        return new ArrayList<Weapon>();
    }

    public void insert(Weapon weapon) {}

    public void delete(Weapon weapon) {}

    public void update(Weapon weapon) {}
}
