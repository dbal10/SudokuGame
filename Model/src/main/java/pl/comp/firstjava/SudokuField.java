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
//import java.util.ListResourceBundle;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.comp.firstjava.exception.BadFieldValueException;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    private int value;
    private boolean isEmptyField;
    private ResourceBundle listBundle = ResourceBundle.getBundle("pl.comp.firstjava.Language");

    public SudokuField(int value) {
        this.value = value;
    }

    public SudokuField() {

    }

    public int getFieldValue() {

        return this.value;
    }

    public boolean isEmptyField() {
        return isEmptyField;
    }

    public void setEmptyField() {
        isEmptyField = true;
    }

    public void setFieldValue(int value) {
        if (value < 0 || value > 9) {
            throw new BadFieldValueException(listBundle.getObject("_wrongFieldValue").toString());
        }
        this.value = value;
    }

    @Override
    public boolean equals(final Object obj) {

        return new EqualsBuilder().append(value, ((SudokuField) obj).value).isEquals();
    }

    //Metode dodajemy tylko w celu pozytywnej weryfikacji testu checkstyle
    @Override
    public int hashCode() {

        return new HashCodeBuilder(17,37).append(value).toHashCode();
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this).append("value", value).toString();
    }

    @Override
    public int compareTo(SudokuField o) {
        if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else if (this.getFieldValue() > o.getFieldValue()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SudokuField sudokuField = new SudokuField();
        sudokuField.value = this.value;
        return sudokuField;
    }
}
