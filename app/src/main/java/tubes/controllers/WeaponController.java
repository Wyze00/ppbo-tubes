package tubes.controllers;

import java.util.List;
import tubes.models.Weapon;
import tubes.repositories.WeaponRepo;

public class WeaponController {
    private WeaponRepo weaponRepo = new WeaponRepo();

    public List<Weapon> getAllWeapons(){
        return weaponRepo.findAll();
    }

    public void addWeapon(Weapon weapon){
        weaponRepo.insert(weapon);
    }

    public void deleteWeapon(Weapon weapon){
        weaponRepo.delete(weapon);
    }
}
