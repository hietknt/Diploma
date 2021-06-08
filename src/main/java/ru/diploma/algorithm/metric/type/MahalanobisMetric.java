package ru.diploma.algorithm.metric.type;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;
import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.MetricType;
import ru.diploma.algorithm.basic.Neuron;
import ru.diploma.algorithm.metric.Metric;
import ru.diploma.algorithm.util.MathFunctions;

import java.util.ArrayList;
import java.util.List;

public class MahalanobisMetric implements Metric {

    private MathFunctions functions = new MathFunctions();
    private List<List<Double>> inverseCovarianceMatrix;

    public MahalanobisMetric() {
        inverseCovarianceMatrix = new ArrayList<>();
    }

    @Override
    public Neuron findMinimumDistance(Item item, List<Neuron> neurons) {
        return null;
    }

    @Override
    public Neuron findMinimumDistance(Item item, List<Item> items, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons) {
        List<Double> distances = calculateDistance(item, notNormalizedItemsCoordinates, neurons);

        double minDistance = distances.get(0);
        Neuron minDistancesNeuron = neurons.get(0);

        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) < minDistance) {
                minDistance = distances.get(i);
                minDistancesNeuron = neurons.get(i);
            }
        }

        return minDistancesNeuron;
    }

    private List<Double> calculateDistance(Item item, List<List<Double>> notNormalizedItemsCoordinates, List<Neuron> neurons) {
        List<List<Double>> X_NotNormalized = notNormalizedItemsCoordinates;
        List<List<Double>> M = new ArrayList<>();
        List<Double> distances = new ArrayList<>();

        for (Neuron neuron : neurons) {
            M.add(neuron.getCoordinates());
        }

        if (this.inverseCovarianceMatrix.isEmpty()) {
            List<List<Double>> covarianceMatrix = getCovarianceMatrix(X_NotNormalized);
            fillInverseCovarianceMatrix(covarianceMatrix);
        }

        // (X - M)
        List<List<Double>> x_minus_m;
        List<List<Double>> tempDistanceMatrix;
        for (int i = 0; i < M.size(); i++) {
            x_minus_m = new ArrayList<>();
            x_minus_m.add(functions.difference(item.getCoordinates(), M.get(i)));

            tempDistanceMatrix =
                    functions.multiplicationMatrix(
                            functions.multiplicationMatrix(
                                    x_minus_m,
                                    this.inverseCovarianceMatrix
                            ),
                            functions.transpose(x_minus_m)
                    );

            distances.add(Math.abs(tempDistanceMatrix.get(0).get(0)));
        }

        return distances;
    }

    private List<List<Double>> getCovarianceMatrix(List<List<Double>> data) {
        double[][] dataArray = new double[data.size()][data.get(0).size()];
        List<List<Double>> covarianceMatrix = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                dataArray[i][j] = data.get(i).get(j);
            }
        }

        RealMatrix matrix = MatrixUtils.createRealMatrix(dataArray);
        RealMatrix covariance = new Covariance(matrix, false).getCovarianceMatrix();

        dataArray = covariance.getData();

        for (int i = 0; i < dataArray.length; i++) {
            List<Double> line = new ArrayList<>();
            for (int j = 0; j < dataArray.length; j++) {
                line.add(dataArray[i][j]);
            }
            covarianceMatrix.add(line);
        }

        return covarianceMatrix;
    }

    private void fillInverseCovarianceMatrix(List<List<Double>> covarianceMatrix) {
        double[][] array = new double[covarianceMatrix.size()][covarianceMatrix.get(0).size()];
        int n = covarianceMatrix.size();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = covarianceMatrix.get(i).get(j);
            }
        }

        RealMatrix matrix = new Array2DRowRealMatrix(array, false);
        RealMatrix inverse = MatrixUtils.inverse(matrix);

        double[][] inverseArray = inverse.getData();

        for (int i = 0; i < n; i++) {
            List<Double> line = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                line.add(inverseArray[i][j]);
            }
            this.inverseCovarianceMatrix.add(line);
        }
    }

    @Override
    public MetricType getMetricType() {
        return MetricType.MAHALANOBIS;
    }
}
