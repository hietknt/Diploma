package ru.diploma.algorithm.metric;

import ru.diploma.algorithm.metric.type.ChebyshevMetric;
import ru.diploma.algorithm.metric.type.EuclideanMetric;
import ru.diploma.algorithm.metric.type.EuclideanWithoutSqrtMetric;
import ru.diploma.algorithm.metric.type.MahalanobisMetric;
import ru.diploma.algorithm.metric.type.ManhattanMetric;

import java.util.HashMap;
import java.util.Map;

public class MetricPicker {

    private Map<MetricType, Metric> metricMap = new HashMap<>();

    public MetricPicker() {
        metricMap.put(MetricType.EUCLIDEAN, new EuclideanMetric());
        metricMap.put(MetricType.EUCLIDEAN_WITHOUT_SQRT, new EuclideanWithoutSqrtMetric());
        metricMap.put(MetricType.MANHATTAN, new ManhattanMetric());
        metricMap.put(MetricType.MAHALANOBIS, new MahalanobisMetric());
        metricMap.put(MetricType.CHEBYSHEV, new ChebyshevMetric());
    }

    public Metric getMetricByType(MetricType type){
        return metricMap.get(type);
    }
}
