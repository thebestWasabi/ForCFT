package ru.khamzin;

import ru.khamzin.exception.SortItException;
import ru.khamzin.parser.ArgumentParser;

public class Main {

    public static void main(String[] args) {
        String[] str = new String[6];
        str[0] = "-i";         // Режим ввода
        str[1] = "-d";         // Режим сортировки (по умолчанию: возрастанию)
        str[2] = "out.txt";    // Выходной файл
        str[3] = "in1.txt";    // Первый входной файл (так же есть файл str1.txt в нем слова)
        str[4] = "in2.txt";    // Второй входной файл (так же есть файл str2.txt в нем слова)
        str[5] = "in3.txt";    // Третий входной файл (так же есть файл str3.txt в нем слова)

        try {
            var argumentParser = new ArgumentParser();                    // Создаю ArgumentParser для парсинга аргументов
            var options = argumentParser.parseArgument(str); // Делаю парсинг аргументов командной строки и получаю объект с параметрами
            var sortItProcessor = new SortItProcessor();                  // Создаю SortItProcessor для обработки сортировки
            sortItProcessor.process(options);                             // Запускаю процесс сортировки с переданными параметрами: options
        }
        catch (SortItException ex) {
            System.out.println(ex.getMessage()); // Пользовательское сообщение
        }
        catch (Exception e) {
            // TODO: 18.08.2023 Написать логи
            System.out.println("Unknown error");
        }
    }

}
