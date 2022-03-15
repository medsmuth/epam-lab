package by.vsu.lab1.domain;

public class PassengerPlane extends Plane {
    int passengerCapacity;

    public PassengerPlane(Type type, int fuel, int passengerCapacity) {
        super(type, fuel);
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public int getLoadCapacity() {
        return passengerCapacity * 80;
    }

    @Override
    public int getFlyDistance() {
        return (int) (0.75 * 1000 * fuel / 8.125);
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public String toString() {
        return "PassengerPlane{" +
                "passengerCapacity=" + passengerCapacity +
                ", fuel=" + fuel +
                ", type=" + type +
                ", loadCapacity=" + getLoadCapacity() +
                ", flyDistance=" + getFlyDistance() +
                '}';
    }
}

