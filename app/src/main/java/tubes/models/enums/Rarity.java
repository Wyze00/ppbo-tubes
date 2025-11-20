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

    public static String[] getAllNames(){
        Rarity[] rarities = Rarity.values();
        String[] names = new String[rarities.length];
        for(int i = 0; i < rarities.length; i++){
            names[i] = rarities[i].getName();
        }
        return names;
    }
}