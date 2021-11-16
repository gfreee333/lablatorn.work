package ru.ssau.tk.ivan.lablatorn.work.function;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.InterpolationException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class LinkedListTabulatedFunctionTest {

    private final double[] xValues = new double[]{1, 2, 3, 5, 6, 10, 20};
    private final double[] xValuesWrong1 = new double[]{10.1, 3.4, 5.2, 6.0};
    private final double[] xValuesWrong2 = new double[]{10.1, 3.4, 5.2, 6.0, 10.0};
    private final double[] yValues = new double[]{2, 20, 50, 40, 60, 70, 6};
    private final double[] yValuesWrong1 = new double[]{10.1, 4.5, 2.2, 2.0};
    private final double[] xValues1 = {1};
    private final double[] yValues1 = {10};
    private final double DELTA = 0.001;

    private final MathFunction tan = new TangFunction();
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction unit = new UnitFunction();

    private LinkedListTabulatedFunction createFromList() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getListFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 9, 5);
    }

    private AbstractTabulatedFunction testFunction() {
        return new LinkedListTabulatedFunction(tan, 10, 20, 40);
    }

    private AbstractTabulatedFunction firstFunction() {
        return new LinkedListTabulatedFunction(sqr, 10, 30, 50);
    }

    private TabulatedFunction secondFunction() {
        return new LinkedListTabulatedFunction(unit, 10, 40, 60);
    }

    @Test
    public void testLinkedListTabulatedFunction() {
        assertThrows(DifferentLengthOfArraysException.class, () -> new LinkedListTabulatedFunction(xValuesWrong2, yValuesWrong1));
        assertThrows(ArrayIsNotSortedException.class, () -> new LinkedListTabulatedFunction(xValuesWrong1, yValuesWrong1));
        assertThrows(IllegalArgumentException.class,()-> new LinkedListTabulatedFunction(xValues1,yValues));
    }

    @Test
    public void testArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(xValues1, yValues1));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqr, 10, -1, 5));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqr, -1, 10, 1));
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction list = createFromList();
        TabulatedFunction test = testFunction();
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction secondFunction = secondFunction();
        assertEquals(list.leftBound(), 1, DELTA);
        assertEquals(test.leftBound(), 10, DELTA);
        assertEquals(firstFunction.leftBound(), 10, DELTA);
        assertEquals(secondFunction.leftBound(), 10, DELTA);
    }

    @Test
    public void testRightBound() {
        TabulatedFunction list = createFromList();
        TabulatedFunction test = testFunction();
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction secondFunction = secondFunction();
        assertEquals(list.rightBound(), 20, DELTA);
        assertEquals(test.rightBound(), 19.999, DELTA);
        assertEquals(firstFunction.leftBound(), 10, DELTA);
        assertEquals(secondFunction.leftBound(), 10, DELTA);
    }

    @Test
    public void testGetX() {
        TabulatedFunction list = createFromList();
        assertEquals(list.getX(3), 5, DELTA);
        assertEquals(list.getX(2), 3.0, DELTA);
        assertEquals(list.getX(3), 5, DELTA);
        assertEquals(list.getX(5), 10, DELTA);
        assertThrows(IllegalArgumentException.class, () -> {
            list.getY(-2000);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            list.getY(1000);
        });
    }

    @Test
    public void testGetY() {
        TabulatedFunction list = createFromList();
        TabulatedFunction test = testFunction();
        assertEquals(list.getY(4), 60, DELTA);
        assertEquals(list.getY(0), 2, DELTA);
        assertEquals(test.getY(3), 4.342, DELTA);
        assertThrows(IllegalArgumentException.class, () -> {
            test.getY(2000);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            list.getY(-1000);
        });
    }

    @Test
    public void testSetY() {
        TabulatedFunction list = createFromList();
        double testValueY = 1703;
        double testValueY1 = 2021;
        list.setY(3, testValueY);
        assertEquals(list.getY(3), testValueY, DELTA);
        list.setY(5, testValueY1);
        assertEquals(list.getY(5), testValueY1, DELTA);
        assertThrows(IllegalArgumentException.class, () -> {
            list.setY(25, testValueY);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            list.setY(-22, 222);
        });
    }

    @Test
    public void testIndexOfX() {
        TabulatedFunction list = createFromList();
        TabulatedFunction test = testFunction();
        assertEquals(list.indexOfX(10), 5, DELTA);
        assertEquals(list.indexOfX(200), -1., DELTA);
        assertEquals(test.indexOfX(20), -1, DELTA);
        assertEquals(list.indexOfX(5), 3, DELTA);
        assertEquals(list.indexOfX(20), 6, DELTA);
    }

    @Test
    public void testIndexOfY() {
        TabulatedFunction list = createFromList();
        TabulatedFunction test = testFunction();
        assertEquals(list.indexOfY(50), 2, DELTA);
        assertEquals(list.indexOfY(2000), -1, DELTA);
        assertEquals(test.indexOfY(2), -1, DELTA);
        assertEquals(list.indexOfY(60), 4, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        AbstractTabulatedFunction list = createFromList();
        AbstractTabulatedFunction test = testFunction();
        AbstractTabulatedFunction firstFunction = firstFunction();
        assertEquals(list.floorIndexOfX(1.5), 0, DELTA);
        assertEquals(list.floorIndexOfX(4), 2, DELTA);
        assertEquals(list.floorIndexOfX(5), 2, DELTA);
        assertEquals(test.floorIndexOfX(100), 40, DELTA);
        assertEquals(firstFunction.floorIndexOfX(25), 36, DELTA);
        assertThrows(IllegalArgumentException.class, () -> list.floorIndexOfX(-15));
        assertThrows(IllegalArgumentException.class, () -> list.floorIndexOfX(0));
    }

    @Test
    public void testExtrapolateLeft() {
        AbstractTabulatedFunction list = createFromList();
        AbstractTabulatedFunction test = testFunction();
        assertEquals(list.extrapolateLeft(-5), -106.0, DELTA);
        assertEquals(list.extrapolateLeft(-2), -52.0, DELTA);
        assertEquals(list.extrapolateLeft(-25), -466.0, DELTA);
        assertEquals(test.extrapolateLeft(1), -15.099, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        AbstractTabulatedFunction list = createFromList();
        AbstractTabulatedFunction test = testFunction();
        assertEquals(list.extrapolateRight(100), -506.0, DELTA);
        assertEquals(list.extrapolateRight(70), -314.0, DELTA);
        assertEquals(test.extrapolateRight(200), 698.851, DELTA);
    }

    @Test
    public void testInterpolate() {
        AbstractTabulatedFunction list = createFromList();
        assertEquals(list.interpolate(2.5, 1), 35.0, DELTA);
        assertEquals(list.interpolate(2.6, 1), 38.0, DELTA);
        assertEquals(list.interpolate(4, 2), 45.0, DELTA);
        assertThrows(InterpolationException.class, () -> list.interpolate(2.5, 3));
        assertThrows(InterpolationException.class, () -> list.interpolate(22, 2));
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction list = createFromList();
        list.addNode(890, 1703);
        assertEquals(list.rightBound(), 890, DELTA);
    }

    @Test
    public void testGetCount() {
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction secondFunction = secondFunction();
        assertEquals(firstFunction.getCount(), 50, DELTA);
        assertEquals(secondFunction.getCount(), 60, DELTA);
    }

    @Test
    public void testApply() {
        TabulatedFunction list = createFromList();
        TabulatedFunction test = testFunction();
        TabulatedFunction firstFunction = firstFunction();
        TabulatedFunction secondFunction = secondFunction();
        assertEquals(list.apply(15), 38, DELTA);
        assertEquals(test.apply(1), -15.099, DELTA);
        assertEquals(firstFunction.apply(30), 900.0, DELTA);
        assertEquals(secondFunction.apply(60), 1.0, DELTA);
    }


    @Test
    public void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> createFromList().getX(-200));
        assertThrows(IllegalArgumentException.class, () -> createFromList().getY(-100));
        assertThrows(IllegalArgumentException.class, () -> createFromList().getX(-20));
        assertThrows(IllegalArgumentException.class, () -> createFromList().getY(-1));
    }

    @Test
    public void testIteratorForEach() {
        final LinkedListTabulatedFunction function = createFromList();
        final Iterator<Point> iterator = function.iterator();
        final LinkedListTabulatedFunction scaryFunction = getListFunction();
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

    @Test
    public void testIteratorWhile() {
        final LinkedListTabulatedFunction function = createFromList();
        final Iterator<Point> iterator = function.iterator();
        final LinkedListTabulatedFunction scaryFunction = getListFunction();
        final Iterator<Point> secondIterator = scaryFunction.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, function.getX(i), DELTA);
            assertEquals(point.y, function.getY(i++), DELTA);
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
}