import ru.diploma.algorithm.KohonenSOM;
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

import java.util.List;

public class Training {
    private NeuronCreatorPicker neuronCreatorPicker = new NeuronCreatorPicker();
    private NormalizationPicker normalizationPicker = new NormalizationPicker();
    private MetricPicker metricPicker = new MetricPicker();
    private ReadItemCreator itemCreator = new ReadItemCreator();
    private KohonenSOM kohonenSOM;

    private OperatingSystem operatingSystem;
    private NeuronInitializeType neuronInitializeType;
    private NormalizationType normalizationType;
    private MetricType metricType;
    private int clusterCount;
    private double lambda;
    private double step;
    private int repeatCount;
    private String pathToData;

    public Training setParams(
            OperatingSystem operatingSystem,
            NeuronInitializeType neuronInitializeType,
            NormalizationType normalizationType,
            MetricType metricType,
            int clusterCount,
            double lambda,
            double step,
            int repeatCount,
            String pathToData
    ) {
        this.operatingSystem = operatingSystem;
        this.neuronInitializeType = neuronInitializeType;
        this.normalizationType = normalizationType;
        this.metricType = metricType;
        this.clusterCount = clusterCount;
        this.lambda = lambda;
        this.step = step;
        this.repeatCount = repeatCount;
        this.pathToData = pathToData;

        return this;
    }

    public void start() {
        List<Item> items = null;
        if (operatingSystem == OperatingSystem.UNIX) {
            items = itemCreator.create(pathToData);
        } else if (operatingSystem == OperatingSystem.WINDOWS) {
            items = itemCreator.createWindows(pathToData);
        }

        List<Neuron> neurons = neuronCreatorPicker.getNeuronCreatorByType(neuronInitializeType)
                .createNeurons(
                        clusterCount,
                        items.get(0).getCoordinates().size()
                );

        items = normalizationPicker.getNormalizationByType(normalizationType).normalize(items);

        kohonenSOM = new KohonenSOM(
                items,
                neurons,
                lambda,
                step,
                repeatCount,
                metricPicker.getMetricByType(metricType)
        );
        kohonenSOM.startLearning();



        System.out.println("\nItems: ");
        printItems(items);
        System.out.println("\nEnd neurons: ");
        printNeurons(kohonenSOM.getNeurons());
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
