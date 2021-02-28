package ru.diploma.algorithm.basic;

import java.util.List;

/**
 * Class that presenting an center of cluster in Kohonen SOM
 */
public class Neuron {
    /**
     * Number of cluster
     */
    private int number;

    /**
     * Coordinates of neuron
     */
    private List<Double> coordinates;

    /**
     * Radius of cluster
     */
    private double radius;

    /**
     * Form of cluster's radius
     */
    private RadiusForm radiusForm;

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public RadiusForm getRadiusForm() {
        return radiusForm;
    }
    public void setRadiusForm(RadiusForm radiusForm) {
        this.radiusForm = radiusForm;
    }

    @Override
    public String toString() {
        return "Neuron{" +
                "number=" + number +
                ", coordinates=" + coordinates +
                ", radius=" + radius +
                ", radiusForm=" + radiusForm +
                '}';
    }
}
