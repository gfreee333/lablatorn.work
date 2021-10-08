package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {

    static final double DELTA = 0.0001;
    static final double begin = 2.9;
    static final double end = 93.9;
    private static double[] xValues = new double[]{3.4, 5.2, 6.0, 2.1};
    private static double[] yValues = new double[]{-2.4, 1.2, 3.0, 5.1};
    static SqrFunction sqrObject = new SqrFunction();
    static ArrayTabulatedFunction arrayTabulatedObject = new ArrayTabulatedFunction(xValues, yValues);
    static ArrayTabulatedFunction arrayTabulatedObjectTwo = new ArrayTabulatedFunction(sqrObject, begin, end, 100);

    @Test
    public static void testArrayTabulatedFunctionWithTwoParameters() {
        assertEquals(arrayTabulatedObject.getCount(), 4);

    }

    @Test
    public static void testGetCount() {
        assertEquals(arrayTabulatedObjectTwo.getCount(), 100);
    }

    @Test
    public static void testGetX() {
        for (int element = 0; element < 99; element++) {
            assertEquals(arrayTabulatedObjectTwo.getX(element), element * (end - begin) / 100.0 + begin, DELTA);
        }
    }

    @Test
    public static void testGetY() {
        for (int element = 0; element < 99; element++) {
            assertEquals(arrayTabulatedObjectTwo.getY(element), sqrObject.apply(arrayTabulatedObjectTwo.getX(element)), DELTA);
        }
    }

    @Test
    public static void testLeftBound() {
        assertEquals(arrayTabulatedObjectTwo.leftBound(), begin, DELTA);
    }

    @Test
    public static void testRightBound() {
        assertEquals(arrayTabulatedObjectTwo.rightBound(), end, DELTA);
    }

    @Test
    public static void testIndexOfX() {
        assertEquals(arrayTabulatedObjectTwo.indexOfX(1.1), -1, DELTA);
        for (int element = 0; element < 99; element++) {
            assertEquals(arrayTabulatedObjectTwo.indexOfX(begin + element * 0.91), element);
        }
    }

    @Test
    public static void testIndexOfY() {
        assertEquals(arrayTabulatedObjectTwo.indexOfY(0.432), -1, DELTA);
        for (int element = 0; element < 99; element++) {
            assertEquals(arrayTabulatedObjectTwo.indexOfY(sqrObject.apply(arrayTabulatedObjectTwo.getX(element))), element);
        }
    }

    @Test
    public static void testFloorIndexOfX() {
        for (int element = 0; element < 99; element++) {
            assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(begin + element * 0.91), element);
        }
        assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(1.1), 0);
        assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(100.0), 100);
    }

    @Test
    public static void testExtrapolateLeft() {
        assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(1.1), -3.6679, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(0.9), -5.0099, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }


}

