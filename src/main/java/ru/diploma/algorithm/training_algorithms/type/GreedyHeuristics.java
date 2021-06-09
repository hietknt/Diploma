package ru.diploma.algorithm.training_algorithms.type;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;
import ru.diploma.algorithm.training_algorithms.TrainingAlgorithm;
import ru.diploma.algorithm.util.MathFunctions;

import java.util.List;

public class GreedyHeuristics implements TrainingAlgorithm {

    // Main data.txt
    private List<Item> items;

    // Main data.txt notNormalized
    private List<List<Double>> notNormalizedItemsCoordinates;

    // Cluster's center
    private List<Neuron> neurons;

    // Neuron count
    private int neuronCount;

    // Initial lambda value
    private double lambda;

    // Step of algorithm
    private double step;

    // Count of repeat with same lambda
    private int repeatCount;

    // Metric
    private Metric metric;

    // Some math/arrays functions
    private MathFunctions mathFunctions = new MathFunctions();

    @Override
    public void setParams(List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons, double lambda, double step, int repeatCount, Metric metric) {
        this.items = items;
        this.notNormalizedItemsCoordinates = notNormalizedItemsCoordinates;
        this.neurons = neurons;
        this.neuronCount = neurons.size();
        this.lambda = lambda;
        this.step = step;
        this.repeatCount = repeatCount;
        this.metric = metric;
    }

    @Override
    public void startLearning() {

    }

    @Override
    public List<Neuron> getNeurons() {
        return this.neurons;
    }

    @Override
    public List<Item> getItems() {
        return this.items;
    }
}
