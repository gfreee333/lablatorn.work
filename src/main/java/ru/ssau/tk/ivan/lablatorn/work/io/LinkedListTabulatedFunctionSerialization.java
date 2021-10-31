package ru.ssau.tk.ivan.lablatorn.work.io;

import ru.ssau.tk.ivan.lablatorn.work.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.SqrFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File fileList = new File("output/serialized linked list functions.bin");
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new SqrFunction(), -4, 4, 9);
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction firstDerivative = differentialOperator.derive(function);
        TabulatedFunction secondDerivative = differentialOperator.derive(firstDerivative);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileList))) {
            FunctionsIO.serialize(out, function);
            FunctionsIO.serialize(out, firstDerivative);
            FunctionsIO.serialize(out, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileList))) {
            TabulatedFunction deserializedList = FunctionsIO.deserialize(in);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(in);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(in);
            System.out.println(deserializedList.toString());
            System.out.println(deserializedFirstDerivative.toString());
            System.out.println(deserializedSecondDerivative.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
