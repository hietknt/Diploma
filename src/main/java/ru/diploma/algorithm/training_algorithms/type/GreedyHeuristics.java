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
    private int exactlyNeuronCount;

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
    public void setParams(List<Item> items,
                          List<List<Double>> notNormalizedItemsCoordinates,
                          List<Neuron> neurons,
                          double lambda,
                          double step,
                          int repeatCount,
                          Metric metric,
                          int neuronsMultiplier
    ) {
        this.items = items;
        this.notNormalizedItemsCoordinates = notNormalizedItemsCoordinates;
        this.neurons = neurons;
        this.neuronCount = neurons.size();
        this.lambda = lambda;
        this.step = step;
        this.repeatCount = repeatCount;
        this.metric = metric;
        this.exactlyNeuronCount = neuronCount / neuronsMultiplier;
    }

    @Override
    public void startLearning() {
        Neuron nearestNeuron;
        Neuron farthestNeuron;
        int neuronIndex;
        List<Double> itemCoordinates;
        List<Double> neuronCoordinates;
        List<Double> newNeuronCoordinates;

        int n = this.items.size();
        repeatCount = (int) Math.floor((double) n / (double) neuronCount + 1);

        int tempLambdaDivider = 1;

        int counter = 0;
        for (Item item : this.items) {
            if (counter == repeatCount) {
                System.out.println("Lambda: " + lambda);
                counter = 0;
                lambda = 0.5 / ++tempLambdaDivider;
                //lambda -= step;

                // Remove farthest neuron
                if (this.neurons.size() != this.exactlyNeuronCount) {
                    switch (metric.getMetricType()) {
                        case MAHALANOBIS -> farthestNeuron = metric.findMaximumDistanceBetweenItemsAndClusters(this.items, this.notNormalizedItemsCoordinates, this.neurons);
                        default -> farthestNeuron = metric.findMaximumDistanceBetweenItemsAndClusters(this.items, this.neurons);
                    }

                    this.neurons.remove(farthestNeuron);
                }
            }
            itemCoordinates = item.getCoordinates();

            switch (metric.getMetricType()) {
                case MAHALANOBIS -> nearestNeuron = metric.findMinimumDistance(item, this.items, this.notNormalizedItemsCoordinates, this.neurons);
                default -> nearestNeuron = metric.findMinimumDistance(item, this.neurons);
            }
            neuronIndex = this.neurons.indexOf(nearestNeuron);

            neuronCoordinates = nearestNeuron.getCoordinates();

            // W(k) = W(k) + lambda*(X(m) - W(k))
            newNeuronCoordinates =
                    mathFunctions
                            .sum(neuronCoordinates, mathFunctions
                                    .multiplication(lambda, (mathFunctions
                                            .difference(itemCoordinates, neuronCoordinates))));

            nearestNeuron.setCoordinates(newNeuronCoordinates);
            this.neurons.set(neuronIndex, nearestNeuron);

            counter++;
        }
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
