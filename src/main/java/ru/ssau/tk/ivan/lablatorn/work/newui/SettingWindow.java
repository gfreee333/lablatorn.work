package ru.ssau.tk.ivan.lablatorn.work.newui;

import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SettingWindow extends JDialog {
    private final JLabel label = new JLabel("Выберите тип фабрики функции:");
    private final JButton button = new JButton("OK");
    private final Map<String, TabulatedFunctionFactory> nameFunction = new HashMap<>();
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    TabulatedFunctionFactory factory;

    public SettingWindow(TabulatedFunctionFactory factory) {
        setTitle("Настройки");

        this.factory = factory;

        setSize(new Dimension(300, 110));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(232, 56, 0));
        getContentPane().add(button);

        label.setForeground(new Color(45,57,22));

        button.setBackground(new Color(45,57,22));
        button.setForeground(new Color(232, 56, 0));

        functionComboBox.setForeground(new Color(232, 56, 0));
        functionComboBox.setBackground(new Color(45,57,22));

        fillMap();
        compose();
        addButtonListeners();

        setModal(true);
        setLocationRelativeTo(null);
    }

    private void fillMap() {
        nameFunction.put("Массив", new ArrayTabulatedFunctionFactory());
        nameFunction.put("Связный список", new LinkedListTabulatedFunctionFactory());
        String[] functions = new String[2];
        int i = 0;
        for (String string : nameFunction.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(functionComboBox)
                        .addComponent(button))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(functionComboBox)
                        .addComponent(button)
                ));
    }

    private void addButtonListeners() {
        button.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                this.factory = nameFunction.get(func);
                this.dispose();
            } catch (Exception e) {
                ExceptionWindow errorWindow = new ExceptionWindow(this, e);
                errorWindow.showExceptionWindow(this, e);
            }
        });
    }

    public static void main(TabulatedFunctionFactory factory) throws IOException {
        SettingWindow frame = new SettingWindow(factory);
        frame.setVisible(true);
    }
}
