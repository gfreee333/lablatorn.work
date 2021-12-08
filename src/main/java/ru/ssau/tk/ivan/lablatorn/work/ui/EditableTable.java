package ru.ssau.tk.ivan.lablatorn.work.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;
public class EditableTable extends AbstractTableModel {
    private static final int X_COLUMN_NUMBER = 0;
    private static final int Y_COLUMN_NUMBER = 1;
    private static final long serialVersionUID = -6711044356115370439L;

    private final List<String> xValues;
    private final List<String> yValues;
    private final boolean editable;

    protected EditableTable(List<String> xValues, List<String> yValues, boolean editable) {
        this.xValues = xValues;
        this.yValues = yValues;
        this.editable = editable;
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
                break;
            case Y_COLUMN_NUMBER:
                yValues.set(rowIndex, String.valueOf(aValue));
                break;
        }
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN_NUMBER:
                return false;
            case Y_COLUMN_NUMBER:
                return editable;
        }
        return false;
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