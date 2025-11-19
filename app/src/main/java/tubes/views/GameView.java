package tubes.views;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tubes.models.Enemy;
import tubes.models.Item;
import tubes.models.Player;
import tubes.models.enums.Element;
import tubes.util.Dialog;

public class GameView {
    
    public int handleMainMenu(boolean isContinue){
        final int[] userChoice = {0};

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        JLabel messageLabel = new JLabel("Selamat Datang di Main Menu", SwingConstants.CENTER);
        JButton btnNewGame = new JButton("New Game");
        JButton btnContinue = new JButton("Continue");

        if (!isContinue) {
            btnContinue.setEnabled(false);
        }

        btnNewGame.addActionListener(e -> {
            userChoice[0] = 0;
        });

        btnContinue.addActionListener(e -> {
            userChoice[0] = 1;
        });

        panel.add(messageLabel);
        panel.add(btnNewGame);
        panel.add(btnContinue);

        Dialog.showCustomPanel("Main Menu", panel);
        return userChoice[0];
    }

    public int handleChooseDifficulty(String[] diffNames){
        return Dialog.inputChoice("Pilih Difficulty", diffNames);
    }

    public String handleChooseElement(String[] elementNames){
        int choice = Dialog.inputChoice("Pilih Element", elementNames);
        return elementNames[choice];
    }
    
    public void handleSaveGame(){
        Dialog.outputInformation("Game Save Success");
    }

    public String handleRunGame(Player player, Enemy enemy, String log, boolean disableWeapon, boolean disableSkill){

        Dialog.outputInformation(log);
        Dialog.outputInformation(player.toString());
        Dialog.outputInformation(enemy.toString());

        return "hand";
    }

    public void handleGameOver(){
        Dialog.outputError("Game Over");
    }

    public int handleShowReward(String[] optionNames){
        return Dialog.inputChoice("Pilih Reward", optionNames);
    }

    public void showRewardApplied(String name, String message){}
}
