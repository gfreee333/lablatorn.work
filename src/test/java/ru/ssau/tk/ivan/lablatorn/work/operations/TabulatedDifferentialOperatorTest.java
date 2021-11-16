package ru.ssau.tk.ivan.lablatorn.work.operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.ArrayTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TabulatedDifferentialOperatorTest {

    @Test
    public void testDerive() {
        TabulatedFunction testList = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        testList = differentialListOperator.derive(testList);
        assertTrue(testList instanceof LinkedListTabulatedFunction);
        assertEquals(testList.getY(0), 3, 0.0001);
        assertEquals(testList.getY(1), 5, 0.0001);
        assertEquals(testList.getY(3), 7, 0.0001);
        TabulatedFunction testArray = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        testArray = differentialArrayOperator.derive(testArray);
        assertTrue(testArray instanceof ArrayTabulatedFunction);
        assertEquals(testArray.getX(3), 4, 0.0001);
        assertEquals(testArray.getY(2), 1, 0.0001);
        assertEquals(testArray.getY(3), 1, 0.0001);
    }

    @Test
    public void testDeriveSynchronously() {
        double[] xValues = new double[]{0, 1, 2, 3, 4, 5};
        double[] yValues = new double[]{0, 3, 6, 9, 12, 15};
        final Object mutex = new Object();
        TabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction arrayTabulatedFunction = arrayTabulatedFunctionFactory.create(xValues, yValues);
        TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(arrayTabulatedFunctionFactory);
        TabulatedFunction arrayTabulatedAfterDerive = tabulatedDifferentialOperator.deriveSynchronously(arrayTabulatedFunction);
        TabulatedFunction arrayTabulatedSynchronizedAfterDerive = tabulatedDifferentialOperator.deriveSynchronously(new SynchronizedTabulatedFunction(arrayTabulatedFunction, mutex));
        Assert .assertTrue(arrayTabulatedSynchronizedAfterDerive instanceof ArrayTabulatedFunction);
        Assert.assertTrue(arrayTabulatedAfterDerive instanceof ArrayTabulatedFunction);
        for (int element = 0; element < arrayTabulatedFunction.getCount(); element++) {
            Assert.assertEquals(arrayTabulatedFunction.getX(element), (double) element);
            Assert.assertEquals(arrayTabulatedFunction.getY(element), (double) 3 * element);
            Assert.assertEquals(arrayTabulatedAfterDerive.getX(element), (double) element);
            Assert.assertEquals(arrayTabulatedAfterDerive.getY(element), 3.0);
            Assert.assertEquals(arrayTabulatedSynchronizedAfterDerive.getX(element), (double) element);
            Assert.assertEquals(arrayTabulatedSynchronizedAfterDerive.getY(element), 3.0);
        }

        TabulatedFunctionFactory linkedTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction linkedTabulatedFunction = linkedTabulatedFunctionFactory.create(xValues, yValues);
        TabulatedDifferentialOperator tabulatedDifferentialOperatorTwo = new TabulatedDifferentialOperator(linkedTabulatedFunctionFactory);
        TabulatedFunction linkedTabulatedAfterDerive = tabulatedDifferentialOperatorTwo.deriveSynchronously(linkedTabulatedFunction);
        TabulatedFunction linkedTabulatedSynchronizedAfterDerive = tabulatedDifferentialOperator.deriveSynchronously(new SynchronizedTabulatedFunction(linkedTabulatedFunction, mutex));
        Assert.assertTrue(linkedTabulatedSynchronizedAfterDerive instanceof LinkedListTabulatedFunction);
        Assert.assertTrue(linkedTabulatedAfterDerive instanceof LinkedListTabulatedFunction);
        for (int element = 0; element < arrayTabulatedFunction.getCount(); element++) {
            Assert.assertEquals(linkedTabulatedFunction.getX(element), (double) element);
            Assert.assertEquals(linkedTabulatedFunction.getY(element), (double) 3 * element);
            Assert.assertEquals(linkedTabulatedAfterDerive.getX(element), (double) element);
            Assert.assertEquals(linkedTabulatedAfterDerive.getY(element), 3.0);
            Assert.assertEquals(linkedTabulatedSynchronizedAfterDerive.getX(element), (double) element);
            Assert.assertEquals(linkedTabulatedSynchronizedAfterDerive.getY(element), 3.0);
        }
    }
}