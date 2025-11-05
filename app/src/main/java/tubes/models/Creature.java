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
    
    public Creature(int hp, int currentHp, int mana, int currentMana, int attack, int defense, Element element) {
        this.hp = hp;
        this.currentHp = currentHp;
        this.mana = mana;
        this.currentMana = currentMana;
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
    
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (this.currentHp > hp) {
            this.currentHp = hp;
        }
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = Math.max(0, Math.min(this.hp, currentHp));
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
        if (this.currentMana > mana) {
            this.currentMana = mana;
        }
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = Math.max(0, Math.min(this.mana, currentMana));
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}