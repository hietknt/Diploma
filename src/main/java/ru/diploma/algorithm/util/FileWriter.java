package ru.diploma.algorithm.util;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.MetricType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {
    private final String APPEND_FILE_PATH = "_result.txt";
    private final String APPEND_FILE_NEURON_PATH = "_neuronsResult.txt";
    private final String APPEND_FILE_DISTANCE_PATH = "_distanceResult.txt";
    private final String APPEND_FILE_CLUSTER_TABLE_PATH = "_clusterTable.txt";
    private final String APPEND_FILE_PIVOT_TABLE_PATH = "_pivotTable.txt";
    private final String PATH_PREFIX = "src/main/resources/results/";
    private String pathToMetricType = "";

    private static List<List<Double>> clusterTable = new ArrayList<>();

    private List<Item> items;
    private List<Neuron> neurons;
    private String pathToData;
    private String appendToPath;

    private int iterationNumber;
    private int iterationCount;

    private MathFunctions functions = new MathFunctions();

    public FileWriter setParams(List<Item> items, List<Neuron> neurons, String pathToData, String appendToPath) {
        this.items = items;
        this.neurons = neurons;
        this.pathToData = pathToData
                .replace("/", "")
                .replace(".txt", "")
                .trim();
        this.appendToPath = appendToPath;

        return this;
    }

    public FileWriter setParams(List<Item> items,
                                List<Neuron> neurons,
                                String pathToData,
                                String appendToPath,
                                MetricType type,
                                int iterationNumber,
                                int iterationCount
    ) {
        this.items = items;
        this.neurons = neurons;
        this.pathToData = pathToData
                .replace("/", "")
                .replace(".txt", "")
                .trim();
        this.appendToPath = appendToPath;
        this.iterationNumber = iterationNumber;
        this.iterationCount = iterationCount;

        switch (type) {
            case CHEBYSHEV -> pathToMetricType = "CHEBYSHEV/";
            case EUCLIDEAN -> pathToMetricType = "EUCLIDEAN/";
            case EUCLIDEAN_WITHOUT_SQRT -> pathToMetricType = "EUCLIDEAN_WITHOUT_SQRT/";
            case MAHALANOBIS -> pathToMetricType = "MAHALANOBIS/";
            case MANHATTAN -> pathToMetricType = "MANHATTAN/";
        }

        return this;
    }

    public FileWriter writeData() {
        String fileName = PATH_PREFIX + pathToMetricType + pathToData + appendToPath + APPEND_FILE_PATH;
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));){
            writer.write(convertItems(items));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public FileWriter writeDistance() {
        String fileName = PATH_PREFIX + pathToMetricType + pathToData + appendToPath + APPEND_FILE_DISTANCE_PATH;
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName))){
            writer.write(convertDistances(functions.distances(items, neurons)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public FileWriter writeNeurons() {
        String fileName = PATH_PREFIX + pathToMetricType + pathToData + appendToPath + APPEND_FILE_NEURON_PATH;
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName))){
            writer.write(convertNeurons(neurons));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public void writePivotTable(int[][] matrix) {
        String fileName = PATH_PREFIX + pathToMetricType + pathToData + appendToPath + APPEND_FILE_PIVOT_TABLE_PATH;
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));
            writer.write(convertPivot(matrix));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeClustersTable(String data) {
        String fileName = PATH_PREFIX + pathToMetricType + pathToData + appendToPath + APPEND_FILE_CLUSTER_TABLE_PATH;
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName))){
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertItems(List<Item> items) {
        StringBuilder result = new StringBuilder();

        for (Item item : items) {
            result.append(item.getClusterNumber())
                    .append(": ")
                    .append(item.getCoordinates())
                    .append("\n");
        }

        List<Double> line = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            line.add(items.get(i).getClusterNumber());
        }
        clusterTable.add(line);

        if (iterationNumber == iterationCount - 1) {
            writeClustersTable(convertClusterTable(clusterTable));
            clusterTable.clear();
        }

        return result.toString();
    }

    private String convertNeurons(List<Neuron> neurons) {
        StringBuilder result = new StringBuilder();
        for (Neuron neuron : neurons) {
            result.append(neuron.getNumber())
                    .append(": ")
                    .append(neuron.getCoordinates())
                    .append("\n");
        }

        return result.toString();
    }

    private String convertDistances(List<List<Double>> distances) {
        List<Double> sumDistances = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int itemIndex = 0;

        double sum = 0.0;
        for (List<Double> coordinates : distances) {
            sum += functions.findMinimalValue(coordinates);
        }

        for (int i = 0; i < distances.size(); i++) {
            result.append("Item ")
                    .append(itemIndex)
                    .append(": ")
                    .append(distances.get(i))
                    .append("\n");
            itemIndex++;
        }
        result.append(sum);

        return result.toString();
    }

    private String convertClusterTable(List<List<Double>> list) {
        List<List<Double>> transposed = functions.transpose(list);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < transposed.size(); i++) {
            for (int j = 0; j < transposed.get(0).size(); j++) {
                result.append(transposed.get(i).get(j)).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    private String convertPivot(int[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result.append(matrix[i][j]).append("\t");
            }
            result.append("\n");
        }

        return  result.toString();
    }
}
