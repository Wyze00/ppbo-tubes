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
    
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public EnemyType getType() { 
        return type; 
    }

    public void setType(EnemyType type) { 
        this.type = type; 
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Enemy Name: ").append(this.getName()).append("\n");
        str.append("Type: ").append(this.getType().toString()).append("\n");
        str.append("HP: ").append(this.getCurrentHp()).append("/").append(this.getHp()).append("\n");
        str.append("Mana: ").append(this.getCurrentMana()).append("/").append(this.getMana()).append("\n");
        str.append("Attack: ").append(this.getAttack()).append("\n");
        str.append("Defense: ").append(this.getDefense()).append("\n");
        str.append("Element: ").append(this.getElement().getName()).append("\n");
        return str.toString();
    }
}