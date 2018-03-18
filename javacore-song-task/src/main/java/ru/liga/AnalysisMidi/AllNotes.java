package ru.liga.AnalysisMidi;

import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.ArrayList;

public class AllNotes {
    private final SimpleMidiFile simpleMidiFile;
  public   AllNotes(SimpleMidiFile simpleMidiFile)
    {
        this.simpleMidiFile=simpleMidiFile;
    }
  public  String GetAnalisis()
    {
        ArrayList<NoteSign> list_note= new ArrayList<>();
        for(int i=0;i<simpleMidiFile.vocalNoteList().size();i++)
        {

            if (!list_note.contains(simpleMidiFile.vocalNoteList().get(i).sign()))
                list_note.add(simpleMidiFile.vocalNoteList().get(i).sign());

        }
        return "Всего нот: " + list_note.size();
    }
    public ArrayList<NoteSign> GetListNotes()
    {
        ArrayList<NoteSign> list_note= new ArrayList<>();
        for(int i=0;i<simpleMidiFile.vocalNoteList().size();i++)
        {

            if (!list_note.contains(simpleMidiFile.vocalNoteList().get(i).sign()))
                list_note.add(simpleMidiFile.vocalNoteList().get(i).sign());

        }
        return list_note;
    }
}
