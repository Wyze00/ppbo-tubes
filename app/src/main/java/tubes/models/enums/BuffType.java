package tubes.models.enums;

public enum BuffType {
    ATTACK("ATTACK"),
    DEFENSE("DEFENSE"),
    HEALTH("HEALTH"),
    MANA("MANA");

    private final String name;

    BuffType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}