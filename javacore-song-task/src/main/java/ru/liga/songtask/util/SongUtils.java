package ru.liga.songtask.util;

public class SongUtils {

    public int tickToMs(float bpm, int resolution, long amountOfTick) {
        return (int) (((60 * 1000) / (bpm * resolution)) * amountOfTick);
    }

}
