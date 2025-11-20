package tubes.views;
import java.util.List;

import tubes.controllers.BossController;
import tubes.models.Boss;
import tubes.models.Enemy;
import tubes.models.enums.EnemyType;
import tubes.models.enums.Element;
import tubes.util.Dialog;

public class BossView {
    private BossController bossController = new BossController();

    public void handleBossMenu () {
        String menu =  "=== Boss Menu ===\n" +
                      "1. View All Boss\n" +
                      "2. Add Boss\n" +
                      "3. Delete Boss\n" +
                      "0. Back to Main Menu\n" +
                      "Choose an option: ";

        int choice = Dialog.inputInt(menu);

        switch(choice){
            case 1:
                this.handleViewAllBoss();
                break;
            case 2:
                this.handleAddBoss();
                break;
            case 3:
                this.handleDeleteBoss();
                break;
            case 0:
                return;
            default:
                Dialog.outputInformation("Invalid option. Please try again.");
        }
    }

    public void handleViewAllBoss(){
        List<Boss> bossData = bossController.getAllBosses();

        for(Boss e : bossData){
            Dialog.outputInformation(e.toString());
        }
    }

    public void handleAddBoss(){
        String name = Dialog.inputString("Enter boss name: ");
        
        String[] allEnemyTypeNames = EnemyType.getAllNames();
        int enemyTypeChoose = Dialog.inputChoice("Select boss type:", allEnemyTypeNames);
        EnemyType enemyType = EnemyType.valueOf(allEnemyTypeNames[enemyTypeChoose]);

        int hp = Dialog.inputInt("Enter boss HP: ");
        
        int attack = Dialog.inputInt("Enter boss Attack: ");


        int defense = Dialog.inputInt("Enter boss Defense: ");

        String[] allElementNames = Element.getAllNames();
        int elementChoose = Dialog.inputChoice("Select enemy element:", allElementNames);
        Element element = Element.valueOf(allElementNames[elementChoose]);

        int skillId = Dialog.inputInt("Enter boss Skill ID: ");

         bossController.addBoss(new Boss(0, name, enemyType, hp, 0, attack, defense, element, skillId));
    }

    public void handleDeleteBoss(){
        List<Boss> bossData = bossController.getAllBosses();

        for(int i = 0; i < bossData.size(); i++){
            Dialog.outputInformation((i+1) + ". " + bossData.get(i).getName());
        }

        int choice = Dialog.inputInt("Select Boss to delete (0 to cancel): ");

        if(choice > 0 && choice <= bossData.size()){
            bossController.deleteBoss(bossData.get(choice - 1));
            Dialog.outputInformation("Enemy deleted successfully.");
        } else if (choice == 0) {
            Dialog.outputInformation("Deletion cancelled.");
        } else {
            Dialog.outputInformation("Invalid choice. Please try again.");
        }
    }
}
