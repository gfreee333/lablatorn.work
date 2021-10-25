package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.ivan.lablatorn.work.function.ArrayTabulatedFunction;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    private final double[] xValues1 = new double[]{2.1, 3.4, 5.2, 6.0};
    private final double[] xValues2 = new double[]{2.1, 3.4, 5.2, 6.0, 7.8};
    private final double[] xValues3 = new double[]{10.0, 2.1, 3.4, 5.2, 6.0, 7.8};

    private final double[] yValues1 = new double[]{2.4, 1.2, 3.0, 5.1};
    private final double[] yValues2 = new double[]{2.4, 1.2, 3.0, 5.1, 10.11};

    private ArrayTabulatedFunction createFromArray() {
        return new ArrayTabulatedFunction(xValues1, yValues1);
    }

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> createFromArray().checkLengthIsTheSame(xValues1, yValues2));
        assertThrows(DifferentLengthOfArraysException.class, () -> createFromArray().checkLengthIsTheSame(xValues2, yValues1));
    }

    @Test
    public void testCheckSorted() {
        ArrayTabulatedFunction function = createFromArray();
        assertThrows(ArrayIsNotSortedException.class, () -> function.checkSorted(xValues3));
        assertThrows(ArrayIsNotSortedException.class, () -> function.checkSorted(yValues1));
        assertThrows(ArrayIsNotSortedException.class, () -> function.checkSorted(yValues2));
    }

}