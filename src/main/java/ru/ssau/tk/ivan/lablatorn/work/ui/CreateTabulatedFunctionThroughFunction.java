package ru.ssau.tk.ivan.lablatorn.work.ui;

import ru.ssau.tk.ivan.lablatorn.work.function.*;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateTabulatedFunctionThroughFunction extends JDialog {

    private final JComboBox<String> functionComboBox = new JComboBox<>();
    private final JButton buttonCreateFunction = new JButton("Создать функцию");


    private final JLabel fromLabel = new JLabel("От:");
    private final JLabel toLabel = new JLabel("До:");
    private final JLabel countLabel = new JLabel("Количество:");
    private final JTextField countField = new JTextField();
    private final JTextField fromField = new JTextField();
    private final JTextField toField = new JTextField();
    private final Map<String, MathFunction> nameFunctionMap = new HashMap<>();
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    protected TabulatedFunction function;


    public CreateTabulatedFunctionThroughFunction() {
        super();
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setModal(true);
        setSize(500, 150);
        setTitle("Function");
        fillMap();

        getContentPane().add(countLabel);
        getContentPane().add(countField);

        getContentPane().add(fromLabel);
        getContentPane().add(fromField);
        getContentPane().add(toLabel);
        getContentPane().add(toField);


        getContentPane().add(buttonCreateFunction);
        getContentPane().add(functionComboBox);

        compose();
        addButtonListeners();
        setVisible(true);
    }

    private void addButtonListeners() {
        buttonCreateFunction.addActionListener(evt -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFunctionMap.get(func);
                double xFrom = Double.parseDouble(fromField.getText());
                double xTo = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                function = CreateTabulatedFunctionThroughFunction.factory.create(selectedFunction,xFrom, xTo, count);
                System.out.println(function.toString());
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });

    }


    public static void main(String[] args) {
        new CreateTabulatedFunctionThroughFunction();
    }


    public void fillMap() {
        nameFunctionMap.put("Квадратичная функция", new SqrFunction());
        nameFunctionMap.put("Нулевая функция", new ZeroFunction());
        nameFunctionMap.put("Единичная функция", new UnitFunction());
        nameFunctionMap.put("Тождественная функция", new IdentityFunction());
        String[] functions = new String[4];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }


    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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
}
