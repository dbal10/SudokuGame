package pl.comp.firstjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.comp.firstjava.exception.FileOperationException;


public abstract class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws FileOperationException {
        SudokuBoard obj = null;

        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            obj = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new FileOperationException(e);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
        return obj;
    }

    @Override
    public void write(SudokuBoard obj) throws FileOperationException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))  {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
    }


}
