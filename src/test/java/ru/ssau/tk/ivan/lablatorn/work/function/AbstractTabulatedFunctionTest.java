package ru.ssau.tk.ivan.lablatorn.work.function;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.DifferentLengthOfArraysException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class AbstractTabulatedFunctionTest {
    private double[] getSortedArray() {
        return new double[]{3, 4, 5};
    }

    private double[] getUnsortedArray() {
        return new double[]{8, 1, 0.5, 4};
    }

    @Test
    public void testCheckLengthIsTheSame() {
        final double[] firstArray = getSortedArray();
        final double[] secondArray = getSortedArray();
        final double[] thirdArray = getUnsortedArray();
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(firstArray, thirdArray));
        AbstractTabulatedFunction.checkLengthIsTheSame(firstArray, secondArray);
        AbstractTabulatedFunction.checkLengthIsTheSame(new double[0], new double[0]);
    }

    @Test
    public void testCheckSorted() {
        final double[] sortedArray = getSortedArray();
        final double[] unsortedArray = getUnsortedArray();
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(unsortedArray));
        AbstractTabulatedFunction.checkSorted(sortedArray);
    }

    @Test
    public void testTestToString() {
        double[] x = {1, 2, 3, 4};
        double[] y = {2, 3, 4, 5};
        assertEquals(new ArrayTabulatedFunction(x, y).toString(), "ArrayTabulatedFunction size = 4\n[1.0; 2.0]\n[2.0; 3.0]\n[3.0; 4.0]\n[4.0; 5.0]");
        assertEquals(new LinkedListTabulatedFunction(x, y).toString(), "LinkedListTabulatedFunction size = 4\n[1.0; 2.0]\n[2.0; 3.0]\n[3.0; 4.0]\n[4.0; 5.0]");
    }
}