package by.vsu.lab1.domain;

public abstract class Plane {
    protected int fuel;
    protected Type type;

    public Plane(Type type, int fuel) {
        this.type = type;
        this.fuel = fuel;
    }

    public abstract int getLoadCapacity();

    public abstract int getFlyDistance();

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}

