package ru.diploma.algorithm.metric;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;

import java.util.List;

public interface Metric {
    Neuron findMinimumDistance(Item item, List<Neuron> neurons);
    Neuron findMinimumDistance(Item item, List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons);

    Neuron findMaximumDistance(Item item, List<Neuron> neurons);
    Neuron findMaximumDistance(Item item, List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons);

    Neuron findMaximumDistanceBetweenItemsAndClusters(List<Item> items, List<Neuron> neurons);
    Neuron findMaximumDistanceBetweenItemsAndClusters(List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons);

    MetricType getMetricType();
}
