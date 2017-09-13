package com.github.gusenov;

import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ExcelFunctionsIteratorTest {
    /**
     * @see <a href="https://stackoverflow.com/a/9785026">c++ - “items list” or “item list” - Stack Overflow</a>
     */
    private static final String[] functionArray = new String[] { "IF", "ISERROR" };

    private static Pattern functionsPattern;

    @Before
    public void before() {
        functionsPattern = ExcelPatternsBuilder.buildFunctionsCallsPattern(functionArray);
    }

    @Test
    public void test() {
        ExcelFunctionsIterator formula = new ExcelFunctionsIterator(functionsPattern, "=IF(ISERROR(P10),0,1)");
        Integer no = 1;
        for (ExcelFunctionCallPosition functionCallPosition : formula) {

            Integer functionNameStart = functionCallPosition.getNameStart();
            Integer functionNameEnd = functionCallPosition.getNameEnd();
            Integer leftParenthesis = functionCallPosition.getLeftParenthesis();
            Integer rightParenthesis = functionCallPosition.getRightParenthesis();

            // =IF(ISERROR(P10),0,1)
            // =  I  F  (  I  S  E  R  R  O  R   (   P   1   0   )   ,   0   ,   1   )
            // 0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  15  16  17  18  19  20

            switch (no++) {
                case 1:
                    assertEquals(1, functionNameStart.longValue());
                    assertEquals(2, functionNameEnd.longValue());
                    assertEquals(3, leftParenthesis.longValue());
                    assertEquals(20, rightParenthesis.longValue());
                    break;
                case 2:
                    assertEquals(4, functionNameStart.longValue());
                    assertEquals(10, functionNameEnd.longValue());
                    assertEquals(11, leftParenthesis.longValue());
                    assertEquals(15, rightParenthesis.longValue());
                    break;
            }

            System.out.println(String.format("%d %d %d %d",
                    functionNameStart, functionNameEnd, leftParenthesis, rightParenthesis));
        }
    }
}
