package by.vsu.hotel.domain;

public enum Role {
    ADMIN("Администратор"),
    CLIENT("Клиент");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return ordinal();
    }

    public String getName() {
        return name;
    }
}
