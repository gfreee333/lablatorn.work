package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    private final double[] xValues = new double[]{1, 2, 3, 5, 10, 6, 20};
    private final double[] yValues = new double[]{2, 20, 50, 40, 60, 70, 6};
    private final double DELTA = 0.001;

    private final MathFunction tan = new TangFunction();
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction unit = new UnitFunction();

    private LinkedListTabulatedFunction createFromList() {
        return new LinkedListTabulatedFunction(xValues, yValues);
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
        assertEquals(list.getX(5), 6, DELTA);
    }

    @Test
    public void testGetY() {
        TabulatedFunction list = createFromList();
        TabulatedFunction test = testFunction();
        assertEquals(list.getY(4), 60, DELTA);
        assertEquals(list.getY(0), 2, DELTA);
        assertEquals(test.getY(3), 4.342, DELTA);
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
    }

    @Test
    public void testIndexOfX() {
        TabulatedFunction list = createFromList();
        TabulatedFunction test = testFunction();
        assertEquals(list.indexOfX(10), 4, DELTA);
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
    }

    @Test
    public void testExtrapolateLeft() {
        AbstractTabulatedFunction list = createFromList();
        AbstractTabulatedFunction test = testFunction();
        assertEquals(list.extrapolateLeft(5), 74.0, DELTA);
        assertEquals(list.extrapolateLeft(2), 20.0, DELTA);
        assertEquals(list.extrapolateLeft(25), 434.0, DELTA);
        assertEquals(test.extrapolateLeft(15), 9.397, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        AbstractTabulatedFunction list = createFromList();
        AbstractTabulatedFunction test = testFunction();
        assertEquals(list.extrapolateRight(10), 51.714, DELTA);
        assertEquals(list.extrapolateRight(7), 65.428, DELTA);
        assertEquals(test.extrapolateRight(20), 2.237, DELTA);
    }

    @Test
    public void testInterpolate() {
        AbstractTabulatedFunction list = createFromList();
        assertEquals(list.interpolate(2.5, 5), 86, DELTA);
        assertEquals(list.interpolate(2.6, 5), 85.542, DELTA);
        assertEquals(list.interpolate(3, 6), 2.421, DELTA);
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
        assertEquals(list.apply(15), 28.857, DELTA);
        assertEquals(test.apply(1), -15.099, DELTA);
        assertEquals(firstFunction.apply(30), 900.0, DELTA);
        assertEquals(secondFunction.apply(60), 1.0, DELTA);
    }

}