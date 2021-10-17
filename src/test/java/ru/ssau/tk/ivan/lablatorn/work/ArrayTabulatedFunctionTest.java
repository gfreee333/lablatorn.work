package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayTabulatedFunctionTest {

    private final double DELTA = 0.0001;
    private final double BEGIN = 1;
    private final double END = 100;
    private final double[] xValues = new double[]{3.4, 5.2, 6.0, 2.1};
    private final double[] yValues = new double[]{-2.4, 1.2, 3.0, 5.1};

    private final MathFunction sqr = new SqrFunction();

    private ArrayTabulatedFunction createFromArray() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }


    private ArrayTabulatedFunction firstFunction() {
        return new ArrayTabulatedFunction(sqr, BEGIN, END, 100);
    }


    @Test
    public void testSetY() {
        TabulatedFunction array = createFromArray();
        array.setY(2, 1000);
        assertEquals(array.getY(2), 1000, DELTA);
        array.setY(2, 2222);
        assertEquals(array.getY(2), 2222, DELTA);
    }

    @Test
    public void testArrayTabulatedFunctionWithTwoParameters() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstFunction = firstFunction();
        assertEquals(array.getCount(), 4);
        assertEquals(firstFunction.getCount(), 100);
    }

    @Test
    public void testGetCount() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstFunction = firstFunction();
        assertEquals(firstFunction.getCount(), 100);
        assertEquals(array.getCount(), 4);
    }

    @Test
    public void testGetX() {
        TabulatedFunction firstFunction = firstFunction();
        for (int element = 0; element < 100; element++) {
            assertEquals(firstFunction.getX(element), element * (END - BEGIN) / 99.0 + BEGIN, DELTA);
        }
    }

    @Test
    public void testGetY() {
        TabulatedFunction firstFunction = firstFunction();
        for (int element = 0; element < 99; element++) {
            assertEquals(firstFunction.getY(element), sqr.apply(firstFunction.getX(element)), DELTA);
        }
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction array = createFromArray();
        assertEquals(firstFunction.leftBound(), BEGIN, DELTA);
        assertEquals(array.leftBound(), 3.4, DELTA);
    }

    @Test
    public void testRightBound() {
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction array = createFromArray();
        assertEquals(firstFunction.rightBound(), END, DELTA);
        assertEquals(array.rightBound(), 2.1, DELTA);
    }

    @Test
    public void testIndexOfX() {
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction array = createFromArray();
        assertEquals(firstFunction.indexOfX(30.6), -1, DELTA);
        assertEquals(firstFunction.indexOfX(5), 4, DELTA);
        for (int element = 0; element < 100; ++element) {
            assertEquals(firstFunction.indexOfX(1 + element * (END - BEGIN) / 99.0), element);
        }
        assertEquals(array.indexOfX(3), -1, DELTA);
    }

    @Test
    public void testIndexOfY() {
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction array = createFromArray();
        assertEquals(firstFunction.indexOfY(0.432), -1, DELTA);
        for (int element = 0; element < 99; element++) {
            assertEquals(firstFunction.indexOfY(sqr.apply(firstFunction.getX(element))), element);
        }
        assertEquals(array.indexOfY(3), 2);
    }

    @Test
    public void testFloorIndexOfX() {
        AbstractTabulatedFunction firstFunction = firstFunction();
        ArrayTabulatedFunction array = createFromArray();
        for (int element = 0; element < 100; element++) {
            assertEquals(firstFunction.floorIndexOfX(BEGIN + element * (END - BEGIN) / 99.0), element, DELTA);
        }
        assertEquals(firstFunction.floorIndexOfX(0.1), 0);
        assertEquals(firstFunction.floorIndexOfX(102.0), 100);
        assertEquals(firstFunction.floorIndexOfX(30.6), 29);
        assertEquals(array.floorIndexOfX(3.0), 0);
    }

    @Test
    public void testExtrapolateLeft() {
        AbstractTabulatedFunction firstFunction = firstFunction();
        AbstractTabulatedFunction array = createFromArray();
        assertEquals(firstFunction.extrapolateLeft(0.1), -1.7000, DELTA);
        assertEquals(firstFunction.extrapolateLeft(0.9), 0.70000, DELTA);
        assertEquals(firstFunction.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
        assertEquals(array.extrapolateLeft(0.1),-8.9999, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        AbstractTabulatedFunction firstFunction = firstFunction();
        AbstractTabulatedFunction array = createFromArray();
        assertEquals(firstFunction.extrapolateRight(150), 19950.0, DELTA);
        assertEquals(firstFunction.extrapolateRight(120), 13980.0, DELTA);
        assertEquals(firstFunction.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals(array.extrapolateRight(4.0), 4.0769, DELTA);
    }

    @Test
    public void testApply() {
        TabulatedFunction firstFunction = firstFunction();
        assertEquals(firstFunction.apply(0.6), -0.2000, DELTA);
        assertEquals(firstFunction.apply(110), 11990.0, DELTA);
        assertEquals(firstFunction.apply(50), 2500.0, DELTA);
        assertEquals(firstFunction.apply(30.6), 936.6, DELTA);
    }
}

