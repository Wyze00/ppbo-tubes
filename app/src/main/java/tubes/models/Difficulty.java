package tubes.models;

public class Difficulty {
    private int id;
    private String name;
    private int baseHp;
    private int baseMana;
    private int baseAttack;
    private int baseDefense;

    public Difficulty(int id, String name, int baseHp, int baseMana, int baseAttack, int baseDefense) {
        this.id = id;
        this.name = name;
        this.baseHp = baseHp;
        this.baseMana = baseMana;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
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

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public int getBaseMana() {
        return baseMana;
    }

    public void setBaseMana(int baseMana) {
        this.baseMana = baseMana;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public String toString() {
        return "Difficulty ID: " + id +
               "\nName: " + name +
               "\nBase HP: " + baseHp +
               "\nBase Mana: " + baseMana +
               "\nBase Attack: " + baseAttack +
               "\nBase Defense: " + baseDefense + "\n";
    }
}