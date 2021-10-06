package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 5, 10, 6, 20};
    private final double[] yValues = new double[]{2, 20, 50, 40, 60, 70, 6};
    private final double DELTA = 0.001;
    private final MathFunction testFunction = new TangFunction();
    LinkedListTabulatedFunction listOfArray = new LinkedListTabulatedFunction(xValues, yValues);
    LinkedListTabulatedFunction listOfMathFunction = new LinkedListTabulatedFunction(testFunction, 20, 40, 60);

    @Test
    public void testLeftBound() {
        assertEquals(listOfArray.leftBound(), 1, DELTA);
        assertEquals(listOfMathFunction.leftBound(), 20, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(listOfArray.rightBound(), 20, DELTA);
        assertEquals(listOfArray.rightBound(), 40, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(listOfArray.getX(3), 5, DELTA);
        assertEquals(listOfMathFunction.getX(2), 20.677, DELTA);
        assertEquals(listOfMathFunction.getX(3), 21.016, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(listOfArray.getY(4), 60, DELTA);
        assertEquals(listOfArray.getY(0), 2, DELTA);
        assertEquals(listOfMathFunction.getY(3), -1.472, DELTA);
    }

    @Test
    public void testSetY() {
        listOfArray.setY(3, 1703);
        assertEquals(listOfArray.getY(3), 1703, DELTA);
        listOfArray.setY(5, 2021);
        assertEquals(listOfArray.getY(5), 2021, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(listOfArray.indexOfX(10), 4, DELTA);
        assertEquals(listOfArray.indexOfX(200), -1., DELTA);
        assertEquals(listOfArray.indexOfX(20), 6, DELTA);

    }

    @Test
    public void testIndexOfY() {
        assertEquals(listOfArray.indexOfY(50), 2, DELTA);
        assertEquals(listOfArray.indexOfY(2000), -1, DELTA);
        assertEquals(listOfArray.indexOfY(2), 0, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(listOfArray.floorIndexOfX(1.5), 0, DELTA);
        assertEquals(listOfArray.floorIndexOfX(4), 2, DELTA);
        assertEquals(listOfArray.floorIndexOfX(5), 2, DELTA);
        assertEquals(listOfMathFunction.floorIndexOfX(100), 60, DELTA);
        assertEquals(listOfMathFunction.floorIndexOfX(21), 2, DELTA);
        assertEquals(listOfArray.floorIndexOfX(22), 7, DELTA);
    }


    @Test
    public void testExtrapolateLeft() {
        assertEquals(listOfArray.extrapolateLeft(1.5), 11, DELTA);
        assertEquals(listOfArray.extrapolateLeft(2.5), 29, DELTA);
        assertEquals(listOfArray.extrapolateLeft(10), 164, DELTA);
        assertEquals(listOfArray.extrapolateLeft(20), 344, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(listOfArray.extrapolateRight(5), 74.571, DELTA);
        assertEquals(listOfArray.extrapolateRight(20), 6, DELTA);
        assertEquals(listOfArray.extrapolateRight(10), 51.714, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(listOfArray.interpolate(2.5, 5), 86, DELTA);
    }

    @Test
    public void testAddNode() {
        listOfArray.addNode(890, 1703);
        assertEquals(listOfArray.rightBound(), 890, DELTA);
    }

    @Test
    public void testGetCount() {
        assertEquals(listOfMathFunction.getCount(), 60, DELTA);
        assertEquals(listOfArray.getCount(), 7, DELTA);
    }


}