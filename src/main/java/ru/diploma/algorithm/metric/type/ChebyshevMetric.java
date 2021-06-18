package ru.diploma.algorithm.metric.type;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.metric.MetricType;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;

import java.util.ArrayList;
import java.util.List;

public class ChebyshevMetric implements Metric {
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

    @Override
    public Neuron findMaximumDistance(Item item, List<Neuron> neurons) {
        List<Double> neuronCoordinates;
        List<Double> itemCoordinates = item.getCoordinates();

        double maxDistance = 0.0;
        Neuron maxDistancesNeuron = null;

        for (Neuron neuron : neurons) {
            neuronCoordinates = neuron.getCoordinates();

            double distance = calculateDistance(itemCoordinates, neuronCoordinates);
            if (distance > maxDistance) {
                maxDistance = distance;
                maxDistancesNeuron = neuron;
            }
        }

        return maxDistancesNeuron;
    }

    @Override
    public Neuron findMaximumDistanceBetweenItemsAndClusters(List<Item> items, List<Neuron> neurons) {
        List<List<Double>> distances = new ArrayList<>();
        for (Item item : items) {
            List<Double> distancesBetweenItemAndNeurons = new ArrayList<>();
            for (Neuron neuron : neurons) {
                distancesBetweenItemAndNeurons.add(calculateDistance(item.getCoordinates(), neuron.getCoordinates()));
            }
            distances.add(distancesBetweenItemAndNeurons);
        }

        List<Double> sums = new ArrayList<>();
        double sum;
        for (int i = 0; i < distances.get(0).size(); i++) {
            sum = 0.0;
            for (int j = 0; j < distances.size(); j++) {
                sum += distances.get(j).get(i);
            }
            sums.add(sum);
        }

        double maxDistance = sums.get(0);
        int neuronIndex = 0;
        for (int i = 1; i < sums.size(); i++) {
            if (sums.get(i) > maxDistance) {
                maxDistance = sums.get(i);
                neuronIndex = i;
            }
        }

        return neurons.get(neuronIndex);
    }

    @Override
    public Neuron findMinimalDistanceBetweenItemsAndClusters(List<Item> items, List<Neuron> neurons) {
        List<List<Double>> distances = new ArrayList<>();
        for (Item item : items) {
            List<Double> distancesBetweenItemAndNeurons = new ArrayList<>();
            for (Neuron neuron : neurons) {
                distancesBetweenItemAndNeurons.add(calculateDistance(item.getCoordinates(), neuron.getCoordinates()));
            }
            distances.add(distancesBetweenItemAndNeurons);
        }

        List<Double> sums = new ArrayList<>();
        double sum;
        for (int i = 0; i < distances.get(0).size(); i++) {
            sum = 0.0;
            for (int j = 0; j < distances.size(); j++) {
                sum += distances.get(j).get(i);
            }
            sums.add(sum);
        }

        double minDistance = sums.get(0);
        int neuronIndex = 0;
        for (int i = 1; i < sums.size(); i++) {
            if (sums.get(i) < minDistance) {
                minDistance = sums.get(i);
                neuronIndex = i;
            }
        }

        return neurons.get(neuronIndex);
    }

    private double calculateDistance(List<Double> itemCoordinate, List<Double> neuronCoordinate) {
        double distance = 0.0;
        double maxDistance = 0.0;
        for (int i = 0; i < itemCoordinate.size(); i++) {
            distance = Math.abs((itemCoordinate.get(i) - neuronCoordinate.get(i)));
            if (maxDistance < distance) {
                maxDistance = distance;
            }
        }
        return maxDistance;
    }

    public Neuron findMinimumDistance(Item item, List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons) {
        return null;
    }

    @Override
    public Neuron findMaximumDistance(Item item, List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons) {
        return null;
    }

    @Override
    public Neuron findMaximumDistanceBetweenItemsAndClusters(List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons) {
        return null;
    }

    @Override
    public Neuron findMinimalDistanceBetweenItemsAndClusters(List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons) {
        return null;
    }

    public MetricType getMetricType() {
        return MetricType.CHEBYSHEV;
    }
}
