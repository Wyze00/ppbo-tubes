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

    public static String[] getAllNames() {
        EnemyType[] types = EnemyType.values();
        String[] names = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            names[i] = types[i].getName();
        }
        return names;
    }
}