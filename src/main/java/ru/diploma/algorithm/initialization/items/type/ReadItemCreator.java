package ru.diploma.algorithm.initialization.items.type;

import ru.diploma.algorithm.basic.Item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadItemCreator {

    public List<Item> create(String filePath){

        List<Item> items = new ArrayList<>();

        try {
            Files.lines(Path.of(ReadItemCreator.class.getResource("/data.txt").getPath())).forEach(line -> {
                List<Double> coordinates = new ArrayList<>();
                String[] lineArray = line.split(" ");
                for (String number : lineArray){
                    coordinates.add(Double.valueOf(number));
                }
                items.add(new Item(0, coordinates));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }
}
