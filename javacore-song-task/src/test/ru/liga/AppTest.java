package ru.liga;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import ru.liga.AnalysisMidi.*;
import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.SimpleMidiFile;

public class AppTest
{
    SimpleMidiFile simpleMidiFile ;
    AllNotes allNotes;
    AnalysisDiapoz analysisDiapoz;
    AnalysisInterval analysisInterval;
    AnalysisHeight analysisHeight;
    AnalysisDuration analysisDuration;
    @Before
    public void setup()
    {
        simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
        allNotes = new AllNotes(simpleMidiFile);
        analysisDiapoz = new AnalysisDiapoz(simpleMidiFile);
        analysisInterval = new AnalysisInterval(simpleMidiFile);
        analysisHeight = new AnalysisHeight(simpleMidiFile);
        analysisDuration = new AnalysisDuration(simpleMidiFile);
    }
    @Test
    public void Right_AllNotes()
    {
        Assertions.assertThat(allNotes.GetListNotes().size()).isEqualTo(11);
    }
    @Test
    public void Right_AnalysisDiapoz()
    {
        Assertions.assertThat(analysisDiapoz.GetAnalysis().get(0)).isEqualTo("A5");
        Assertions.assertThat(analysisDiapoz.GetAnalysis().get(1)).isEqualTo("E4");
        Assertions.assertThat(analysisDiapoz.GetAnalysis().get(2)).isEqualTo("17");
    }
    @Test
    public void Right_Analysisinterval()
    {
        Assertions.assertThat(analysisInterval.GetAnalysis().get(0)).isEqualTo(34);
        Assertions.assertThat(analysisInterval.GetAnalysis().get(2)).isEqualTo(93);
    }
    @Test
    public void Right_AnalysisHeight()
    {
        Assertions.assertThat(analysisHeight.GetAnalysis().get("E5")).isEqualTo(57);
        Assertions.assertThat(analysisHeight.GetAnalysis().get("C5")).isEqualTo(12);
    }
    @Test
    public void Right_AnalysisDuration()
    {
        Assertions.assertThat(analysisDuration.GetAnalysis().get(10975.6045f)).isEqualTo(15);
        Assertions.assertThat(analysisDuration.GetAnalysis().get(18658.527f)).isEqualTo(25);
    }

}
