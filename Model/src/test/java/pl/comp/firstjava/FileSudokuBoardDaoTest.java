package pl.comp.firstjava;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import pl.comp.firstjava.exception.DaoException;
import pl.comp.firstjava.exception.FileOperationException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileSudokuBoardDaoTest {
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private SudokuSolver sudokuSolver;
    private SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);

    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoard sudokuBoardOther;

    @Test
    public void writeReadTest() throws DaoException {
        fileSudokuBoardDao = factory.getFileDao("aaa.txt");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoardOther = fileSudokuBoardDao.read();

        assertEquals(sudokuBoard, sudokuBoardOther);
    }

    @Test
    public void readIOExceptionTest() {
        assertThrows(FileOperationException.class, () -> {
            fileSudokuBoardDao = factory.getFileDao("bbb.txt");
            fileSudokuBoardDao.read();
        });

    }

    @Test
    public void writeIOExceptionTest() {
        assertThrows(FileOperationException.class, () -> {
            if (SystemUtils.IS_OS_WINDOWS) {
                fileSudokuBoardDao = factory.getFileDao("?");
            } else if (SystemUtils.IS_OS_LINUX) {
                fileSudokuBoardDao = factory.getFileDao("/");
            } else {
                fileSudokuBoardDao = factory.getFileDao("?");
            }
            fileSudokuBoardDao.write(sudokuBoard);
        });
    }
}
