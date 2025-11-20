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

    public static String[] getAllNames() {
        BuffType[] types = BuffType.values();
        String[] names = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            names[i] = types[i].name();
        }
        return names;
    }
}