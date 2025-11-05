package tubes.views;

import tubes.models.Enemy;
import tubes.models.Player;
import tubes.models.enums.Element;

public class GameView {
    
    public int handleMainMenu(boolean isContinue){
        return isContinue == true ? 1 : 0;
    }

    public int handleChooseDifficulty(){
        return 0;
    }

    public Element handleChooseElement(){
        return Element.DARK;
    }
    
    public void handleSaveGame(){
        
    }

    public String handleRunGame(Player player, Enemy enemy, String log, boolean disableWeapon, boolean disableSkill){
        return "hand";
    }

    public void handleGameOver(){
        
    }
}
