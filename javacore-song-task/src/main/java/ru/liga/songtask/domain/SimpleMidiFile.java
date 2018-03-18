package ru.liga.songtask.domain;


import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


/**
 * Created by Lenovo on 15.08.2017.
 */
public class SimpleMidiFile {
    private MidiFile midiFile;
    public SimpleMidiFile(String base64EncodedString) {
        try {
            InputStream is = new ByteArrayInputStream(Base64.decodeBase64(base64EncodedString.getBytes()));
            this.midiFile = new MidiFile(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SimpleMidiFile(File file) {
        try {
            this.midiFile = new MidiFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Note> vocalNoteList() {
        return noteList(0);
    }

    public List<Note> noteList(Integer channelNumber) {
        List<Note> vbNotes = new ArrayList<>();
        TreeSet<MidiEvent> events = getEventsByChannel(channelNumber).getEvents();
        NoteOn noteOn = null;
        for (MidiEvent event : events) {
            if (event instanceof NoteOff) {
                NoteOff noteOff = (NoteOff) event;
                vbNotes.add(
                        new Note(NoteSign.fromMidiNumber(noteOff.getNoteValue()), noteOn.getTick(), (noteOff.getTick() - noteOn.getTick()))
                );
            } else if (event instanceof NoteOn) {
                noteOn = (NoteOn) event;
            }
        }
        return vbNotes;
    }

    public Float tickInMs() {
        Tempo tempo = (Tempo) (midiFile.getTracks().get(0).getEvents()).stream()
                .filter(value -> value instanceof Tempo)
                .findFirst()

                .get();
        return (60 * 1000) / (tempo.getBpm() * midiFile.getResolution());
    }

    private MidiTrack getEventsByChannel(Integer channelNumber) {
        for (MidiTrack midiTrack : midiFile.getTracks()) {
            if ((midiTrack.getEvents().stream()
                    .anyMatch(value -> (value instanceof NoteOn) && channelNumber.equals(((NoteOn) value).getChannel())))) {
                return midiTrack;
            }
        }
        return null;
    }

    public Note getNoteByEvent(NoteOn noteOn, int channelNumber) {
        List<Note> vbNotes = noteList(channelNumber);
        return (vbNotes).stream()
                .filter(value -> value.equalsToNoteOn(noteOn))
                .findAny()
                .orElse(null);
    }


    public MidiFile getMidiFormat() {
        return midiFile;
    }





    private List<Integer> createTransponationScheme(Integer toHighNoteCount, Integer toLowNoteCount) {
        List<Integer> transponationScheme = new ArrayList<>();
        for (int i = 1; i <= toHighNoteCount; i++) {
            transponationScheme.add(i);
        }

        for (int i = 1; i <= (toHighNoteCount + toLowNoteCount); i++) {
            transponationScheme.add(toHighNoteCount - i);
        }

        return transponationScheme;
    }

    private boolean hasNotes(TreeSet<MidiEvent> events) {
        return (events).stream()
                .anyMatch(value -> value instanceof NoteOn);
    }

    private void generateOtherSamples(MidiTrack midiTrack, long lengthInTicks, List<Integer> transponationScheme) {
        TreeSet<MidiEvent> sampleEvents = copySampleEvents(midiTrack.getEvents());
        generateSamplesByTransponationScheme(midiTrack, lengthInTicks, sampleEvents, transponationScheme);
    }


    private TreeSet<MidiEvent> copySampleEvents(TreeSet<MidiEvent> events) {
        TreeSet<MidiEvent> midiEvents = new TreeSet<>();
        midiEvents.addAll(events);
        return midiEvents;
    }

    private void generateSamplesByTransponationScheme(MidiTrack midiTrack, long lengthInTicks, TreeSet<MidiEvent> sampleEvents, List<Integer> transponationScheme) {
        Long currentTick = 0L;
        for (int i = 0; i < transponationScheme.size(); i++) {
            Integer transponationDiff = transponationScheme.get(i);
            currentTick = lengthInTicks * (i + 1);
            addSample(midiTrack, currentTick, sampleEvents, transponationDiff);
        }
    }

    private void addSample(MidiTrack midiTrack, Long currentTick, TreeSet<MidiEvent> sampleEvents, int t) {
        for (MidiEvent sampleEvent : sampleEvents) {
            if (sampleEvent instanceof NoteOn) {
                NoteOn noteOn = (NoteOn) sampleEvent;
                midiTrack.insertEvent(createEvent(noteOn, currentTick, t));
            }

            if (sampleEvent instanceof NoteOff) {
                NoteOff noteOff = (NoteOff) sampleEvent;
                midiTrack.insertEvent(createEvent(noteOff, currentTick, t));
            }
        }
    }

    private MidiEvent createEvent(NoteOn noteOn, Long currentTick, int transponation) {
        return new NoteOn(currentTick + noteOn.getTick(), noteOn.getChannel(), noteOn.getNoteValue() + transponation, noteOn.getVelocity());
    }

    private MidiEvent createEvent(NoteOff noteOff, Long currentTick, int transponation) {
        return new NoteOff(currentTick + noteOff.getTick(), noteOff.getChannel(), noteOff.getNoteValue() + transponation, noteOff.getVelocity());
    }

    public Long durationMs() {
        return (long) (midiFile.getLengthInTicks() * tickInMs());
    }


}
