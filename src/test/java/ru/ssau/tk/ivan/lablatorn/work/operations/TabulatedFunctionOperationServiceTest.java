package ru.ssau.tk.ivan.lablatorn.work.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.ArrayTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.LinkedListTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.TabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.Point;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    private final double[] valuesX = new double[]{-2, -4, -5, 6, 8, 10, 27};
    private final double[] valuesY = new double[]{3, -22, 1, 0, 21, 25, 30};
    private final double[] valuesYForList = new double[]{1, 2, 3, 4, 5, 6, 7};
    double DELTA = 0.001;

    ArrayTabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    LinkedListTabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesYForList);
    }

    @Test
    public void testAsPoints() {
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        Point[] Points = TabulatedFunctionOperationService.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), DELTA);
        }
        assertEquals(testArrayFunction.getCount(), i);
        LinkedListTabulatedFunction testListFunction = getTestList();
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
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        LinkedListTabulatedFunction testListFunction = getTestList();

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
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        LinkedListTabulatedFunction testListFunction = getTestList();

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
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        LinkedListTabulatedFunction testListFunction = getTestList();

        TabulatedFunction testMultiplicationOfArrays = new TabulatedFunctionOperationService().multiplication(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testMultiplicationOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesY[i++]);
        }
        TabulatedFunction testMultiplicationOfList = new TabulatedFunctionOperationService().multiplication(testListFunction, testListFunction);
        i = 0;
        for (Point point : testMultiplicationOfList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesY[i++]);
        }
        TabulatedFunction testMultiplicationArrayAndList = new TabulatedFunctionOperationService().multiplication(testArrayFunction, testListFunction);
        {
            i = 0;
            for (Point point : testMultiplicationArrayAndList) {
                assertEquals(point.x, valuesX[i]);
                assertEquals(point.y, valuesY[i] * valuesY[i++]);
            }
        }
    }

    @Test
    public void testDivision() {
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        LinkedListTabulatedFunction testListFunction = getTestList();

        TabulatedFunction testDivisionOfArrays = new TabulatedFunctionOperationService().division(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testDivisionOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesY[i++]);
        }
        TabulatedFunction testDivisionOfList = new TabulatedFunctionOperationService().division(testListFunction, testListFunction);
        i = 0;
        for (Point point : testDivisionOfList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesY[i++]);
        }
        TabulatedFunction testDivisionArrayAndList = new TabulatedFunctionOperationService().division(testArrayFunction, testListFunction);
        {
            i = 0;
            for (Point point : testDivisionArrayAndList) {
                assertEquals(point.x, valuesX[i]);
                assertEquals(point.y, valuesY[i] / valuesY[i++]);
            }
        }
    }
}