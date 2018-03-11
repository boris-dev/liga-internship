package ru.liga;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Всего нот: 15
 * <p>
 * Анализ диапазона:
 * верхняя: E4
 * нижняя: F3
 * диапазон: 11
 * <p>
 * Анализ длительности нот (мс):
 * 4285: 10
 * 2144: 5
 * <p>
 * Анализ нот по высоте:
 * E4: 3
 * D4: 3
 * A3: 3
 * G3: 3
 * F3: 3
 * <p>
 * Анализ интервалов:
 * 2: 9
 * 5: 3
 * 11: 2
 */
public class App {
    public static void main(String[] args) {
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
        System.out.println("Всего нот: " + simpleMidiFile.vocalNoteList().size());
        System.out.println("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
        System.out.println("<p>\nАнализ диапазона:");
        List<Note> notes = simpleMidiFile.vocalNoteList();
        Note minNote = notes.get(0);
        Note maxNote = notes.get(0);
        for(Note note : notes){
            if(note.sign().higher(maxNote.sign())){
                maxNote = note;
            }
            if(note.sign().lower(minNote.sign())){
                minNote = note;
            }
        }
        System.out.println("верхняя: " + maxNote.sign().fullName());
        System.out.println("нижняя: " + minNote.sign().fullName());
        System.out.println("диапазон: " + maxNote.sign().diffInSemitones(minNote.sign()) + "\n<p>");

        System.out.println("Анализ длительности нот (мс):");
        Map<Long, Integer> durations = new HashMap<>();
        for(Note note : notes){
            if(durations.containsKey(note.durationTicks())){
                int temp = durations.get(note.durationTicks());
                durations.put(note.durationTicks(), ++temp);
            }
            else {
                durations.put(note.durationTicks(), 1);
            }
        }
        for (Map.Entry<Long, Integer> entry : durations.entrySet())
        {
            System.out.println((int)(entry.getKey() * simpleMidiFile.tickInMs()) + ": " + entry.getValue());
        }

        System.out.println("<p>\nАнализ нот по высоте:");
        Map<String, Integer> heights = new HashMap<>();
        for(Note note : notes){
            if(heights.containsKey(note.sign().fullName())){
                int temp = heights.get(note.sign().fullName());
                heights.put(note.sign().fullName(), ++temp);
            }
            else {
                heights.put(note.sign().fullName(), 1);
            }
        }
        for (Map.Entry<String, Integer> entry : heights.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("<p>\nАнализ интервалов:");
        Map<Integer, Integer> intervals = new HashMap<>();
        for(int i = 0; i < notes.size() - 1; i++) {
            int interval = notes.get(i).sign().diffInSemitones(notes.get(i + 1).sign());
            if (intervals.containsKey(interval)) {
                int temp = intervals.get(interval);
                intervals.put(interval, ++temp);
            } else {
                intervals.put(interval, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
