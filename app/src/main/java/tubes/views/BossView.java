package tubes.views;

import java.util.List;
import tubes.controllers.BossController;
import tubes.models.Boss;
import tubes.models.enums.EnemyType;
import tubes.models.enums.Element;
import tubes.util.Dialog;

public class BossView {
    private BossController bossController = new BossController();

    public void handleBossMenu () {
        String menu = "Boss Menu\n" +
                      "1. View All Boss\n" +
                      "2. Add Boss\n" +
                      "3. Delete Boss\n" +
                      "0. Back to Main Menu\n";

        int choice = Dialog.inputInt(menu);

        switch(choice){
            case 1 -> this.handleViewAllBoss();
            case 2 -> this.handleAddBoss();
            case 3 -> this.handleDeleteBoss();
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

        String[] bossNames = new String[bossData.size()];
        for(int i = 0; i < bossData.size(); i++){
            bossNames[i] = bossData.get(i).getName();
        }

        int bossChoose = Dialog.inputChoice("Select boss to delete:", bossNames);
        Boss selectedBoss = bossData.get(bossChoose);

        bossController.deleteBoss(selectedBoss);
    }
}
