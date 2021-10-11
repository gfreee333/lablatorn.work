package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {

    static final double DELTA = 0.0001;
    static final double begin = 1;
    static final double end = 101;
    private static double[] xValues = new double[]{3.4, 5.2, 6.0, 2.1};
    private static double[] yValues = new double[]{-2.4, 1.2, 3.0, 5.1};
    private static double[] x1Values = new double[]{34, 5.2, 60, 2};
    private static double[] y1Values = new double[]{-2.4, 1.2, 30, 5.1};
    static SqrFunction sqrObject = new SqrFunction();
    static ArrayTabulatedFunction arrayTabulatedObject = new ArrayTabulatedFunction(xValues, yValues);
    static ArrayTabulatedFunction array1TabulatedObject = new ArrayTabulatedFunction(x1Values, y1Values);
    static ArrayTabulatedFunction arrayTabulatedObjectTwo = new ArrayTabulatedFunction(sqrObject, begin, end, 100);

    /*@Test
    public static void justTest() {
        assertEquals(arrayTabulatedObjectTwo.getX(5), 23);
    }*/

    @Test
    public static void testSetY(){
        array1TabulatedObject.setY(2,1000);
        assertEquals(array1TabulatedObject.getY(2),1000,0.001);
        array1TabulatedObject.setY(2,2222);
        assertEquals(array1TabulatedObject.getY(2),2222,0.001);
    }
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
        assertEquals(arrayTabulatedObjectTwo.indexOfX(0.1), -1, DELTA);
        assertEquals(arrayTabulatedObjectTwo.indexOfX(5), 4, DELTA);
        for (int element = 0; element < 99; ++element) {
            assertEquals(arrayTabulatedObjectTwo.indexOfX(1 + element * (end - begin) / 100.0), element);
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
            assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(begin + element * (end - begin) / 100.0), element, DELTA);
        }
        assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(0.1), 0);
        assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(102.0), 100);
    }

    @Test
    public static void testExtrapolateLeft() {
        assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(0.1), -1.7000, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(0.9), 0.70000, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }

    @Test
    public static void testExtrapolateRight() {
        assertEquals(arrayTabulatedObjectTwo.extrapolateRight(100), 10001.0, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateRight(120), 14001.0, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
    }
}

