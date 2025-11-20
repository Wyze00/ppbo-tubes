package tubes.views;

import java.util.List;

import tubes.controllers.BuffController;
import tubes.models.Buff;
import tubes.models.enums.BuffType;
import tubes.models.enums.Rarity;
import tubes.util.Dialog;

public class BuffView {
    private BuffController buffController = new BuffController();

    public void handleBuffMenu(){

        String menu =  "=== Buff Menu ===\n" +
                      "1. View All Buffs\n" +
                      "2. Add Buff\n" +
                      "3. Delete Buff\n" +
                      "0. Back to Admin Menu\n" +
                      "Choose an option: ";

        int choice = Dialog.inputInt(menu);

        switch(choice){
            case 1:
                this.handleViewAllBuffs();
                break;
            case 2:
                this.handleAddBuff();
                break;
            case 3:
                this.handleDeleteBuff();
                break;
            case 0:
                return;
            default:
                Dialog.outputInformation("Invalid option. Please try again.");
        }
    }

    public void handleViewAllBuffs(){
       List<Buff> buffData = buffController.getAllBuffs();

        for(Buff e : buffData){
            Dialog.outputInformation(e.toString());
        }
    }

    public void handleAddBuff(){
        String name = Dialog.inputString("Enter buff name: ");
        
        String[] allRarityNames = Rarity.getAllNames();
        int rarityChoose = Dialog.inputChoice("Select buff rarity:", allRarityNames);
        Rarity rarity = Rarity.valueOf(allRarityNames[rarityChoose]);
        
        String[] allBuffTypeNames = BuffType.getAllNames();
        int typeChoose = Dialog.inputChoice("Select buff type (ATTACK, DEFENSE, etc.):", allBuffTypeNames);
        BuffType type = BuffType.valueOf(allBuffTypeNames[typeChoose]);
        
        int multiplier = Dialog.inputInt("Enter buff multiplier (as percentage, e.g., 10 for 10%): ");

        // Buff constructor: Buff(int id, String name, Rarity rarity, BuffType type, int multiplier)
        buffController.addBuff(new Buff(0, name, rarity, type, multiplier));
    }

    public void handleDeleteBuff(){
        List<Buff> buffsData = buffController.getAllBuffs();
        
        if (buffsData.isEmpty()) {
            Dialog.outputInformation("No buffs to delete.");
            return;
        }

        String[] buffNames = new String[buffsData.size()];
        for(int i = 0; i < buffsData.size(); i++){
            buffNames[i] = buffsData.get(i).getId() + ". " + buffsData.get(i).getName();
        }

        int buffChoose = Dialog.inputChoice("Select buff to delete:", buffNames);
        Buff selectedBuff = buffsData.get(buffChoose);

        buffController.deleteBuff(selectedBuff);
    }
}
