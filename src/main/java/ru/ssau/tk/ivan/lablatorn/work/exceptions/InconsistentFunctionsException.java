package ru.ssau.tk.ivan.lablatorn.work.exceptions;

public class InconsistentFunctionsException extends RuntimeException {
    private static final long serialVersionUID = 559032299456330508L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}