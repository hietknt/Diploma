package ru.diploma.algorithm.initialization.neurons;

import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.basic.NeuronInitializeType;
import ru.diploma.algorithm.basic.RadiusForm;

import java.util.List;

public interface NeuronCreator {
    List<Neuron> createNeurons(
            int clusterCount,
            int parameterCount,
            double initRadius,
            RadiusForm radiusForm,
            NeuronInitializeType initializeType
    );
}
