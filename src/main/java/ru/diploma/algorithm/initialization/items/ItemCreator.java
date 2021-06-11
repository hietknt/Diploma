package ru.diploma.algorithm.initialization.items;

import ru.diploma.algorithm.basic.Item;

import java.util.List;

public interface ItemCreator {
    List<Item> createItems(ItemInitializeType type);
}
