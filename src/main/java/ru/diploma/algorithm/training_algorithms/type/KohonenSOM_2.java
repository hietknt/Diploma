package ru.diploma.algorithm.training_algorithms.type;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;
import ru.diploma.algorithm.training_algorithms.TrainingAlgorithm;
import ru.diploma.algorithm.util.MathFunctions;

import java.util.List;
import java.util.Random;

public class KohonenSOM_2 implements TrainingAlgorithm {
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

    // Random lib
    private Random random = new Random();

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
    }

    @Override
    public void startLearning() {
        Neuron nearestNeuron;
        int neuronIndex;
        List<Double> itemCoordinates;
        List<Double> neuronCoordinates;

        Item item = null;
        int itemsCount = this.items.size();
        int t = 0;
        double value;
        while (t < repeatCount) {
            item = this.items.get(random.nextInt(itemsCount));
            itemCoordinates = item.getCoordinates();

            switch (metric.getMetricType()) {
                case MAHALANOBIS -> nearestNeuron = metric.findMinimumDistance(item, this.items, this.notNormalizedItemsCoordinates, this.neurons);
                default -> nearestNeuron = metric.findMinimumDistance(item, this.neurons);
            }
            neuronCoordinates = nearestNeuron.getCoordinates();

            // W(m)n = W(m)n + n(t)*h(t, p(m, m*))[Xn - W(m)n]
            for (int i = 0; i < neuronCount; i++) {
                for (int j = 0; j < neurons.get(0).getCoordinates().size(); j++) {
                    value = this.neurons.get(i).getCoordinates().get(j);
                    this.neurons.get(i).getCoordinates().set(j,
                            value + (eta(t) * h(this.getNeurons().get(i).getCoordinates(), neuronCoordinates, t) * (itemCoordinates.get(j) - value)));
                }
            }

            t++;
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

    private double h(List<Double> coordinates1, List<Double> coordinates2, int t) {
        double distance = mathFunctions.calculateDistance(coordinates1, coordinates2);
        return Math.exp(-(distance * distance) / (2 * sigma(t)));
    }

    private double sigma(double t) {
        double sigma0 = 20.0;
        double tau1 = 1000.0 / Math.log(sigma0);

        return sigma0 * Math.exp(-t / tau1);
    }

    private double eta(int t) {
        double eta0 = 0.1;
        double tau2 = 2000;

        return eta0 * Math.exp(-t / tau2);
    }
}
