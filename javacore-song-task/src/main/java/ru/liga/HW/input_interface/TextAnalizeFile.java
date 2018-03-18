package ru.liga.HW.input_interface;

import ru.liga.AnalysisMidi.*;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextAnalizeFile {
    private final SimpleMidiFile simpleMidiFile;
    public   TextAnalizeFile(SimpleMidiFile simpleMidiFile)
    {
        this.simpleMidiFile=simpleMidiFile;
    }
    public void save()
    {
        AllNotes allNotes = new AllNotes(simpleMidiFile);
        AnalysisDiapoz analysisDiapoz = new AnalysisDiapoz(simpleMidiFile);
        AnalysisInterval analysisInterval = new AnalysisInterval(simpleMidiFile);
        AnalysisHeight analysisHeight= new AnalysisHeight(simpleMidiFile);
        AnalysisDuration analysisDuration = new AnalysisDuration(simpleMidiFile);
        try(FileWriter writer = new FileWriter("C:\\Users\\ishun\\Desktop\\ЛИГА ЭКОНОМИКИ\\lesson_3\\liga-internship\\Analysis.txt", true))
        {
            writer.write(allNotes.GetAnalisis() + "\r\n");
            writer.write(analysisDiapoz.GetAnalysis() + "\r\n");
            writer.write(analysisInterval.GetAnalysis() + "\r\n");
            writer.write(analysisHeight.GetAnalysis() + "\r\n");
            writer.write(analysisDuration.GetAnalysis() + "\r\n");

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        File input = new File("C:\\Users\\ishun\\Desktop\\ЛИГА ЭКОНОМИКИ\\lesson_3\\liga-internship","zombie.mid");
        try
        {
            simpleMidiFile.getMidiFormat().writeToFile(input);

        }
        catch(IOException e)
        {
            System.err.println("Error writing MIDI file:");
            e.printStackTrace();
        }
    }
}
