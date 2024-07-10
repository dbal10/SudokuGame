package pl.comp.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChoiceWindowControlTest {

    private ChoiceWindowControl choiceWindowControl = new ChoiceWindowControl();

    @Test
    public void constructorTest() { assertNotNull(choiceWindowControl); }

    @Test
    public void getLevelTest() { assertNull(ChoiceWindowControl.getLevel()); }

    @Test
    public void getLanguageTest() { assertNull(choiceWindowControl.getLanguage()); }

    @Test
    public void getSudokuBoardFromFileTest() {
        assertNull(ChoiceWindowControl.getSudokuBoardFromFile());
    }


}
