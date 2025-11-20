package tubes.models;

import tubes.models.enums.BuffType;
import tubes.models.enums.Rarity;

public class Buff extends Item {
    private BuffType type;
    private int multiplier;

    public Buff(int id, String name, Rarity rarity, BuffType type, int multiplier) {
        super(id, name, rarity);
        this.type = type;
        this.multiplier = multiplier;
    }

    public BuffType getType() {
        return type;
    }

    public void setType(BuffType type) {
        this.type = type;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public String toString(){
        return "Buff ID: " + super.getId() +
               "\nName: " + super.getName() +
               "\nRarity: " + super.getRarity().name() +
               "\nType: " + type.name() +
               "\nMultiplier: " + multiplier + "%\n";
    }
}