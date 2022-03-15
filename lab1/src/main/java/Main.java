import by.vsu.lab1.domain.*;
import comparators.FlyDistanceComparator;
import filters.FuelFilter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PassengerPlane airbus220 = new PassengerPlane(Type.Passenger, 1200, 100);
        PassengerPlane comaC919 = new PassengerPlane(Type.Passenger, 1400, 120);
        CargoPlane boeing747 = new CargoPlane(Type.Cargo, 1300, 30_000);
        List<Plane> vtbPlanes = new ArrayList<>();
        vtbPlanes.add(comaC919);
        vtbPlanes.add(boeing747);
        vtbPlanes.add(airbus220);
        for (Plane p : vtbPlanes) {
            System.out.println(p);
        }
        MyAirway vtb = new MyAirway(vtbPlanes);
        vtb.sort(new FlyDistanceComparator());
        System.out.println("---------- sorting result -------------");
        for (Plane p : vtb.getAllPlanes()) {
            System.out.println(p);
        }
        List<Plane> filteredByFlyDistancePlanes = vtb.find(new FuelFilter(1250, 1500));
        System.out.println("--------- filter result --------------");
        for (Plane p : filteredByFlyDistancePlanes) {
            System.out.println(p);
        }
    }
}
