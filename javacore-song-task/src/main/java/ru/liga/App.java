package ru.liga;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.*;

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
    private static void printRangeAnalysis(List<Note> vocalNoteList) {
        Note bottomNote = null, topNote = null;
        for (Note note : vocalNoteList) {
            if (topNote == null ||
                    topNote.sign().getFrequencyHz() > note.sign().getFrequencyHz()) {
                topNote = note;
            }
            if (bottomNote == null ||
                    bottomNote.sign().getFrequencyHz() < note.sign().getFrequencyHz()) {
                bottomNote = note;
            }
        }
        System.out.println("Анализ диапазона:");
        System.out.println("верхняя: " + bottomNote.sign().fullName());
        System.out.println("нижняя: " + topNote.sign().fullName());
        System.out.println("диапазон: " + topNote.sign().diffInSemitones(bottomNote.sign()));
    }

    private static void printNoteDurationAnalysis(List<Note> vocalNoteList) {
        System.out.println("Анализ длительности нот (мс):");
        List<Note> lst = new ArrayList<>(vocalNoteList);
        lst.sort(Comparator.comparingLong(Note::durationTicks).reversed());
        int cnt = 0;
        for (int i = 0; i < lst.size(); i++) {
            cnt++;
            Note curNote = lst.get(i);
            if (i == lst.size() - 1 ||
                    !lst.get(i + 1).durationTicks().equals(curNote.durationTicks())) {
                System.out.println(curNote.durationTicks() + ": " + cnt);
                cnt = 0;
            }
        }
    }

    private static void printNoteHeightAnalysis(List<Note> vocalNoteList) {
        System.out.println("Анализ нот по высоте:");
        List<Note> lst = new ArrayList<>(vocalNoteList);
        lst.sort(Comparator.comparingDouble(note -> note.sign().getFrequencyHz()));
        int cnt = 0;
        for (int i = 0; i < lst.size(); i++) {
            cnt++;
            Note curNote = lst.get(i);
            if (i == lst.size() - 1 ||
                    !lst.get(i + 1).sign().getFrequencyHz().
                            equals(curNote.sign().getFrequencyHz())) {
                System.out.println(curNote.sign().fullName() + ": " + cnt);
                cnt = 0;
            }
        }
    }

    private static void printIntervalAnalysis(List<Note> vocalNoteList) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < vocalNoteList.size() - 1; i++) {
            Note curNote = vocalNoteList.get(i);
            Note nextNote = vocalNoteList.get(i + 1);
            Integer diff = curNote.sign().diffInSemitones(nextNote.sign());
            Integer cnt = map.get(diff);
            if (cnt == null) {
                cnt = 0;
            }
            map.put(diff, cnt + 1);
        }
        System.out.println("Анализ интервалов:");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
//        System.out.println("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
        List<Note> vocalNoteList = simpleMidiFile.vocalNoteList();
        System.out.println("Всего нот: " + vocalNoteList.size());
        System.out.println();
        printRangeAnalysis(vocalNoteList);
        System.out.println();
        printNoteDurationAnalysis(vocalNoteList);
        System.out.println();
        printNoteHeightAnalysis(vocalNoteList);
        System.out.println();
        printIntervalAnalysis(vocalNoteList);
    }
}
