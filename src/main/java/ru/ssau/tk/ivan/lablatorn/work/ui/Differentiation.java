package ru.ssau.tk.ivan.lablatorn.work.ui;

import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.operations.TabulatedDifferentialOperator;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Differentiation extends JDialog {
    //Таблица результата
    private final java.util.List<String> xValuesResult = new ArrayList<>(0);
    private final List<String> yValuesResult = new ArrayList<>(0);
    private final AbstractTableModel tableModelResult = new EditableTable(xValuesResult, yValuesResult, false);
    private final JTable tableResult = new JTable(tableModelResult);

    private final java.util.List<String> xValuesInitial = new ArrayList<>();
    private final List<String> yValuesInitial = new ArrayList<>();
    private final AbstractTableModel tableModelInitial = new EditableTable(xValuesInitial, yValuesInitial, true);
    private final JTable tableInitial = new JTable(tableModelInitial);

    private final JButton button = new JButton("Вычислить..");
    private final JButton buttonResult = new JButton("Сохранить результат");

    private final JButton buttonCreate = new JButton("Создать..");
    private final JButton buttonSave = new JButton("Сохранить..");
    private final JButton buttonDownload = new JButton("Загрузить..");

    private final TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(MainWindow.functionFactory);

    protected TabulatedFunction functionResult;
    protected TabulatedFunction functionInitial;

    protected Differentiation() {
        super();
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setModal(true);
        setBounds(100, 100, 800, 700);

        compose();
        addButtonListeners();

        CreatingTFThroughArray.checkBoxSave.setVisible(false);
        CreatingTFThroughFunction.checkBoxSave.setVisible(false);

        tableInitial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setVisible(true);
    }

    private void addButtonListeners() {
        //создание функции
        buttonCreate.addActionListener(e -> {
            Object[] buttonsName = {"Массив", "Функция", "Отмена"};
            int resultDialog = JOptionPane.showOptionDialog(new JFrame(), "Как вы хотите создать функцию?",
                    "Создать..", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, buttonsName, buttonsName[2]);
            xValuesInitial.clear();
            yValuesInitial.clear();

            xValuesResult.clear();
            yValuesResult.clear();

            switch (resultDialog) {
                case 0:
                    new CreatingTFThroughArray(function -> functionInitial = function);
                    break;
                case 1:
                    new CreatingTFThroughFunction(function -> functionInitial = function);
                    break;
            }

            for (int i = 0; i < functionInitial.getCount(); i++) {
                xValuesInitial.add(i, String.valueOf(functionInitial.getX(i)));
                yValuesInitial.add(i, String.valueOf(functionInitial.getY(i)));

                tableModelInitial.fireTableDataChanged();
            }
        });

        //подсчет и вывод результата
        button.addActionListener(e -> {
            //заменить измененные значения функции
            tableInitial.clearSelection();
            xValuesResult.clear();
            yValuesResult.clear();

            double[] arrayX = convert(xValuesInitial);
            double[] arrayY = convert(yValuesInitial);

            //подсчет производной и вставка в таблицу
            functionResult = differentialOperator.derive(MainWindow.functionFactory.create(arrayX, arrayY));
            for (int i = 0; i < functionResult.getCount(); i++) {
                xValuesResult.add(i, String.valueOf(functionResult.getX(i)));
                yValuesResult.add(i, String.valueOf(functionResult.getY(i)));

                tableModelResult.fireTableDataChanged();
            }
        });

        buttonSave.addActionListener(e -> {
            //save
        });

        buttonDownload.addActionListener(e -> {
            //Download
        });
    }

    private void compose() {
        JPanel panelResult = new JPanel();
        GroupLayout layoutResult = new GroupLayout(panelResult);
        panelResult.setLayout(layoutResult);
        layoutResult.setAutoCreateGaps(true);
        layoutResult.setAutoCreateContainerGaps(true);

        JScrollPane scrollPaneResult = new JScrollPane(tableResult);
        layoutResult.setHorizontalGroup(
                layoutResult.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layoutResult.createSequentialGroup()
                                .addComponent(buttonResult)
                                .addComponent(button))
                        .addComponent(scrollPaneResult));

        layoutResult.setVerticalGroup(layoutResult.createSequentialGroup()
                .addGroup(layoutResult.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonResult)
                        .addComponent(button))
                .addComponent(scrollPaneResult));


        JPanel panelInitial = new JPanel();
        GroupLayout layoutInitial = new GroupLayout(panelInitial);
        panelInitial.setLayout(layoutInitial);
        layoutInitial.setAutoCreateGaps(true);
        layoutInitial.setAutoCreateContainerGaps(true);

        JScrollPane scrollPaneInitial = new JScrollPane(tableInitial);
        layoutInitial.setHorizontalGroup(
                layoutInitial.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layoutInitial.createSequentialGroup()
                                .addComponent(buttonCreate)
                                .addComponent(buttonDownload)
                                .addComponent(buttonSave))
                        .addComponent(scrollPaneInitial));

        layoutInitial.setVerticalGroup(layoutInitial.createSequentialGroup()
                .addGroup(layoutInitial.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonCreate)
                        .addComponent(buttonDownload)
                        .addComponent(buttonSave))
                .addComponent(scrollPaneInitial));


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup().addGroup(layout.createSequentialGroup()
                .addComponent(panelInitial)
                .addComponent(panelResult)));

        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup()
                .addComponent(panelInitial)
                .addComponent(panelResult)));

        getContentPane().setBackground(Settings.color);
        panelInitial.setBackground(Settings.color);
        panelResult.setBackground(Settings.color);
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
