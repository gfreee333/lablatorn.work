package ru.ssau.tk.ivan.lablatorn.work.io;

import ru.ssau.tk.ivan.lablatorn.work.function.ArrayTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File fileArray = new File("output/serialized array functions.bin");
        double[] xValues = {-3, -2, -1, 0, 1, 2, 3};
        double[] yValues = {-9, -4, -1, 0, 1, 4, 9};
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());

        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction arrayFunction1 = differentialOperator.derive(arrayFunction);
        TabulatedFunction arrayFunction2 = differentialOperator.derive(arrayFunction1);

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileArray));
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileArray))) {
            FunctionsIO.serialize(out, arrayFunction);
            FunctionsIO.serialize(out, arrayFunction1);
            FunctionsIO.serialize(out, arrayFunction2);

            out.close();

            TabulatedFunction resultArray = FunctionsIO.deserialize(in);
            TabulatedFunction resultArray1 = FunctionsIO.deserialize(in);
            TabulatedFunction resultArray2 = FunctionsIO.deserialize(in);

            in.close();

            System.out.println(resultArray.toString());
            System.out.println(resultArray1.toString());
            System.out.println(resultArray2.toString());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }



    }
}
