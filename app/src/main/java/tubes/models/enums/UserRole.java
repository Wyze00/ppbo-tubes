package tubes.models.enums;

public enum UserRole {
    PLAYER("PLAYER"),
    ADMIN("ADMIN");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}