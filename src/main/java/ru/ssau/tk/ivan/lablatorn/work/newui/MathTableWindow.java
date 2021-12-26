package ru.ssau.tk.ivan.lablatorn.work.newui;

import ru.ssau.tk.ivan.lablatorn.work.function.*;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MathTableWindow extends JDialog {
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    private final JButton buttonCreateFunction = new JButton("Создать функцию");

    private final JLabel fromLabel = new JLabel("От:");
    private final JLabel toLabel = new JLabel("До:");
    private final JLabel countLabel = new JLabel("Количество точек разбиения:");

    private final JTextField fromField = new JTextField();
    private final JTextField toField = new JTextField();
    private final JTextField countField = new JTextField();

    private final Map<String, MathFunction> nameFunctionMap = new HashMap<>();
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    protected TabulatedFunction function;

    public MathTableWindow(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        super();
        setTitle("Создание функцию c помощью другой функции");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 150);
        allFunctions();

        container.add(countLabel);
        container.add(countField);
        container.add(fromLabel);
        container.add(fromField);
        container.add(toLabel);
        container.add(toField);
        container.add(buttonCreateFunction);
        container.add(functionComboBox);
        container.setBackground(Color.PINK);

        fromLabel.setForeground(new Color(45,57,22));
        toLabel.setForeground(new Color(45,57,22));
        countLabel.setForeground(new Color(45,57,22));

        buttonCreateFunction.setBackground(new Color(45,57,22));
        buttonCreateFunction.setForeground(new Color(232, 56, 0));

        functionComboBox.setForeground(new Color(45,57,22));
        functionComboBox.setBackground(new Color(232, 56, 0));

        compose();
        addButtonListeners(callback);

        setModal(true);
        setLocationRelativeTo(null);
    }

    private void addButtonListeners(Consumer<? super TabulatedFunction> callback) {
        buttonCreateFunction.addActionListener(evt -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFunctionMap.get(func);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                function = MathTableWindow.factory.create(selectedFunction, from, to, count);
                callback.accept(function);
                setVisible(true);
                this.dispose();
            } catch (Exception e) {
                new ExceptionWindow(this, e);
            }
        });
    }

    private void compose() {
        Container container = getContentPane();
        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(buttonCreateFunction)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(buttonCreateFunction)
        );
    }

    public void allFunctions() {
        nameFunctionMap.put("Единичная функция", new UnitFunction());
        nameFunctionMap.put("Квадратичная функция", new SqrFunction());
        nameFunctionMap.put("Нулевая функция", new ZeroFunction());
        nameFunctionMap.put("Тождественная функция", new IdentityFunction());
        nameFunctionMap.put("Функция кубического корня", new ThreeRootFunction());
        nameFunctionMap.put("Функция тангенса", new TangFunction());
        String[] functions = new String[7];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public static void main(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) throws IOException {
        MathTableWindow mathTableWindow = new MathTableWindow(factory, callback);
        mathTableWindow.setVisible(true);
    }
}
