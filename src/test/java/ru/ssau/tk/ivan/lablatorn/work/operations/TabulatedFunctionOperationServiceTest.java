package ru.ssau.tk.ivan.lablatorn.work.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.function.ArrayTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.Point;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest { // Доделать эти тесты относительно двух фабрик. вывести их как поля в начале.
    private final double[] valuesX = new double[]{-5, -4, -3, 6, 8, 10, 27};
    private final double[] valuesY = new double[]{-33, -22, -1, 0, 21, 25, 30};
    private final double[] valuesYForList = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final double DELTA = 0.001;

    private ArrayTabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    private LinkedListTabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesYForList);
    }

    @Test
    public void testAsPoints() {
        TabulatedFunction testArrayFunction = getTestArray();
        Point[] Points = TabulatedFunctionOperationService.asPoints(testArrayFunction);
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
        TabulatedFunction testArrayFunction = getTestArray();
        TabulatedFunction testListFunction = getTestList();

        TabulatedFunction testSumOfArrays = new TabulatedFunctionOperationService().sum(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }

        TabulatedFunction testSumOfLists = new TabulatedFunctionOperationService().sum(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] + valuesYForList[i++]);
        }

        TabulatedFunction testSumOfArrayAndList = new TabulatedFunctionOperationService().sum(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYForList[i++]);
        }

    }

    @Test
    public void testSubtract() {
        TabulatedFunction testArrayFunction = getTestArray();
        TabulatedFunction testListFunction = getTestList();

        TabulatedFunction testSubtractOfArrays = new TabulatedFunctionOperationService().subtract(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }

        TabulatedFunction testSubtractOfLists = new TabulatedFunctionOperationService().subtract(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] - valuesYForList[i++]);
        }

        TabulatedFunction testSubtractOfArrayAndList = new TabulatedFunctionOperationService().subtract(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYForList[i++]);
        }
    }

    @Test
    public void testMultiplication() {
        TabulatedFunction testArrayFunction = getTestArray();
        TabulatedFunction testListFunction = getTestList();

        TabulatedFunction testMultiplicationOfArrays = new TabulatedFunctionOperationService().multiplication(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testMultiplicationOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesY[i++]);
        }

        TabulatedFunction testMultiplicationOfLists = new TabulatedFunctionOperationService().multiplication(testListFunction, testListFunction);
        i = 0;
        for (Point point : testMultiplicationOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] * valuesYForList[i++]);
        }

        TabulatedFunction testMultiplicationOfArrayAndList = new TabulatedFunctionOperationService().multiplication(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testMultiplicationOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesYForList[i++]);
        }
    }

    @Test
    public void testDivision() {
        TabulatedFunction testArrayFunction = getTestArray();
        TabulatedFunction testListFunction = getTestList();

        TabulatedFunction testDivisionOfArrays = new TabulatedFunctionOperationService().division(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testDivisionOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesY[i++]);
        }

        TabulatedFunction testDivisionOfLists = new TabulatedFunctionOperationService().division(testListFunction, testListFunction);
        i = 0;
        for (Point point : testDivisionOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] / valuesYForList[i++]);
        }

        TabulatedFunction testDivisionOfArrayAndList = new TabulatedFunctionOperationService().division(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testDivisionOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesYForList[i++]);
        }
    }
}