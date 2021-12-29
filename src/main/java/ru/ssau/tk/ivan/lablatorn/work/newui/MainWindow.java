package ru.ssau.tk.ivan.lablatorn.work.newui;

import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {

    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private final TableForMain tableModel = new TableForMain();
    private final JTable table = new JTable(tableModel);
    private final JLabel topLabel = new JLabel("Eternal");
    private final JLabel label = new JLabel();

    private final JButton createFunctionButton = new JButton("Создать табулированную функцию из массивов");
    private final JButton settingsButton = new JButton("Настройки");
    private final JButton createMathFunctionButton = new JButton("Создать табулированную функцию с помощью другой функции");

    private final JButton openButton = new JButton("Открыть функцию");
    private final JButton saveButton = new JButton("Сохранить функцию");
    private final JButton operationButton = new JButton("Операции");
    private final JButton differential = new JButton("Производная");

    private TabulatedFunctionFactory factory;

    public MainWindow() throws IOException {
        super("Основное окно");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 580);
        container.add(topLabel);
        container.setBackground(new Color(45,57,22));
        container.add(designButton(createFunctionButton));
        container.add(designButton(settingsButton));
        container.add(designButton(createMathFunctionButton));
        container.add(designButton(openButton));
        container.add(designButton(saveButton));
        container.add(designButton(operationButton));
        container.add(designButton(differential));

        ImageIcon icon = new ImageIcon(javax.imageio.ImageIO.read((new File("photo_2021-12-29_10-42-49.jpg"))));
        label.setIcon(icon);
        label.setPreferredSize(new Dimension(1, 1));

        designTable(table);
        designLabel(topLabel);

        compose();
        addButtonListeners();

        setLocationRelativeTo(null);
    }


    public Component designButton(JButton button) {
        button.setBackground(new Color(45,57,22));
        button.setForeground(new Color(232, 56, 0));
        button.setFocusPainted(false);
        return button;
    }

    public void designLabel(JLabel label) {
        label.setFont(new Font("TimesNewRoman", Font.ITALIC + Font.BOLD, 28));
        label.setForeground(new Color(232, 56, 0));
        label.setVerticalAlignment(JLabel.TOP);
    }

    public void designTable(JTable table) {
        table.setBackground(Color.WHITE);
        table.setGridColor(new Color(232, 56, 0));
    }

    private void wrapTable(int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i <= countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i);
            if (yValues.size() != 0) yValues.remove(countOld - i);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    private void addButtonListeners() {
        createFunctionButton.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                TabulatedTableWindow.main(factory, tableModel::setFunction);
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        settingsButton.addActionListener(event -> {
            try {
                SettingWindow.main(factory);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        createMathFunctionButton.addActionListener(event -> {
            try {
            int countOld = xValues.size();
            MathTableWindow.main(factory, tableModel::setFunction);
            int countNew = tableModel.getFunction().getCount();
            wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        openButton.addActionListener(event -> {
            try {
            int countOld = xValues.size();
            FileReader.main(tableModel::setFunction);
            int countNew = tableModel.getFunction().getCount();
            wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        saveButton.addActionListener(event -> {
            try {
                FileWriter.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        operationButton.addActionListener(event -> {
            try {
                OperationsWindow.main();
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        differential.addActionListener(event -> {
            try {
                DifferentialOperationWindow.main();
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(topLabel))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createFunctionButton)
                        .addComponent(createMathFunctionButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(operationButton)
                        .addComponent(differential)
                        .addComponent(settingsButton)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScrollPane))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(topLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(createFunctionButton)
                        .addComponent(createMathFunctionButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(operationButton)
                        .addComponent(differential)
                        .addComponent(settingsButton)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(tableScrollPane))
        );
    }

    public static void main(String[] args) throws IOException {
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}
