package ru.liga.HW.input_interface;

import ru.liga.AnalysisMidi.*;
import ru.liga.App;
import ru.liga.songtask.domain.SimpleMidiFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class InLoggers {
    private final SimpleMidiFile simpleMidiFile;
    private static Logger logger = LoggerFactory.getLogger(App.class);
    public   InLoggers(SimpleMidiFile simpleMidiFile)
    {
        this.simpleMidiFile=simpleMidiFile;
    }
    public void getLoggers()
    {
        AllNotes allNotes = new AllNotes(simpleMidiFile);
        AnalysisDiapoz analysisDiapoz = new AnalysisDiapoz(simpleMidiFile);
        AnalysisInterval analysisInterval = new AnalysisInterval(simpleMidiFile);
        AnalysisHeight analysisHeight= new AnalysisHeight(simpleMidiFile);
        AnalysisDuration analysisDuration = new AnalysisDuration(simpleMidiFile);

        logger.info(allNotes.GetAnalisis());
        logger.info(analysisDiapoz.GetAnalysis());
        logger.info(analysisInterval.GetAnalysis());
        logger.info(analysisHeight.GetAnalysis());
        logger.info(analysisDuration.GetAnalysis());

    }
}
