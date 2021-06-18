package ru.diploma.algorithm.util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<Integer> readFullFileAndGetClustersOnly(String fullPath) {
        List<Integer> startedCluster = new ArrayList<>();

        try {
            Files.lines(Path.of(FileReader.class.getResource(fullPath).getPath())).forEach(line -> {
                String cluster = line.split(" ")[0].substring(0, 2);
                startedCluster.add(Integer.valueOf(cluster));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return startedCluster;
    }

    public List<Integer> readClusteredFileGetClusterOnly(String fullPath) {
        List<Integer> endedCluster = new ArrayList<>();

        try {
            Files.lines(Path.of(FileReader.class.getResource(fullPath).getPath())).forEach(line -> {
                String cluster = line.split(":")[0];
                endedCluster.add(Double.valueOf(cluster).intValue());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return endedCluster;
    }
}
