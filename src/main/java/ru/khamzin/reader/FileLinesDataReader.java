package ru.khamzin.reader;

import ru.khamzin.exception.DataReaderException;
import ru.khamzin.exception.NullFileNameException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLinesDataReader extends DataReader<String> {

    private String fileName;

    public FileLinesDataReader(String fileName) {
        if (fileName == null) {
            throw new NullFileNameException("File name cannot be null");
        }
        this.fileName = fileName;
    }

    @Override
    public List<String> readData() {
        List<String> result = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                result.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new DataReaderException("Can't read file: " + fileName);
        }
        return result;
    }
}
