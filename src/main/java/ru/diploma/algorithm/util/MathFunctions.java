package ru.diploma.algorithm.util;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;

import java.util.ArrayList;
import java.util.List;

public class MathFunctions {
    public List<Double> difference(List<Double> firstList, List<Double> secondList) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < firstList.size(); i++) {
            result.add(firstList.get(i) - secondList.get(i));
        }

        return result;
    }

    public List<Double> multiplication(Double value, List<Double> list) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            result.add(list.get(i) * value);
        }

        return result;
    }

    public List<Double> sum(List<Double> firstList, List<Double> secondList) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < firstList.size(); i++) {
            result.add(firstList.get(i) + secondList.get(i));
        }

        return result;
    }

    public List<Double> divideToValue(List<Double> firstList, double value) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < firstList.size(); i++) {
            result.add(firstList.get(i) / value);
        }

        return result;
    }

    public List<List<Double>> distances(List<Item> items, List<Neuron> neurons) {
        List<Double> itemCoordinates = null;
        List<Double> neuronCoordinates = null;
        List<List<Double>> distances = new ArrayList<>();
        List<Double> distance;

        for (Item item : items) {
            distance = new ArrayList<>();
            itemCoordinates = item.getCoordinates();
            for (Neuron neuron : neurons) {
                neuronCoordinates = neuron.getCoordinates();
                distance.add(calculateDistance(itemCoordinates, neuronCoordinates));
            }
            distances.add(distance);
        }

        return distances;
    }

    public double calculateDistance(List<Double> itemCoordinate, List<Double> neuronCoordinate) {
        double distance = 0.0;
        for (int i = 0; i < itemCoordinate.size(); i++) {
            distance += Math.pow((itemCoordinate.get(i) - neuronCoordinate.get(i)), 2);
        }
        return Math.sqrt(distance);
    }
}
