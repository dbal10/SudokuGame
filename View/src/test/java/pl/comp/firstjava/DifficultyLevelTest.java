package pl.comp.firstjava;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pl.comp.firstjava.exception.EmptyBoardException;
import pl.comp.firstjava.exception.UnknownLevelException;
import java.util.ResourceBundle;
import static org.junit.jupiter.api.Assertions.*;

public class DifficultyLevelTest {
    private SudokuBoard sudokuBoard;
    private DifficultyLevel difficultyLevel;
    private BacktrackingSudokuSolver solver;
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private SudokuSolver sudokuSolver;

    @BeforeEach
    public void setUp() {
        sudokuBoard = new SudokuBoard(sudokuSolver);
        difficultyLevel = new DifficultyLevel();
        solver = new BacktrackingSudokuSolver();
    }

    @Test
    public void chooseLevelUnknownLevelExceptionTest() throws EmptyBoardException {
        assertThrows(UnknownLevelException.class, () -> {
            difficultyLevel.chooseLevel(sudokuBoard, "wrongName");
        });
    }

    @Test
    public void chooseLevelEmptyBoardExceptionTest() throws EmptyBoardException {
        assertThrows(EmptyBoardException.class, () -> {
            difficultyLevel.chooseLevel(sudokuBoard, bundle.getString( "_lvlEasy"));
        });
    }


    @Test
    public void chooseLevelTest1() throws EmptyBoardException {
        checkEmptyFields(bundle.getString( "_lvlVeryEasy"), DifficultyLevel.MULTIPLIER_LEVEL_ARRAY[0]);
    }

    @Test
    public void chooseLevelTest2() throws EmptyBoardException {
        checkEmptyFields(bundle.getString( "_lvlEasy"), DifficultyLevel.MULTIPLIER_LEVEL_ARRAY[1]);
    }

    @Test
    public void chooseLevelTest3() throws EmptyBoardException {
        checkEmptyFields(bundle.getString( "_lvlMedium"), DifficultyLevel.MULTIPLIER_LEVEL_ARRAY[2]);
    }

    @Test
    public void chooseLevelTest4() throws EmptyBoardException {
        checkEmptyFields(bundle.getString( "_lvlHard"), DifficultyLevel.MULTIPLIER_LEVEL_ARRAY[3]);
    }

    @Test
    public void chooseLevelTest5() throws EmptyBoardException {
        checkEmptyFields(bundle.getString("_lvlFromExternal"), 0);
    }


    private void checkEmptyFields(String level, int multiplier) throws EmptyBoardException {
        solver.solve(sudokuBoard);
        SudokuBoard changedBoard = difficultyLevel.chooseLevel(sudokuBoard, level);
        int emptyFieldNumber = 0;

        for (int i = 0; i < SudokuBoard.Size; i++) {
            for (int j = 0; j < SudokuBoard.Size; j++) {
                if (changedBoard.get(i, j) == 0) {
                    emptyFieldNumber++;
                }
            }
        }

        assertEquals(emptyFieldNumber, DifficultyLevel.BASIC_LEVEL * multiplier);
    }

    @AfterEach
    void tearDown() {
    }

}
