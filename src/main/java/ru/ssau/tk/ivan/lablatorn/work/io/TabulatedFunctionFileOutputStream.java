package ru.ssau.tk.ivan.lablatorn.work.io;

import ru.ssau.tk.ivan.lablatorn.work.function.ArrayTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        File fileArray = new File("output/array function.bin");
        File fileList = new File("output/linked list function.bin");
        double[] xValue = new double[]{1, 2, 3, 4, 5};
        double[] yValue = new double[]{6, 7, 8, 9, 10};
        TabulatedFunction functionList = new LinkedListTabulatedFunction(xValue, yValue);
        TabulatedFunction functionArray = new ArrayTabulatedFunction(xValue, yValue);
        try (BufferedOutputStream outArray = new BufferedOutputStream(
                new FileOutputStream(fileArray));
             BufferedOutputStream outList = new BufferedOutputStream(
                     new FileOutputStream(fileList))
        ) {
            FunctionsIO.writeTabulatedFunction(outArray, functionArray);
            FunctionsIO.writeTabulatedFunction(outList, functionList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
