package tubes.views;

import java.util.List;

import tubes.controllers.DifficultyController;
import tubes.models.Difficulty;
import tubes.util.Dialog;

public class DifficultyView {
    private DifficultyController difficultyController = new DifficultyController();

    public void handleDifficultyMenu(){

        String menu =  "=== Difficulty Menu ===\n" +
                      "1. View All Difficulties\n" +
                      "2. Add Difficulty\n" +
                      "3. Delete Difficulty\n" +
                      "0. Back to Admin Menu\n" +
                      "Choose an option: ";

        int choice = Dialog.inputInt(menu);

        switch(choice){
            case 1:
                this.handleViewAllDifficulties();
                break;
            case 2:
                this.handleAddDifficulty();
                break;
            case 3:
                this.handleDeleteDifficulty();
                break;
            case 0:
                return;
            default:
                Dialog.outputInformation("Invalid option. Please try again.");
        }
    }

    public void handleViewAllDifficulties(){
        List<Difficulty> diffcultyData = difficultyController.getAllDifficulties();

        for(Difficulty d : diffcultyData){
            Dialog.outputInformation(d.toString());
        }
    }

    public void handleAddDifficulty(){
        String name = Dialog.inputString("Enter difficulty name (e.g., Nightmare): ");
        
        int hp = Dialog.inputInt("Enter base HP: ");
        int mana = Dialog.inputInt("Enter base Mana: ");
        int attack = Dialog.inputInt("Enter base Attack: ");
        int defense = Dialog.inputInt("Enter base Defense: ");
        
        difficultyController.addDifficulty(new Difficulty(0, name, hp, mana, attack, defense));
    }

    public void handleDeleteDifficulty(){
        List<Difficulty> difficultiesData = difficultyController.getAllDifficulties();
        
        if (difficultiesData.isEmpty()) {
            Dialog.outputInformation("No difficulties to delete.");
            return;
        }

        String[] diffNames = new String[difficultiesData.size()];
        for(int i = 0; i < difficultiesData.size(); i++){
            diffNames[i] = difficultiesData.get(i).getId() + ". " + difficultiesData.get(i).getName();
        }

        int diffChoose = Dialog.inputChoice("Select difficulty to delete:", diffNames);
        Difficulty selectedDifficulty = difficultiesData.get(diffChoose);

        difficultyController.deleteDifficulty(selectedDifficulty);
    }
}