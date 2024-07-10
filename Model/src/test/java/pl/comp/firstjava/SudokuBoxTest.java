package pl.comp.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class SudokuBoxTest {

    private SudokuBox sudokuBox;
    private SudokuBox sudokuBoxOther;

    private SudokuBox objectWithValidList() {
        return new SudokuBox(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5), new SudokuField(6),
                new SudokuField(7), new SudokuField(8), new SudokuField(9)));
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        sudokuBox = objectWithValidList();
        sudokuBoxOther = (SudokuBox) sudokuBox.clone();

        assertTrue(sudokuBox.equals(sudokuBoxOther) && sudokuBoxOther.equals(sudokuBox));
    }

}
