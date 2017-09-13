package com.github.gusenov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class ExcelFunctionsEliminator implements Iterable<ExcelFunctionCallPosition> {
    private List<ExcelFunctionCallPosition> functionCallPositionCollection
            = new ArrayList<ExcelFunctionCallPosition>();

    public ExcelFunctionsEliminator(Pattern functionsPattern, String excelFormula) {

        ExcelFunctionsIterator functionsIterator = new ExcelFunctionsIterator(functionsPattern, excelFormula);

        for (ExcelFunctionCallPosition functionCallPosition : functionsIterator) {
            this.functionCallPositionCollection.add(functionCallPosition);
        }

        Collections.sort(this.functionCallPositionCollection);
    }

    @Override
    public Iterator<ExcelFunctionCallPosition> iterator() {
        Iterator<ExcelFunctionCallPosition> it = new Iterator<ExcelFunctionCallPosition>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < functionCallPositionCollection.size()
                        && functionCallPositionCollection.get(currentIndex) != null;
            }

            @Override
            public ExcelFunctionCallPosition next() {
                return functionCallPositionCollection.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

}
