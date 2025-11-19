package tubes.models.enums;

public enum ItemType {
    POTION("POTION"),
    BUFF("BUFF"),
    WEAPON("WEAPON"),
    SKILL("SKILL");

    private final String name;

    ItemType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
