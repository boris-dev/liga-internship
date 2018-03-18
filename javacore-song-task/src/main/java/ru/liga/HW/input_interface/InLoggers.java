package ru.liga.HW.input_interface;

import ru.liga.AnalysisMidi.*;
import ru.liga.App;
import ru.liga.songtask.domain.SimpleMidiFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

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
        logger.info("Верхняя: " + analysisDiapoz.GetAnalysis().get(0));
        logger.info("нижняя: " + analysisDiapoz.GetAnalysis().get(1));
        logger.info("Диапозон: " + analysisDiapoz.GetAnalysis().get(2));
        String ResultStr = "";
        for (Map.Entry entry : analysisInterval.GetAnalysis().entrySet()) {
            ResultStr += entry.getKey() + " : "
                    + entry.getValue()  + "\r\n";
        }
        logger.info("Анализ интервалов:" + "\r\n" + ResultStr);
        ResultStr = "";
        for (Map.Entry entry : analysisHeight.GetAnalysis().entrySet()) {
            ResultStr += entry.getKey() + " : "
                    + entry.getValue() + "\r\n";
        }

        logger.info("Анализ нот по высоте:" + "\r\n" + ResultStr);
        ResultStr = "";
        Map<Float, Integer> returnhashMapNote = new HashMap<>();
        for (Map.Entry entry : analysisDuration.GetAnalysis().entrySet()) {
            ResultStr += entry.getKey() + " : " + entry.getValue() + "\r\n";

        }
        logger.info("Анализ длительности нот (мс):" + "\r\n" + ResultStr);
        //logger.info(analysisDuration.GetAnalysis());

    }
}
