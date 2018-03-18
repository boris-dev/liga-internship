package ru.liga.AnalysisMidi;

import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.HashMap;
import java.util.Map;

public class AnalysisHeight {
    private final SimpleMidiFile simpleMidiFile;

    public AnalysisHeight(SimpleMidiFile simpleMidiFile) {
        this.simpleMidiFile = simpleMidiFile;
    }

    public Map<String, Integer> GetAnalysis() {
        Map<String, Integer> hashMapNote = new HashMap<>();
        for (int i = 0; i < simpleMidiFile.vocalNoteList().size(); i++) {

            Integer count = hashMapNote.get(simpleMidiFile.vocalNoteList().get(i).sign().fullName());
            hashMapNote.put(simpleMidiFile.vocalNoteList().get(i).sign().fullName(), count == null ? 1 : count + 1);
        }
        String ResultStr = "";

        for (Map.Entry entry : hashMapNote.entrySet()) {
            ResultStr += entry.getKey() + " : "
                    + entry.getValue() + "\r\n";
        }
        return hashMapNote;
      //  return "Анализ нот по высоте:" + "\r\n" + ResultStr;
    }
}
