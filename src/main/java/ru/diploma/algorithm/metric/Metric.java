package ru.diploma.algorithm.metric;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;

import java.util.List;

public interface Metric {
    List<Item> findMinimumDistance(List<Item> items, List<Neuron> neurons);
}
