package ru.ssau.tk.ivan.lablatorn.work.ui;

import ru.ssau.tk.ivan.lablatorn.work.function.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CreatingTFThroughFunction extends JDialog {
    //Count
    private final JLabel labelCount = new JLabel("Количество точек: ");
    private final JTextField textFieldCount = new JTextField(2);
    //From - To
    private final JLabel labelInterval = new JLabel("Интервал: ");
    private final JLabel labelBracket1 = new JLabel("[ ");
    private final JLabel labelBracket3 = new JLabel(" ]");
    private final JLabel labelBracket2 = new JLabel(" ; ");
    private final JTextField textFieldTo = new JTextField();
    private final JTextField textFieldFrom = new JTextField();
    //TF
    private final JButton buttonCreateFunction = new JButton("Создать функцию");
    public TabulatedFunction function;
    protected static JCheckBox checkBoxSave = new JCheckBox("Сохранить функцию");
    //
    Map<String, MathFunction> functionMap = new HashMap<>();
    JComboBox<String> comboBoxFunctions = showComboBox();

    protected CreatingTFThroughFunction(Consumer<? super TabulatedFunction> callback) {
        super();
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setBounds(100, 100, 800, 700);

        getContentPane().add(labelCount);
        getContentPane().add(textFieldCount);

        getContentPane().add(labelInterval);
        getContentPane().add(labelBracket1);
        getContentPane().add(textFieldFrom);
        getContentPane().add(labelBracket2);
        getContentPane().add(textFieldTo);
        getContentPane().add(labelBracket3);

        getContentPane().add(buttonCreateFunction);
        getContentPane().add(comboBoxFunctions);

        compose();
        addButtonListeners();
        setVisible(true);

        callback.accept(function);
        dispose();
    }

    private void addButtonListeners() {
        buttonCreateFunction.addActionListener(
                e -> {
                    int count = Integer.parseInt(textFieldCount.getText());
                    double from = Double.parseDouble(textFieldFrom.getText());
                    double to = Double.parseDouble(textFieldTo.getText());
                    String str = comboBoxFunctions.getItemAt(comboBoxFunctions.getSelectedIndex());
                    if (str.equals("Константная функция")) {
                        String result = JOptionPane.showInputDialog("Введите значение константы");
                        double constant = Double.parseDouble(result);
                        function = MainWindow.functionFactory.create(new ConstantFunction(constant), from, to, count);
                    } else {
                        MathFunction mathFunction = functionMap.get(str);

                        function = MainWindow.functionFactory.create(mathFunction, from, to, count);
                    }
                    dispose();
                    System.out.println(function.toString());
                }
        );
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(labelCount)
                        .addComponent(textFieldCount))
                .addComponent(labelInterval)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(labelBracket1)
                        .addComponent(textFieldFrom)
                        .addComponent(labelBracket2)
                        .addComponent(textFieldTo)
                        .addComponent(labelBracket3))
                .addComponent(comboBoxFunctions)
                .addComponent(checkBoxSave)
                .addComponent(buttonCreateFunction)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelCount)
                        .addComponent(textFieldCount))
                .addComponent(labelInterval)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelBracket1)
                        .addComponent(textFieldFrom)
                        .addComponent(labelBracket2)
                        .addComponent(textFieldTo)
                        .addComponent(labelBracket3))
                .addComponent(comboBoxFunctions)
                .addComponent(checkBoxSave)
                .addComponent(buttonCreateFunction));

        getContentPane().setBackground(Settings.color);
        checkBoxSave.setBackground(Settings.color);
        comboBoxFunctions.setBackground(Settings.color.brighter());
    }

    private JComboBox<String> showComboBox() {
        functionMap.put("Квадратичная функция", new SqrFunction());
        functionMap.put("Константная функция", new ConstantFunction(15));
        functionMap.put("Логарифмическая функция", new LnFunction());
        functionMap.put("Тождественная функция", new IdentityFunction());
        functionMap.put("Функция кубического корня", new ThreeRootFunction());

        DefaultComboBoxModel<String> functions = new DefaultComboBoxModel<>();

        functions.addElement("Квадратичная функция");
        functions.addElement("Константная функция");
        functions.addElement("Логарифмическая функция");
        functions.addElement("Тождественная функция");
        functions.addElement("Функция кубического корня");

        return new JComboBox<>(functions);
    }
}
