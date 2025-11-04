package tubes;

import java.util.ArrayList;
import java.util.List;

public class App {

  enum Element {
    FIRE,
    ICE,
    EARTH,
    WIND,
    LIGHT,
    DARK,
    LIGHTNING,
  }

  class Inventory {
    List<Potion> potions = new ArrayList<Potion>();

    public void addItem(Potion potion) {
      potions.add(potion);
    }

    public void removeItem(Potion potion) {
      potions.remove(potion);
    }

    public List<Potion> getItems() {
      return potions;
    }
  }

  enum ItemType {
    HealthPotion,
    ManaPotion,
  }

  enum BuffType {
    ATTACK,
    DEFENSE,
    HEALTH,
    MANA,
  }

  enum Grade {
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    LEGENDARY,
  }

  class Potion {
    private int id;
    private String potionName;
    private ItemType potionType;
    private int potionEffect;
    private Grade potionGrade;
  }

  class Weapon {
    private int id;
    private String weaponName;
    private int weaponAttack;
    private Element element;
    private int weaponMana;
    private Grade weaponGrade;
  }

  class Skill {
    private int id;
    private String skillName;
    private int skillTurn;
    private int skillAttack; // Character attack + Skill attack * multiplier;
    private int skillMultiplier; // 10 = 1.1x;
    private Element element;
  }

  abstract class Creature {
    private String name;
    private int healthPoint;
    private int currentHealthPoint;
    private int attack;
    private int defense;
    private int mana;
    private int currentMana;
    private Element element;
  }

  class Player extends Creature {
    private Inventory inventory;
    private Weapon weapon;
    private Skill skill;
  }

  class Enemy extends Creature {
    private int id;
    private EnemyType type;
  }

  class Boss extends Enemy {
    private Skill skill;
  }

  enum EnemyType {
    Goblin,
    Skeleton,
    Orc,
    Troll,
  }

  class Buff {
    private int id;
    private BuffType buffType;
    private int buffMultiplier;
    private Grade grade;
  }

  class Stage {
    private int stageNumber;
    private List<Enemy> enemies;
    private Boss boss;
  }
}