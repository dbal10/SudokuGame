package pl.comp.firstjava;

import java.io.File;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.beans.binding.Bindings;
import pl.comp.firstjava.exception.DaoException;
import pl.comp.firstjava.exception.EmptyBoardException;


public class BoardWindowControl {

    @FXML
    private GridPane sudokuBoardGrid;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private static final Logger logger = Logger.getLogger(BoardWindowControl.class.getName());
    private SudokuSolver sudokuSolver;
    private SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private DifficultyLevel difficultyLevel = new DifficultyLevel();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> jdbcSudokuBoardDao;
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private FileChooser fileChooser;
    private File file;



    @FXML
    void initialize() throws CloneNotSupportedException, EmptyBoardException {
        if (ChoiceWindowControl.getSudokuBoardFromFile() != null) {
            sudokuBoard = ChoiceWindowControl.getSudokuBoardFromFile();
        } else {
            solver.solve(sudokuBoard);
            difficultyLevel.chooseLevel(sudokuBoard, ChoiceWindowControl.getLevel());
        }

        fillGrid();
    }

    private void fillGrid() {
        for (int i = 0; i < SudokuBoard.Size; i++) {
            for (int j = 0; j < SudokuBoard.Size; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(18));

                if (!(sudokuBoard.get(i, j) == 0 || sudokuBoard.isEditableField(i, j))) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                } else if (sudokuBoard.get(i, j) != 0 && sudokuBoard.isEditableField(i, j)) {
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }

                sudokuBoardGrid.add(textField, j, i);
            }
        }

    }

    private boolean isInputValid() {
        boolean isValid = true;

        for (int i = 0; i < SudokuBoard.Size * SudokuBoard.Size; i++) {
            String fieldValue = ((TextField) sudokuBoardGrid.getChildren().get(i)).getText();
            if (!(fieldValue.matches("[1-9]") || fieldValue.equals(""))) {
                isValid = false;
            }
        }

        return isValid;
    }

    private void updateBoard() {
        for (int i = 0; i < SudokuBoard.Size; i++) {
            for (int j = 0; j < SudokuBoard.Size; j++) {
                String fieldValue = ((TextField) sudokuBoardGrid.getChildren()
                        .get(i * SudokuBoard.Size + j)).getText();

                if (!fieldValue.equals("")) {
                    sudokuBoard.set(i, j, Integer.parseInt(fieldValue));
                } else {
                    sudokuBoard.set(i, j, 0);
                }
//                Bindings.bindBidirectional();

            }
        }
    }





    @FXML
    public void onActionButtonCheck(ActionEvent actionEvent) {
        if (!isInputValid()) {
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_validWindow"), Alert.AlertType.WARNING);
        } else {
            updateBoard();
            if (sudokuBoard.checkBoard()) {
                popOutWindow.messageBox("", bundle.getString("_wonWindow"),
                        Alert.AlertType.INFORMATION);
            } else {
                popOutWindow.messageBox("", bundle.getString("_lostWindow"),
                        Alert.AlertType.INFORMATION);
            }
        }
    }


    @FXML
    public void onActionButtonDatabase(ActionEvent actionEvent) {
        fileChooser = new FileChooser();

        if (isInputValid()) {
            updateBoard();

            try {
                file = fileChooser.showSaveDialog(FxmlStageSetup.getStage());
                jdbcSudokuBoardDao = factory.getDatabaseDao(file.getName());
                jdbcSudokuBoardDao.write(sudokuBoard);
            } catch (NullPointerException | DaoException e) {
                logger.warning("Cannot save to database!");
                popOutWindow.messageBox(bundle.getString("_warning"),
                        bundle.getString("_fileWindow"), Alert.AlertType.WARNING);
            }
        } else {
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_validWindow"), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onActionButtonFile(ActionEvent actionEvent) {
        fileChooser = new FileChooser();

//        Bindings.bindBidirectional(file, fileSudokuBoardDao,  );
//        StringProperty file = new SimpleStringProperty();



        if (isInputValid()) {
            updateBoard();
            try {
                File file = fileChooser.showSaveDialog(FxmlStageSetup.getStage());
                fileSudokuBoardDao = factory.getFileDao(file.getName());
                fileSudokuBoardDao.write(sudokuBoard);
            } catch (NullPointerException | DaoException e) {
                logger.warning("Cannot save to file!");
                popOutWindow.messageBox(bundle.getString("_warning"),
                        bundle.getString("_fileWindow"), Alert.AlertType.WARNING);
            }
        } else {
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_validWindow"), Alert.AlertType.WARNING);
        }
    }

}
