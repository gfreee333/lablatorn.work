package ru.ssau.tk.ivan.lablatorn.work.ui;

import javax.swing.*;
import java.awt.*;

public class Operations extends JDialog {
    Operations() {
        super();
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setModal(true);
        setBounds(100, 100, 800, 700);
        compose();
        addButtonListeners();
        setVisible(true);
    }
    private void addButtonListeners() {
    }
    private void compose() {
    }
}
