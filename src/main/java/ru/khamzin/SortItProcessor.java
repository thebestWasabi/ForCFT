package ru.khamzin;

import ru.khamzin.entity.ModeType;
import ru.khamzin.entity.SortItOptions;
import ru.khamzin.exception.SortItException;
import ru.khamzin.reader.DataReader;
import ru.khamzin.reader.FileIntegerDataReader;
import ru.khamzin.reader.FileLinesDataReader;
import ru.khamzin.sorted.MergeSorter;
import ru.khamzin.writer.FileDataWriter;

import java.util.ArrayList;
import java.util.List;

class SortItProcessor {

    public void process(SortItOptions options) {
        var mergeSorter = new MergeSorter();     // Создание MergeSorter для сортировки

        List<List> data = new ArrayList<List>(); // Список для хранения всех данных из входных файлов

        // Обрабатываю каждый входной файл
        for (String file : options.inputFiles()) {
            var dataReader = getDataReader(file, options.modeType()); // Получаю объект DataReader в зависимости от типа данных
            data.addAll(dataReader.readData());                                   // Читаю данные из файла и добавляю в общий список: data
        }

        data = mergeSorter.sort(data, options.sortingMode());  // Применяю свою кастомную сортировку слиянием к общему списку данных: data

        FileDataWriter writer = new FileDataWriter();          // Создаю FileDataWriter для записи данных в выходной файл
        writer.writeData(data, options.outputFile());          // Записываю отсортированные данные в выходной файл (out.txt)
    }

    // Получаю объект DataReader в зависимости от типа данных
    public DataReader getDataReader(String fileName, ModeType modeType) {
        return switch (modeType) {
            case STRING -> new FileLinesDataReader(fileName);    // Тут все понятно, строковый тип
            case INTEGER -> new FileIntegerDataReader(fileName); // А тут целочисленный тип
            default -> throw new SortItException("Unexpected modeType");
        };
    }
}
