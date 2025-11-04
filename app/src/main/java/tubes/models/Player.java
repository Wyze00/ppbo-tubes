package tubes.models;

import tubes.models.enums.Element;

public class Player extends Creature {
    private String username;
    private Weapon equippedWeapon;
    private Skill equippedSkill;
    
    public Player(String username, int hp, int mana, int attack, int defense, Element element) {
        super(hp, mana, attack, defense, element);
        this.username = username;
    }
    
    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }
    
    public void equipSkill(Skill skill) {
        this.equippedSkill = skill;
    }
    
    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public Skill getEquippedSkill() { return equippedSkill; }
}