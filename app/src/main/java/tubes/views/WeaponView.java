package tubes.views;

import java.util.List;

import tubes.controllers.WeaponController;
import tubes.models.Weapon;
import tubes.models.enums.Element;
import tubes.models.enums.Rarity;
import tubes.util.Dialog;

public class WeaponView {
    private WeaponController weaponController = new WeaponController();

    public void handleMainMenu(){

        String menu =  "=== Weapon Menu ===\n" +
                      "1. View All Weapons\n" +
                      "2. Add Weapon\n" +
                      "3. Delete Weapon\n" +
                      "0. Back to Main Menu\n" +
                      "Choose an option: ";

        int choice = Dialog.inputInt(menu);

        switch(choice){
            case 1:
                this.handleViewAllWeapons();
                break;
            case 2:
                this.handleAddWeapon();
                break;
            case 3:
                this.handleDeleteWeapon();
                break;
            case 0:
                return;
            default:
                Dialog.outputInformation("Invalid option. Please try again.");
        }
    }

    public void handleViewAllWeapons(){
        List<Weapon> weaponsData = weaponController.getAllWeapons();

        for(Weapon w : weaponsData){
            Dialog.outputInformation(w.toString());
        }
    }

    public void handleAddWeapon(){
        String name = Dialog.inputString("Enter weapon name: ");
        
        String[] allRarityNames = Rarity.getAllNames();
        int rarityChoose = Dialog.inputChoice("Select weapon rarity:", allRarityNames);
        Rarity rarity = Rarity.valueOf(allRarityNames[rarityChoose]);
        
        int attack = Dialog.inputInt("Enter weapon attack: ");
        
        int mana = Dialog.inputInt("Enter weapon mana: ");
        
        String[] allElementNames = Element.getAllNames();
        int elementChoose = Dialog.inputChoice("Select weapon element:", allElementNames);
        Element element = Element.valueOf(allElementNames[elementChoose]);

        weaponController.addWeapon(new Weapon(0, name, rarity, attack, mana, element));
    }

    public void handleDeleteWeapon(){
        List<Weapon> weaponsData = weaponController.getAllWeapons();

        String[] weaponNames = new String[weaponsData.size()];
        for(int i = 0; i < weaponsData.size(); i++){
            weaponNames[i] = weaponsData.get(i).getName();
        }

        int weaponChoose = Dialog.inputChoice("Select weapon to delete:", weaponNames);
        Weapon selectedWeapon = weaponsData.get(weaponChoose);

        weaponController.deleteWeapon(selectedWeapon);
    }
}
