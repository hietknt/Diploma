import ru.diploma.algorithm.OperatingSystem;
import ru.diploma.algorithm.metric.MetricType;
import ru.diploma.algorithm.initialization.neurons.NeuronInitializeType;
import ru.diploma.algorithm.normalization.NormalizationType;
import ru.diploma.algorithm.training_algorithms.TrainingAlgorithmType;

public class StartPoint {

    private static int iterationCountSize = 10;

    public static void main(String[] args) {
        //OneIterationStart();
        //ManyIterationStart();
        fullStartManyIteration();
    }

    public static void OneIterationStart() {
        new Training().setParams(
                TrainingAlgorithmType.KOHONEN_SOM_2,
                OperatingSystem.WINDOWS,
                NeuronInitializeType.RANDOM,
                NormalizationType.DEFAULT,
                MetricType.EUCLIDEAN,
                2,
                0.5,
                0.075,
                100,
                2,
                "/data.txt", // Starts with "/" and ends with ".txt"
                ""
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
                    //MetricType.EUCLIDEAN,
                    //MetricType.EUCLIDEAN_WITHOUT_SQRT,
                    //MetricType.MAHALANOBIS,
                    //MetricType.MANHATTAN,
                    7,
                    0.5,
                    0.005,
                    100,
                    1,
                    "/4000data.txt", // Starts with "/" and ends with ".txt"
                    String.valueOf(i)
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
        iterationCountSize = 30;

        for (int count = 0; count < 5; count++) {
            MetricType type = types[count];
            for (int i = 0; i < iterationCountSize; i++) {
                new Training().setParams(
                        TrainingAlgorithmType.KOHONEN_SOM_2,
                        OperatingSystem.WINDOWS,
                        NeuronInitializeType.AVERAGE,
                        NormalizationType.DEFAULT,
                        type,
                        2,
                        0.5,
                        0.005,
                        10000,
                        3,
                        "/data.txt", // Starts with "/" and ends with ".txt"
                        ("_" + type + "_" + i)
                ).start();
            }
        }
    }
}
