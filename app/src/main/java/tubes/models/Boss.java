package tubes.models;

import tubes.models.enums.Element;
import tubes.models.enums.EnemyType;

public class Boss extends Enemy {
    private int skillId;
    private Skill skill;
    private int skillCooldown;
    

    public Boss(int id, String name, EnemyType type, int hp, int mana, int attack, int defense, Element element, int skillId) {
        super(id, name, type, hp, mana, attack, defense, element);
        this.skillId = skillId;
    }
    
    public int getSkillId(){
        return this.skillId;
    }

    public void setSkill(Skill skill){
        this.skill = skill;
    }

    public Skill getSkill() { return skill; }

    public int getSkillCooldown(){
        return this.skillCooldown;
    }

    public void setSkillCooldown(int skillCooldown){
        this.skillCooldown = skillCooldown;
    }
}