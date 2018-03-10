package ru.liga.songtask.domain;


import com.leff.midi.event.NoteOn;

/**
 * Created by bshestakov on 13.07.2017.
 * <p>
 * Нота с длительностью
 */
public class Note {

    private final NoteSign note;

    private final Long durationTicks;

    private final Long startTick;

    public Note(NoteSign note, Long startTick, Long durationTicks) {
        this.note = note;
        this.startTick = startTick;
        this.durationTicks = durationTicks;
    }

    public NoteSign sign() {
        return note;
    }

    public Long durationTicks() {
        return durationTicks;
    }

    public Long startTick() {
        return startTick;
    }

    public Long endTickInclusive() {
        return startTick + durationTicks;
    }

    public boolean equalsToNoteOn(NoteOn noteOn) {
        return startTick().equals(noteOn.getTick()) && sign().getMidi().equals(noteOn.getNoteValue());
    }

    public boolean isInAccuracyInterval(Long tick) {
        return (tick >= startTick - durationTicks) && (tick <= startTick + durationTicks * 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note1 = (Note) o;

        if (note != note1.note) return false;
        if (durationTicks != null ? !durationTicks.equals(note1.durationTicks) : note1.durationTicks != null)
            return false;
        return startTick != null ? startTick.equals(note1.startTick) : note1.startTick == null;
    }

    @Override
    public int hashCode() {
        int result = note != null ? note.hashCode() : 0;
        result = 31 * result + (durationTicks != null ? durationTicks.hashCode() : 0);
        result = 31 * result + (startTick != null ? startTick.hashCode() : 0);
        return result;
    }
}
