/*
 * MIT License
 *
 * Copyright (c) 2019.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *------------------------------------
 * Library Apache commons-lang3 has open source license
 */

package pl.comp.firstjava;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class BacktrackingSudokuSolver implements SudokuSolver {

    public void solve(final SudokuBoard board) {
        Random rand = new Random();
        //Tutaj tworzymy liste i inicjalizujemy zerem
        List<Integer> startNumbers = Arrays.asList(new Integer[SudokuBoard.How_Many_Cells]);

        for (int i = 0; i < startNumbers.size(); i++) {
            startNumbers.set(i, 0);
        }

        for (int i = 0; i < SudokuBoard.How_Many_Cells; i++) {

            int numberOfRow = i / SudokuBoard.Size;
            int numberOfColumn = i % SudokuBoard.Size;

            boolean checkIfFlagGood = false;

            if (startNumbers.get(i) == 0) {
                //W przypadku wykonania kroku do przodu ustawiamy nowa wartosc poczatkowa
                startNumbers.set(i, rand.nextInt(9) + 1);
                board.set(numberOfRow, numberOfColumn, startNumbers.get(i));

                do {
                    if (checkIfIsValid(i, board)) {
                        checkIfFlagGood = true;
                        break;
                    }
                    board.set(numberOfRow, numberOfColumn, board.get(numberOfRow, numberOfColumn)
                            % 9 + 1);
                } while (board.get(numberOfRow, numberOfColumn) != startNumbers.get(i));

            } else {
                //W przypadku kroku do tylu poprzednia wartosc poczatkowa jest uzywana
                board.set(numberOfRow, numberOfColumn, board.get(numberOfRow, numberOfColumn)
                        % 9 + 1);
                while (board.get(numberOfRow, numberOfColumn) != startNumbers.get(i)) {
                    if (checkIfIsValid(i, board)) {
                        checkIfFlagGood = true;
                        break;
                    }
                    board.set(numberOfRow, numberOfColumn, board.get(numberOfRow, numberOfColumn)
                            % 9 + 1);
                }
            }

            //W przypadku innej opcji cofamy sie
            if (!checkIfFlagGood) {
                startNumbers.set(i, 0);
                board.set(numberOfRow, numberOfColumn, 0);
                i -= 2;
            }
        }

    }

    private boolean checkIfIsValid(int index, final SudokuBoard board) {
        int numberOfRow = index / SudokuBoard.Size;
        int numberOfColumn = index % SudokuBoard.Size;
        int number = board.get(numberOfRow, numberOfColumn);

        //Sprawdzamy wiersz
        for (int i = 0; i < numberOfColumn; i++) {
            if (number == board.get(numberOfRow, i)) {
                return false;
            }
        }

        //Sprawdzamy kolumny
        for (int i = 0; i < numberOfRow; i++) {
            if (number == board.get(i, numberOfColumn)) {
                return false;
            }
        }



        //Sprawdzamy maly kwadrat
        int bigIndexOfRow = numberOfRow / (SudokuBoard.Size / 3);
        int bigIndexOfColumn = numberOfColumn / (SudokuBoard.Size / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int realIndexOfRow = i + bigIndexOfRow * 3;
                int realIndexOfColumn = j + bigIndexOfColumn * 3;
                if (board.get(realIndexOfRow, realIndexOfColumn) == number
                        && (realIndexOfRow * 9 + realIndexOfColumn) < index) {
                    return false;
                }
            }
        }
        return true;

    }
}
