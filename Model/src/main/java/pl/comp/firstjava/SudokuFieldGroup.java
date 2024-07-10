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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.comp.firstjava.exception.BadGroupSizeException;


public abstract class SudokuFieldGroup implements Serializable, Cloneable {
    public static final int Size = 9;
    private List<SudokuField> fields;
    private ResourceBundle listBundle = ResourceBundle.getBundle("pl.comp.firstjava.Language");

    public SudokuFieldGroup(final List<SudokuField> fields) {
        if (fields.size() != Size) {
            throw new BadGroupSizeException(listBundle.getObject("_wrongLength").toString());
        }
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int i2 = i + 1; i2 < 9; i2++) {
                if (fields.get(i).getFieldValue() == fields.get(i2).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }


    public List<SudokuField> getSudokuFieldList() {
        return Collections.unmodifiableList(fields);
    }

    public List<Integer> getFields() {
        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i < Size; i++) {
            valueList.add(fields.get(i).getFieldValue());
        }

        return valueList;
    }

    @Override
    public boolean equals(final Object obj) {

        return new EqualsBuilder().append(fields, ((SudokuFieldGroup) obj).fields).isEquals();
    }

    //Metode dodajemy tylko w celu pozytywnej weryfikacji testu checkstyle
    @Override
    public int hashCode() {

        return new HashCodeBuilder(17,37).append(fields).toHashCode();
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this).append("fields", fields).toString();
    }
}
