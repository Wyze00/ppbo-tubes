package tubes.views;

import java.util.List;
import tubes.controllers.PotionController;
import tubes.models.Potion;
import tubes.models.enums.PotionType;
import tubes.models.enums.Rarity;
import tubes.util.Dialog;

public class PotionView {
    private PotionController potionController = new PotionController();

    public void handlePotionMenu(){

        String menu = "Potion Menu\n" +
                      "1. View All Potions\n" +
                      "2. Add Potion\n" +
                      "3. Delete Potion\n" +
                      "0. Back to Admin Menu\n";

        int choice = Dialog.inputInt(menu);

        switch(choice){
            case 1 -> this.handleViewAllPotions();
            case 2 -> this.handleAddPotion();
            case 3 -> this.handleDeletePotion();
        }
    }

    public void handleViewAllPotions(){
        List<Potion> potionData = potionController.getAllPotions();

        for(Potion e : potionData){
            Dialog.outputInformation(e.toString());
        }
    }

    public void handleAddPotion(){
        String name = Dialog.inputString("Enter potion name: ");
        
        String[] allRarityNames = Rarity.getAllNames();
        int rarityChoose = Dialog.inputChoice("Select potion rarity:", allRarityNames);
        Rarity rarity = Rarity.valueOf(allRarityNames[rarityChoose]);
        
        String[] allPotionTypeNames = {PotionType.HEALTH.getName(), PotionType.MANA.getName()};
        int typeChoose = Dialog.inputChoice("Select potion type:", allPotionTypeNames);
        PotionType type = PotionType.valueOf(allPotionTypeNames[typeChoose]);
        
        int effect = Dialog.inputInt("Enter potion effect amount: ");

        potionController.addPotion(new Potion(0, name, rarity, type, effect));
    }

    public void handleDeletePotion(){
        List<Potion> potionsData = potionController.getAllPotions();
        
        String[] potionNames = new String[potionsData.size()];
        for(int i = 0; i < potionsData.size(); i++){
            potionNames[i] = potionsData.get(i).getPotionId() + ". " + potionsData.get(i).getName();
        }

        int potionChoose = Dialog.inputChoice("Select potion to delete:", potionNames);
        Potion selectedPotion = potionsData.get(potionChoose);

        potionController.deletePotion(selectedPotion);
    }
}