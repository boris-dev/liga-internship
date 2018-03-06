package ru.liga;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.SimpleMidiFile;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
        System.out.println("Duration(sec): " + simpleMidiFile.durationMs() / 1000);
        System.out.println("Note count: " + simpleMidiFile.vocalNoteList().size());
    }
}
