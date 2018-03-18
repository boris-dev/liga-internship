package ru.liga.AnalysisMidi;

import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.HashMap;
import java.util.Map;

 class note {
    Long time;
    int count;

    note(Long ThatTime, int ThatCount) {
        this.count = ThatCount;
        this.time = ThatTime;
    }
}

public class AnalysisDuration {
    private final SimpleMidiFile simpleMidiFile;
    public AnalysisDuration(SimpleMidiFile simpleMidiFile) {
        this.simpleMidiFile = simpleMidiFile;
    }

    public Map<Float, Integer> GetAnalysis() {

        Map<String, note> hashMapNoteTime = new HashMap<>();

        for (int i = 0; i < simpleMidiFile.vocalNoteList().size(); i++) {

            note note = hashMapNoteTime.get(simpleMidiFile.vocalNoteList().get(i).sign().fullName());
            if (note == null) {
                note new_note = new note(simpleMidiFile.vocalNoteList().get(i).durationTicks(), 1);
                hashMapNoteTime.put(simpleMidiFile.vocalNoteList().get(i).sign().fullName(), new_note);
            } else {
                int new_count = note.count + 1;
                Long new_time = note.time + simpleMidiFile.vocalNoteList().get(i).durationTicks();
                note new_note = new note(new_time, new_count);
                hashMapNoteTime.put(simpleMidiFile.vocalNoteList().get(i).sign().fullName(), new_note);
            }
        }
        String ResultStr = "";

        for (Map.Entry entry : hashMapNoteTime.entrySet()) {
            ResultStr += hashMapNoteTime.get(entry.getKey()).time * simpleMidiFile.tickInMs() + " : " + hashMapNoteTime.get(entry.getKey()).count + "\r\n";

        }
        Map<Float, Integer> returnhashMapNote = new HashMap<>();
        for (Map.Entry entry : hashMapNoteTime.entrySet()) {
            ResultStr += hashMapNoteTime.get(entry.getKey()).time * simpleMidiFile.tickInMs() + " : " + hashMapNoteTime.get(entry.getKey()).count + "\r\n";
            returnhashMapNote.put(hashMapNoteTime.get(entry.getKey()).time * simpleMidiFile.tickInMs(),hashMapNoteTime.get(entry.getKey()).count);
        }
        return returnhashMapNote;
    //return "Анализ длительности нот (мс):" + "\r\n" + ResultStr;
    }
}
