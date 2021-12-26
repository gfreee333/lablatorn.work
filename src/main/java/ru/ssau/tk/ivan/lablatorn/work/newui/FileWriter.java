package ru.ssau.tk.ivan.lablatorn.work.newui;

import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileWriter extends JDialog {
    public FileWriter(TabulatedFunction func) {
        setModal(true);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(
                new FileNameExtensionFilter("Bin files", "bin"));

        chooser.setAcceptAllFileFilterUsed(false);
        int rVal = chooser.showSaveDialog(FileWriter.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file != null) {
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                    FunctionsIO.writeTabulatedFunction(outputStream, func);
                } catch (Exception e) {
                    new ExceptionWindow(this, e);
                }
            }
        }
    }

    public static void main(TabulatedFunction func) {
        new FileWriter(func);
    }
}
