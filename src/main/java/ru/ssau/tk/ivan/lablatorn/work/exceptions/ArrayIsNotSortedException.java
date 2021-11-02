package ru.ssau.tk.ivan.lablatorn.work.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    private static final long serialVersionUID = 4033611472706349162L;

    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
