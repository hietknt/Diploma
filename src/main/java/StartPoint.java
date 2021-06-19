import ru.diploma.algorithm.OperatingSystem;
import ru.diploma.algorithm.initialization.neurons.NeuronInitializeType;
import ru.diploma.algorithm.metric.MetricType;
import ru.diploma.algorithm.normalization.NormalizationType;
import ru.diploma.algorithm.training_algorithms.TrainingAlgorithmType;

import java.util.HashMap;
import java.util.Map;

public class StartPoint {

    private static int iterationCountSize = 10;

    public static void main(String[] args) {
        //OneIterationStart();
        //ManyIterationStart();
        //fullStartManyIteration();
        fullStartManyIteration2();
    }

    public static void OneIterationStart() {
        new Training().setParams(
                TrainingAlgorithmType.GREEDY_HEURISTICS,
                OperatingSystem.WINDOWS,
                NeuronInitializeType.RANDOM,
                NormalizationType.DEFAULT,
                MetricType.CHEBYSHEV,
                3,
                0.5,
                0.005,
                240000,
                1,
                "/300data_3c.txt", // Starts with "/" and ends with ".txt"
                "",
                0,
                1
        ).start();
    }

    public static void ManyIterationStart() {
        for (int i = 0; i < iterationCountSize; i++) {
            new Training().setParams(
                    TrainingAlgorithmType.GREEDY_HEURISTICS,
                    OperatingSystem.WINDOWS,
                    NeuronInitializeType.AVERAGE,
                    NormalizationType.DEFAULT,
                    MetricType.CHEBYSHEV,
                    7,
                    0.5,
                    0.005,
                    100,
                    1,
                    "/4000data.txt", // Starts with "/" and ends with ".txt"
                    String.valueOf(i),
                    i,
                    iterationCountSize
            ).start();
        }
    }

    public static void fullStartManyIteration() {
        MetricType[] types = {
                MetricType.CHEBYSHEV,
                MetricType.EUCLIDEAN,
                MetricType.EUCLIDEAN_WITHOUT_SQRT,
                MetricType.MAHALANOBIS,
                MetricType.MANHATTAN
        };
        iterationCountSize = 15;

        for (int count = 0; count < types.length; count++) {
            MetricType type = types[count];
            for (int i = 0; i < iterationCountSize; i++) {
                new Training().setParams(
                        TrainingAlgorithmType.KOHONEN_SOM,
                        OperatingSystem.WINDOWS,
                        NeuronInitializeType.AVERAGE,
                        NormalizationType.DEFAULT,
                        type,
                        4,
                        0.5,
                        0.005,
                        20, // 240k for KOHONEN_2, 10-100 for KOHONEN_1
                        1,
                        "/446data_4c.txt", // Starts with "/" and ends with ".txt"
                        ("_" + type + "_" + i),
                        i,
                        iterationCountSize
                ).start();
            }
        }
    }

    public static void fullStartManyIteration2() {
        TrainingAlgorithmType[] algorithmTypes = {
                TrainingAlgorithmType.GREEDY_HEURISTICS,
                TrainingAlgorithmType.GREEDY_HEURISTICS_2,
                TrainingAlgorithmType.KOHONEN_SOM_GREEDY,
                TrainingAlgorithmType.KOHONEN_SOM_GREEDY_2,
                TrainingAlgorithmType.KOHONEN_SOM,
                TrainingAlgorithmType.KOHONEN_SOM_2,
        };

        MetricType[] types = {
                MetricType.CHEBYSHEV,
                MetricType.EUCLIDEAN,
                MetricType.EUCLIDEAN_WITHOUT_SQRT,
                MetricType.MAHALANOBIS,
                MetricType.MANHATTAN
        };

        Map<TrainingAlgorithmType, Params> algorithms = new HashMap<>() {{
            put(TrainingAlgorithmType.GREEDY_HEURISTICS, new Params(
                    10,
                    1,
                    new int[]{1, 2, 3},
                    new NeuronInitializeType[]{NeuronInitializeType.RANDOM},
                    types
            ));
            put(TrainingAlgorithmType.GREEDY_HEURISTICS_2, new Params(
                    10,
                    1,
                    new int[]{1, 2, 3},
                    new NeuronInitializeType[]{NeuronInitializeType.RANDOM},
                    types
            ));
            put(TrainingAlgorithmType.KOHONEN_SOM_GREEDY, new Params(
                    10,
                    1,
                    new int[]{2, 3},
                    new NeuronInitializeType[]{NeuronInitializeType.AVERAGE, NeuronInitializeType.KMEANS, NeuronInitializeType.RANDOM},
                    types
            ));
            put(TrainingAlgorithmType.KOHONEN_SOM_GREEDY_2, new Params(
                    10,
                    1,
                    new int[]{2, 3},
                    new NeuronInitializeType[]{NeuronInitializeType.AVERAGE, NeuronInitializeType.KMEANS, NeuronInitializeType.RANDOM},
                    types
            ));
            put(TrainingAlgorithmType.KOHONEN_SOM, new Params(
                    10,
                    20,
                    new int[]{1},
                    new NeuronInitializeType[]{NeuronInitializeType.AVERAGE, NeuronInitializeType.KMEANS, NeuronInitializeType.RANDOM},
                    types
            ));
            put(TrainingAlgorithmType.KOHONEN_SOM_2, new Params(
                    10,
                    240000,
                    new int[]{1},
                    new NeuronInitializeType[]{NeuronInitializeType.AVERAGE, NeuronInitializeType.KMEANS, NeuronInitializeType.RANDOM},
                    types
            ));
        }};

        for (int i = 0; i < algorithmTypes.length; i++) {
            TrainingAlgorithmType algorithmType = algorithmTypes[i];
            int[] neuronMultipliers = algorithms.get(algorithmType).neuronMultiplier;
            String algorithmAppend = getAlgorithmNameByType(algorithmType);

            for (int j = 0; j < neuronMultipliers.length; j++) {
                int neuronMultiplier = neuronMultipliers[j];
                String neuronMultiplierAppend = "x" + neuronMultiplier;
                NeuronInitializeType[] neuronInitializeTypes = algorithms.get(algorithmTypes[i]).neuronInitializeTypes;

                for (int k = 0; k < neuronInitializeTypes.length; k++) {
                    NeuronInitializeType neuronInitializeType = neuronInitializeTypes[k];
                    String neuronInitializeTypeAppend = getNeuronInitializeTypeName(neuronInitializeType);
                    MetricType[] metricTypes = algorithms.get(algorithmType).metricTypes;

                    for (int l = 0; l < metricTypes.length; l++) {
                        MetricType metricType = metricTypes[l];
                        int iterationCount = algorithms.get(algorithmType).iterationCount;
                        int repeatCount = algorithms.get(algorithmType).repeatCount;

                        for (int q = 0; q < iterationCount; q++) {
                            new Training().setParams(
                                    algorithmType,
                                    OperatingSystem.WINDOWS,
                                    neuronInitializeType,
                                    NormalizationType.DEFAULT,
                                    metricType,
                                    7,
                                    0.5,
                                    0.005,
                                    repeatCount, // 240k for KOHONEN_2, 10-100 for KOHONEN_1
                                    neuronMultiplier,
                                    "/4000data_7c.txt", // Starts with "/" and ends with ".txt"
                                    ("_" + algorithmAppend + "_" + neuronMultiplierAppend + "_" + metricType + "_" + neuronInitializeTypeAppend + "_" + q),
                                    q,
                                    iterationCount
                            ).start();
                        }
                    }
                }

            }



        }
    }

    private static String getAlgorithmNameByType(TrainingAlgorithmType type) {
        String algorithmName = "";

        switch (type) {
            case KOHONEN_SOM -> algorithmName = "KOHONEN_SOM_1";
            case KOHONEN_SOM_2 -> algorithmName = "KOHONEN_SOM_2";
            case GREEDY_HEURISTICS -> algorithmName = "GREEDY_HEURISTICS_1";
            case GREEDY_HEURISTICS_2 -> algorithmName = "GREEDY_HEURISTICS_2";
            case KOHONEN_SOM_GREEDY -> algorithmName = "KOHONEN_SOM_GREEDY_1";
            case KOHONEN_SOM_GREEDY_2 -> algorithmName = "KOHONEN_SOM_GREEDY_2";
        }

        return algorithmName;
    }

    private static String getNeuronInitializeTypeName(NeuronInitializeType type) {
        String typeName = "";

        switch (type) {
            case AVERAGE -> typeName = "AVERAGE";
            case KMEANS -> typeName = "KMEANS";
            case RANDOM -> typeName = "RANDOM";
        }

        return typeName;
    }


    private static class Params {
        private int iterationCount;
        private int repeatCount;
        private int[] neuronMultiplier;
        private NeuronInitializeType[] neuronInitializeTypes;
        MetricType[] metricTypes;

        public Params(
                    int iterationCount,
                    int repeatCount,
                    int[] neuronMultiplier,
                    NeuronInitializeType[] neuronInitializeTypes,
                    MetricType[] metricTypes
               ) {
            this.iterationCount = iterationCount;
            this.repeatCount = repeatCount;
            this.neuronMultiplier = neuronMultiplier;
            this.neuronInitializeTypes = neuronInitializeTypes;
            this.metricTypes = metricTypes;
        }

        public int getIterationCount() {
            return iterationCount;
        }

        public int getRepeatCount() {
            return repeatCount;
        }

        public int[] getNeuronMultiplier() {
            return neuronMultiplier;
        }

        public NeuronInitializeType[] getNeuronInitializeTypes() {
            return neuronInitializeTypes;
        }

        public MetricType[] getMetricTypes() {
            return metricTypes;
        }
    }
}
