package ru.ssau.tk.ivan.lablatorn.work.exceptions;

public class WrongNumberOfElementsException extends RuntimeException {

    private static final long serialVersionUID = -1401008486061160331L;

    public WrongNumberOfElementsException() {
        super("Wrong number");
    }
}