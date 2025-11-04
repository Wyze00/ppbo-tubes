package tubes.models;

import tubes.models.enums.Element;
import tubes.models.enums.Rarity;

public class Skill {
    private int id;
    private String name;
    private int attack;
    private int manaCost; 
    private int cooldown;
    private Element element;
    private Rarity rarity;
}