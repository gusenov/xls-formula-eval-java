package com.github.gusenov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для обхода всех функций в формуле.
 *
 * @author <a href="mailto:gusenov@live.ru">Аббас Гусенов</a>
 */
public class ExcelFunctionsIterator implements Iterable<ExcelFunctionCallPosition> {
    private Pattern functionsPattern;

    private String excelFormula;

    public ExcelFunctionsIterator(Pattern functionsPattern, String excelFormula) {
        this.functionsPattern = functionsPattern;
        this.excelFormula = excelFormula;
    }

    /**
     * @see <a href="https://stackoverflow.com/a/5849625/2289640">Can we write our own iterator in Java? - Stack Overflow</a>
     */
    @Override
    public Iterator<ExcelFunctionCallPosition> iterator() {
        Iterator<ExcelFunctionCallPosition> it = new Iterator<ExcelFunctionCallPosition>() {
            private Matcher functionsMatcher = functionsPattern.matcher(excelFormula);

            private ExcelFunctionCallPosition functionCallPosition;

            /**
             * @see <a href="https://stackoverflow.com/a/42442308/2289640">regex - Index of each matcher group of a pattern in Java - Stack Overflow</a>
             */
            @Override
            public boolean hasNext() {
                if (functionsMatcher.find()) {
                    Integer functionNameStart = functionsMatcher.start(1);
                    Integer leftParenthesis = functionsMatcher.end(1);
                    Integer functionNameEnd = leftParenthesis - 1;
                    Integer rightParenthesis = findRightParenthesis(leftParenthesis);
                    this.functionCallPosition = new ExcelFunctionCallPosition(functionNameStart, functionNameEnd, leftParenthesis, rightParenthesis);
                } else {
                    this.functionCallPosition = null;
                }
                return functionCallPosition != null;
            }

            @Override
            public ExcelFunctionCallPosition next() {
                return this.functionCallPosition;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    private Integer findRightParenthesis(Integer startPosition) {
        Stack<Character> stack = new Stack<Character>();
        for (Integer charIndex  = startPosition; charIndex < this.excelFormula.length(); charIndex++) {
            if (this.excelFormula.charAt(charIndex) == '(') {
                stack.push('(');
            } else if (this.excelFormula.charAt(charIndex) == ')') {
                if (stack.isEmpty()) {
                    throw new ExcelFormulaException("Не сбалансированы скобки в формуле!");
                }
                stack.pop();
                if (stack.isEmpty()) {
                    return charIndex;
                }
            }
        }
        throw new ExcelFormulaException("Не сбалансированы скобки в формуле!");
    }
}
