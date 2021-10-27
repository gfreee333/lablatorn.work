package ru.ssau.tk.ivan.lablatorn.work.io;

import ru.ssau.tk.ivan.lablatorn.work.function.Point;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.PrintWriter;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for(Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        printWriter.flush();
    }
}
