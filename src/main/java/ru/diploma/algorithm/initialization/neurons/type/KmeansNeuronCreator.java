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
        List<Double> cluster;
        List<Neuron> neurons;
        List<Double> oldClusters;
        List<Double> newClusters;
        int count;

        do {
            for (Item item : items) {
                item.setClusterNumber(random.nextInt(clusterCount));
            }
        } while (!hasAllCluster(items, clusterCount));

        oldClusters = getClusterNumbers(items);
        newClusters = getClusterNumbers(items);

        do {
            neurons = new ArrayList<>();
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

                if (!cluster.isEmpty()) {
                    Neuron neuron = new Neuron();
                    neuron.setNumber(i);
                    neuron.setCoordinates(cluster);
                    neurons.add(neuron);
                }
            }

            double minDistance;
            int clusterNumber = 0;
            double distance;
            for (Item item: items) {
                minDistance = 9999999999.0;
                for (Neuron neuron: neurons) {
                    distance = findDistance(item.getCoordinates(), neuron.getCoordinates());
                    if (minDistance > distance) {
                        minDistance = distance;
                        clusterNumber = neuron.getNumber();
                    }
                }
                item.setClusterNumber(clusterNumber);
            }
            oldClusters = newClusters;
            newClusters = getClusterNumbers(items);

        } while (!oldClusters.equals(newClusters));

        return neurons;
    }

    private List<Double> getClusterNumbers(List<Item> items) {
        List<Double> result = new ArrayList<>();
        for (Item item : items) {
            result.add(item.getClusterNumber());
        }
        return result;
    }

    private double findDistance(List<Double> itemCoordinates, List<Double> neuronCoordinates){
        double distance = 0.0;
        for (int i = 0; i < itemCoordinates.size(); i++) {
            distance += Math.pow((itemCoordinates.get(i) - neuronCoordinates.get(i)), 2);
        }
        return Math.sqrt(distance);
    }

    private boolean hasAllCluster(List<Item> items, int clusterCount) {
        boolean temp;
        for (int i = 0; i < clusterCount; i++) {
            temp = false;
            for (Item item : items) {
                if (item.getClusterNumber() == i) {
                    temp = true;
                    break;
                }
            }
            if (temp == false) {
                return false;
            }
        }
        return  true;
    }
}
