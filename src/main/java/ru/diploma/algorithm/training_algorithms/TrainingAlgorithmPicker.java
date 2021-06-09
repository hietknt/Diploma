package ru.diploma.algorithm.training_algorithms;

import ru.diploma.algorithm.training_algorithms.type.GreedyHeuristics;
import ru.diploma.algorithm.training_algorithms.type.KohonenSOM;

import java.util.HashMap;
import java.util.Map;

public class TrainingAlgorithmPicker {

    private Map<TrainingAlgorithmType, TrainingAlgorithm> metricMap = new HashMap<>();

    public TrainingAlgorithmPicker() {
        metricMap.put(TrainingAlgorithmType.KOHONEN_SOM, new KohonenSOM());
        metricMap.put(TrainingAlgorithmType.GREEDY_HEURISTICS, new GreedyHeuristics());
    }

    public TrainingAlgorithm getMetricByType(TrainingAlgorithmType type){
        return metricMap.get(type);
    }
}
