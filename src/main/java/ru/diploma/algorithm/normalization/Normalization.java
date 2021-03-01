package ru.diploma.algorithm.normalization;

import ru.diploma.algorithm.basic.Item;
import ru.diploma.algorithm.basic.NormalizationType;

import java.util.List;

public interface Normalization {
    List<Item> normalize(List<Item> data);
}
