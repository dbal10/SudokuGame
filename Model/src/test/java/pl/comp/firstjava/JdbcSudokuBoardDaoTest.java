package pl.comp.firstjava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import pl.comp.firstjava.exception.DaoException;
import pl.comp.firstjava.exception.DatabaseException;

import static org.junit.Assert.assertEquals;

public class JdbcSudokuBoardDaoTest {
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private SudokuSolver sudokuSolver;
    private SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
    private SudokuBoard sudokuBoardOther;
    private Dao<SudokuBoard> databaseSudokuBoardDao;

    @Test
    public void writeReadDbTest() throws IOException {
        Files.deleteIfExists(Paths.get("./aaa.db"));
        databaseSudokuBoardDao = factory.getDatabaseDao("aaa.db");
        databaseSudokuBoardDao.write(sudokuBoard);
        sudokuBoardOther = databaseSudokuBoardDao.read();

        assertEquals(sudokuBoard, sudokuBoardOther);
    }

    @Test(expected = DatabaseException.class)
    public void name() throws DaoException {
        if (SystemUtils.IS_OS_WINDOWS) {
            databaseSudokuBoardDao = factory.getDatabaseDao("?");
        } else if (SystemUtils.IS_OS_LINUX) {
            databaseSudokuBoardDao = factory.getDatabaseDao("/");
        } else {
            databaseSudokuBoardDao = factory.getDatabaseDao("?");
        }
        databaseSudokuBoardDao.write(sudokuBoard);
    }
}
