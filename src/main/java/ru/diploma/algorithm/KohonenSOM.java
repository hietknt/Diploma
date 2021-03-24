package ru.diploma.algorithm;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;
import ru.diploma.algorithm.util.MathFunctions;

import java.util.List;

public class KohonenSOM {

    // Main data.txt
    private List<Item> items;

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

    public KohonenSOM(List<Item> items, List<Neuron> neurons, double lambda, double step, int repeatCount, Metric metric) {
        this.items = items;
        this.neurons = neurons;
        this.neuronCount = neurons.size();
        this.lambda = lambda;
        this.step = step;
        this.repeatCount = repeatCount;

        this.metric = metric;
    }

    public void startLearning() {

        Neuron nearestNeuron;
        int neuronIndex;
        List<Double> itemCoordinates;
        List<Double> neuronCoordinates;
        List<Double> newNeuronCoordinates;

        while (lambda > 0.000001) {
            System.out.println("Lambda: " + lambda);
            for (int i = 0; i < repeatCount; i++) {
                for (Item item : this.items) {
                    itemCoordinates = item.getCoordinates();

                    nearestNeuron = metric.findMinimumDistance(item, this.neurons);
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
            lambda -= step;
        }
    }

    public List<Neuron> getNeurons() {
        return this.neurons;
    }

    public List<Item> getItems() {
        return items;
    }
}
