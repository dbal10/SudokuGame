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

import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends SudokuFieldGroup {
    public SudokuRow(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuFieldList());
        return new SudokuRow(fields);
    }
}
