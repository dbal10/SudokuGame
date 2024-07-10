package pl.comp.firstjava;

import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import pl.comp.firstjava.exception.EmptyBoardException;
import pl.comp.firstjava.exception.UnknownLevelException;


public class DifficultyLevel {

    public static final int BASIC_LEVEL = 5;
    public static final int[] MULTIPLIER_LEVEL_ARRAY = {1, 2, 3, 4};

    private Random rand = new Random();
    private Set<FieldCoordinates> randomPositions = new HashSet<>();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    private void fillRandomPositionsList(int capacity) {
        for (int i = 0; i < capacity; i++) {
            boolean isElementAdded = false;

            while (!isElementAdded) {
                int axisX = rand.nextInt(9);
                int axisY = rand.nextInt(9);
                isElementAdded = randomPositions.add(new FieldCoordinates(axisX, axisY));
            }
        }
    }

    public SudokuBoard chooseLevel(SudokuBoard sudokuBoard, String level)
            throws EmptyBoardException {
        if (!(level.equals(bundle.getString("_lvlVeryEasy"))
                || level.equals(bundle.getString("_lvlEasy"))
                || level.equals(bundle.getString("_lvlMedium"))
                || level.equals(bundle.getString("_lvlHard"))
                || level.equals(bundle.getString("_lvlFromExternal")))) {
            throw new UnknownLevelException();
        } else if (!sudokuBoard.checkBoard()) {
            throw new EmptyBoardException(bundle.getString("_emptyBoard"));
        }

        if (level.equals(bundle.getString("_lvlVeryEasy"))) {
            fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[0]);
        } else if (level.equals(bundle.getString("_lvlEasy"))) {
            fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[1]);
        } else if (level.equals(bundle.getString("_lvlMedium"))) {
            fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[2]);
        } else if (level.equals(bundle.getString("_lvlHard"))) {
            fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[3]);
        }

        for (FieldCoordinates it : randomPositions) {
            sudokuBoard.set(it.getAxisX(), it.getAxisY(), 0);
            sudokuBoard.setEditableField(it.getAxisX(), it.getAxisY());
        }
        return sudokuBoard;
    }

}
