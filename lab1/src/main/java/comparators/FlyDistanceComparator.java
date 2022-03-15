package comparators;

import java.util.Comparator;
import by.vsu.lab1.domain.*;

public class FlyDistanceComparator implements Comparator<Plane> {
    @Override
    public int compare(Plane o1, Plane o2) {
        return (o1.getFlyDistance() - o2.getFlyDistance());
    }
}
