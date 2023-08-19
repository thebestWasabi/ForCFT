package ru.khamzin.writer;

import ru.khamzin.exception.DataWriterException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileDataWriter {

    public void writeData(List data, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));  // Открываю файл для записи

            // Записываю каждый элемент списка данных в файл
            for (Object datum : data) {
                writer.write(datum.toString());  // Преобразую элемент в строку и записываю
                writer.newLine();                // Перехожу на следующую строку
            }
            writer.close();

        } catch (IOException e) {
            throw new DataWriterException("Can't write data to file: " + fileName);
        }
    }
}
