package ru.ssau.tk.ivan.lablatorn.work.exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {
    private static final long serialVersionUID = 4540223782096626345L;

    public DifferentLengthOfArraysException() {

    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }

}
