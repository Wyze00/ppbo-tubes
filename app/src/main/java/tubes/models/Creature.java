package tubes.models;

import tubes.models.enums.Element;

public abstract class Creature {
    private int hp;
    private int currentHp;
    private int mana;
    private int currentMana;
    private int attack;
    private int defense;
    private Element element;
    
    public Creature(int hp, int mana, int attack, int defense, Element element) {
        this.hp = hp;
        this.currentHp = hp;
        this.mana = mana;
        this.currentMana = mana;
        this.attack = attack;
        this.defense = defense;
        this.element = element;
    }
    
    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - this.defense);
        this.currentHp = Math.max(0, this.currentHp - actualDamage);
    }
    
    public void heal(int amount) {
        this.currentHp = Math.min(this.hp, this.currentHp + amount);
    }
    
    public void restoreMana(int amount) {
        this.currentMana = Math.min(this.mana, this.currentMana + amount);
    }
    
    public boolean isAlive() {
        return this.currentHp > 0;
    }
    
    public int getCurrentHp() { return currentHp; }
    public int getHp() { return hp; }
}