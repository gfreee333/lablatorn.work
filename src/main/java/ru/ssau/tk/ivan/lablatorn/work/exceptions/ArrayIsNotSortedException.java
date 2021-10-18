package ru.ssau.tk.ivan.lablatorn.work.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    public ArrayIsNotSortedException(){
    }
    public ArrayIsNotSortedException(String message){
    super(message);
    }
}
