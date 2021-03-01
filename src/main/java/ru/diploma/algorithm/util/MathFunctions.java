package ru.diploma.algorithm.util;

import java.util.ArrayList;
import java.util.List;

public class MathFunctions {
    public List<Double> difference(List<Double> firstList, List<Double> secondList){
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < firstList.size(); i++){
            result.add(firstList.get(i) - secondList.get(i));
        }

        return result;
    }

    public List<Double> multiplication(Double value, List<Double> list){
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){
            result.add(list.get(i) * value);
        }

        return result;
    }

    public List<Double> sum(List<Double> firstList, List<Double> secondList){
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < firstList.size(); i++){
            result.add(firstList.get(i) + secondList.get(i));
        }

        return result;
    }
}
