package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    private final double[] xValues = new double[]{1, 2, 3, 5, 10, 6, 20};
    private final double[] yValues = new double[]{2, 20, 50, 40, 60, 70, 6};
    private final double[] xValues1 = new double[]{100, 200, 300, 500, 1000, 600, 500};
    private final double[] yValues1 = new double[]{25, 20, 50, 40, 10, 70, 40};
    private final double[] xValues2 = new double[]{100, 200, 300, 500, 1000, 600, 500};
    private final double[] yValues2 = new double[]{25, 20, 50, 40, 10, 70, 40};
    private final double[] xValues3 = new double[]{100, 200, 300, 500, 1000, 600, 500};
    private final double[] yValues3 = new double[]{25, 20, 50, 40, 10, 70, 40};

    private final double DELTA = 0.001;
    private final MathFunction testFunction = new TangFunction();
    LinkedListTabulatedFunction listOfArray3 = new LinkedListTabulatedFunction(xValues3, yValues3);
    LinkedListTabulatedFunction listOfArray1 = new LinkedListTabulatedFunction(xValues1, yValues1);
    LinkedListTabulatedFunction listOfArray = new LinkedListTabulatedFunction(xValues, yValues);
    LinkedListTabulatedFunction listOfArray2 = new LinkedListTabulatedFunction(xValues2, yValues2);
    LinkedListTabulatedFunction listOfMathFunction = new LinkedListTabulatedFunction(testFunction, 20, 40, 60);
    LinkedListTabulatedFunction listOfMathFunction1 = new LinkedListTabulatedFunction(testFunction, 10, 40, 20);

    @Test
    public void testLeftBound() {
        assertEquals(listOfArray.leftBound(), 1, DELTA);
        assertEquals(listOfMathFunction.leftBound(), 20, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(listOfArray1.rightBound(), 500, DELTA);
        assertEquals(listOfMathFunction.rightBound(), 40, DELTA);
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
        double testValueY = 1703;
        double testValueY1 = 2021;
        listOfArray3.setY(3, testValueY);
        assertEquals(listOfArray3.getY(3), testValueY, DELTA);
        listOfArray3.setY(5, testValueY1);
        assertEquals(listOfArray3.getY(5), testValueY1, DELTA);
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
    }


    @Test
    public void testExtrapolateLeft() {
        assertEquals(listOfArray2.extrapolateLeft(500), 5, DELTA);
        assertEquals(listOfArray2.extrapolateLeft(200), 20, DELTA);
        assertEquals(listOfArray2.extrapolateLeft(250), 17.5, DELTA);
        assertEquals(listOfArray2.extrapolateLeft(750), -7.5, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(listOfArray2.extrapolateRight(1000), 190, DELTA);
        assertEquals(listOfArray2.extrapolateRight(750), 115.0, DELTA);
        assertEquals(listOfArray2.extrapolateRight(500), 40.0, DELTA);
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
        assertEquals(listOfArray1.getCount(), 7, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(listOfMathFunction.apply(15), -145.636, DELTA);
        assertEquals(listOfMathFunction.apply(40), -1.1172, DELTA);
        assertEquals(listOfMathFunction.apply(30), 32.7061, DELTA);
        assertEquals(listOfMathFunction.apply(60), 76.048, DELTA);
    }

}