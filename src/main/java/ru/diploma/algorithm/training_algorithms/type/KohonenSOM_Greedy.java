package ru.diploma.algorithm.training_algorithms.type;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;
import ru.diploma.algorithm.training_algorithms.TrainingAlgorithm;
import ru.diploma.algorithm.util.MathFunctions;

import java.util.List;

public class KohonenSOM_Greedy implements TrainingAlgorithm {

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
    private double startLambda;

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
        this.startLambda = lambda;
    }

    @Override
    public void startLearning() {

        Neuron nearestNeuron;
        Neuron farthestNeuron;
        int neuronIndex;
        List<Double> itemCoordinates;
        List<Double> neuronCoordinates;
        List<Double> newNeuronCoordinates;

        int tempLambdaDivider = 1;

        while (lambda > 0.000001) {
            System.out.println("Lambda: " + lambda);
            for (int i = 0; i < repeatCount; i++) {
                for (Item item : this.items) {
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
                }
            }

            if (this.neurons.size() != exactlyNeuronCount) {
                this.lambda = this.startLambda / ++tempLambdaDivider;

                // Remove farthest neuron
                if (this.neurons.size() != this.exactlyNeuronCount) {
                    switch (metric.getMetricType()) {
                        case MAHALANOBIS -> farthestNeuron = metric.findMaximumDistanceBetweenItemsAndClusters(this.items, this.notNormalizedItemsCoordinates, this.neurons);
                        default -> farthestNeuron = metric.findMaximumDistanceBetweenItemsAndClusters(this.items, this.neurons);
                    }

                    this.neurons.remove(farthestNeuron);
                }
            } else {
                lambda -= step;
            }
        }
    }

    @Override
    public List<Neuron> getNeurons() {
        return this.neurons;
    }

    @Override
    public List<Item> getItems() {
        return items;
    }
}
