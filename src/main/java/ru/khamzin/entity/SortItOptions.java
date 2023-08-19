package ru.khamzin.entity;

import java.util.Collection;

// Этот класс что-то вроде DTO - передает данные между слоями приложения
public record SortItOptions(
        ModeType modeType,
        SortingMode sortingMode,
        Collection<String> inputFiles,
        String outputFile
)
{

}
