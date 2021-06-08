package ru.diploma.algorithm.util;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
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

    public List<List<Double>> differenceWithArray(List<List<Double>> array, List<Double> list) {
        List<List<Double>> result = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            result.add(difference(array.get(i), list));
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

    public List<List<Double>> multiplicationMatrix(List<List<Double>> firstMatrix, List<List<Double>> secondMatrix) {
        List<List<Double>> result = new ArrayList<>();
        int row1 = firstMatrix.size();
        int row2 = secondMatrix.size();
        int col2 = secondMatrix.get(0).size();

        List<Double> line;
        double value;
        for (int i = 0; i < row1; i++) {
            line = new ArrayList<>();
            for (int j = 0; j < col2; j++) {
                value = 0.0;
                for (int k = 0; k < row2; k++) {
                    value += firstMatrix.get(i).get(k) * secondMatrix.get(k).get(j);
                }
                line.add(value);
            }
            result.add(line);
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

    public List<List<Double>> transpose(List<List<Double>> list) {
        List<List<Double>> matrix = new ArrayList<>();
        List<Double> line;

        for (int i = 0; i < list.get(0).size(); i++) {
            line = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                line.add(list.get(j).get(i));
            }
            matrix.add(line);
        }

        return matrix;
    }

    public List<List<Double>> inverseMatrix(List<List<Double>> list) {
        List<List<Double>> identityMatrix = new ArrayList<>();
        List<List<Double>> matrix = new ArrayList<>();
        List<Double> line;

        for (int i = 0; i < list.size(); i++) {
            line = new ArrayList<>();
            for (int j = 0; j < list.get(i).size(); j++) {
                if (i == j) {
                    line.add(1.0);
                } else {
                    line.add(0.0);
                }
            }
            identityMatrix.add(line);
        }

        for (int i = 0; i < list.size(); i++) {
            matrix.add(multiplication(1.0/determinantOfMatrix(list), list.get(i)));
        }

        return matrix;
    }

    private void getCofactor(double mat[][], double temp[][], int p, int q, int n) {
        int i = 0, j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    private double determinantOfMatrix(double mat[][], int n)
    {
        int D = 0;
        if (n == 1)
            return mat[0][0];

        double temp[][] = new double[n][n];

        int sign = 1;

        for (int f = 0; f < n; f++) {
            getCofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f]
                    * determinantOfMatrix(temp, n - 1);
            sign = -sign;
        }
        return D;
    }

    public double determinantOfMatrix(List<List<Double>> matrix) {
        double[][] array = new double[matrix.size()][matrix.get(0).size()];
        int n = matrix.size();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = matrix.get(i).get(j);
            }
        }

        return determinantOfMatrix(array, n);
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

    public void findCovarianceMatrix() {

    }
}
