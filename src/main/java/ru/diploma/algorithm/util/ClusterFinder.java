package ru.diploma.algorithm.util;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.MetricType;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;
import ru.diploma.algorithm.metric.MetricPicker;

import java.util.List;

public class ClusterFinder {
    private MetricPicker metricPicker = new MetricPicker();
    private MetricType metricType = MetricType.EUCLIDEAN;
    private Metric metric = metricPicker.getMetricByType(metricType);

    public void find(List<Item> items, List<Neuron> neurons){
        for (Item item : items){
            item.setClusterNumber(metric.findMinimumDistance(item, neurons).getNumber());
        }
    }
}
