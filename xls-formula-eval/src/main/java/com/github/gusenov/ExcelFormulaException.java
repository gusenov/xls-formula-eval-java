package com.github.gusenov;

public class ExcelFormulaException extends RuntimeException {
    public ExcelFormulaException(String message) {
        super(message);
    }

    public ExcelFormulaException(String message, Throwable cause) {
        super(message, cause);
    }
}
