package tubes.models;

import tubes.models.enums.Element;
import tubes.models.enums.EnemyType;

public class Boss extends Enemy {
    private Skill skill;

    public Boss(int id, String name, int hp, int mana, int attack, int defense, Element element, Skill skill) {
        super(id, name, EnemyType.Troll, hp, mana, attack, defense, element);
        this.skill = skill;
    }
    
    public Skill getSkill() { return skill; }
}