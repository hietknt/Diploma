package ru.diploma.algorithm.training_algorithms;

import ru.diploma.algorithm.training_algorithms.type.*;

import java.util.HashMap;
import java.util.Map;

public class TrainingAlgorithmPicker {

    private Map<TrainingAlgorithmType, TrainingAlgorithm> metricMap = new HashMap<>();

    public TrainingAlgorithmPicker() {
        metricMap.put(TrainingAlgorithmType.KOHONEN_SOM, new KohonenSOM());
        metricMap.put(TrainingAlgorithmType.KOHONEN_SOM_2, new KohonenSOM_2());
        metricMap.put(TrainingAlgorithmType.KOHONEN_SOM_GREEDY, new KohonenSOM_Greedy());
        metricMap.put(TrainingAlgorithmType.KOHONEN_SOM_GREEDY_2, new KohonenSOM_Greedy_2());
        metricMap.put(TrainingAlgorithmType.GREEDY_HEURISTICS, new GreedyHeuristics());
        metricMap.put(TrainingAlgorithmType.GREEDY_HEURISTICS_2, new GreedyHeuristic_2());
    }

    public TrainingAlgorithm getMetricByType(TrainingAlgorithmType type){
        return metricMap.get(type);
    }
}
