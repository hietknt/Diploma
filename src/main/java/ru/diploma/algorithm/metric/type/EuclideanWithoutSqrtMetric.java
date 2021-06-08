package ru.diploma.algorithm.metric.type;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.MetricType;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;

import java.util.List;

public class EuclideanWithoutSqrtMetric implements Metric {
    @Override
    public Neuron findMinimumDistance(Item item, List<Neuron> neurons) {
        List<Double> neuronCoordinates;
        List<Double> itemCoordinates = item.getCoordinates();

        double minDistance = 999999999.9;
        Neuron minDistancesNeuron = null;

        for (Neuron neuron : neurons) {
            neuronCoordinates = neuron.getCoordinates();

            double distance = calculateDistance(itemCoordinates, neuronCoordinates);
            if (distance < minDistance) {
                minDistance = distance;
                minDistancesNeuron = neuron;
            }
        }

        return minDistancesNeuron;
    }

    private double calculateDistance(List<Double> itemCoordinate, List<Double> neuronCoordinate) {
        double distance = 0.0;
        for (int i = 0; i < itemCoordinate.size(); i++) {
            distance += Math.pow((itemCoordinate.get(i) - neuronCoordinate.get(i)), 2);
        }
        return distance;
    }

    public Neuron findMinimumDistance(Item item, List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons) {
        return null;
    }

    public MetricType getMetricType() {
        return MetricType.EUCLIDEAN_WITHOUT_SQRT;
    }
}