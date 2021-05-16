package ru.diploma.algorithm.initialization.neurons;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;

import java.util.List;

public interface NeuronCreator {
    List<Neuron> createNeurons(
            int clusterCount,
            int parameterCount,
            List<Item> items
    );
}
