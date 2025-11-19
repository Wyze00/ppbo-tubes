package tubes.models.enums;

public enum PotionType {
    HEALTH("HEALTH"),
    MANA("MANA");

    private final String name;

    PotionType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
