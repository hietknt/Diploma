package ru.diploma.algorithm.util;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.metric.Metric;

import java.util.List;

public class ClusterFinder {

    private Metric

    public List<Item> find(List<Item> data){
        for (int i = 0; i < data.size(); i++){

        }
    }

    private double calculateDistance(List<Double> itemCoordinate, List<Double> neuronCoordinate) {
        double distance = 0.0;
        for (int i = 0; i < itemCoordinate.size(); i++) {
            distance += Math.pow((itemCoordinate.get(i) - neuronCoordinate.get(i)), 2);
        }
        return Math.sqrt(distance);
    }
}
