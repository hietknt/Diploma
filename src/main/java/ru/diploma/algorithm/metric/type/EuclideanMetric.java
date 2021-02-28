package ru.diploma.algorithm.metric.type;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;

import java.util.ArrayList;
import java.util.List;

public class EuclideanMetric implements Metric {
    @Override
    public List<Item> findMinimumDistance(List<Item> items, List<Neuron> neurons) {
        List<Item> minDistancesItems = new ArrayList<>();

        double minDistance;
        Item minDistancesItem;
        for (Neuron neuron : neurons){
            List<Double> neuronCoordinates = neuron.getCoordinates();
            minDistance = 999999999.9;
            minDistancesItem = null;

            for (Item item : items){
                List<Double> itemCoordinates = item.getCoordinates();

                double distance = calculateDistance(itemCoordinates, neuronCoordinates);
                System.out.println(distance);
                if (distance < minDistance){
                    minDistance = distance;
                    minDistancesItem = item;
                }

            }

            minDistancesItems.add(minDistancesItem);
        }
        return minDistancesItems;
    }

    private double calculateDistance(List<Double> itemCoordinate, List<Double> neuronCoordinate){
        double distance = 0.0;
        for (int i = 0; i < itemCoordinate.size(); i++){
            distance += Math.pow((itemCoordinate.get(i) - neuronCoordinate.get(i)), 2);
        }
        return Math.sqrt(distance);
    }
}
