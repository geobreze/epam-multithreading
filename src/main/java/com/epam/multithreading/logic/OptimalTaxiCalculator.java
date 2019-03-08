package com.epam.multithreading.logic;

import com.epam.multithreading.entity.Taxi;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OptimalTaxiCalculator {
    public OptimalTaxiCalculator() {
    }

    public List<Taxi> findFree(List<Taxi> taxis) {
        List<Taxi> freeTaxis = taxis
                .stream()
                .filter(Taxi::isFree)
                .collect(Collectors.toList());
        return freeTaxis;
    }

    public Taxi calculateOptimal(List<Taxi> taxis, int destination) {
        Comparator<Taxi> shortestDistanceComparator = Comparator.
                comparing(taxi -> countDistance(taxi.getCoordinate(), destination));

        return Collections.min(taxis, shortestDistanceComparator);
    }

    private int countDistance(int x, int y) {
        return Math.abs(x - y);
    }
}
