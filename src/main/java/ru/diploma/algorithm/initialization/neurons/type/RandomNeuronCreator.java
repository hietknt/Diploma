package ru.diploma.algorithm.initialization.neurons.type;

import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.initialization.neurons.NeuronCreator;
import ru.diploma.algorithm.util.RandomCoordinatesGenerator;

import java.util.ArrayList;
import java.util.List;

public class RandomNeuronCreator implements NeuronCreator {

    private RandomCoordinatesGenerator coordinatesGenerator = new RandomCoordinatesGenerator();

    public List<Neuron> createNeurons(int clusterCount, int parameterCount) {
        List<Neuron> neurons = new ArrayList<>();

        for (int i = 0; i < clusterCount; i++) {
            Neuron neuron = new Neuron();
            neuron.setNumber(i);
            neuron.setCoordinates(coordinatesGenerator.generate(parameterCount, 0, 1));

            neurons.add(neuron);
        }

        return neurons;
    }
}
