package tubes.models;

import tubes.models.enums.Element;
import tubes.models.enums.Rarity;

public class Weapon extends Item {
    private int attack;
    private int mana;
    private Element element;

    public Weapon(int id, String name, Rarity rarity, int attack, int mana, Element element) {
        super(id, name, rarity);
        this.attack = attack;
        this.mana = mana;
        this.element = element;
    }

    public int getWeaponId() {
        return super.getId();
    }

    public void setWeaponId(int id) {
        super.setId(id);
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
}