package ru.diploma.algorithm.metric;

import ru.diploma.algorithm.basic.MetricType;
import ru.diploma.algorithm.metric.type.EuclideanMetric;

import java.util.HashMap;
import java.util.Map;

public class MetricPicker {

    private Map<MetricType, Metric> metricMap = new HashMap<>();

    public MetricPicker() {
        metricMap.put(MetricType.EUCLIDEAN, new EuclideanMetric());
    }

    public Metric getMetricByType(MetricType type){
        return metricMap.get(type);
    }
}
