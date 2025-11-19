package tubes.models.enums;

public enum EnemyType {
    GOBLIN("GOBLIN"),
    SKELETON("SKELETON"),
    ORC("ORC"),
    TROLL("TROLL"),
    SLIME("SLIME");

    private final String name;

    EnemyType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}