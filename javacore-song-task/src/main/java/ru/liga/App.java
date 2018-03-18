package ru.liga;
import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import ru.liga.AnalysisMidi.*;
import ru.liga.HW.input_interface.InLoggers;
import ru.liga.HW.input_interface.TextAnalizeFile;
import ru.liga.HW.input_interface.TransMidi;
import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
///////lesson 4

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//////

/**
 * Всего нот: 15
 * <p>
 * Анализ диапазона:
 * верхняя: E4
 * нижняя: F3
 * диапазон: 11
 * <p>
 * Анализ длительности нот (мс):
 * 4285: 10
 * 2144: 5
 * <p>
 * Анализ нот по высоте:
 * E4: 3
 * D4: 3
 * A3: 3
 * G3: 3
 * F3: 3
 * <p>
 * Анализ интервалов:
 * 2: 9
 * 5: 3
 * 11: 2
 */
public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
       /* AllNotes allNotes = new AllNotes(simpleMidiFile);
        AnalysisDiapoz analysisDiapoz = new AnalysisDiapoz(simpleMidiFile);
        AnalysisInterval analysisInterval = new AnalysisInterval(simpleMidiFile);
        AnalysisHeight analysisHeight= new AnalysisHeight(simpleMidiFile);
        AnalysisDuration analysisDuration = new AnalysisDuration(simpleMidiFile);*/
    if (args.length==3)
    {
        if (args[0].equals("C:\\zombie.mid") && args[1].equals("analyze") && args[2].equals("-f"))
        {
            TextAnalizeFile textAnalizeFile = new TextAnalizeFile(simpleMidiFile);
            textAnalizeFile.save();
        }

    }
        if (args.length==2)
        {
            if (args[0].equals("C:\\zombie.mid") && args[1].equals("analyze") )
            {
                InLoggers inLoggers = new InLoggers(simpleMidiFile);
                inLoggers.getLoggers();
            }

        }

        if (args.length==6)
        {
            if (args[0].equals("C:\\zombie.mid") && args[1].equals("change") && args[2].equals("-trans") && args[3].equals("2") && args[4].equals("-tempo") && args[5].equals("20")  )
            {
                TransMidi transMidi = new TransMidi(simpleMidiFile);
                transMidi.get_Trans();
            }
        }

/////////////////////////////////////////////////////////////////////////////////////// ВСЕГО нот
       // AllNotes allNotes = new AllNotes(simpleMidiFile);
        //System.out.print(allNotes.GetAnalisis());
////////////////////////////////////////////////////////////////////////////////////Анализ диапозона
       // AnalysisDiapoz diapozon = new AnalysisDiapoz(simpleMidiFile);
       // System.out.print(diapozon.GetAnalysis());
        //////////////////////////////////////////////////Анализ интервалов:

       // AnalysisInterval analysisInterval = new AnalysisInterval(simpleMidiFile);
       // System.out.print(analysisInterval.GetAnalysis());
//////////////////////////////////////////////////////////////////////////////////////////Анализ нот по высоте:

      //  AnalysisHeight n= new AnalysisHeight(simpleMidiFile);
      //  System.out.print(n.GetAnalysis());
//////////////////////////////////////////////////////////////////////////////////////////Анализ длительности нот (мс):
      //  AnalysisDuration x= new AnalysisDuration(simpleMidiFile);
       // System.out.print(x.GetAnalysis());

        //////////////////////////////lesson4 class work

        /*try {
            Files.write(Paths.get("C:\\Users\\ishun\\Desktop\\ЛИГА ЭКОНОМИКИ\\lesson_4\\file.txt"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       /* try (PrintStream out = new PrintStream(new FileOutputStream("C:\\\\Users\\\\ishun\\\\Desktop\\\\ЛИГА ЭКОНОМИКИ\\\\lesson_4\\\\file.txt"))) {
            out.println(data);
        }catch (IOException e) {
            e.printStackTrace();
        }*/
        /////////с помощью логов
        //logger.info(data);

    }
}
