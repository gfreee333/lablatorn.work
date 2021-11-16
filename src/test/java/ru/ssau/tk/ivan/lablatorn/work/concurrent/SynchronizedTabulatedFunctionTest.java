package ru.ssau.tk.ivan.lablatorn.work.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.function.ArrayTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.Point;
import ru.ssau.tk.ivan.lablatorn.work.function.ZeroFunction;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class SynchronizedTabulatedFunctionTest {
    public static final double DELTA= 0.0001;
    private final double[] xValues = {1.1, 1.2, 1.3, 1.4, 1.5};
    private final double[] yValues = {3.1, 3.2, 3.3, 3.4, 3.5};
    private final Object mutex = new Object();

    private SynchronizedTabulatedFunction getSynchronizedList() {
        return new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), mutex);
    }

    private SynchronizedTabulatedFunction getSynchronizedArray() {
        return new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues), mutex);
    }

    @Test
    public void testGetCount() {
        assertEquals(getSynchronizedList().getCount(), 5);
        assertEquals(getSynchronizedArray().getCount(), 5);
    }

    @Test
    public void testGetX() {
        assertEquals(getSynchronizedList().getX(0), 1.1);
        assertEquals(getSynchronizedList().getX(1), 1.2);
        assertEquals(getSynchronizedArray().getX(3), 1.4);
        assertEquals(getSynchronizedArray().getX(4), 1.5);
    }

    @Test
    public void testGetY() {
        assertEquals(getSynchronizedList().getY(0), 3.1);
        assertEquals(getSynchronizedList().getY(1), 3.2);
        assertEquals(getSynchronizedArray().getY(3), 3.4);
        assertEquals(getSynchronizedArray().getY(4), 3.5);
    }

    @Test
    public void testSetY() {
        ZeroFunction source = new ZeroFunction();
        SynchronizedTabulatedFunction checkFunction = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(source, 1, 16, 8), mutex);
        checkFunction.setY(0, 2);
        assertEquals(checkFunction.getY(0), 2, DELTA);
        checkFunction.setY(1, 3);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getSynchronizedArray().indexOfX(1.3), 2);
        assertEquals(getSynchronizedArray().indexOfX(1.5), 4);
        assertEquals(getSynchronizedList().indexOfX(1.3), 2);
        assertEquals(getSynchronizedList().indexOfX(1.5), 4);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getSynchronizedArray().indexOfY(3.3), 2);
        assertEquals(getSynchronizedArray().indexOfY(3.5), 4);
        assertEquals(getSynchronizedList().indexOfY(3.3), 2);
        assertEquals(getSynchronizedList().indexOfY(3.5), 4);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getSynchronizedList().leftBound(), 1.1);
        assertEquals(getSynchronizedArray().leftBound(), 1.1);
    }

    @Test
    public void testRightBound() {
        assertEquals(getSynchronizedArray().rightBound(), 1.5);
        assertEquals(getSynchronizedList().rightBound(), 1.5);
    }

    @Test
    public void testIteratorWhile() {
        Iterator<Point> it1 = getSynchronizedArray().iterator();
        int i = 0;
        while (it1.hasNext()) {
            Point a = it1.next();
            assertEquals(getSynchronizedArray().getX(i), a.x);
            assertEquals(getSynchronizedArray().getY(i++), a.y);
        }
        assertEquals(getSynchronizedArray().getCount(), i);
        assertThrows(NoSuchElementException.class, it1::next);

    }

    @Test
    public void testIteratorForEach() {
        int i = 0;
        for (Point a : getSynchronizedList()) {
            assertEquals(a.x, getSynchronizedList().getX(i));
            assertEquals(a.y, getSynchronizedList().getY(i++));
        }
        assertEquals(getSynchronizedList().getCount(), i);
    }

    @Test
    public void testApply() {
        assertEquals(getSynchronizedList().apply(5), 7, DELTA);
        assertEquals(getSynchronizedList().apply(7), 9, DELTA);
        assertEquals(getSynchronizedArray().apply(8), 10, DELTA);
        assertEquals(getSynchronizedArray().apply(10), 12, DELTA);
    }
}