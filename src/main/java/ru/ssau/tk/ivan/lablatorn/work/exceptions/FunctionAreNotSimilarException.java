package ru.ssau.tk.ivan.lablatorn.work.exceptions;

public class FunctionAreNotSimilarException extends RuntimeException{

    private static final long serialVersionUID = 7511967091764794816L;
    public FunctionAreNotSimilarException(){

    }
    public FunctionAreNotSimilarException(String message){
        super(message);
    }
}
