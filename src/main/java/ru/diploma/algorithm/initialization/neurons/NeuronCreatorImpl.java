package ru.diploma.algorithm.initialization.neurons;

import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.basic.NeuronInitializeType;
import ru.diploma.algorithm.basic.RadiusForm;
import ru.diploma.algorithm.initialization.neurons.type.RandomNeuronCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeuronCreatorImpl implements NeuronCreator {

    private RandomNeuronCreator randomNeuronCreator = new RandomNeuronCreator();

    private Map<NeuronInitializeType, Creator> creatorMap = new HashMap<>();

    public NeuronCreatorImpl(){
        creatorMap.put(NeuronInitializeType.RANDOM, (int clusterCount, int parameterCount, double initRadius, RadiusForm radiusForm) -> {
            return randomNeuronCreator.create(clusterCount, parameterCount, initRadius, radiusForm);
        });
    }

    @Override
    public List<Neuron> createNeurons(
            int clusterCount,
            int parameterCount,
            double initRadius,
            RadiusForm radiusForm,
            NeuronInitializeType initializeType) {

        return creatorMap.get(initializeType).createNeurons(clusterCount, parameterCount, initRadius, radiusForm);
    }

    private interface Creator{
        List<Neuron> createNeurons(int clusterCount, int parameterCount, double initRadius, RadiusForm radiusForm);
    }
}
