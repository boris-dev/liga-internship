package ru.liga.songtask.domain;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by bshestakov on 13.07.2017.
 * <p>
 * Нота
 */
public enum NoteSign {

    H_7("H", 7, 3951.07d, 107),
    A_SHARP_7("A#", 7, 3729.31d, 106),
    A_7("A", 7, 3520d, 105),
    G_SHARP_7("G#", 7, 3322.44d, 104),
    G_7("G", 7, 3135.96d, 103),
    F_SHARP_7("F#", 7, 2959.96d, 102),
    F_7("F", 7, 2793.83d, 101),
    E_7("E", 7, 2637.02d, 100),
    D_SHARP_7("D#", 7, 2489.02d, 99),
    D_7("D", 7, 2349.32d, 98),
    C_SHARP_7("C#", 7, 2217.46d, 97),
    C_7("C", 7, 2093d, 96),
    H_6("H", 6, 1975.53d, 95),
    A_SHARP_6("A#", 6, 1864.66d, 94),
    A_6("A", 6, 1760d, 93),
    G_SHARP_6("G#", 6, 1661.22d, 92),
    G_6("G", 6, 1567.98d, 91),
    F_SHARP_6("F#", 6, 1479.98d, 90),
    F_6("F", 6, 1396.91d, 89),
    E_6("E", 6, 1318.51d, 88),
    D_SHARP_6("D#", 6, 1244.51d, 87),
    D_6("D", 6, 1174.66, 86),
    C_SHARP_6("C#", 6, 1108.73d, 85),
    C_6("C", 6, 1046.5d, 84),
    H_5("H", 5, 987.767d, 83),
    A_SHARP_5("A#", 5, 932.328d, 82),
    A_5("A", 5, 880d, 81),
    G_SHARP_5("G#", 5, 830.609d, 80),
    G_5("G", 5, 783.991d, 79),
    F_SHARP_5("F#", 5, 739.989d, 78),
    F_5("F", 5, 698.456d, 77),
    E_5("E", 5, 659.255d, 76),
    D_SHARP_5("D#", 5, 622.254d, 75),
    D_5("D", 5, 587.33d, 74),
    C_SHARP_5("C#", 5, 554.365d, 73),
    C_5("C", 5, 523.251d, 72),
    H_4("H", 4, 493.883d, 71),
    A_SHARP_4("A#", 4, 466.164d, 70),
    A_4("A", 4, 440d, 69),
    G_SHARP_4("G#", 4, 415.305d, 68),
    G_4("G", 4, 391.995d, 67),
    F_SHARP_4("F#", 4, 369.994d, 66),
    F_4("F", 4, 349.228d, 65),
    E_4("E", 4, 329.628d, 64),
    D_SHARP_4("D#", 4, 311.127d, 63),
    D_4("D", 4, 293.665d, 62),
    C_SHARP_4("C#", 4, 277.183d, 61),
    C_4("C", 4, 261.626d, 60),
    H_3("H", 3, 246.942d, 59),
    A_SHARP_3("A#", 3, 233.082d, 58),
    A_3("A", 3, 220d, 57),
    G_SHARP_3("G#", 3, 207.652d, 56),
    G_3("G", 3, 195.998d, 55),
    F_SHARP_3("F#", 3, 184.997d, 54),
    F_3("F", 3, 174.614d, 53),
    E_3("E", 3, 164.814d, 52),
    D_SHARP_3("D#", 3, 155.563d, 51),
    D_3("D", 3, 146.832d, 50),
    C_SHARP_3("C#", 3, 138.591d, 49),
    C_3("C", 3, 130.813d, 48),
    H_2("H", 2, 123.471d, 47),
    A_SHARP_2("A#", 2, 116.541d, 46),
    A_2("A", 2, 110d, 45),
    G_SHARP_2("G#", 2, 103.826d, 44),
    G_2("G", 2, 97.9989d, 43),
    F_SHARP_2("F#", 2, 92.4986d, 42),
    F_2("F", 2, 87.3071d, 41),
    E_2("E", 2, 82.4069d, 40),
    D_SHARP_2("D#", 2, 77.7817d, 39),
    D_2("D", 2, 73.4162d, 38),
    C_SHARP_2("C#", 2, 69.2957d, 37),
    C_2("C", 2, 65.4064d, 36),
    H_1("H", 1, 61.7354d, 35),
    A_SHARP_1("A#", 1, 58.2705d, 34),
    A_1("A", 1, 55d, 33),
    G_SHARP_1("G#", 1, 51.9131d, 32),
    G_1("G", 1, 48.9994d, 31),
    F_SHARP_1("F#", 1, 46.2493d, 30),
    F_1("F", 1, 43.6535d, 29),
    E_1("E", 1, 41.2034d, 28),
    D_SHARP_1("D#", 1, 38.8909d, 27),
    D_1("D", 1, 36.7081d, 26),
    C_SHARP_1("C#", 1, 34.6478d, 25),
    C_1("C", 1, 32.7032d, 24),
    H_0("H", 0, 30.8677d, 23),
    A_SHARP_0("A#", 0, 29.1352d, 22),
    A_0("A", 0, 27.5d, 21),

    NULL_VALUE("NO_NOTE", 0, 0d, 0);

    /**
     * Имя ноты
     */
    private final String noteName;

    /**
     * Октава ноты
     */
    private final Integer octave;

    /**
     * Номер ноты в миди формате
     */
    private final Integer midi;

    /**
     * Частота ноты (физическая величена в Hz)
     */
    private final Double frequency_hz;

    NoteSign(String noteName, Integer octave, Double frequency_hz, Integer midi) {
        this.noteName = noteName;
        this.octave = octave;
        this.frequency_hz = frequency_hz;
        this.midi = midi;
    }

    public static NoteSign fromFrequency(float pitchInHz) {
        return Stream.of(values())
                .min((n1, n2) ->
                        Double.valueOf(Math.abs(n1.frequency_hz - pitchInHz))
                                .compareTo(Math.abs(n2.frequency_hz - pitchInHz)))
                .get();
    }

    public static NoteSign fromMidiNumber(Integer midiNumber) {
        return Stream.of(values())
                .filter(value -> value.getMidi().equals(midiNumber))
                .findFirst()
                .get();
    }


    public Integer getMidi() {
        return midi;
    }

    public String getNoteName() {
        return noteName;
    }

    public Integer getOctave() {
        return octave;
    }

    public Double getFrequencyHz() {
        return frequency_hz;
    }

    public boolean higher(NoteSign note) {
        return frequency_hz > note.frequency_hz;
    }

    public boolean lower(NoteSign note) {
        return frequency_hz < note.frequency_hz;
    }

    public String fullName() {
        return noteName + octave;
    }

    public Integer moreThanInSemitones(NoteSign highNote) {
        return midi - highNote.getMidi();
    }

    public Integer diffInSemitones(NoteSign highNote) {
        return Math.abs(midi - highNote.getMidi());
    }

    public boolean inRange(NoteSign from, NoteSign to) {
        return !this.lower(from) && !this.higher(to);
    }

    public String shortName() {
        return noteName;
    }

}
