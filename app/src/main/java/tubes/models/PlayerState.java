package tubes.models;

import tubes.models.enums.Element;

public class PlayerState {
    private String username;
    private int hp;
    private int currentHp;
    private int mana;
    private int currentMana;
    private int defense;
    private int attack;
    private Element element;
    private int currentStage;
    private int weaponId; 
    private int skillId;

    public PlayerState(String username, int hp, int currentHp, int mana, int currentMana,
                       int defense, int attack, Element element, int currentStage,
                       int weaponId, int skillId) {
        this.username = username;
        this.hp = hp;
        this.currentHp = currentHp;
        this.mana = mana;
        this.currentMana = currentMana;
        this.defense = defense;
        this.attack = attack;
        this.element = element;
        this.currentStage = currentStage;
        this.weaponId = weaponId;
        this.skillId = skillId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
}