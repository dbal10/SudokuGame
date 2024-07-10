package pl.comp.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class SudokuColumnTest {

    private SudokuColumn sudokuColumn;
    private SudokuColumn sudokuColumnOther;

    private SudokuColumn objectWithValidList() {
        return new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5), new SudokuField(6),
                new SudokuField(7), new SudokuField(8), new SudokuField(9)));
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        sudokuColumn = objectWithValidList();
        sudokuColumnOther = (SudokuColumn) sudokuColumn.clone();

        assertTrue(sudokuColumn.equals(sudokuColumnOther) && sudokuColumnOther.equals(sudokuColumn));
    }
}
