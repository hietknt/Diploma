package ru.diploma.algorithm.initialization.neurons.type;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.initialization.neurons.NeuronCreator;
import ru.diploma.algorithm.util.MathFunctions;
import ru.diploma.algorithm.util.RandomCoordinatesGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AverageNeuronCreator implements NeuronCreator {

    private RandomCoordinatesGenerator coordinatesGenerator = new RandomCoordinatesGenerator();
    private MathFunctions functions = new MathFunctions();
    private Random random = new Random();

    @Override
    public List<Neuron> createNeurons(int clusterCount, int parameterCount, List<Item> items) {
        List<Neuron> neurons = new ArrayList<>();

        for (int i = 0; i < items.size(); i++){
            items.get(i).setClusterNumber(random.nextInt(clusterCount));
        }

        List<Double> averageClusterCoordinates = new ArrayList<>();
        int tempCount;
        for (int i = 0; i < clusterCount; i++){
            averageClusterCoordinates.clear();
            tempCount = 0;

            for (int j = 0; j < items.size(); j++){
                if (i == items.get(j).getClusterNumber()){
                    if (averageClusterCoordinates.size() == 0) {
                        averageClusterCoordinates.addAll(items.get(j).getCoordinates());
                    }else {
                        averageClusterCoordinates = functions.sum(averageClusterCoordinates, items.get(j).getCoordinates());
                    }
                    tempCount++;
                }
            }

            Neuron neuron = new Neuron();
            neuron.setNumber(i);
            neuron.setCoordinates(functions.divideToValue(averageClusterCoordinates, tempCount));

            neurons.add(neuron);
        }

        return neurons;
    }
}
