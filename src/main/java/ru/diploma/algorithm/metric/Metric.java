package ru.diploma.algorithm.metric;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;

import java.util.List;

public interface Metric {
    Neuron findMinimumDistance(Item item, List<Neuron> neurons);
}
