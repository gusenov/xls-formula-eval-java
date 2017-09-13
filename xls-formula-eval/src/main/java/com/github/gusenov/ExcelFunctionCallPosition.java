package com.github.gusenov;

/**
 * Класс представляет собой информацию о позиции функции в формуле.
 *
 * @author <a href="mailto:gusenov@live.ru">Аббас Гусенов</a>
 */
public class ExcelFunctionCallPosition implements Comparable<ExcelFunctionCallPosition> {
    private Integer nameStart;

    private Integer nameEnd;

    private Integer leftParenthesis;

    private Integer rightParenthesis;

    public ExcelFunctionCallPosition(Integer nameStart, Integer nameEnd, Integer leftParenthesis, Integer rightParenthesis) {
        this.setNameStart(nameStart);
        this.setNameEnd(nameEnd);
        this.setLeftParenthesis(leftParenthesis);
        this.setRightParenthesis(rightParenthesis);
    }

    /**
     *
     * @param other
     * @return
     *
     * @see <a href="https://stackoverflow.com/a/21626529/2289640">How to implement the Java comparable interface? - Stack Overflow</a>
     * @see <a href="https://stackoverflow.com/a/4066659/2289640">java - Sort an ArrayList based on an object field - Stack Overflow</a>
     */
    @Override
    public int compareTo(ExcelFunctionCallPosition other) {
        return Integer.compare(this.getRightParenthesis() - this.getNameStart(), other.getRightParenthesis() - other.getNameStart());
    }

    public Integer getNameStart() {
        return nameStart;
    }

    private void setNameStart(Integer nameStart) {
        this.nameStart = nameStart;
    }

    public Integer getNameEnd() {
        return nameEnd;
    }

    private void setNameEnd(Integer nameEnd) {
        this.nameEnd = nameEnd;
    }

    public Integer getLeftParenthesis() {
        return leftParenthesis;
    }

    private void setLeftParenthesis(Integer leftParenthesis) {
        this.leftParenthesis = leftParenthesis;
    }

    public Integer getRightParenthesis() {
        return rightParenthesis;
    }

    private void setRightParenthesis(Integer rightParenthesis) {
        this.rightParenthesis = rightParenthesis;
    }
}
