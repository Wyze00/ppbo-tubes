package tubes.controllers;

import tubes.models.Player;
import tubes.models.PlayerState;
import tubes.models.User;
import tubes.repositories.PlayerStateRepo;
import tubes.repositories.SkillRepo;
import tubes.repositories.WeaponRepo;
import tubes.views.GameView;

public class GameController {

    private PlayerStateRepo playerStateRepo = new PlayerStateRepo();
    private WeaponRepo weaponRepo = new WeaponRepo();
    private SkillRepo skillRepo = new SkillRepo();
    private GameView gameView = new GameView();
    private User user;

    public GameController(User user){
        this.user = user;
    }

    public void start(){

        PlayerState state = this.playerStateRepo.findByUsername(this.user.getUsername());
        int choose = this.gameView.handleMainMenu(state != null);

        if(choose == 1){
            this.loadGame(state);
        } else {
            this.newGame();
        }
    }

    public void loadGame(PlayerState state){

    }

    public void newGame(){

    }

    public void saveGame(PlayerState state){

    }

    public Player convertPlayerStateToPlayer(PlayerState state){

        Player player = new Player(state.getUsername(), state.getHp(), state.getCurrentHp(), state.getMana(), state.getCurrentMana(), state.getAttack(), state.getDefense(), state.getElement());
        return null;
    }
}
