package ru.diploma.algorithm.analization;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.metric.MetricType;
import ru.diploma.algorithm.util.FileReader;
import ru.diploma.algorithm.util.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class PivotTable {

    private String PREFIX_FILE = "/results/";
    private String ADDITIONAL_FULL_NAME_PATH = "_full.txt";
    private String RESULT_PATH = "_result";
    private String fileName;

    private int iteration;

    private List<Integer> startedClusters;
    private List<Integer> endedClusters;

    private FileReader reader = new FileReader();
    private FileWriter writer = new FileWriter();

    public PivotTable() {

    }

    public PivotTable setParams(
            String fileName,
            int iteration
    ) {
        this.fileName = fileName;
        this.iteration = iteration;

        return this;
    }

    public int[][] create(MetricType type, List<Item> items) {
        String fullPath1 = "/" + fileName + ADDITIONAL_FULL_NAME_PATH;
        String metric = getMetricNameByType(type);
        String fullPath2 = PREFIX_FILE + metric + fileName + "_" + metric.replace("/", "") + "_"+ + iteration + RESULT_PATH + ".txt";

        endedClusters = new ArrayList<>();
        for (Item item : items) {
            endedClusters.add((int) item.getClusterNumber());
        }

        startedClusters = reader.readFullFileAndGetClustersOnly(fullPath1);

        return createTable(startedClusters, endedClusters);
    }

    public int[][] createTable(List<Integer> startedClusters, List<Integer> endedClusters) {
        int batchCount = batchCount(startedClusters);
        int[][] matrix = new int[batchCount + 1][batchCount + 1];
        List<Integer> batchesNumber = diffNumbers(startedClusters);
        List<Integer> clusterNumber = diffNumbers(endedClusters);

        for (int i = 1; i < matrix.length; i++) {
            if (clusterNumber.size() != (i - 1)) {
                matrix[0][i] = clusterNumber.get(i - 1);
            } else {
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (endedClusters.size() != (i - 1)) {
                matrix[i][0] = batchesNumber.get(i - 1);
            } else {
                break;
            }
        }

        for (int i = 0; i < startedClusters.size(); i++) {
            int indexCluster = findClusterIndex(matrix, endedClusters.get(i));
            int indexBatch = findBatchIndex(matrix, startedClusters.get(i));

            matrix[indexBatch][indexCluster]++;
        }

        return matrix;
    }

    private int batchCount(List<Integer> startedClusters) {
        List<Integer> batches = new ArrayList<>();

        for (Integer value : startedClusters) {
            if (!batches.contains(value)) {
                batches.add(value);
            }
        }

        return batches.size();
    }

    private List<Integer> diffNumbers(List<Integer> startedClusters){
        List<Integer> batches = new ArrayList<>();

        for (Integer value : startedClusters) {
            if (!batches.contains(value)) {
                batches.add(value);
            }
        }

        return batches;
    }

    private int findClusterIndex(int[][] matrix, int clusterNumber) {
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[0][i] == clusterNumber) {
                return i;
            }
        }

        return 0;
    }

    private int findBatchIndex(int [][] matrix, int batchNumber) {
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == batchNumber) {
                return i;
            }
        }

        return 0;
    }


    private String getMetricNameByType(MetricType type) {
        String pathToMetricType = "";

        switch (type) {
            case CHEBYSHEV -> pathToMetricType = "CHEBYSHEV/";
            case EUCLIDEAN -> pathToMetricType = "EUCLIDEAN/";
            case EUCLIDEAN_WITHOUT_SQRT -> pathToMetricType = "EUCLIDEAN_WITHOUT_SQRT/";
            case MAHALANOBIS -> pathToMetricType = "MAHALANOBIS/";
            case MANHATTAN -> pathToMetricType = "MANHATTAN/";
        }

        return pathToMetricType;
    }
}
