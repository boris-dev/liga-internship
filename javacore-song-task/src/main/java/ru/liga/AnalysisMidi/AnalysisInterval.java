package ru.liga.AnalysisMidi;

import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.HashMap;
import java.util.Map;

public class AnalysisInterval {

    private final SimpleMidiFile simpleMidiFile;
   public AnalysisInterval(SimpleMidiFile simpleMidiFile){
        this.simpleMidiFile=simpleMidiFile;
    }

    public Map<Integer, Integer> GetAnalysis()
    {
        Map<Integer, Integer> hashMapInterval = new HashMap<>();
        int interval;
        for (int i = 0; i < simpleMidiFile.vocalNoteList().size() - 1; i++) {
            interval = simpleMidiFile.vocalNoteList().get(i).sign().diffInSemitones(simpleMidiFile.vocalNoteList().get(i + 1).sign());
            Integer frequency = hashMapInterval.get(interval);
            hashMapInterval.put(interval, frequency == null ? 1 : frequency + 1);
        }

        String ResultStr="";
        for (Map.Entry entry : hashMapInterval.entrySet()) {
            ResultStr += entry.getKey() + " : "
                    + entry.getValue()  + "\r\n";
        }
        return hashMapInterval;
        //return "Анализ интервалов:" + "\r\n" + ResultStr;
    }
}
