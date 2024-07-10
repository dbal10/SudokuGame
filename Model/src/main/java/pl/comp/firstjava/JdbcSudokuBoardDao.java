package pl.comp.firstjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pl.comp.firstjava.exception.DatabaseException;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {
    public static final String tableName1 = "SudokuBoard1";
    public static final String tableName2 = "SudokuBoard2";
    private String filename;
    private SudokuSolver sudokuSolver;

    JdbcSudokuBoardDao(String filename) { this.filename = filename; }

    private Connection prepareConnection(String jdbcUrl) throws DatabaseException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return connection;
    }

    @Override
    public SudokuBoard read() throws DatabaseException {
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        String jdbcUrl = "jdbc:sqlite:" + filename;
        Connection connection = prepareConnection(jdbcUrl);
        String receivedDataX;
        String receivedDataY;
        String receivedDataValue;
        ResultSet resultSet;
        String selectData = "SELECT v.x, v.y, v.value FROM" + tableName1 + "t, " + tableName2 + " v WHERE t.id = v.id and t.tableName=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
            preparedStatement.setString(1, filename);
            resultSet = preparedStatement.executeQuery();
            receivedDataX = resultSet.getString(1);
            receivedDataY = resultSet.getString(2);
            receivedDataValue = resultSet.getString(3);
            int x;
            int y;
            int value;

            for (int i = 0; i < receivedDataX.length(); i++) {
                x = Integer.valueOf(receivedDataX.charAt(i));
                y = Integer.valueOf(receivedDataY.charAt(i));
                value = Integer.valueOf(receivedDataValue.charAt(i));
                sudokuBoard.set(x, y, value);

            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return sudokuBoard;
    }



    @Override
    public void write(SudokuBoard sudokuBoard) throws DatabaseException {

        int id = 11;

        String jdbcUrl = "jdbc:sqlite:./" + filename;
        Connection connection = prepareConnection(jdbcUrl);

        String createTable1 = "create table " + tableName1 + "(id int primary key not null," + " tableName varchar(200) not null)";

        String createTable2 = "create table " + tableName2 + "(x integer not null," + " y integer not null," + " value integer," + " id integer references tableName1(id))" ;

        String insertData1 = "insert into SudokuBoard1(id, table_name) values (?,?)";


        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable1);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

//        try (PreparedStatement preparedStatement = connection.prepareStatement(insertData1)) {
//            preparedStatement.setInt(1, id);
//            preparedStatement.setString(2, "nazwa tablicy");
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new DatabaseException(e);
//        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }


//        try (Statement statement = connection.createStatement()) {
//            statement.execute(createTable2);
//        } catch (SQLException e) {
//            throw new DatabaseException(e);
//        }
//
//
//        String insertData2 = "insert into SudokuBoard2(x, y, value, id) values (?,?,?,?)";
//        for (int x = 0; x < SudokuBoard.Size; x++) {
//            for (int y = 0; y < SudokuBoard.Size; y++) {
//                int value = sudokuBoard.get(x, y);
//
//
//                try (PreparedStatement preparedStatement = connection.prepareStatement(insertData2)) {
//                    preparedStatement.setInt(1, x);
//                    preparedStatement.setInt(2, y);
//                    preparedStatement.setInt(3, value);
//                    preparedStatement.setInt(4, id);
////                    preparedStatement.setString(1, tableName2);
//                    preparedStatement.executeUpdate();
//                } catch (SQLException e) {
//                    throw new DatabaseException(e);
//                }
//
//
//            }
//        }
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            throw new DatabaseException(e);
//        }


    }

    @Override
    public void close() throws Exception {

    }
}
