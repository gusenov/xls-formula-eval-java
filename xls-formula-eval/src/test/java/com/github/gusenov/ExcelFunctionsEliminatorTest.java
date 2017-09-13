package com.github.gusenov;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class ExcelFunctionsEliminatorTest {
    private static final String[] functionArray = new String[] { "AND", "NOW" };

    private static Pattern functionsPattern;

    @Before
    public void before() {
        functionsPattern = ExcelPatternsBuilder.buildFunctionsCallsPattern(functionArray);
    }

    /**
     * @see <a href="https://www.javatpoint.com/substring">Substring in Java - javatpoint</a>
     */
    @Test
    public void test() {

        // https://exceljet.net/conditional-formatting-with-formulas
        // =AND(B4>NOW(),B4<=(NOW()+30))
        // =  A  N  D  (  B  4  >  N   O   W   (   )   ,   B   4   <   =   (   N   O   W   (   )   +   3   0   )   )
        // 0  1  2  3  4  5  6  7  8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28

        String excelFormula = "=AND(B4>NOW(),B4<=(NOW()+30))";
        System.out.println(excelFormula);

        ExcelFunctionsEliminator formula = new ExcelFunctionsEliminator(functionsPattern, excelFormula);

        Integer no = 1;

        for (ExcelFunctionCallPosition functionCallPosition : formula) {

            Integer functionNameStart = functionCallPosition.getNameStart();
            Integer functionNameEnd = functionCallPosition.getNameEnd();
            Integer leftParenthesis = functionCallPosition.getLeftParenthesis();
            Integer rightParenthesis = functionCallPosition.getRightParenthesis();

            switch (no++) {
                case 1:
                    assertEquals(8, functionNameStart.longValue());
                    assertEquals(10, functionNameEnd.longValue());
                    assertEquals(11, leftParenthesis.longValue());
                    assertEquals(12, rightParenthesis.longValue());
                    break;
                case 2:
                    assertEquals(19, functionNameStart.longValue());
                    assertEquals(21, functionNameEnd.longValue());
                    assertEquals(22, leftParenthesis.longValue());
                    assertEquals(23, rightParenthesis.longValue());
                    break;
                case 3:
                    assertEquals(1, functionNameStart.longValue());
                    assertEquals(3, functionNameEnd.longValue());
                    assertEquals(4, leftParenthesis.longValue());
                    assertEquals(28, rightParenthesis.longValue());
                    break;
            }

            System.out.println(String.format("%d %d %d %d",
                    functionNameStart, functionNameEnd, leftParenthesis, rightParenthesis));
            System.out.println(String.format("%s◉%s",
                    excelFormula.substring(0, functionNameStart),
                    excelFormula.substring(rightParenthesis + 1, excelFormula.length())
            ));

        }
    }
}
