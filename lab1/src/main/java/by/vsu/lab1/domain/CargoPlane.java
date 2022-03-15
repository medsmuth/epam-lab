package by.vsu.lab1.domain;

public class CargoPlane extends Plane {
    private int cargoCapacity;

    public CargoPlane(Type type, int fuel, int cargoCapacity) {
        super(type, fuel);
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public int getLoadCapacity() {
        return cargoCapacity;
    }

    @Override
    public int getFlyDistance() {
        return (int) (1000 * fuel / 8.125);
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String toString() {
        return "CargoPlane{" +
                "cargoCapacity=" + cargoCapacity +
                ", fuel=" + fuel +
                ", type=" + type +
                ", loadCapacity=" + getLoadCapacity() +
                ", flyDistance=" + getFlyDistance() +
                '}';
    }
}