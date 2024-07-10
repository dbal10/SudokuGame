/*
 * MIT License
 *
 * Copyright (c) 2019.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 * ------------------------------------
 *  Library Apache commons-lang3 has open source license
 *
 */



package pl.comp.firstjava;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBoard implements Serializable, Cloneable {

    public static final int Size = 9;
    public static final int How_Many_Cells = Size * Size;

    private final SudokuSolver sudokuSolver;

    private List<List<SudokuField>> board;

    public SudokuBoard(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
        //Przypisujemy do referencji pierwszy wymiar macierzy
        board = Arrays.asList(new List[Size]);

        //Przypisujemy do referencji drugi wymiar macierzy
        for (int i = 0; i < Size; i++) {
            board.set(i, Arrays.asList(new SudokuField[Size]));
        }

        //Zmieniamy zawartosc listy z typu NULL na obiekty SudokuField
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }
    }

    public int get(int i, int j) {

        return board.get(i).get(j).getFieldValue();
    }

    public void set(int i, int j, int value) {

        this.board.get(i).get(j).setFieldValue(value);
    }

    public SudokuRow getRow(int row) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.Size]);
        for (int i = 0; i < Size; i++) {
            fields.set(i, board.get(row).get(i));
        }
        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int column) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.Size]);
        for (int i = 0; i < Size; i++) {
            fields.set(i, board.get(i).get(column));
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int indexOfRow, int indexOfColumn) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.Size]);
        int index = 0;
        for (int i = 0; i < SudokuBox.BOX_SIZE; i++) {
            for (int j = 0; j < SudokuBox.BOX_SIZE; j++) {
                fields.set(index++, board.get(indexOfRow * 3 + i).get(indexOfColumn * 3 + j));
            }
        }
        return new SudokuBox(fields);
    }

    public boolean isEditableField(int axisX, int axisY) {
        return board.get(axisX).get(axisY).isEmptyField();
    }

    public void setEditableField(int axisX, int axisY) {
        board.get(axisX).get(axisY).setEmptyField();
    }

    boolean checkBoard() {
        boolean checkIfCorrect = true;
        //Sprawdzamy tu wiersze
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int j2 = j + 1; j2 < 9; j2++) {
                    if (board.get(i).get(j).getFieldValue()
                            == board.get(i).get(j2).getFieldValue()) {
                        checkIfCorrect = false;
                    }
                }
            }
        }

        //Sprawdzamy kolumny
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int i2 = i + 1; i2 < 9; i2++) {
                    if (board.get(i).get(j).getFieldValue()
                            == board.get(i2).get(j).getFieldValue()) {

                        checkIfCorrect = false;

                    }
                }
            }
        }

        //Sprawdzamy tu bloki 3x3
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                //Tutaj tworzymy blok 3x3 (a, b)
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1; compared < 9; compared++) {
                        if (board.get(a * 3 + checked / 3)
                                .get(b * 3 + checked % 3).getFieldValue()
                                == board.get(a * 3 + compared / 3).get(b * 3 + compared % 3)
                                .getFieldValue()) {

                            checkIfCorrect = false;

                        }
                    }
                }
            }
        }

        return checkIfCorrect;

    }

    public String convertSudokuBoardToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SudokuBoard.Size; i++) {
            for (int j = 0; j < SudokuBoard.Size; j++) {
                builder.append(String.valueOf(get(i, j)));
            }
        }

        return builder.toString();
    }

    public String convertIsEditableToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SudokuBoard.Size; i++) {
            for (int j = 0; j < SudokuBoard.Size; j++) {
                builder.append(String.valueOf(isEditableField(i, j) ? 1 : 0));
            }
        }

        return builder.toString();
    }

    public SudokuBoard convertStringToSudokuBoard(String string) {
        for (int i = 0; i < SudokuBoard.Size; i++) {
            for (int j = 0; j < SudokuBoard.Size; j++) {
                set(i, j, Character.getNumericValue(string.charAt(i * SudokuBoard.Size + j)));
            }
        }

        return this;
    }

    public SudokuBoard convertStringToIsEditable(String string) {
        for (int i = 0; i < SudokuBoard.Size; i++) {
            for (int j = 0; j < SudokuBoard.Size; j++) {
                if (string.charAt(i * SudokuBoard.Size + j) == '1') {
                    setEditableField(i, j);
                }
            }
        }

        return this;
    }

    @Override
    public boolean equals(final Object obj) {

        return new EqualsBuilder().append(board, ((SudokuBoard) obj).board).isEquals();
    }

    //Metode dodajemy tylko w celu pozytywnej weryfikacji testu checkstyle
    @Override
    public int hashCode() {

        return new HashCodeBuilder(17,37).append(board).toHashCode();
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this).append("board", board).toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                sudokuBoard.set(i, j, get(i, j));
            }
        }
        return sudokuBoard;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }
}
