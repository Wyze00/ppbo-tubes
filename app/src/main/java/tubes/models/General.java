package tubes.models;

public class General {

    enum Element {
        FIRE,
        ICE,
        EARTH,
        WIND,
        LIGHT,
        DARK,
        LIGHTNING,
    }

    enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        EPIC,
        LEGENDARY,
    }

    enum BuffType {
        ATTACK,
        DEFENSE,
        HEALTH,
        MANA,
    }

    enum UserRole {
        PLAYER,
        ADMIN
    }

    enum EnemyType {
        Goblin,
        Skeleton,
        Orc,
        Troll,
        Slime
    }

    class User {
        private int id;
        private String username;
        private String password; 
        private UserRole role;
    }

    class Weapon {
        private int id;
        private String name;
        private int attack;
        private int mana;
        private Element element;
        private Rarity rarity;
    }

    class Skill {
        private int id;
        private String name;
        private int attack;
        private int manaCost; 
        private int cooldown;
        private Element element;
        private Rarity rarity;
    }

    class Buff {
        private int id;
        private String name;
        private BuffType type;
        private int multiplier;
        private Rarity rarity;
    }

    class Difficulty {
        private int id;
        private String name;
        private int baseHp;
        private int baseMana;
        private int baseAttack;
        private int baseDefense;
    }


    abstract class Creature {
        private int hp;
        private int currentHp;
        private int mana;
        private int currentMana;
        private int attack;
        private int defense;
        private Element element;
        
        Creature(int hp, int mana, int attack, int defense, Element element) {
            this.hp = hp;
            this.currentHp = hp;
            this.mana = mana;
            this.currentMana = mana;
            this.attack = attack;
            this.defense = defense;
            this.element = element;
        }
        
        void takeDamage(int damage) {
            int actualDamage = Math.max(0, damage - this.defense);
            this.currentHp = Math.max(0, this.currentHp - actualDamage);
        }
        
        void heal(int amount) {
            this.currentHp = Math.min(this.hp, this.currentHp + amount);
        }
        
        void restoreMana(int amount) {
            this.currentMana = Math.min(this.mana, this.currentMana + amount);
        }
        
        boolean isAlive() {
            return this.currentHp > 0;
        }
        
        int getCurrentHp() { return currentHp; }
        int getHp() { return hp; }
    }

    class Player extends Creature {
        private String username;
        private Weapon equippedWeapon;
        private Skill equippedSkill;
        
        Player(String username, int hp, int mana, int attack, int defense, Element element) {
            super(hp, mana, attack, defense, element);
            this.username = username;
        }
        
        void equipWeapon(Weapon weapon) {
            this.equippedWeapon = weapon;
        }
        
        void equipSkill(Skill skill) {
            this.equippedSkill = skill;
        }
        
        Weapon getEquippedWeapon() { return equippedWeapon; }
        Skill getEquippedSkill() { return equippedSkill; }
    }

    class Enemy extends Creature {
        private int id;
        private String name;
        private EnemyType type;
        
        Enemy(int id, String name, EnemyType type, int hp, int mana, int attack, int defense, Element element) {
            super(hp, mana, attack, defense, element);
            this.id = id;
            this.name = name;
            this.type = type;
        }
        
        String getName() { return name; }
    }

    class Boss extends Enemy {
        private Skill skill;

        Boss(int id, String name, int hp, int mana, int attack, int defense, Element element, Skill skill) {
            super(id, name, EnemyType.Troll, hp, mana, attack, defense, element); // Asumsi Boss punya tipe sendiri
            this.skill = skill;
        }
        
        Skill getSkill() { return skill; }
    }

    class PlayerState {
        private String username;
        private int hp;
        private int currentHp;
        private int mana;
        private int currentMana;
        private int defense;
        private int attack;
        private Element element;
        private int difficultyId;
        private int currentStage;
        private int weaponId; 
        private int skillId;
    }
}