package tubes.models.enums;

import tubes.models.Player;

public enum PlayerAttackType {
    WEAPON("WEAPON"),
    SKILL("SKILL"),
    HAND("HAND");

    private final String name;

    PlayerAttackType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String[] getAllNames() {
        PlayerAttackType[] types = PlayerAttackType.values();
        String[] names = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            names[i] = types[i].getName();
        }
        return names;
    }
}
