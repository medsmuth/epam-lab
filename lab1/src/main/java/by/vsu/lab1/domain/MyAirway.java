package by.vsu.lab1.domain;

import filters.Filter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyAirway {
    private String name;
    private List<Plane> allPlanes;

    public List<Plane> getAllPlanes() {
        return allPlanes;
    }

    public void setAllPlanes(List<Plane> allPlanes) {
        this.allPlanes = allPlanes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyAirway(List<Plane> allPlanes) {
        this.allPlanes = new ArrayList<>(allPlanes);
    }

    public List<Plane> find(Filter filter) {
        List<Plane> list = new ArrayList<>();
        for (Plane plane : allPlanes) {
            if (filter.check(plane)) {
                list.add(plane);
            }
        }
        return list;
    }

    public void sort(Comparator<Plane> comparator) {
        getAllPlanes().sort(comparator);
    }
}
