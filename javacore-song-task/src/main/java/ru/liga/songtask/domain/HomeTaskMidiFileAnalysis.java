package ru.liga.songtask.domain;


import ru.liga.songtask.content.Content;

import java.util.*;

/**
 * Created by ildar on 10.03.18.
 */
public class HomeTaskMidiFileAnalysis extends SimpleMidiFile {

    /**
     * Компаратор, с пом. которого сортируются ноты по частоте, от меньшей к большей.
     */
    private Comparator<Note> fromLowestNoteToHighByHz = new Comparator<Note>() {
        @Override
        public int compare(Note note, Note t1) {
            return note.sign().getFrequencyHz().compareTo(t1.sign().getFrequencyHz());
        }
    };

    /**
     * Конструктор, который в кач-ве параметра принимает закодированную строку.
     *
     * @param base64EncodedString - ноты в формате 64EncodedString.
     */
    public HomeTaskMidiFileAnalysis(String base64EncodedString) {
        super(base64EncodedString);
    }
    public void printAllVocalNotes() {
        System.out.printf("Oбщее количество вокальных нот: %d.%n", vocalNoteList().size());
    }
    /**
     * @param allVocalNotes - список с нотами вокальной партии.
     * @return - нота, с самой выскокой частотой.
     */
    public Note getHighestNote(List<Note> allVocalNotes) {

        allVocalNotes.sort(fromLowestNoteToHighByHz);

        return allVocalNotes.get(allVocalNotes.size() - 1);
    }

    /**
     * @param allVocalNotes - список со всеми вокальными нотами.
     * @return - нота, с самой низкой частотой.
     */
    public Note lowestNoteIs(List<Note> allVocalNotes) {

        allVocalNotes.sort(fromLowestNoteToHighByHz);

        return allVocalNotes.get(0);

    }

    /**
     * @param current    - текущая нота.
     * @param higherNote - след нота в списке.
     * @return - полутон(диапазон) между текущей и следующей нотой.
     */
    public Integer findsARangeBetweenNotes(Note current, Note higherNote) {

        return current.sign().diffInSemitones(higherNote.sign());

    }

    /**
     * @param allVocalNotes - все ноты в вокальной партии.
     * Печать карты с ключами : длительность ноты - количество ноты.
     */
    public void printMapWithDurationAndAmountOfThem(List<Note> allVocalNotes) {
        int amountOfNotesWithSameDuration = 0;
        Set<Long> diferrentDurations = new TreeSet<>();
        Map<Long, Integer> resultMap = new TreeMap<>();

        for (Note note : allVocalNotes) {
            diferrentDurations.add(note.durationTicks());
        }

        for (Long durationOfNote : diferrentDurations) {
            for (Note note : allVocalNotes) {
                if (durationOfNote.compareTo(note.durationTicks()) == 0) {
                    amountOfNotesWithSameDuration++;

                }
            }
            resultMap.put(durationOfNote, amountOfNotesWithSameDuration);
            amountOfNotesWithSameDuration = 0;
        }
        System.out.printf("%s%n", "Анализ нот по длительности:");
        System.out.printf("%s %10s %n", "Длит. ноты(мс)", "кол. нот");
        for (Map.Entry<Long, Integer> entry : resultMap.entrySet()) {
            System.out.printf("%10d %2s %5d%n", entry.getKey(), ":", entry.getValue());
        }
    }

    /**
     * @param vocalNotes - список с вок. нотами.
     * печать карты, ключ знач которой -  полное название ноты, её высота.
     */
    public void getStingAnalysNotesByMidiHeigh(List<Note> vocalNotes) {
        Map<String, Integer> mapWithAnalysByMidiHeigh = new TreeMap<>();

        for (Note note : vocalNotes) {
            if (!mapWithAnalysByMidiHeigh.containsKey(note.sign().fullName())) {
                mapWithAnalysByMidiHeigh.put(note.sign().fullName(), note.sign().getMidi());
            }
        }
        System.out.printf("%s%n", "Aнализ нот по высоте:");
        System.out.printf("%12s %10s %n", "Имя ноты", "высота");
        for (Map.Entry<String, Integer> stringIntegerEntry : mapWithAnalysByMidiHeigh.entrySet()) {
            System.out.printf("%10s %2s %5d%n", stringIntegerEntry.getKey(), ":", stringIntegerEntry.getValue());
        }
    }

    /**
     * @param vocalNotes - список с вок. нотами.
     * печать карты, ключ знач которой - интервал между нотами, кол-во такого интервала.
     */
    public void showIntervalsBetweenNotes(List<Note> vocalNotes) {

        int amountOfIntervals = 0;
        Map<Integer, Integer> resultMapWithIntervalAnalys = new TreeMap<>();
        Set<Integer> intervals = new TreeSet<>();

        for (int i = 0; i < vocalNotes.size() - 1; i++) {
            intervals.add(findsARangeBetweenNotes(vocalNotes.get(i), vocalNotes.get(i + 1)));
        }

        for (Integer currentInterval : intervals) {
            for (int i = 0; i < vocalNotes.size() - 1; i++) {
                if (currentInterval.equals(findsARangeBetweenNotes(vocalNotes.get(i), vocalNotes.get(i + 1)))) {
                    amountOfIntervals++;
                }
            }
            resultMapWithIntervalAnalys.put(currentInterval, amountOfIntervals);
            amountOfIntervals = 0;
        }

        System.out.printf("%s%n", "Aнализ нот по интервалам:");
        System.out.printf("%12s %10s %n", "Интервал", "кол. интервалов");
        for (Map.Entry<Integer, Integer> resultEntry : resultMapWithIntervalAnalys.entrySet()) {
            System.out.printf("%10s %2s %5d%n", resultEntry.getKey(), ":", resultEntry.getValue());
        }

    }

    /**
     * печать полного анализа вокальной партии песни Zombie группы The Cranberries.
     */
    public void printFullAnalysOfNotes() {
        HomeTaskMidiFileAnalysis tmpObj = new HomeTaskMidiFileAnalysis(Content.ZOMBIE);
        List<Note> vocalNoteList = tmpObj.vocalNoteList();
        Note highestNote = tmpObj.getHighestNote(vocalNoteList);
        Note lowestNote = tmpObj.lowestNoteIs(vocalNoteList);

        System.out.printf("%45s%n", "Анализ вокальной партии песни Zombie:");
        printAllVocalNotes();
        System.out.println("Анализ диапазона: ");
        System.out.printf("Самая высокая нота: %s c частотой %.3f Hz. %n", highestNote.sign().fullName(), highestNote.sign().getFrequencyHz());
        System.out.printf("Самая низкая нота: %s c частотой %.3f Hz. %n", lowestNote.sign().fullName(), lowestNote.sign().getFrequencyHz());
        System.out.printf("Диапазон: %d. %n", tmpObj.findsARangeBetweenNotes(lowestNote, highestNote));
        printMapWithDurationAndAmountOfThem(vocalNoteList);
        getStingAnalysNotesByMidiHeigh(vocalNoteList);
        showIntervalsBetweenNotes(vocalNoteList);

    }

}


