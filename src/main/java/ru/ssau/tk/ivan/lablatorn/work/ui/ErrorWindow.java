package ru.ssau.tk.ivan.lablatorn.work.ui;

import ru.ssau.tk.ivan.lablatorn.work.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.FunctionAreNotSimilarException;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.WrongNumberOfElementsException;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow {

    ErrorWindow(Component parent, Exception e) {
        getErrorWindow(parent, e);
    }

    public void getErrorWindow(Component parent, Exception e) {
        String message = MessageForException(e);
        JOptionPane.showMessageDialog(parent, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private String MessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат данных!";
        }
        if (e instanceof WrongNumberOfElementsException) {
            return "Вы ввели неверное значение!";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Элементы X должны быть упорядочены!";
        }
        if (e instanceof NullPointerException) {
            return "Что-то пошло не так...";
        }

        if (e instanceof FunctionAreNotSimilarException)
            return "Абсциссы функций не совпадают";
        return "Неверные данные!";
    }
}
