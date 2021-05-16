package ru.diploma.algorithm.initialization.neurons.type;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.initialization.neurons.NeuronCreator;
import ru.diploma.algorithm.util.MathFunctions;
import ru.diploma.algorithm.util.RandomCoordinatesGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KmeansNeuronCreator implements NeuronCreator {

    private RandomCoordinatesGenerator coordinatesGenerator = new RandomCoordinatesGenerator();
    private RandomNeuronCreator randomNeuronCreator = new RandomNeuronCreator();
    private Random random = new Random();

    private MathFunctions functions = new MathFunctions();

    @Override
    public List<Neuron> createNeurons(int clusterCount, int parameterCount, List<Item> items) {

        //List<Neuron> neurons = randomNeuronCreator.createNeurons(clusterCount, parameterCount, items);
        List<Double> cluster;
        List<List<Double>> clusters = new ArrayList<>();
        List<Double> oldClusters;
        List<Double> newClusters;
        int count;

        for (Item item : items) {
            item.setClusterNumber(random.nextInt(clusterCount));
        }

        oldClusters = getClusterNumbers(items);
        newClusters = getClusterNumbers(items);

        do {
            // Find clusters by avg values
            for (int i = 0; i < clusterCount; i++) {
                cluster = new ArrayList<>();
                count = 0;
                for (Item item : items) {
                    if ((int) item.getClusterNumber() == i) {
                        if (cluster.isEmpty()){
                            cluster = item.getCoordinates();
                        } else {
                            cluster = functions.sum(cluster, item.getCoordinates());
                        }
                        count++;
                    }
                }
                cluster = functions.divideToValue(cluster, count);
                clusters.add(cluster);
            }

            //


        } while (oldClusters != newClusters);


        return null;
    }

    private List<Double> getClusterNumbers(List<Item> items) {
        List<Double> result = new ArrayList<>();
        for (Item item : items) {
            result.add(item.getClusterNumber());
        }
        return result;
    }
}
