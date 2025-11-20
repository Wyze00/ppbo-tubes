package tubes.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tubes.models.Boss;
import tubes.models.Buff;
import tubes.models.Difficulty;
import tubes.models.Enemy;
import tubes.models.Item;
import tubes.models.Player;
import tubes.models.PlayerState;
import tubes.models.Potion;
import tubes.models.Skill;
import tubes.models.User;
import tubes.models.Weapon;
import tubes.models.enums.BuffType;
import tubes.models.enums.Element;
import tubes.models.enums.EnemyType;
import tubes.models.enums.ItemType;
import tubes.models.enums.PlayerAttackType;
import tubes.models.enums.PotionType;
import tubes.models.enums.Rarity;
import tubes.repositories.BossRepo;
import tubes.repositories.BuffRepo;
import tubes.repositories.DifficultyRepo;
import tubes.repositories.EnemyRepo;
import tubes.repositories.PlayerStateRepo;
import tubes.repositories.PotionRepo;
import tubes.repositories.SkillRepo;
import tubes.repositories.WeaponRepo;
import tubes.util.Dialog;
import tubes.views.GameView;

public class GameController {

    private PlayerStateRepo playerStateRepo = new PlayerStateRepo();
    private WeaponRepo weaponRepo = new WeaponRepo();
    private SkillRepo skillRepo = new SkillRepo();
    private GameView gameView = new GameView();
    private DifficultyRepo difficultyRepo = new DifficultyRepo();
    private PotionRepo potionRepo = new PotionRepo();
    private BuffRepo buffRepo = new BuffRepo();
    private EnemyRepo enemyRepo = new EnemyRepo();
    private BossRepo bossRepo = new BossRepo();

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
        this.playerStateRepo.delete(this.user.getUsername());

        // Pilih difficulty
        List<Difficulty> diffs = this.difficultyRepo.findAll();
        String[] diffNames = new String[diffs.size()];

        for(Difficulty d : diffs){
            diffNames[diffs.indexOf(d)] = d.getName();    
        }

        int chooseDiff = this.gameView.handleChooseDifficulty(diffNames);
        Difficulty dif = diffs.get(chooseDiff);

        // Pilih element
        Element element = Element.valueOf(this.gameView.handleChooseElement(Element.getAllNames()));
        
        Player player = new Player(this.user.getUsername(), dif.getBaseHp(), dif.getBaseHp(), dif.getBaseMana(), dif.getBaseMana(), dif.getBaseAttack(), dif.getBaseDefense(), element, 1);
        Skill defaultSkill = this.skillRepo.findDefaultByElement(element);

        player.setEquippedSkill(defaultSkill);
        player.setSkillCooldown(player.getEquippedSkill().getCooldown());

        saveGame(player, true);
        runGame(player);
    }

    public void saveGame(Player player, boolean isNew){
        PlayerState state = this.convertPlayerToPlayerState(player);
        if(isNew){
            this.playerStateRepo.insert(state);
        } else {
            this.playerStateRepo.update(state);
        }

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
            saveGame(player, false);
        }
    }

    // Reward

   public void getReward(Player player, String enemyType) {
        
        List<Item> rewardOptions = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            
            ItemType chosenType = getWeightedItemType();
            Rarity chosenRarity = getWeightedRarity(enemyType);
            Item rewardItem = fetchRandomItem(chosenType, chosenRarity);

            if (rewardItem == null) {
                rewardItem = fetchRandomItem(chosenType, Rarity.COMMON);
            }
            
            if (rewardItem != null) {
                rewardOptions.add(rewardItem);
            }
        }

        rewardOptions.add(null);

        String[] optionNames = new String[rewardOptions.size()];

        for(int i = 0; i < rewardOptions.size(); i++) {
            if (rewardOptions.get(i) == null) {
                optionNames[i] = "Tidak ambil reward";
                continue;
            }
            optionNames[i] = rewardOptions.get(i).getName() + " (" + rewardOptions.get(i).getRarity().toString() + ")";
        }

        int choice = gameView.handleShowReward(optionNames);
        Item chosenItem = rewardOptions.get(choice);
        applyReward(player, chosenItem);
    }

    private ItemType getWeightedItemType() {
        Random random = new Random();
        int chance = random.nextInt(100);
        
        if (chance < 40) { // 40% (0-39)
            return ItemType.POTION;
        } else if (chance < 80) { // 40% (40-79)
            return ItemType.BUFF;
        } else if (chance < 90) { // 10% (80-89)
            return ItemType.WEAPON;
        } else { // 10% (90-99)
            return ItemType.SKILL;
        }
    }

    private Rarity getWeightedRarity(String enemyType) {
        Random random = new Random();
        int chance = random.nextInt(100);
        
        if (enemyType.equals("boss")) {
            if (chance < 5) return Rarity.COMMON;     // 5%
            else if (chance < 35) return Rarity.UNCOMMON; // 30%
            else if (chance < 75) return Rarity.RARE;      // 40%
            else if (chance < 95) return Rarity.EPIC;      // 20%
            else return Rarity.LEGENDARY;                 // 5%
        } else {
            if (chance < 50) return Rarity.COMMON;     // 50%
            else if (chance < 75) return Rarity.UNCOMMON; // 25%
            else if (chance < 90) return Rarity.RARE;      // 15%
            else if (chance < 98) return Rarity.EPIC;      // 8%
            else return Rarity.LEGENDARY;                 // 2% 
        }
    }

    private Item fetchRandomItem(ItemType type, Rarity rarity) {
        switch (type) {
            case POTION:
                return new Potion(0, "Small Health Potion", Rarity.COMMON, null, 25);
            case BUFF:
                // return buffRepo.findRandomByRarity(rarity);                
                return new Buff(0, "Small Attack Boost", Rarity.COMMON, BuffType.ATTACK, 10); 

            case WEAPON:
                // return weaponRepo.findRandomByRarity(rarity);
                return new Weapon(1, "Fire Staff", Rarity.RARE, 10, 5, Element.FIRE);
            case SKILL:
                // return skillRepo.findRandomByRarity(rarity);
                return new Skill(6, "Shadow Ball", Rarity.COMMON, 10, 10, 3, Element.DARK);
            default:
                return null;
        }
    }

    private void applyReward(Player player, Item item) {
        
        if (item instanceof Potion) {
            Potion potion = (Potion) item;

            if(potion.getPotionType() == PotionType.HEALTH){
                player.setCurrentHp(Math.min(player.getHp(), player.getCurrentHp() + potion.getPotionEffect()));
            } else {
                player.setCurrentMana(Math.min(player.getMana(), player.getCurrentMana() + potion.getPotionEffect()));
            }
            
        } else if (item instanceof Buff) {
            Buff buff = (Buff) item;

            if(buff.getType() == BuffType.ATTACK){
                player.setAttack(player.getAttack() + buff.getMultiplier());
            } else if(buff.getType() == BuffType.DEFENSE){
                player.setDefense(player.getDefense() + buff.getMultiplier());
            } else if(buff.getType() == BuffType.HEALTH){
                player.setHp(player.getHp() + buff.getMultiplier());
                player.setCurrentHp(player.getCurrentHp() + buff.getMultiplier());
            } else if(buff.getType() == BuffType.MANA){
                player.setMana(player.getMana() + buff.getMultiplier());
                player.setCurrentMana(player.getCurrentMana() + buff.getMultiplier());
            }

        } else if (item instanceof Weapon) {
            Weapon newWeapon = (Weapon) item;
            player.equipWeapon(newWeapon);
        } else if (item instanceof Skill) {
            Skill newSkill = (Skill) item;
            player.equipSkill(newSkill);
        }
    }

    // Combat

    public List<Enemy> generate5EnemyAnd1Boss(){

        ArrayList<Enemy> shuffledEnemies = new ArrayList<>();
        List<Enemy> enemies = enemyRepo.findAll();
        List<Boss> bosses = bossRepo.findAll();

        for(int i = 0; i < 5; i++){
            Random rand = new Random();
            int randomIndex = rand.nextInt(enemies.size());
            shuffledEnemies.add(enemies.get(randomIndex));
            enemies.remove(randomIndex);
        }

        shuffledEnemies.add(bosses.get(new Random().nextInt(bosses.size())));

        Enemy b = shuffledEnemies.get(shuffledEnemies.size()-1);
        Boss boss = (Boss) b;
        boss.setSkill(skillRepo.findDefaultByElement(boss.getElement()));

        return shuffledEnemies;
    }

    public TurnResult handlePlayerAttack(Player player, Enemy enemy, String attackType){

        TurnResult result = null;

        if(player.getSkillCooldown() != 0){
            player.setSkillCooldown(player.getSkillCooldown() - 1);
        }

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
        int damage = (int)((aAttack - bDefense) * multiplier);
        return new TurnResult(damage, "attack with hand, dealt " + damage + "damage\n");
    }

    public TurnResult handleSkillAttack(int aAttack, int skillAttack, Element skillElement, int bDefense, Element bElement){
        double multiplier = isStrongerElement(skillElement, bElement) ? 1.2 : 0.8;
        int damage = (int)((aAttack - bDefense) * multiplier);
        return new TurnResult(damage, "attack with skill, dealt " + damage + "damage\n");
    }

    public TurnResult handleWeaponAttack(int aAttack, int weaponAttack, Element weaponElement, int bDefense, Element bElement){
        double multiplier = isStrongerElement(weaponElement, bElement) ? 1.2 : 0.8;
        int damage = (int)((aAttack - bDefense) * multiplier);
        return new TurnResult(damage, "attack with weapon, dealt " + damage + "damage\n");
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
