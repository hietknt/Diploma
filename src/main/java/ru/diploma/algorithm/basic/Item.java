package ru.diploma.algorithm.basic;

import java.util.List;

/**
 * Class that presenting an item in Kohonen SOM
 */
public class Item {
    /**
     * Number of cluster that contains this item
     */
    private double clusterNumber;

    /**
     * Coordinates of item
     */
    private List<Double> coordinates;

    public Item(double clusterNumber, List<Double> coordinates) {
        this.clusterNumber = clusterNumber;
        this.coordinates = coordinates;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public double getClusterNumber() {
        return clusterNumber;
    }
    public void setClusterNumber(double clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    @Override
    public String toString() {
        return "Item{" +
                "clusterNumber=" + clusterNumber +
                ", coordinates=" + coordinates +
                '}';
    }
}
