package tubes.models;

import tubes.models.enums.Element;

public class Player extends Creature {
    private String username;
    private Weapon equippedWeapon;
    private Skill equippedSkill;
    private int stage;
    private int skillCooldown;
    
    public Player(String username, int hp, int currentHp, int mana, int currentMana, int attack, int defense, Element element, int stage) {
        super(hp, currentHp, mana, currentMana, attack, defense, element);
        this.username = username;
        this. stage = stage;
    }
    
    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }
    
    public void equipSkill(Skill skill) {
        this.equippedSkill = skill;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Skill getEquippedSkill() {
        return equippedSkill;
    }

    public void setEquippedSkill(Skill equippedSkill) {
        this.equippedSkill = equippedSkill;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage){
        this.stage = stage;
    }

    public int getSkillCooldown(){
        return this.skillCooldown;
    }

    public void setSkillCooldown(int skillCooldown){
        this.skillCooldown = skillCooldown;
    }
}