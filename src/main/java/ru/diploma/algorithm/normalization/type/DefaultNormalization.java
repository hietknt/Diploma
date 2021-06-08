package ru.diploma.algorithm.normalization.type;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.normalization.Normalization;

import java.util.List;

public class DefaultNormalization implements Normalization {

    @Override
    public List<Item> normalize(List<Item> data) {
        double paramsSize = data.get(0).getCoordinates().size();

        double value;
        double minValue;
        double maxValue;

        double a, b;
        for (int i = 0; i < paramsSize; i++){
            minValue = data.get(0).getCoordinates().get(i);
            maxValue = data.get(0).getCoordinates().get(i);
            for (int j = 0; j < data.size(); j++){

                value = data.get(j).getCoordinates().get(i);
                if (value > maxValue){
                    maxValue = value;
                }
                if (value < minValue){
                    minValue = value;
                }
            }

            a = 1 / (maxValue - minValue);
            b = -minValue / (maxValue - minValue);
            for (int j = 0; j < data.size(); j++){
                data.get(j).getCoordinates().set(i, (data.get(j).getCoordinates().get(i) * a + b));
            }
        }

        return data;
    }
}
