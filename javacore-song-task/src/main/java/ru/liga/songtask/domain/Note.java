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


}
