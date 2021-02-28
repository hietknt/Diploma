package ru.diploma.algorithm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCoordinatesGenerator {

    Random random = new Random();

    public List<Double> generate(int parameterCount, double lowLimit, double highLimit){

        List<Double> coordinates = new ArrayList<>();

        for (int i = 0; i < parameterCount; i++){
            double value = lowLimit + (highLimit - lowLimit) * random.nextDouble();
            coordinates.add(value);
        }

        return coordinates;
    }
}
