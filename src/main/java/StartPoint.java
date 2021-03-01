import ru.diploma.algorithm.OperatingSystem;
import ru.diploma.algorithm.basic.MetricType;
import ru.diploma.algorithm.basic.NeuronInitializeType;
import ru.diploma.algorithm.basic.NormalizationType;

public class StartPoint {
    public static void main(String[] args) {
        new Training().setParams(
                        OperatingSystem.WINDOWS,
                        NeuronInitializeType.RANDOM,
                        NormalizationType.DEFAULT,
                        MetricType.EUCLIDEAN,
                        3,
                        0.7,
                        0.005,
                        100,
                        "/bigData.txt"
                ).start();
    }
}
