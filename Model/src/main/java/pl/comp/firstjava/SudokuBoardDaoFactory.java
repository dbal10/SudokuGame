package pl.comp.firstjava;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) {

        return new FileSudokuBoardDao(fileName) {
            @Override
            public void close() throws Exception {

            }
        };
    }

    public Dao<SudokuBoard> getDatabaseDao(String filename) {
        return new JdbcSudokuBoardDao(filename);
    }

}
