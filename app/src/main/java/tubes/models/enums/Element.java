package tubes.models.enums;

public enum Element {
    FIRE("FIRE"),
    ICE("ICE"),
    EARTH("EARTH"),
    WIND("WIND"),
    LIGHT("LIGHT"),
    DARK("DARK"),
    LIGHTNING("LIGHTNING");

    private final String name;

    Element(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String[] getAllNames(){
        Element[] elements = Element.values();
        String[] names = new String[elements.length];
        for(int i=0; i<elements.length; i++){
            names[i] = elements[i].getName();
        }
        return names;
    }
}