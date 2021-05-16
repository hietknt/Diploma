package ru.diploma.algorithm.initialization.neurons;

import ru.diploma.algorithm.basic.NeuronInitializeType;
import ru.diploma.algorithm.initialization.neurons.type.AverageNeuronCreator;
import ru.diploma.algorithm.initialization.neurons.type.KmeansNeuronCreator;
import ru.diploma.algorithm.initialization.neurons.type.RandomNeuronCreator;

import java.util.HashMap;
import java.util.Map;

public class NeuronCreatorPicker {

    private Map<NeuronInitializeType, NeuronCreator> neuronInitializeMap = new HashMap<>();

    public NeuronCreatorPicker() {
        neuronInitializeMap.put(NeuronInitializeType.RANDOM, new RandomNeuronCreator());
        neuronInitializeMap.put(NeuronInitializeType.AVERAGE, new AverageNeuronCreator());
        neuronInitializeMap.put(NeuronInitializeType.KMEANS, new KmeansNeuronCreator());
    }

    public NeuronCreator getNeuronCreatorByType(NeuronInitializeType type) {
        return neuronInitializeMap.get(type);
    }
}
