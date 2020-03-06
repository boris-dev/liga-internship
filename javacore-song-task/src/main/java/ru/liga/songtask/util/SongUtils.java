package ru.liga.songtask.util;

public class SongUtils {

    /**
     * Перевод тиков в миллисекунды
     * @param bpm - количество ударов в минуту (темп)
     * @param resolution - midiFile.getResolution()
     * @param amountOfTick - то что переводим в миллисекунды
     * @return
     */
    public static int tickToMs(float bpm, int resolution, long amountOfTick) {
        return (int) (((60 * 1000) / (bpm * resolution)) * amountOfTick);
    }

}
