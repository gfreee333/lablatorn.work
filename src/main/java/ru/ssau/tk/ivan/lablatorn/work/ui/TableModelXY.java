package ru.ssau.tk.ivan.lablatorn.work.ui;

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class TableModelXY extends AbstractTableModel {

    public static final int X_COLUMN_NUMBER = 0;
    public static final int Y_COLUMN_NUMBER = 1;
    private static final long serialVersionUID = -909339284950570810L;

    private final List<String> xValues;
    private final List<String> yValues;

    protected TableModelXY(List<String> xValues, List<String> yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN_NUMBER:
                return xValues.get(rowIndex);

            case Y_COLUMN_NUMBER:
                return yValues.get(rowIndex);

        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN_NUMBER:
                xValues.set(rowIndex, String.valueOf(aValue));
                break;
            case Y_COLUMN_NUMBER:
                yValues.set(rowIndex, String.valueOf(aValue));
                break;
        }
    }

    @Override
    public int getRowCount() {
        return xValues.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case X_COLUMN_NUMBER:
                return "X: ";
            case Y_COLUMN_NUMBER:
                return "Y: ";

        }
        return super.getColumnName(column);
    }
}
