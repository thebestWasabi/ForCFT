package ru.khamzin.parser;

import ru.khamzin.entity.ModeType;
import ru.khamzin.entity.SortItOptions;
import ru.khamzin.entity.SortingMode;
import ru.khamzin.exception.ArgumentParserException;

import java.io.File;
import java.util.ArrayList;

public class ArgumentParser {

    public SortItOptions parseArgument(String[] args) {
        if (args == null) {
            throw new NullPointerException();
        }

        // Тут инициализация переменных для хранения параметров аргументов
        var modeType = ModeType.STRING;
        var sortingMode = SortingMode.ASC;
        var inputFiles = new ArrayList<String>();
        String outputFile = null;

        // Обрабатываю каждый аргумент из переданного массива
        for (String arg : args) {
            if (arg == null) {
                throw new ArgumentParserException("null argument");
            }

            switch (arg) {
                // Определяю режим сортировки и типа данных
                case "-a" -> sortingMode = SortingMode.ASC;
                case "-d" -> sortingMode = SortingMode.DESC;
                case "-i" -> modeType = ModeType.INTEGER;
                case "-s" -> modeType = ModeType.STRING;
                default -> {
                    // Если аргумент не является опцией, то проверяю, является ли он файлом
                    var file = new File(arg);
                    if (file.exists() && file.isFile()) {
                        if (outputFile == null) {
                            outputFile = arg;     // Устанавливаю выходной файл
                        }
                        else {
                            inputFiles.add(arg);  // Добавляю входной файл в список
                        }
                    }
                    else {
                        throw new ArgumentParserException("unknown argument: " + arg);
                    }
                }
            }
        }

        // Проверяю наличие выходного файла
        if (inputFiles == null) {
            throw new ArgumentParserException("No output file");
        }

        // Проверяю наличие входных файлов
        if (inputFiles.isEmpty()) {
            throw new ArgumentParserException("No files to sort");
        }

        return new SortItOptions(modeType, sortingMode, inputFiles, outputFile);
    }
}
