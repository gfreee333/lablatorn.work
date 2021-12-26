package ru.ssau.tk.ivan.lablatorn.work.newui;

import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.operations.TabulatedDifferentialOperator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DifferentialOperationWindow extends JFrame {
    private final TabulatedDifferentialOperator diffOperator = new TabulatedDifferentialOperator();

    private final TableForMain tableForFirstFunction = new TableForMain();
    private final TableForResult tableForResult = new TableForResult();
    private final JLabel create = new JLabel("Создать функцию с помощью:");
    private final JLabel createResult = new JLabel("Ваш результат:");
    private final JTable tableFirst = new JTable(tableForFirstFunction);
    private final JTable tableResult = new JTable(tableForResult);
    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();

    private final JButton diff = new JButton("Найти производную");
    private final JButton createTub = new JButton("массива");
    private final JButton createMath = new JButton("функции");
    private final JButton save = new JButton("Сохранить");
    private final JButton input = new JButton("Открыть");

    private final JButton saveResult = new JButton("Сохранить");

    private TabulatedFunctionFactory factory;

    public DifferentialOperationWindow() {
        setTitle("Нахождение производной");

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        container.setBackground(new Color(45,57,22));
        setSize(1000, 580);

        container.add(designButton(diff));

        container.add(designButton(createTub));
        container.add(designButton(createMath));
        container.add(designButton(save));
        container.add(designButton(input));

        container.add(designButton(saveResult));

        designTable(tableFirst);
        designTable(tableResult);

        designLabel(create);
        designLabel(createResult);

        compose();
        addButtonListeners();
        setLocationRelativeTo(null);
    }

    public Component designButton(JButton button) {
        button.setBackground(new Color(232, 56, 0));
        button.setForeground(new Color(45,57,22));
        button.setFocusPainted(false);
        return button;
    }

    public void designLabel(JLabel label) {
        label.setFont(new Font("Consolas", Font.ITALIC + Font.BOLD, 15));
        label.setForeground(new Color(232, 56, 0));
        label.setVerticalAlignment(JLabel.TOP);
    }

    public void designTable(JTable table) {
        table.setBackground(new Color(45,57,22));
        table.setGridColor(new Color(232, 56, 0));
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane firstTableScrollPane = new JScrollPane(tableFirst);
        JScrollPane resultTableScrollPane = new JScrollPane(tableResult);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(create)
                        .addGap(298)
                        .addComponent(createResult))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createTub)
                        .addComponent(createMath)
                        .addGap(325)
                        .addComponent(diff))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(firstTableScrollPane)
                        .addGap(40)
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addGap(125)
                        .addComponent(save)
                        .addComponent(input)
                        .addGap(360)
                        .addComponent(saveResult)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(create)
                        .addComponent(createResult))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createTub)
                        .addComponent(createMath)
                        .addComponent(diff))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(firstTableScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120))
                        .addComponent(resultTableScrollPane))
                .addGap(30)
                .addGroup(layout.createParallelGroup()
                        .addComponent(save)
                        .addComponent(input)
                        .addComponent(saveResult)));
    }

    public void wrapTable(TableForMain tableModel, int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    public void wrapTableForResult(TableForResult tableModel, int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    private void addButtonListeners() {
        createTub.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        TabulatedTableWindow.main(factory, tableForFirstFunction::setFunction);
                        int countNew = tableForFirstFunction.getFunction().getCount();
                        wrapTable(tableForFirstFunction, countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new ExceptionWindow(this, e);
                    }
                }
        );

        createMath.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MathTableWindow.main(factory, tableForFirstFunction::setFunction);
                int countNew = tableForFirstFunction.getFunction().getCount();
                wrapTable(tableForFirstFunction, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        save.addActionListener(event -> {
            try {
                FileWriter.main(tableForFirstFunction.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        saveResult.addActionListener(event -> {
            try {
                FileWriter.main(tableForResult.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        input.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(tableForFirstFunction::setFunction);
                int countNew = tableForFirstFunction.getFunction().getCount();
                wrapTable(tableForFirstFunction, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        diff.addActionListener(event -> {
            try {
                int countOld = tableForFirstFunction.getFunction().getCount();
                tableForResult.setFunction(diffOperator.derive(tableForFirstFunction.getFunction()));
                int countNew = tableForResult.getFunction().getCount();
                wrapTableForResult(tableForResult, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });
    }

    public static void main() {
        DifferentialOperationWindow window = new DifferentialOperationWindow();
        window.setVisible(true);
        window.setResizable(false);
    }
}
