package com.epam.multithreading.logic;

import com.epam.multithreading.entity.Taxi;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OptimalTaxiCalculatorTest {
    private static final List<Taxi> NO_FREE_TAXIS = Arrays.asList(
            new Taxi(1, 10, false),
            new Taxi(2, 11, false),
            new Taxi(3, 12, false),
            new Taxi(4, 13, false)
    );
    private static final List<Taxi> THREE_FREE_TAXIS = Arrays.asList(
            new Taxi(5, 14),
            new Taxi(6, 15),
            new Taxi(7, 16),
            new Taxi(8, 17, false)
    );
    private final OptimalTaxiCalculator calculator = new OptimalTaxiCalculator();

    @Test
    public void findFreeShouldReturnEmptyListWhenNoFreeTaxis() {
        List<Taxi> freeTaxis = calculator.findFree(NO_FREE_TAXIS);
        List<Taxi> expectedList = Collections.emptyList();

        Assert.assertEquals(freeTaxis, expectedList);
    }

    @Test
    public void findFreeShouldReturnOnlyFreeTaxis() {
        List<Taxi> freeTaxis = calculator.findFree(THREE_FREE_TAXIS);
        List<Taxi> expectedList = Arrays.asList(
                new Taxi(5, 14),
                new Taxi(6, 15),
                new Taxi(7, 16));

        Assert.assertEquals(freeTaxis, expectedList);
    }

    @Test
    public void calculateOptimalShouldReturnClosestBusyTaxi() {
        int destination = 18;
        Taxi optimalTaxi = calculator.calculateOptimal(THREE_FREE_TAXIS, destination);
        Taxi expectedTaxi = THREE_FREE_TAXIS.get(3);

        Assert.assertEquals(optimalTaxi, expectedTaxi);
    }
}