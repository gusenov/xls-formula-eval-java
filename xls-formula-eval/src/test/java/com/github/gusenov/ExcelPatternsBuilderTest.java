package com.github.gusenov;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ExcelPatternsBuilderTest {
    @Before
    public void before() {
    }

    @Test
    public void test() {
        String[] functionArray;
        String regExp;
        functionArray = new String[] { "IF", "ISERROR" };;
        regExp = ExcelPatternsBuilder.buildFunctionsCallsRegExp(functionArray);
        System.out.println(regExp);
        assertEquals("\\b(IF|ISERROR)\\s*\\(", regExp);
    }
}
