package ru.diploma.algorithm.normalization;

import ru.diploma.algorithm.normalization.type.DefaultNormalization;

import java.util.HashMap;
import java.util.Map;

public class NormalizationPicker {

    private Map<NormalizationType, Normalization> normalizationMap = new HashMap<>();

    public NormalizationPicker() {
        normalizationMap.put(NormalizationType.DEFAULT, new DefaultNormalization());
    }

    public Normalization getNormalizationByType(NormalizationType type){
        return normalizationMap.get(type);
    }
}
