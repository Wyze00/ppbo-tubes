package tubes.models;

import tubes.models.enums.Element;
import tubes.models.enums.Rarity;

public class Weapon {
    private int id;
    private String name;
    private int attack;
    private int mana;
    private Element element;
    private Rarity rarity;

    public int getWeaponId() {
        return id;
    }

    public void setWeaponId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}