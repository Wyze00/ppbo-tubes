package tubes.models;

import tubes.models.enums.Rarity;

public abstract class Item {
    private int id;
    private String name;
    private Rarity rarity;

    public Item(int id, String name, Rarity rarity){
        this.id = id;
        this.name = name;
        this.rarity = rarity;
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

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}
