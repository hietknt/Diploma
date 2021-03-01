package ru.diploma.algorithm.basic;

import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Neuron{" +
                "number=" + number +
                ", coordinates=" + coordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neuron neuron = (Neuron) o;
        return number == neuron.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
