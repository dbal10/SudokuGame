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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pl.comp.firstjava.exception.BadFieldValueException;

public class SudokuFieldTest {
    private SudokuField sudokuField;
    private SudokuField sudokuFieldOther;

    @BeforeEach
    public void setUp() {
        sudokuField = new SudokuField();
    }

    @Test
    public void getFieldValueTest() {
        assertEquals(sudokuField.getFieldValue(), 0);
    }

    @Test
    public void setFieldValueTest() {
        sudokuField.setFieldValue(5);
        assertEquals(sudokuField.getFieldValue(), 5);
    }

    @Test
    public void setBadFieldValueTest() {
        assertThrows(BadFieldValueException.class, () -> {
            sudokuField.setFieldValue(-1);
    });
    }

    @Test
    public void setBadFieldValueTestOther() {
        assertThrows(BadFieldValueException.class, () -> {
            sudokuField.setFieldValue(10);
        });
    }

    @Test
    public void isEmptyFieldTest() { assertFalse(sudokuField.isEmptyField()); }

    @Test
    public void setEmptyFieldTest() {
        sudokuField.setEmptyField();
        assertTrue(sudokuField.isEmptyField());
    }

    @Test
    public void toStringTest() {
        assertNotNull(sudokuField.toString());

    }

    @Test
    public void equalsTest() {
        sudokuFieldOther = new SudokuField();

        assertTrue(sudokuField.equals(sudokuFieldOther) && sudokuFieldOther.equals(sudokuField));
    }

    @Test
    public void hashCodeTest() {
        sudokuFieldOther = new SudokuField();

        assertEquals(sudokuField.hashCode(), sudokuFieldOther.hashCode());
    }

    @Test
    public void compareToTest() {
        sudokuFieldOther = new SudokuField();
        sudokuField.setFieldValue(4);
        sudokuFieldOther.setFieldValue(4);
        assertEquals(sudokuField.compareTo(sudokuFieldOther), 0);

        sudokuField.setFieldValue(8);
        sudokuFieldOther.setFieldValue(4);
        assertEquals(sudokuField.compareTo(sudokuFieldOther),1);

        sudokuField.setFieldValue(4);
        sudokuFieldOther.setFieldValue(8);
        assertEquals(sudokuField.compareTo(sudokuFieldOther),-1);
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        sudokuField = new SudokuField(8);
        sudokuFieldOther = (SudokuField) sudokuField.clone();

        assertTrue(sudokuField.equals(sudokuFieldOther) && sudokuFieldOther.equals(sudokuField));
    }


    @AfterEach
    void tearDown() {
    }

}
