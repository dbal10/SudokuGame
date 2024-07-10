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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class BacktrackingSudokuSolverTest {
    private SudokuBoard sudokuBoard;
    private BacktrackingSudokuSolver solver;
    private SudokuSolver sudokuSolver;

    @BeforeEach
    public void setUp() {
        sudokuBoard = new SudokuBoard(sudokuSolver);
        solver = new BacktrackingSudokuSolver();

    }

    private boolean CheckOfRows(SudokuBoard board) {
        for (int i = 0; i < SudokuBoard.Size; i++) {
            for (int j = 0; j < SudokuBoard.Size; j++) {
                for (int j2 = j + 1; j2 < SudokuBoard.Size; j2++) {
                    if (board.get(i, j) == board.get(i, j2)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean CheckOfColumns(SudokuBoard board) {
        for (int j = 0; j < SudokuBoard.Size; j++) {
            for (int i = 0; i < SudokuBoard.Size; i++) {
                for (int i2 = i + 1; i2 < SudokuBoard.Size; i2++) {
                    if (board.get(i, j) == board.get(i2, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean CheckOfMiniBlocks(SudokuBoard board) {
        for (int I = 0; I < 3; I++) {
            for (int J = 0; J < 3; J++) {
                //maly kwadrat (I, J)
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1; compared < 9; compared++) {
                        if (board.get(I * 3 + (checked / 3), J * 3 + (checked % 3)) == board.get(I * 3 + (compared / 3), J * 3 + (compared % 3))) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }


    @Test
    public void solveTest() {
        solver.solve(sudokuBoard);

        assertTrue(CheckOfRows(sudokuBoard));
        assertTrue(CheckOfColumns(sudokuBoard));
        assertTrue(CheckOfMiniBlocks(sudokuBoard));
    }


    @Test
    public void solveRepeatTest() {
        SudokuBoard obj1 = new SudokuBoard(sudokuSolver);
        SudokuBoard obj2 = new SudokuBoard(sudokuSolver);
        solver.solve(obj1);
        solver.solve(obj2);

        assertTrue(!obj1.equals(obj2));
    }

    @AfterEach
    void tearDown() {
    }

}
