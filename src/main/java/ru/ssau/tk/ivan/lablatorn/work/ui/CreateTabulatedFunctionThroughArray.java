package ru.ssau.tk.ivan.lablatorn.work.ui;

import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CreateTabulatedFunctionThroughArray extends JDialog {

    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private final AbstractTableModel tableModel = new TableModel(xValues, yValues);
    private final JTable table = new JTable(tableModel);
    private final JLabel label = new JLabel("Введите количество точек:");
    private final JTextField countField = new JTextField();
    private final JButton inputButton = new JButton("Ввести");
    private final JButton createButton = new JButton("Создать");
    private TabulatedFunction function;


    public CreateTabulatedFunctionThroughArray() {
        super();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setBounds(300, 300, 500, 500);

        getContentPane().add(countField);
        getContentPane().add(inputButton);
        getContentPane().add(createButton);


        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void addButtonListeners() {
        inputButton.addActionListener(event -> {
            try {
                int count = Integer.parseInt(countField.getText());
                for (int i = 0; i < count; i++) {
                    xValues.add(0.);
                    yValues.add(0.);
                    tableModel.fireTableDataChanged();
                }
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });
        createButton.addActionListener(event -> {
            try {
                double[] x = new double[xValues.size()];
                double[] y = new double[xValues.size()];


                for (int i = 0; i < xValues.size(); i++) {

                    x[i] = xValues.get(i);
                    y[i] = Double.parseDouble(tableModel.getValueAt(i, 2).toString());
                }
                function = new ArrayTabulatedFunctionFactory().create(x, y);
                System.out.println(function.toString());
                setVisible(false);
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });


    }


    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(createButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(createButton)
        );
    }

    public static void main(String[] args) {
        new CreateTabulatedFunctionThroughArray();
    }

}
