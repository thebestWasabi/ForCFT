package ru.khamzin.reader;

import java.util.List;

public abstract class DataReader<T extends Comparable<T>> {

    public abstract List<T> readData();

}
