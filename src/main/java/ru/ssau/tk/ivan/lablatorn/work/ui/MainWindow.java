package ru.ssau.tk.ivan.lablatorn.work.ui;

import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JButton buttonCreateArray = new JButton("Массив");
    private final JButton buttonCreateFunction = new JButton("Функция");
    private final JButton buttonSettings = new JButton("Настройки");
    private final JButton buttonDifferentiation = new JButton("Дифференцирование");

    protected static TabulatedFunctionFactory functionFactory = new ArrayTabulatedFunctionFactory();
    public MainWindow() {
        super("Главное окно");
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);
        getContentPane().add(buttonCreateArray);
        getContentPane().add(buttonCreateFunction);
        getContentPane().add(buttonSettings);
        getContentPane().add(buttonDifferentiation);

        compose();
        addButtonListeners();
        getContentPane().setBackground(new Color(0,204,204));
        setVisible(true);
    }
    private void addButtonListeners() {
        buttonCreateFunction.addActionListener(e -> {
            CreatingTFThroughFunction.checkBoxSave.setVisible(true);
            new CreatingTFThroughFunction(function -> {
            });
        });
        buttonCreateArray.addActionListener(e -> {
            CreatingTFThroughArray.checkBoxSave.setVisible(true);
            new CreatingTFThroughArray(function -> {
            });
        });
        buttonSettings.addActionListener(e -> {
            new Settings(tabulatedFunctionFactory -> {
                functionFactory = tabulatedFunctionFactory;
            }
            );
        });

        buttonDifferentiation.addActionListener(e -> {
            new Differentiation();
        });
    }
    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(buttonCreateArray)
                .addComponent(buttonCreateFunction)

                .addComponent(buttonSettings)
                .addComponent(buttonDifferentiation));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(buttonCreateArray)
                .addComponent(buttonCreateFunction)

                .addComponent(buttonSettings)
                .addComponent(buttonDifferentiation));

        getContentPane().setBackground(Settings.color);
    }
    public static void main(String[] args) {
        new MainWindow();
    }
}