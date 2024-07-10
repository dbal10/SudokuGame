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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.comp.firstjava.exception.BadGroupSizeException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class SudokuFieldGroupTest {

    private SudokuRow objectWithValidList() {
        return new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5), new SudokuField(6),
                new SudokuField(7), new SudokuField(8), new SudokuField(9)));
    }

    @Test
    public void verifyValidTest() {
        SudokuRow sudokuRow = objectWithValidList();
        assertTrue(sudokuRow.verify());

    }

    @Test
    public void verifyInvalidTest() {
        SudokuRow sudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(2), new SudokuField(2), new SudokuField(3),
                new SudokuField(4),new SudokuField(5), new SudokuField(6),
                new SudokuField(7), new SudokuField(8), new SudokuField(9)));

        assertFalse(sudokuRow.verify());
    }

    @Test
    public void badGroupSizeTest() {
        assertThrows(BadGroupSizeException.class, () -> {
            SudokuRow sudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(1), new SudokuField(3),
                new SudokuField(4),new SudokuField(5), new SudokuField(6),
                new SudokuField(7), new SudokuField(8), new SudokuField(9)));
    });
    }

    @Test
    public void getFieldTest() {
        SudokuRow sudokuRow = objectWithValidList();
        List<Integer> sudokuRowList = sudokuRow.getFields();
        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i < SudokuFieldGroup.Size; i++) {
            valueList.add(i + 1);
        }
        assertEquals(sudokuRowList, valueList);
    }

    @Test
    public void toStringTest() {
        SudokuRow sudokuRow = objectWithValidList();
        assertNotNull(sudokuRow.toString());

    }

    @Test
    public void equalsTest() {
        SudokuRow sudokuRow = objectWithValidList();
        SudokuRow sudokuRowOther = objectWithValidList();

        assertTrue(sudokuRow.equals(sudokuRowOther) && sudokuRowOther.equals(sudokuRow));
    }

    @Test
    public void hashCodeTest() {
        SudokuRow sudokuRow = objectWithValidList();
        SudokuRow sudokuRowOther = objectWithValidList();

        assertEquals(sudokuRow.hashCode(), sudokuRowOther.hashCode());
    }

    @Test
    public void getSudokuFieldListTest() {
        assertThrows(UnsupportedOperationException.class, () -> {
            SudokuRow sudokuRow = objectWithValidList();
            SudokuField sudokuField = new SudokuField(3);
            List<SudokuField> fields = sudokuRow.getSudokuFieldList();
            fields.set(1, sudokuField);
        });
    }

}
