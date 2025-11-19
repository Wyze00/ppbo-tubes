package tubes.models.enums;

public enum Rarity {
    COMMON("COMMON"),
    UNCOMMON("UNCOMMON"),
    RARE("RARE"),
    EPIC("EPIC"),
    LEGENDARY("LEGENDARY");

    private final String name;

    Rarity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}