package ru.liga;


import com.leff.midi.MidiFile;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.util.SongUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.LinkedBlockingQueue;

public class App {

    /**
     * Это пример работы, можете всё стирать и переделывать
     * Пример, чтобы убрать у вас начальный паралич разработки
     * Также посмотрите класс SongUtils, он переводит тики в миллисекунды
     */
    public static void main(String[] args) throws IOException {
        MidiFile midiFile = new MidiFile(new FileInputStream("C:\\Users\\Xiaomi\\IdeaProjects\\liga-internship\\javacore-song-task\\src\\main\\resources\\Wrecking Ball.mid"));
        List<Note> notes = eventsToNotes(midiFile.getTracks().get(3).getEvents());
        Tempo last = (Tempo) midiFile.getTracks().get(0).getEvents().last();
        Note ninthNote = notes.get(8);
        System.out.println("Длительность девятой ноты (" + ninthNote.sign().fullName() + "): " + SongUtils.tickToMs(last.getBpm(), midiFile.getResolution(), ninthNote.durationTicks()) + "мс");
        System.out.println("Все ноты:");
        System.out.println(notes);
    }

    /**
     * Этот метод, чтобы вы не афигели переводить эвенты в ноты
     *
     * @param events эвенты одного трека
     * @return список нот
     */
    public static List<Note> eventsToNotes(TreeSet<MidiEvent> events) {
        List<Note> vbNotes = new ArrayList<>();

        Queue<NoteOn> noteOnQueue = new LinkedBlockingQueue<>();
        for (MidiEvent event : events) {
            if (event instanceof NoteOn || event instanceof NoteOff) {
                if (isEndMarkerNote(event)) {
                    NoteSign noteSign = NoteSign.fromMidiNumber(extractNoteValue(event));
                    if (noteSign != NoteSign.NULL_VALUE) {
                        NoteOn noteOn = noteOnQueue.poll();
                        if (noteOn != null) {
                            long start = noteOn.getTick();
                            long end = event.getTick();
                            vbNotes.add(
                                    new Note(noteSign, start, end - start));
                        }
                    }
                } else {
                    noteOnQueue.offer((NoteOn) event);
                }
            }
        }
        return vbNotes;
    }

    private static Integer extractNoteValue(MidiEvent event) {
        if (event instanceof NoteOff) {
            return ((NoteOff) event).getNoteValue();
        } else if (event instanceof NoteOn) {
            return ((NoteOn) event).getNoteValue();
        } else {
            return null;
        }
    }

    private static boolean isEndMarkerNote(MidiEvent event) {
        if (event instanceof NoteOff) {
            return true;
        } else if (event instanceof NoteOn) {
            return ((NoteOn) event).getVelocity() == 0;
        } else {
            return false;
        }

    }
}
