package ru.diploma.algorithm.training_algorithms;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;

import java.util.List;

public interface TrainingAlgorithm {

    void setParams(
            List<Item> items,
            List<List<Double>> notNormalizedItemsCoordinates,
            List<Neuron> neurons,
            double lambda,
            double step,
            int repeatCount,
            Metric metric
    );

    void startLearning();

    List<Neuron> getNeurons();

    List<Item> getItems();
}
