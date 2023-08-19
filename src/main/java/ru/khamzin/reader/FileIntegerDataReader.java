package ru.khamzin.reader;

import ru.khamzin.exception.DataReaderException;
import ru.khamzin.exception.NullFileNameException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIntegerDataReader extends DataReader<Integer> {

    private String fileName;

    public FileIntegerDataReader(String fileName) {
        // TODO: 18.08.2023 Проверить что не null, по все программе
        if (fileName == null) {
            throw new NullFileNameException("File name cannot be null");
        }
        this.fileName = fileName;
    }

    @Override
    public List<Integer> readData() {
        List<Integer> result = new ArrayList<>();

        try {
            // Открываю файл для чтения
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                try {
                    // Пытаюсь преобразовать строку в Integer и добавить в список
                    result.add(Integer.parseInt(line));
                    line = bufferedReader.readLine();
                } catch (NumberFormatException e) {
                    throw new DataReaderException(String.format("Can't parse line: %s as Integer", line));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new DataReaderException(String.format("Can't read file: %s", fileName));
        }
        return result;
    }
}
