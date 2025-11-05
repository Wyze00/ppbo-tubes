package tubes.models;

import tubes.models.enums.Element;
import tubes.models.enums.EnemyType;

public class Enemy extends Creature {
    private int id;
    private String name;
    private EnemyType type;
    
    public Enemy(int id, String name, EnemyType type, int hp, int mana, int attack, int defense, Element element) {
        super(hp, hp, mana, mana, attack, defense, element);
        this.id = id;
        this.name = name;
        this.type = type;
    }
    
    public String getName() { return name; }
}