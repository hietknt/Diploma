package ru.diploma.algorithm.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<Integer> readFullFileAndGetClustersOnly(String fullPath) {
        List<Integer> startedCluster = new ArrayList<>();

        try {
            BufferedReader bufferedReader =
                    new BufferedReader(
                            new java.io.FileReader(FileReader.class.getResource(fullPath).getPath().replaceFirst("/", "")));

            String line = bufferedReader.readLine();
            while(line != null) {
                String cluster = line.split(" ")[0].substring(0, 2);
                startedCluster.add(Integer.valueOf(cluster));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return startedCluster;
    }

    public List<Integer> readClusteredFileGetClusterOnly(String fullPath) {
        List<Integer> endedCluster = new ArrayList<>();

        try {
            BufferedReader bufferedReader =
                    new BufferedReader(
                            new java.io.FileReader(FileReader.class.getResource(fullPath).getPath().replaceFirst("/", "")));

            String line = bufferedReader.readLine();
            while(line != null) {
                String cluster = line.split(":")[0];
                endedCluster.add(Double.valueOf(cluster).intValue());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return endedCluster;
    }
}
