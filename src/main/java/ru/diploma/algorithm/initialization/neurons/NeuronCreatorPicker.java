package ru.diploma.algorithm.initialization.neurons;

import ru.diploma.algorithm.basic.NeuronInitializeType;
import ru.diploma.algorithm.initialization.neurons.type.RandomNeuronCreator;

import java.util.HashMap;
import java.util.Map;

public class NeuronCreatorPicker {

    private Map<NeuronInitializeType, NeuronCreator> neuronInitializeMap = new HashMap<>();

    public NeuronCreatorPicker() {
        neuronInitializeMap.put(NeuronInitializeType.RANDOM, new RandomNeuronCreator());
    }

    public NeuronCreator getNeuronCreatorByType(NeuronInitializeType type) {
        return neuronInitializeMap.get(type);
    }
}
