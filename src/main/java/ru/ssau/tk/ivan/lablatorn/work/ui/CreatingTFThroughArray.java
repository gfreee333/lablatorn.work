package ru.ssau.tk.ivan.lablatorn.work.ui;

import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
public class CreatingTFThroughArray extends JDialog {
    //Count
    private final JLabel labelCount = new JLabel("Количество точек n:");
    private final JTextField textFieldCount = new JTextField("2");
    private final JButton buttonCreateTable = new JButton("Создать");
    //X & Y
    private final java.util.List<String> xValues = new ArrayList<>();
    private final List<String> yValues = new ArrayList<>();
    private final AbstractTableModel tableModel = new TableModelXY(xValues, yValues);
    private final JTable tableXY = new JTable(tableModel);
    //TF
    private final JButton buttonCreateFunction = new JButton("Создать функцию");
    public TabulatedFunction function;
    protected static JCheckBox checkBoxSave = new JCheckBox("Сохранить функцию");

    protected CreatingTFThroughArray(Consumer<? super TabulatedFunction> callback) {
        super();
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setModal(true);
        setBounds(100, 100, 800, 700);
        getContentPane().add(labelCount);
        getContentPane().add(textFieldCount);
        getContentPane().add(buttonCreateTable);
        getContentPane().add(buttonCreateFunction);
        getContentPane().add(checkBoxSave);
        compose();
        addButtonListeners();
        tableXY.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setVisible(true);
        callback.accept(function);
        dispose();
    }
    private void addButtonListeners() {
        buttonCreateTable.addActionListener(
                e -> {
                    int count = Integer.parseInt(textFieldCount.getText());
                    for (int i = 0; i < count; i++) {
                        xValues.add(i, "");
                        yValues.add(i, "");
                        tableModel.fireTableDataChanged();
                    }
                }
        );
        buttonCreateFunction.addActionListener(e -> {
            tableXY.clearSelection();
            tableXY.getCellEditor().stopCellEditing();
            double[] arrayX = convert(xValues);
            double[] arrayY = convert(yValues);

            function = MainWindow.functionFactory.create(arrayX, arrayY);

            tableXY.setCellSelectionEnabled(true);
            dispose();

        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane scrollPane = new JScrollPane(tableXY);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(labelCount)
                        .addComponent(textFieldCount)
                        .addComponent(buttonCreateTable))
                .addComponent(scrollPane)
                .addComponent(checkBoxSave)
                .addComponent(buttonCreateFunction)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelCount)
                        .addComponent(textFieldCount)
                        .addComponent(buttonCreateTable))
                .addComponent(scrollPane)
                .addComponent(checkBoxSave)
                .addComponent(buttonCreateFunction));
        getContentPane().setBackground(Settings.color);
        checkBoxSave.setBackground(Settings.color);
    }
    private double[] convert(List<String> values) {
        double[] array = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            String num = values.get(i);
            array[i] = Double.parseDouble(num);
        }
        return array;

    }
}