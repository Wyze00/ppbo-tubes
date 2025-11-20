package tubes.models;

import tubes.models.enums.Element;
import tubes.models.enums.Rarity;

public class Skill extends Item {
    private int attack;
    private int manaCost; 
    private int cooldown;
    private Element element;

    public Skill(int id, String name, Rarity rarity, int attack, int manaCost, int cooldown, Element element) {
        super(id, name, rarity);
        this.attack = attack;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.element = element;
    }

    public int getSkillId() {
        return super.getId();
    }

    public void setSkillId(int id) {
        super.setId(id);
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Skill ID: ").append(this.getSkillId()).append("\n");
        str.append("Skill Name: ").append(this.getName()).append("\n");
        str.append("Rarity: ").append(this.getRarity().toString()).append("\n");
        str.append("Attack: ").append(this.getAttack()).append("\n");
        str.append("Mana Cost: ").append(this.getManaCost()).append("\n");
        str.append("Cooldown: ").append(this.getCooldown()).append("\n");
        str.append("Element: ").append(this.getElement().toString()).append("\n");
        return str.toString();
    }
}