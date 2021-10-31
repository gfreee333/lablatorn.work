package ru.ssau.tk.ivan.lablatorn.work.io;

import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction functionArray = FunctionsIO.readTabulatedFunction(in, factory);
            System.out.println(functionArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции");
            LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction LinkedList = FunctionsIO.readTabulatedFunction(in, factory);
            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            TabulatedFunction diffFunctionList = differentialOperator.derive(LinkedList);
            System.out.println(diffFunctionList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
