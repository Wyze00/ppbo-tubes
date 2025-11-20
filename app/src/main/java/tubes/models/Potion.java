package tubes.models;

import tubes.models.enums.PotionType;
import tubes.models.enums.Rarity;

public class Potion extends Item {
    PotionType potionType;
    int potionEffect;

    public Potion(int id, String name, Rarity rarity, PotionType potionType, int potionEffect) {
        super(id, name, rarity);
        this.potionType = potionType;
        this.potionEffect = potionEffect;
    }

    public int getPotionId() {
        return super.getId();
    }

    public void setPotionId(int id) {
        super.setId(id);
    }

    public PotionType getPotionType() {
        return potionType;
    }

    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
    }

    public int getPotionEffect() {
        return potionEffect;
    }

    public void setPotionEffect(int potionEffect) {
        this.potionEffect = potionEffect;
    }

    public String toString(){
        return "Potion ID: " + super.getId() +
               "\nName: " + super.getName() +
               "\nRarity: " + super.getRarity().name() +
               "\nType: " + potionType.getName() +
               "\nEffect: " + potionEffect + "\n";
    }
}
