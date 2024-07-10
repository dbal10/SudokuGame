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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class SudokuBoardTest {

    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoardOther;
    private SudokuSolver sudokuSolver;

    @BeforeEach
    public void setUp() {
        sudokuBoard = new SudokuBoard(sudokuSolver);
    }

    @Test
    public void checkBoardTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    public void checkBoardFailTest() {
        assertFalse(sudokuBoard.checkBoard());
    }


    @Test
    public void getSetMethodsTest() {
        assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(0, 0, 5);
        assertEquals(sudokuBoard.get(0, 0), 5);
    }

    @Test
    public void getRowTest() {
        assertNotNull(sudokuBoard.getRow(2));
    }

    @Test
    public void getColumnTest() {
        assertNotNull(sudokuBoard.getColumn(2));
    }

    @Test
    public void getBoxTest() {
        assertNotNull(sudokuBoard.getBox(1,1));
    }

    @Test
    public void isEditableFieldTest() { assertFalse(sudokuBoard.isEditableField(1,1)); }

    @Test
    public void setEditableFieldTest() {
        sudokuBoard.setEditableField(1,1);
        assertTrue(sudokuBoard.isEditableField(1,1));
    }

    @Test
    public void toStringTest() {

        assertNotNull(sudokuBoard.toString());

    }

    @org.junit.Test
    public void convertSudokuBoardToStringTest() { assertNotNull(sudokuBoard.convertSudokuBoardToString()); }

    @org.junit.Test
    public void convertStringTuSudokuBoardTest() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SudokuBoard.Size * SudokuBoard.Size; i++) {
            builder.append(0);
        }

        assertNotNull(sudokuBoard.convertStringToSudokuBoard(builder.toString()));
    }

    @org.junit.Test
    public void dualSideConvertTest() throws CloneNotSupportedException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        sudokuBoardOther = (SudokuBoard) sudokuBoard.clone();

        String values = sudokuBoard.convertSudokuBoardToString();
        sudokuBoard.convertStringToSudokuBoard(values);
        assertEquals(sudokuBoard,sudokuBoardOther);

        String editable = sudokuBoard.convertIsEditableToString();
        sudokuBoard.convertStringToIsEditable(editable);
        assertEquals(sudokuBoard, sudokuBoardOther);
    }

    @org.junit.Test
    public void convertIsEditableToStringTest() {
        sudokuBoard.setEditableField(3, 3);
        String temp = sudokuBoard.convertIsEditableToString();
        assertEquals(temp.charAt(3 * SudokuBoard.Size + 3), '1');
    }

    @org.junit.Test
    public void convertStringToIsEditableTest() {
        StringBuilder builder = new StringBuilder();

        builder.append(1);
        for (int i = 0; i < SudokuBoard.Size * SudokuBoard.Size - 1; i++) {
            builder.append(0);
        }

        assertEquals(sudokuBoard.convertStringToIsEditable(builder.toString()).isEditableField(0,0),true);
    }


    @Test
    public void equalsTest() {
        sudokuBoardOther = new SudokuBoard(sudokuSolver);

        assertTrue(sudokuBoard.equals(sudokuBoardOther) && sudokuBoardOther.equals(sudokuBoard));
    }

    @Test
    public void hashCodeTest() {
        sudokuBoardOther = new SudokuBoard(sudokuSolver);

        assertEquals(sudokuBoard.hashCode(), sudokuBoardOther.hashCode());
    }


    @Test
    public void testget() {
        SudokuBoard sudokuBoard6 = new SudokuBoard(sudokuSolver);
        sudokuBoard6.solveGame();
        sudokuBoard6.get(1,1);
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        sudokuBoardOther = (SudokuBoard) sudokuBoard.clone();

        assertTrue(sudokuBoard.equals(sudokuBoardOther) && sudokuBoardOther.equals(sudokuBoard));
    }



    @AfterEach
    void tearDown() {
    }



}