import ru.diploma.algorithm.training_algorithms.TrainingAlgorithm;
import ru.diploma.algorithm.training_algorithms.TrainingAlgorithmPicker;
import ru.diploma.algorithm.training_algorithms.TrainingAlgorithmType;
import ru.diploma.algorithm.OperatingSystem;
import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.MetricType;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.basic.NeuronInitializeType;
import ru.diploma.algorithm.basic.NormalizationType;
import ru.diploma.algorithm.initialization.items.type.ReadItemCreator;
import ru.diploma.algorithm.initialization.neurons.NeuronCreatorPicker;
import ru.diploma.algorithm.metric.MetricPicker;
import ru.diploma.algorithm.normalization.NormalizationPicker;
import ru.diploma.algorithm.util.ClusterFinder;
import ru.diploma.algorithm.util.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class Training {
    private NeuronCreatorPicker neuronCreatorPicker = new NeuronCreatorPicker();
    private NormalizationPicker normalizationPicker = new NormalizationPicker();
    private MetricPicker metricPicker = new MetricPicker();
    private ReadItemCreator itemCreator = new ReadItemCreator();
    private ClusterFinder clusterFinder = new ClusterFinder();

    private FileWriter writer = new FileWriter();

    private OperatingSystem operatingSystem;
    private NeuronInitializeType neuronInitializeType;
    private NormalizationType normalizationType;
    private MetricType metricType;
    private int clusterCount;
    private double lambda;
    private double step;
    private int repeatCount;
    private int clusterMultiplier;
    private String pathToData;
    private String appendToPath;

    private TrainingAlgorithmType trainingAlgorithmType;

    public Training setParams(
            TrainingAlgorithmType trainingAlgorithmType,
            OperatingSystem operatingSystem,
            NeuronInitializeType neuronInitializeType,
            NormalizationType normalizationType,
            MetricType metricType,
            int clusterCount,
            double lambda,
            double step,
            int repeatCount,
            int neuronsMultiplier,
            String pathToData,
            String appendToPath
    ) {
        this.trainingAlgorithmType = trainingAlgorithmType;
        this.operatingSystem = operatingSystem;
        this.neuronInitializeType = neuronInitializeType;
        this.normalizationType = normalizationType;
        this.metricType = metricType;
        this.clusterCount = clusterCount;
        this.lambda = lambda;
        this.step = step;
        this.repeatCount = repeatCount;
        this.clusterMultiplier = neuronsMultiplier;
        this.pathToData = pathToData;
        this.appendToPath = appendToPath;

        return this;
    }

    public void start() {
        System.out.println("STARTED" + (appendToPath.isEmpty() ? "" : (" iteration " + appendToPath)));
        long start = System.currentTimeMillis();

        List<Item> items = null;
        List<List<Double>> notNormalizedItemsCoordinates = new ArrayList<>();
        if (operatingSystem == OperatingSystem.UNIX) {
            items = itemCreator.create(pathToData);
        } else if (operatingSystem == OperatingSystem.WINDOWS) {
            items = itemCreator.createWindows(pathToData);
        }
        for (Item item : items) {
            List<Double> line = new ArrayList<>();
            for (int i = 0; i < item.getCoordinates().size(); i++) {
                line.add(item.getCoordinates().get(i));
            }
            notNormalizedItemsCoordinates.add(line);
        }
        items = normalizationPicker.getNormalizationByType(normalizationType).normalize(items);

        // if using greedy heuristics then use only RANDOM neuron generator
        if (trainingAlgorithmType == TrainingAlgorithmType.GREEDY_HEURISTICS) {
            this.clusterCount *= clusterMultiplier;
            this.neuronInitializeType = NeuronInitializeType.RANDOM;
        }

        List<Neuron> neurons = neuronCreatorPicker.getNeuronCreatorByType(neuronInitializeType)
                .createNeurons(
                        clusterCount,
                        items.get(0).getCoordinates().size(),
                        items
                );

        TrainingAlgorithm trainingAlgorithm = new TrainingAlgorithmPicker().getMetricByType(this.trainingAlgorithmType);
        trainingAlgorithm.setParams(
                items,
                notNormalizedItemsCoordinates,
                neurons,
                lambda,
                step,
                repeatCount,
                metricPicker.getMetricByType(metricType)
        );
        trainingAlgorithm.startLearning();

        clusterFinder.find(items, neurons);

        writer.setParams(items, neurons, pathToData, appendToPath)
                .writeData()
                .writeNeurons()
                .writeDistance();

        System.out.println("ENDED" + (appendToPath.isEmpty() ? "" : (" iteration " + appendToPath)) + " with time: " + (System.currentTimeMillis() - start) + "ms.\n");
    }

    private void printNeurons(List<Neuron> neurons) {
        for (int i = 0; i < neurons.size(); i++) {
            System.out.println(neurons.get(i));
        }
    }

    private void printItems(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
    }
}
