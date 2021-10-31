package ru.ssau.tk.ivan.lablatorn.work.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.ivan.lablatorn.work.function.ArrayTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.Point;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest { // Доделать эти тесты относительно двух фабрик. вывести их как поля в начале.
    private final double[] valuesX = new double[]{-5, -4, -3, 6, 8, 10, 27};
    private final double[] valuesY = new double[]{-33, -22, -1, 0, 21, 25, 30};
    private final double[] valuesYForList = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final TabulatedFunctionOperationService serviceArray = new TabulatedFunctionOperationService();
    private final TabulatedFunctionOperationService serviceList = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
    private final double DELTA = 0.0001;

    private TabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    private LinkedListTabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesYForList);
    }

    @Test
    public void testGetFactory() {
        assertTrue(serviceArray.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        serviceArray.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(serviceArray.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testAsPoints() {
        TabulatedFunction testArrayFunction = getTestArray();
        Point[] Points = serviceArray.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), DELTA);
        }
        assertEquals(testArrayFunction.getCount(), i);
        TabulatedFunction testListFunction = getTestList();
        Points = TabulatedFunctionOperationService.asPoints(testListFunction);
        i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testListFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testListFunction.getY(i++), DELTA);
        }
        assertEquals(testListFunction.getCount(), i);
    }

    @Test
    public void testSum() {

        final double[] xValuesErr1 = new double[]{-3, -2, -1, 0, 1, 2};
        final double[] yValuesErr1 = new double[]{-9, -4, -1, 0, 1, 4};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(xValuesErr1, yValuesErr1);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.sum(getTestList(), errorTest1));

        final double[] xValuesErr2 = new double[]{-4, -2, -1, 0, 1, 2, 3};
        TabulatedFunction errorTest2 = new ArrayTabulatedFunction(xValuesErr2, valuesY);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.sum(getTestList(), errorTest2));


        TabulatedFunction testArrayFunction = getTestArray();
        TabulatedFunction testListFunction = getTestList();
        TabulatedFunction testSumOfArrays = serviceArray.sum(testArrayFunction, testArrayFunction);
        assertTrue(testSumOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }

        TabulatedFunction testSumOfLists = serviceList.sum(testListFunction, testListFunction);
        assertTrue(testSumOfLists instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] + valuesYForList[i++]);
        }

        TabulatedFunction testSumOfArrayAndList = serviceArray.sum(testArrayFunction, testListFunction);
        assertTrue(testSumOfArrayAndList instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYForList[i++]);
        }

    }

    @Test
    public void testSubtract() {
        final double[] xValuesErr1 = new double[]{-25, -5, -1, 0, 1, 2};
        final double[] yValuesErr1 = new double[]{-100, -55, -14, 0, 12, 44};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(xValuesErr1, yValuesErr1);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.subtract(getTestList(), errorTest1));

        final double[] xValuesErr2 = new double[]{-4, -2, -1, 0, 1, 2, 3};
        TabulatedFunction errorTest2 = new ArrayTabulatedFunction(xValuesErr2, valuesY);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.subtract(getTestList(), errorTest2));

        TabulatedFunction testArrayFunction = getTestArray();
        TabulatedFunction testListFunction = getTestList();

        TabulatedFunction testSubtractOfArrays = serviceArray.subtract(testArrayFunction, testArrayFunction);
        assertTrue(testSubtractOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }

        TabulatedFunction testSubtractOfLists = serviceList.subtract(testListFunction, testListFunction);
        assertTrue(testSubtractOfLists instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] - valuesYForList[i++]);
        }

        TabulatedFunction testSubtractOfArrayAndList = serviceArray.subtract(testArrayFunction, testListFunction);
        assertTrue(testSubtractOfArrayAndList instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYForList[i++]);
        }
    }

    @Test
    public void testMultiplication() {
        final double[] xValuesErr1 = new double[]{-1, -0.5, -0.45, 0, 1, 2};
        final double[] yValuesErr1 = new double[]{-3, -2, -1, 0, 1, 4};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(xValuesErr1, yValuesErr1);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.multiplication(getTestList(), errorTest1));

        final double[] xValuesErr2 = new double[]{-10, -9, -5, 0, 1, 2, 3};
        TabulatedFunction errorTest2 = new ArrayTabulatedFunction(xValuesErr2, valuesY);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.multiplication(getTestList(), errorTest2));
        TabulatedFunction testArrayFunction = getTestArray();
        TabulatedFunction testListFunction = getTestList();

        TabulatedFunction testMultiplicationOfArrays = serviceArray.multiplication(testArrayFunction, testArrayFunction);
        assertTrue(testMultiplicationOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testMultiplicationOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesY[i++]);
        }

        TabulatedFunction testMultiplicationOfLists = serviceList.multiplication(testListFunction, testListFunction);
        assertTrue(testMultiplicationOfLists instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testMultiplicationOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] * valuesYForList[i++]);
        }

        TabulatedFunction testMultiplicationOfArrayAndList = serviceArray.multiplication(testArrayFunction, testListFunction);
        assertTrue(testMultiplicationOfArrayAndList instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : testMultiplicationOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesYForList[i++]);
        }
    }

    @Test
    public void testDivision() {
        final double[] xValuesErr1 = new double[]{-1, -0.5, -0.45, 0, 1, 2};
        final double[] yValuesErr1 = new double[]{-3, -2, -1, 0, 1, 4};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(xValuesErr1, yValuesErr1);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.division(getTestList(), errorTest1));

        final double[] xValuesErr2 = new double[]{-10, -9, -5, 0, 1, 2, 3};
        TabulatedFunction errorTest2 = new ArrayTabulatedFunction(xValuesErr2, valuesY);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.division(getTestList(), errorTest2));
        TabulatedFunction testArrayFunction = getTestArray();
        TabulatedFunction testListFunction = getTestList();

        TabulatedFunction testDivisionOfArrays = serviceArray.division(testArrayFunction, testArrayFunction);
        assertTrue(testDivisionOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testDivisionOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesY[i++]);
        }

        TabulatedFunction testDivisionOfLists = serviceList.division(testListFunction, testListFunction);
        assertTrue(testDivisionOfLists instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testDivisionOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] / valuesYForList[i++]);
        }

        TabulatedFunction testDivisionOfArrayAndList = serviceArray.division(testArrayFunction, testListFunction);
        assertTrue(testDivisionOfArrayAndList instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : testDivisionOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesYForList[i++]);
        }
    }
}