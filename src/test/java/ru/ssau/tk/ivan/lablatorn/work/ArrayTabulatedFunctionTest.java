package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.InterpolationException;
import ru.ssau.tk.ivan.lablatorn.work.function.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Double.NaN;
import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {

    private final double DELTA = 0.0001;
    private final double BEGIN = 1;
    private final double END = 100;
    private final double[] xValues = new double[]{2.1, 3.4, 5.2, 6.0};
    private final double[] xValuesWrong1 = new double[]{10.1, 3.4, 5.2, 6.0};
    private final double[] xValuesWrong2 = new double[]{10.1, 3.4, 5.2, 6.0, 10.0};
    private final double[] yValues = new double[]{-2.4, 1.2, 3.0, 5.1};
    private final double[] yValuesWrong1 = new double[]{10.1, 4.5, 2.2, 2.0};

    private final MathFunction identityFunction = new IdentityFunction();
    private final MathFunction sqr = new SqrFunction();

    private ArrayTabulatedFunction createFromArray() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    private ArrayTabulatedFunction firstFunction() {
        return new ArrayTabulatedFunction(sqr, BEGIN, END, 100);
    }

    private ArrayTabulatedFunction getRightBoundNaNFunction() {
        return new ArrayTabulatedFunction(identityFunction, 1, Double.NaN, 3);
    }

    @Test
    public void ArrayTabulatedFunction() {
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(xValuesWrong1, yValuesWrong1));
        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(xValuesWrong2, yValuesWrong1));
    }

    @Test
    public void testSetY() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction arrayFunction = firstFunction();
        array.setY(2, 1000);
        assertEquals(array.getY(2), 1000, DELTA);
        array.setY(2, 2222);
        assertEquals(array.getY(2), 2222, DELTA);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            array.setY(-20, 200);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            array.setY(2000, 22);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayFunction.setY(-5, 10));
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
        assertThrows(IndexOutOfBoundsException.class, () -> {
            firstFunction.getX(20000);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            firstFunction.getX(-20);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> firstFunction.getX(-5));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            firstFunction.getX(-1);
        });
    }

    @Test
    public void testGetY() {
        TabulatedFunction firstFunction = firstFunction();
        for (int element = 0; element < 99; element++) {
            assertEquals(firstFunction.getY(element), sqr.apply(firstFunction.getX(element)), DELTA);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> {
            firstFunction.getY(400);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            firstFunction.getY(-20);
        });
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction array = createFromArray();
        assertEquals(firstFunction.leftBound(), BEGIN, DELTA);
        assertEquals(array.leftBound(), 2.1, DELTA);
    }

    @Test
    public void testRightBound() {
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction array = createFromArray();
        assertEquals(firstFunction.rightBound(), END, DELTA);
        assertEquals(array.rightBound(), 6.0, DELTA);
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
        assertEquals(firstFunction.floorIndexOfX(102.0), 100);
        assertEquals(firstFunction.floorIndexOfX(30.6), 29);
    }

    @Test
    public void testExtrapolateLeft() {
        AbstractTabulatedFunction firstFunction = firstFunction();
        AbstractTabulatedFunction array = createFromArray();
        assertEquals(firstFunction.extrapolateLeft(0.1), -1.7000, DELTA);
        assertEquals(firstFunction.extrapolateLeft(0.9), 0.70000, DELTA);
        assertEquals(firstFunction.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
        assertEquals(array.extrapolateLeft(0.1), -7.9384, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        AbstractTabulatedFunction firstFunction = firstFunction();
        AbstractTabulatedFunction array = createFromArray();
        assertEquals(firstFunction.extrapolateRight(150), 19950.0, DELTA);
        assertEquals(firstFunction.extrapolateRight(120), 13980.0, DELTA);
        assertEquals(firstFunction.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals(array.extrapolateRight(4.0), -0.1500, DELTA);
    }

    @Test
    public void testInterpolate() {
        AbstractTabulatedFunction firstFunction = firstFunction();
        AbstractTabulatedFunction array = createFromArray();
        assertEquals(firstFunction.interpolate(4.5, 3), 20.5, DELTA);
        assertEquals(array.interpolate(3.5, 1), 1.3, DELTA);
        assertThrows(InterpolationException.class, () -> array.interpolate(3.5, 2));
        assertThrows(InterpolationException.class, () -> firstFunction.interpolate(22, 2));
    }

    @Test
    public void testApply() {
        TabulatedFunction firstFunction = firstFunction();
        assertEquals(firstFunction.apply(0.6), -0.2000, DELTA);
        assertEquals(firstFunction.apply(110), 11990.0, DELTA);
        assertEquals(firstFunction.apply(50), 2500.0, DELTA);
        assertEquals(firstFunction.apply(30.6), 936.6, DELTA);
    }

    @Test
    public void testIteratorWhile() {
        final ArrayTabulatedFunction function = createFromArray();
        final Iterator<Point> iterator = function.iterator();
        final ArrayTabulatedFunction scaryFunction = getRightBoundNaNFunction();
        final Iterator<Point> secondIterator = scaryFunction.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, xValues[i], DELTA);
            assertEquals(point.y, yValues[i++], DELTA);
        }
        assertEquals(i, function.getCount());
        assertThrows(NoSuchElementException.class, iterator::next);
        i = 0;
        while (secondIterator.hasNext()) {
            Point point = secondIterator.next();
            assertEquals(point.x, scaryFunction.getX(i), DELTA);
            assertEquals(point.y, scaryFunction.getY(i++), DELTA);
        }
        assertEquals(i, scaryFunction.getCount());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorForEach() {
        final ArrayTabulatedFunction function = createFromArray();
        final Iterator<Point> iterator = function.iterator();
        final ArrayTabulatedFunction scaryFunction = getRightBoundNaNFunction();
        final Iterator<Point> secondIterator = scaryFunction.iterator();
        int i = 0;
        for (Point point : function) {
            Point iteratorPoint = iterator.next();
            assertEquals(iteratorPoint.x, point.x, DELTA);
            assertEquals(iteratorPoint.y, point.y, DELTA);
            i++;
        }
        assertEquals(i, function.getCount());
        assertThrows(NoSuchElementException.class, iterator::next);
        i = 0;
        for (Point point : scaryFunction) {
            Point iteratorPoint = secondIterator.next();
            assertEquals(iteratorPoint.x, point.x, DELTA);
            assertEquals(iteratorPoint.y, point.y, DELTA);
            i++;
        }
        assertEquals(i, scaryFunction.getCount());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

}

