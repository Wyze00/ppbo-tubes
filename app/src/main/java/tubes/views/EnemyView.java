package tubes.views;

import java.util.List;

import tubes.controllers.EnemyController;
import tubes.models.Enemy;
import tubes.models.enums.EnemyType;
import tubes.models.enums.Element;
import tubes.util.Dialog;

public class EnemyView {
    private EnemyController enemyController = new EnemyController();

    public void handleEnemyMenu () {
        String menu =  "=== Enemy Menu ===\n" +
                      "1. View All Enemy\n" +
                      "2. Add Enemy\n" +
                      "3. Delete Enemy\n" +
                      "0. Back to Main Menu\n" +
                      "Choose an option: ";

        int choice = Dialog.inputInt(menu);

        switch(choice){
            case 1:
                this.handleViewAllEnemy();
                break;
            case 2:
                this.handleAddEnemy();
                break;
            case 3:
                this.handleDeleteEnemy();
                break;
            case 0:
                return;
            default:
                Dialog.outputInformation("Invalid option. Please try again.");
        }
    }

    public void handleViewAllEnemy(){
        List<Enemy> enemyData = enemyController.getAllEnemies();

        for(Enemy e : enemyData){
            Dialog.outputInformation(e.toString());
        }
    }

    public void handleAddEnemy(){
        String name = Dialog.inputString("Enter enemy name: ");
        
        String[] allEnemyTypeNames = EnemyType.getAllNames();
        int enemyTypeChoose = Dialog.inputChoice("Select enemy type:", allEnemyTypeNames);
        EnemyType enemyType = EnemyType.valueOf(allEnemyTypeNames[enemyTypeChoose]);

        int hp = Dialog.inputInt("Enter enemy HP: ");
        
        int attack = Dialog.inputInt("Enter enemy Attack: ");

        int defense = Dialog.inputInt("Enter enemy Defense: ");

        String[] allElementNames = Element.getAllNames();
        int elementChoose = Dialog.inputChoice("Select enemy element:", allElementNames);
        Element element = Element.valueOf(allElementNames[elementChoose]);

        enemyController.addEnemy(new Enemy(0, name, enemyType, hp, 0, attack, defense, element));
    }

    public void handleDeleteEnemy(){
        List<Enemy> enemyData = enemyController.getAllEnemies();

        for(int i = 0; i < enemyData.size(); i++){
            Dialog.outputInformation((i+1) + ". " + enemyData.get(i).getName());
        }

        int choice = Dialog.inputInt("Select enemy to delete (0 to cancel): ");

        if(choice > 0 && choice <= enemyData.size()){
            enemyController.deleteEnemy(enemyData.get(choice - 1));
            Dialog.outputInformation("Enemy deleted successfully.");
        } else if (choice == 0) {
            Dialog.outputInformation("Deletion cancelled.");
        } else {
            Dialog.outputInformation("Invalid choice. Please try again.");
        }
    }
}