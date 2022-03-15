package filters;


import by.vsu.lab1.domain.*;

public class FuelFilter implements filters.Filter {
    private int minFuel;
    private int maxFuel;

    public FuelFilter(int minFuel, int maxFuel) {
        this.minFuel = minFuel;
        this.maxFuel = maxFuel;
    }

    public int getMinFuel() {
        return minFuel;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMinFuel(int minFuel) {
        this.minFuel = minFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    @Override
    public boolean check(Plane plane) {
        return (minFuel <= plane.getFuel()) && (plane.getFuel() <= maxFuel);
    }

    @Override
    public String toString() {
        return "FuelFilter{" +
                "minFuel=" + minFuel +
                ", maxFuel=" + maxFuel +
                '}';
    }
}
