package pl.comp.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class SudokuRowTest {

    private SudokuRow sudokuRow;
    private SudokuRow sudokuRowOther;

    private SudokuRow objectWithValidList() {
        return new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5), new SudokuField(6),
                new SudokuField(7), new SudokuField(8), new SudokuField(9)));
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        sudokuRow = objectWithValidList();
        sudokuRowOther = (SudokuRow) sudokuRow.clone();

        assertTrue(sudokuRow.equals(sudokuRowOther) && sudokuRowOther.equals(sudokuRow));
    }
}
