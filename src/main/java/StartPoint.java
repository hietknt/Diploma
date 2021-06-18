import ru.diploma.algorithm.OperatingSystem;
import ru.diploma.algorithm.analization.PivotTable;
import ru.diploma.algorithm.metric.MetricType;
import ru.diploma.algorithm.initialization.neurons.NeuronInitializeType;
import ru.diploma.algorithm.normalization.NormalizationType;
import ru.diploma.algorithm.training_algorithms.TrainingAlgorithmType;

public class StartPoint {

    private static int iterationCountSize = 10;

    public static void main(String[] args) {
        //OneIterationStart();
        //ManyIterationStart();
        //fullStartManyIteration();

        PivotTable table = new PivotTable();
        table.setParams(
                "4000data_7c",
                0
        ).create(MetricType.CHEBYSHEV);
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
        iterationCountSize = 1;

        for (int count = 0; count < 5; count++) {
            MetricType type = types[count];
            for (int i = 0; i < iterationCountSize; i++) {
                new Training().setParams(
                        TrainingAlgorithmType.KOHONEN_SOM_GREEDY,
                        OperatingSystem.WINDOWS,
                        NeuronInitializeType.AVERAGE,
                        NormalizationType.DEFAULT,
                        type,
                        3,
                        0.5,
                        0.005,
                        100, // 240k for KOHONEN_2, 10-100 for KOHONEN_1
                        3,
                        "/300data_3c.txt", // Starts with "/" and ends with ".txt"
                        ("_" + type + "_" + i),
                        i,
                        iterationCountSize
                ).start();
            }
        }
    }
}
