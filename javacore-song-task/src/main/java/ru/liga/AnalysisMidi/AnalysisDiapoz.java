package ru.liga.AnalysisMidi;

import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.ArrayList;

public class AnalysisDiapoz {
    private final SimpleMidiFile simpleMidiFile;

   public AnalysisDiapoz(SimpleMidiFile simpleMidiFile)
    {
        this.simpleMidiFile=simpleMidiFile;
    }

    public  String GetAnalysis()
    {
        int maxIndex= 0;
        int minIndex = 0;
        for (int i = 0; i < simpleMidiFile.vocalNoteList().size(); i++) {
            if (simpleMidiFile.vocalNoteList().get(i).sign().higher(simpleMidiFile.vocalNoteList().get(maxIndex).sign())) {
                maxIndex = i;
            }
            if (simpleMidiFile.vocalNoteList().get(i).sign().lower(simpleMidiFile.vocalNoteList().get(minIndex).sign())) {
                minIndex = i;
            }

        }
        String max = simpleMidiFile.vocalNoteList().get(maxIndex).sign().fullName();
        String min = simpleMidiFile.vocalNoteList().get(minIndex).sign().fullName();
        int diapozon=simpleMidiFile.vocalNoteList().get(maxIndex).sign().getMidi() - simpleMidiFile.vocalNoteList().get(minIndex).sign().getMidi();
        return "Анализ диапазона:\r\n" +
                "верхняя: " + max + "\r\n" +
                "нижняя: " + min + "\r\n" +
                "диапазон: " + diapozon + "\r\n";
    }

}
