package by.vsu.hotel.domain;

public enum ApartmentType {
    USUAL("Обычный"),
    LUX("Люкс");

    private final String name;

    ApartmentType(String name) {
        this.name = name;
    }

    public Integer getId() {
        return ordinal();
    }

    public String getName() {
        return name;
    }
}
