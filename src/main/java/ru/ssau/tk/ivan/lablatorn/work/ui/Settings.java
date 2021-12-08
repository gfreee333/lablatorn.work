package ru.ssau.tk.ivan.lablatorn.work.ui;

import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.function.Consumer;

public class Settings extends JDialog {
    private TabulatedFunctionFactory tabulatedFunctionFactory;
    private final JLabel labelFunctionFactory = new JLabel("Используемая фабрика: ");
    private final JLabel labelColor = new JLabel("Цвет фона");
    private final JColorChooser colorChooser = new JColorChooser();

    private final JRadioButton factoryArray = new JRadioButton("ArrayTabulatedFunctionFactory (по умолчанию)");
    private final JRadioButton factoryList = new JRadioButton("LinkedListTabulatedFunctionFactory");
    private final JButton buttonColor = new JButton("Изменить цвет фона");
    protected static Color color = new Color(0, 204, 204);

    protected Settings(Consumer<? super TabulatedFunctionFactory> callback) {
        super();
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setBounds(100, 100, 800, 700);

        showButtonGroup();
        compose();
        addButtonListeners();
        setVisible(true);

        callback.accept(Objects.requireNonNullElseGet(tabulatedFunctionFactory, ArrayTabulatedFunctionFactory::new));

        dispose();
    }

    private void addButtonListeners() {
        buttonColor.addActionListener(e -> {
            color = colorChooser.getColor();
            if (color != null) {
                getContentPane().setBackground(color);
                factoryArray.setBackground(color);
                factoryList.setBackground(color);
                colorChooser.setBackground(color);
            }
        });

        factoryArray.addItemListener(e -> tabulatedFunctionFactory = new ArrayTabulatedFunctionFactory());

        factoryList.addItemListener(e -> tabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory());
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(labelFunctionFactory)
                .addComponent(factoryArray)
                .addComponent(factoryList)
                .addComponent(labelColor)
                .addComponent(colorChooser)
                .addComponent(buttonColor)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(labelFunctionFactory)
                .addComponent(factoryArray)
                .addComponent(factoryList)
                .addComponent(labelColor)
                .addComponent(colorChooser)
                .addComponent(buttonColor)
        );
        getContentPane().setBackground(color);
        factoryArray.setBackground(color);
        factoryList.setBackground(color);
        colorChooser.setBackground(color);
    }

    private void showButtonGroup() {
        //неожиданная приколюшка: можно переключать с помощью alt+C (B)
        factoryArray.setMnemonic(KeyEvent.VK_C);
        factoryList.setMnemonic(KeyEvent.VK_B);

        ButtonGroup group = new ButtonGroup();
        group.add(factoryArray);
        group.add(factoryList);
    }
}
