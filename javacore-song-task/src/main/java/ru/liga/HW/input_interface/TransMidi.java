package ru.liga.HW.input_interface;

import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class TransMidi {
    private final SimpleMidiFile simpleMidiFile;
    public   TransMidi(SimpleMidiFile simpleMidiFile)
    {
        this.simpleMidiFile=simpleMidiFile;
    }
    public void get_Trans()
    {
        File out = new File("C:\\Users\\ishun\\Desktop\\ЛИГА ЭКОНОМИКИ\\lesson_3\\liga-internship","zombie-trans2-tempo20.mid");
        MidiTrack T = simpleMidiFile.getMidiFormat().getTracks().get(0);
        Iterator<MidiEvent> it = T.getEvents().iterator();
        while(it.hasNext())
        {
            MidiEvent E = it.next();

            if(E.getClass().equals(Tempo.class))
            {

                Tempo tempo = (Tempo) E;
                tempo.setBpm(tempo.getBpm() * 10);
            }
        }
        T =simpleMidiFile.getMidiFormat().getTracks().get(1);
        it = T.getEvents().iterator();
        while(it.hasNext())
        {
            MidiEvent E = it.next();

            if(E.getClass().equals(NoteOn.class) )
            {
                NoteOn on = (NoteOn) E;
                on.setNoteValue(on.getNoteValue() + 2);
            }
            if( E.getClass().equals(NoteOff.class))
            {
                NoteOff off = (NoteOff) E;
                off.setNoteValue(off.getNoteValue() + 2);
            }
        }
        while(it.hasNext())
        {
            MidiEvent E = it.next();
            if(E.getClass().equals(NoteOn.class) && E.getClass().equals(NoteOff.class))
            {
                NoteOn on = (NoteOn) E;
                System.out.println(on.getNoteValue());
            }
        }

        try
        {
            simpleMidiFile.getMidiFormat().writeToFile(out);

        }
        catch(IOException e)
        {
            System.err.println("Error writing MIDI file:");
            e.printStackTrace();
        }
    }
}
