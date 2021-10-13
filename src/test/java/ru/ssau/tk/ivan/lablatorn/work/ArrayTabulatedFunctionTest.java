package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {

    static final double DELTA = 0.0001;
    static final double BEGIN = 1;
    static final double END = 101;
    private final double[] xValues = new double[]{3.4, 5.2, 6.0, 2.1};
    private final double[] yValues = new double[]{-2.4, 1.2, 3.0, 5.1};
    private final double[] xValuesOne = new double[]{34, 5.2, 60, 2};
    private final double[] yValuesOne = new double[]{-2.4, 1.2, 30, 5.1};
    private final SqrFunction sqrObject = new SqrFunction();
    private final ArrayTabulatedFunction arrayTabulatedObject = new ArrayTabulatedFunction(xValues, yValues);
    private final ArrayTabulatedFunction arrayTabulatedObjectOne = new ArrayTabulatedFunction(xValuesOne, yValuesOne);
    private final ArrayTabulatedFunction arrayTabulatedObjectTwo = new ArrayTabulatedFunction(sqrObject, BEGIN, END, 100);


    @Test
    public void testSetY() {
        arrayTabulatedObjectOne.setY(2, 1000);
        assertEquals(arrayTabulatedObjectOne.getY(2), 1000, 0.001);
        arrayTabulatedObjectOne.setY(2, 2222);
        assertEquals(arrayTabulatedObjectOne.getY(2), 2222, 0.001);
    }

    @Test
    public void testArrayTabulatedFunctionWithTwoParameters() {
        assertEquals(arrayTabulatedObject.getCount(), 4);

    }

    @Test
    public void testGetCount() { assertEquals(arrayTabulatedObjectTwo.getCount(), 100);}

    @Test
    public void testGetX() {
        for (int element = 0; element < 99; element++) {
            assertEquals(arrayTabulatedObjectTwo.getX(element), element * (END - BEGIN) / 100.0 + BEGIN, DELTA);
        }
    }

    @Test
    public void testGetY() {
        for (int element = 0; element < 99; element++) {
            assertEquals(arrayTabulatedObjectTwo.getY(element), sqrObject.apply(arrayTabulatedObjectTwo.getX(element)), DELTA);
        }
    }

    @Test
    public void testLeftBound() {
        assertEquals(arrayTabulatedObjectTwo.leftBound(), BEGIN, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(arrayTabulatedObjectTwo.rightBound(), END, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(arrayTabulatedObjectTwo.indexOfX(30.6), -1, DELTA);
        assertEquals(arrayTabulatedObjectTwo.indexOfX(5), 4, DELTA);
        for (int element = 0; element < 99; ++element) {
            assertEquals(arrayTabulatedObjectTwo.indexOfX(1 + element * (END - BEGIN) / 100.0), element);
        }
    }

    @Test
    public void testIndexOfY() {
        assertEquals(arrayTabulatedObjectTwo.indexOfY(0.432), -1, DELTA);
        for (int element = 0; element < 99; element++) {
            assertEquals(arrayTabulatedObjectTwo.indexOfY(sqrObject.apply(arrayTabulatedObjectTwo.getX(element))), element);
        }
    }

    @Test
    public void testFloorIndexOfX() {
        for (int element = 0; element < 99; element++) {
            assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(BEGIN + element * (END - BEGIN) / 100.0), element, DELTA);
        }
        assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(0.1), 0);
        assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(102.0), 100);
        assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(30.6), 29);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(0.1), -1.7000, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(0.9), 0.70000, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(arrayTabulatedObjectTwo.extrapolateRight(100), 10001.0, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateRight(120), 14001.0, DELTA);
        assertEquals(arrayTabulatedObjectTwo.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
    }

    @Test
    public void testApply() {
        assertEquals(arrayTabulatedObjectTwo.apply(0.6), -0.2000, DELTA);
        assertEquals(arrayTabulatedObjectTwo.apply(110), 12001.0, DELTA);
        assertEquals(arrayTabulatedObjectTwo.apply(50), 2500.0, DELTA);
        assertEquals(arrayTabulatedObjectTwo.apply(30.6), 901.22, DELTA);
    }
}

