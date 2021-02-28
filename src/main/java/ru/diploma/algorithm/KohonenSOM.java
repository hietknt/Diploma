package ru.diploma.algorithm;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.MetricType;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.type.EuclideanMetric;

import java.util.List;

public class KohonenSOM {

    // Main data.txt
    private List<Item> items;

    // Cluster's center
    private List<Neuron> neurons;

    // Neuron count
    private int neuronCount;

    // Step of algorithm
    private double step;

    // Type of metric
    private MetricType metricType;

    //TEMP
    EuclideanMetric euclideanMetric = new EuclideanMetric();

    public KohonenSOM(List<Item> items, List<Neuron> neurons, double step, MetricType metricType){
        this.items = items;
        this.neurons = neurons;
        this.neuronCount = neurons.size();
        this.step = step;

        this.metricType = metricType;
    }

    public void startLearning(){
        System.out.println(euclideanMetric.findMinimumDistance(this.items, this.neurons));
    }

    public List<Neuron> getNeurons(){
        return this.neurons;
    }

    public List<Item> getItems() {
        return items;
    }
}
