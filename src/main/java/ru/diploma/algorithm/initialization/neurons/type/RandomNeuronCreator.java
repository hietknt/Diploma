package ru.diploma.algorithm.initialization.neurons.type;

import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.basic.RadiusForm;
import ru.diploma.algorithm.util.RandomCoordinatesGenerator;

import java.util.ArrayList;
import java.util.List;

public class RandomNeuronCreator {

    RandomCoordinatesGenerator coordinatesGenerator = new RandomCoordinatesGenerator();

    public List<Neuron> create(int clusterCount, int parameterCount, double initRadius, RadiusForm radiusForm){
        List<Neuron> neurons = new ArrayList<>();

        for (int i = 0; i < clusterCount; i++){
            Neuron neuron = new Neuron();
            neuron.setNumber(i);
            neuron.setCoordinates(coordinatesGenerator.generate(parameterCount, -1, 1));
            neuron.setRadius(initRadius);
            neuron.setRadiusForm(radiusForm);

            neurons.add(neuron);
        }

        return neurons;
    }
}
