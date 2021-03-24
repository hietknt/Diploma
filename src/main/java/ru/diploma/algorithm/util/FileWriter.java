package ru.diploma.algorithm.util;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.Neuron;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class FileWriter {
    private String APPEND_FILE_PATH = "_result.txt";
    private String APPEND_FILE_NEURON_PATH = "_neuronsResult.txt";
    private String PATH_PREFIX = "src/main/resources/results/";

    private List<Item> items;
    private List<Neuron> neurons;
    private String pathToData;
    private String appendToPath;

    public FileWriter setParams(List<Item> items, List<Neuron> neurons, String pathToData, String appendToPath){
        this.items = items;
        this.neurons = neurons;
        this.pathToData = pathToData
                .replace("/", "")
                .replace(".txt", "")
                .trim();
        this.appendToPath = appendToPath;

        return this;
    }

    public FileWriter writeData(){
        String fileName = PATH_PREFIX + pathToData + appendToPath + APPEND_FILE_PATH;
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));
            writer.write(convertItems(items));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public FileWriter writeNeurons(){
        String fileName = PATH_PREFIX + pathToData + appendToPath + APPEND_FILE_NEURON_PATH;
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));
            writer.write(convertNeurons(neurons));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    private String convertItems(List<Item> items){
        StringBuilder result = new StringBuilder();
        for (Item item : items){
            result.append(item.getClusterNumber())
                    .append(": ")
                    .append(item.getCoordinates())
                    .append("\n");
        }

        return result.toString();
    }

    private String convertNeurons(List<Neuron> neurons){
        StringBuilder result = new StringBuilder();
        for (Neuron neuron : neurons){
            result.append(neuron.getNumber())
                    .append(": ")
                    .append(neuron.getCoordinates())
                    .append("\n");
        }

        return result.toString();
    }
}
