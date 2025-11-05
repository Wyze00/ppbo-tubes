package tubes.controllers;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

import tubes.models.Boss;
import tubes.models.Difficulty;
import tubes.models.Enemy;
import tubes.models.Player;
import tubes.models.PlayerState;
import tubes.models.Skill;
import tubes.models.User;
import tubes.models.Weapon;
import tubes.models.enums.Element;
import tubes.repositories.DifficultyRepo;
import tubes.repositories.PlayerStateRepo;
import tubes.repositories.PotionRepo;
import tubes.repositories.SkillRepo;
import tubes.repositories.WeaponRepo;
import tubes.views.GameView;

public class GameController {

    private PlayerStateRepo playerStateRepo = new PlayerStateRepo();
    private WeaponRepo weaponRepo = new WeaponRepo();
    private SkillRepo skillRepo = new SkillRepo();
    private GameView gameView = new GameView();
    private DifficultyRepo difficultyRepo = new DifficultyRepo();
    private PotionRepo potionRepo = new PotionRepo();

    private User user;

    class TurnResult {
        private int damage;
        private String message;

        public TurnResult(int damage, String message){
            this.damage = damage;
            this.message = message;
        }

        public int getDamage(){
            return this.damage;
        }

        public void setMessage(String message){
            this.message = message;
        }

        public String getMessage(){
            return this.message;
        }
    }

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

    // State

    public void loadGame(PlayerState state){
        Player player = this.convertPlayerStateToPlayer(state);
        runGame(player);
    }

    public void newGame(){

        List<Difficulty> diffs = this.difficultyRepo.findAll();
        int chooseDiff = this.gameView.handleChooseDifficulty();

        Difficulty dif = diffs.get(chooseDiff);
        Element element = this.gameView.handleChooseElement();
        
        Player player = new Player(this.user.getUsername(), dif.getBaseHp(), dif.getBaseHp(), dif.getBaseMana(), dif.getBaseMana(), dif.getBaseAttack(), dif.getBaseDefense(), element, 1);

        Skill defaultSkill = this.skillRepo.findDefaultByElement(element);

        player.setEquippedSkill(defaultSkill);
        player.setSkillCooldown(player.getEquippedSkill().getCooldown());

        saveGame(player);
        runGame(player);
    }

    public void saveGame(Player player){
        PlayerState state = this.convertPlayerToPlayerState(player);
        this.playerStateRepo.save(state);
        this.gameView.handleSaveGame();
    }

    public void runGame(Player player){

        boolean disableWeapon = false;
        boolean disableSkill = false;

        while(true) {
            
            List<Enemy> enemy = this.generate5EnemyAnd1Boss();
            int currentEnemyIndex = 0;
            StringBuilder log = new StringBuilder("Game Start\n\n");
    
            for(Enemy e : enemy){
    
                while (e.isAlive()) {

                    if(player.getSkillCooldown() > 0 || player.getCurrentMana() < player.getEquippedSkill().getManaCost()){
                        disableSkill = true;
                    }

                    if(player.getEquippedWeapon() == null || player.getCurrentMana() < player.getEquippedWeapon().getMana()){
                        disableWeapon = true;
                    }
    
                    String playerTurn = this.gameView.handleRunGame(player, e, log.toString(), disableWeapon, disableSkill);
                    TurnResult playerResult = this.handlePlayerAttack(player, e, playerTurn);
                    log.append(playerResult.getMessage());
                    e.takeDamage(playerResult.damage);
    
                    if(!e.isAlive()){
                        log.append("You defeated enemy\n\n");
                        break;
                    }
    
                    TurnResult enemyResult = this.handleEnemyAttack(player, e);
                    log.append(enemyResult.getMessage());
                    player.takeDamage(enemyResult.getDamage()); 
    
                    if(!player.isAlive()){
                        this.gameView.handleGameOver();
                        return;
                    }

                    disableWeapon = false;
                    disableSkill = false;
                }
    
                if(currentEnemyIndex == 5){
                    this.getReward(player, "boss");
                } else {
                    this.getReward(player, "enemy");
                }
    
                currentEnemyIndex++;
            }
    
            player.setStage(player.getStage()+1);
            saveGame(player);
        }
    }

    // Reward

    public void getReward(Player player, String enemy){




    }

    // Combat

    public List<Enemy> generate5EnemyAnd1Boss(){
        return new ArrayList<Enemy>();
    }

    public TurnResult handlePlayerAttack(Player player, Enemy enemy, String attackType){

        TurnResult result = null;
        player.setSkillCooldown(player.getSkillCooldown() - 1);

        if(attackType.equalsIgnoreCase("hand")){
            result = handleHandAttack(player.getAttack(), player.getElement(), enemy.getDefense(), enemy.getElement());
        } else if(attackType.equalsIgnoreCase("weapon")){
            result = handleWeaponAttack(player.getAttack(), player.getEquippedWeapon().getAttack(), player.getEquippedWeapon().getElement(), enemy.getDefense(), enemy.getElement());
        } else {
            player.setSkillCooldown(player.getEquippedSkill().getCooldown());
            player.setCurrentMana(player.getCurrentMana() - player.getEquippedSkill().getManaCost());
            result = handleSkillAttack(player.getAttack(), player.getEquippedSkill().getAttack(), player.getEquippedSkill().getElement(), enemy.getDefense(), enemy.getElement());
        } 

        result.setMessage("Player " + result.getMessage());
        return result;
    }

    public TurnResult handleEnemyAttack(Player player, Enemy enemy){

        TurnResult result = null;

        if(enemy instanceof Boss){

            Boss boss = (Boss) enemy;
            boss.setSkillCooldown(boss.getSkillCooldown() - 1);

            // Skill turn
            if(boss.getSkillCooldown() == 0){
                boss.setSkillCooldown(boss.getSkill().getCooldown());;
                // Skill
                result = handleSkillAttack(boss.getAttack(), boss.getSkill().getAttack(), boss.getSkill().getElement(), player.getDefense(), player.getElement());

            } else {
                result = handleHandAttack(boss.getAttack(), boss.getElement(), player.getDefense(), player.getElement());
            }

            result.setMessage("Boss " + result.getMessage());

        } else {
            result = handleHandAttack(enemy.getAttack(), enemy.getElement(), player.getDefense(), player.getElement());
            result.setMessage("Enemy " + result.getMessage());
        }

        return result;
    }

    public TurnResult handleHandAttack(int aAttack, Element aElement, int bDefense, Element bElement){
        double multiplier = isStrongerElement(aElement, bElement) ? 1.2 : 0.8;
        int damage = 10;
        return new TurnResult(damage, "attack with hand, dealt " + damage + "damage\n\n");
    }

    public TurnResult handleSkillAttack(int aAttack, int skillAttack, Element skillElement, int bDefense, Element bElement){
        double multiplier = isStrongerElement(skillElement, bElement) ? 1.2 : 0.8;
        int damage = 10;
        return new TurnResult(damage, "attack with skill, dealt " + damage + "damage\n\n");
    }

    public TurnResult handleWeaponAttack(int aAttack, int weaponAttack, Element weaponElement, int bDefense, Element bElement){
        double multiplier = isStrongerElement(weaponElement, bElement) ? 1.2 : 0.8;
        int damage = 10;
        return new TurnResult(damage, "attack with weapon, dealt " + damage + "damage\n\n");
    }

    public boolean isStrongerElement(Element aElement, Element bElement){
        return true;
    }

    // Util

    public Player convertPlayerStateToPlayer(PlayerState state){

        Player player = new Player(state.getUsername(), state.getHp(), state.getCurrentHp(), state.getMana(), state.getCurrentMana(), state.getAttack(), state.getDefense(), state.getElement(), state.getCurrentStage());
        Weapon weapon = state.getWeaponId() != 0 ? this.weaponRepo.findById(state.getWeaponId()) : null;
        Skill skill = this.skillRepo.findById(state.getSkillId());

        player.equipWeapon(weapon);
        player.equipSkill(skill);
        player.setSkillCooldown(player.getEquippedSkill().getCooldown());

        return player;
    }

    public PlayerState convertPlayerToPlayerState(Player player){

        int weaponId = 0;

        if (player.getEquippedWeapon() != null) {
            weaponId = player.getEquippedWeapon().getWeaponId();
        }   

        PlayerState state = new PlayerState(this.user.getUsername(), player.getHp(), player.getCurrentHp(), player.getMana(), player.getCurrentMana(), player.getDefense(), player.getAttack(), player.getElement(), player.getStage(), weaponId, player.getEquippedSkill().getSkillId());
        return state;
    }
}
