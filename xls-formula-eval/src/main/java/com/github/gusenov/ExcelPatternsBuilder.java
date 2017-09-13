package com.github.gusenov;

import java.util.regex.Pattern;

/**
 * @see <a href="https://stackoverflow.com/questions/17971466/java-regex-overlapping-matches">Java regex - overlapping matches - Stack Overflow</a>
 * @author <a href="mailto:gusenov@live.ru">Аббас Гусенов</a>
 */
public class ExcelPatternsBuilder {
    /**
     * Сконструировать регулярное выражение для поиска вызовов функций.
     *
     * Пример:
     * Сигнатура вызова функции SUM или IF: \b(SUM|IF)\s*\(
     * где:
     *  \b     - word boundary
     *  SUM|IF - match SUM or IF
     *
     * @param functionsNames наименования функций вызовы которых нужно искать.
     * @return               регулярное выражение.
     */
    public static String buildFunctionsCallsRegExp(String[] functionsNames) {
        StringBuilder regExpBuilder = new StringBuilder("\\b(");
        for (Integer idx = 0; idx < functionsNames.length; idx++) {
            String functionName = functionsNames[idx];
            regExpBuilder.append(functionName);
            if (idx != functionsNames.length - 1) {
                regExpBuilder.append('|');
            }
        }
        regExpBuilder.append(")\\s*\\(");
        return regExpBuilder.toString();
    }

    public static Pattern buildFunctionsCallsPattern(String[] functionsNames) {
        Pattern functionsPattern = Pattern.compile(buildFunctionsCallsRegExp(functionsNames));
        return functionsPattern;
    }
}
