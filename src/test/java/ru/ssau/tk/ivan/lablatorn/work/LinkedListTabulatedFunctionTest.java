package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    private final double[] xValues = new double[]{1, 2, 3, 5, 10, 6, 20};
    private final double[] yValues = new double[]{2, 20, 50, 40, 60, 70, 6};
    private final double[] xValuesOne = new double[]{100, 200, 300, 500, 1000, 600, 500};
    private final double[] yValuesOne = new double[]{25, 20, 50, 40, 10, 70, 40};
    private final double[] xValuesTwo = new double[]{100, 200, 300, 500, 1000, 600, 500};
    private final double[] yValuesTwo = new double[]{25, 20, 50, 40, 10, 70, 40};
    private final double[] xValuesThree = new double[]{100, 200, 300, 500, 1000, 600, 500};
    private final double[] yValuesThree = new double[]{25, 20, 50, 40, 10, 70, 40};

    private final double DELTA = 0.001;
    private final MathFunction testFunction = new TangFunction();
    LinkedListTabulatedFunction listOfArrayThree = new LinkedListTabulatedFunction(xValuesThree, yValuesThree);
    LinkedListTabulatedFunction listOfArrayOne = new LinkedListTabulatedFunction(xValuesOne, yValuesOne);
    LinkedListTabulatedFunction listOfArray = new LinkedListTabulatedFunction(xValues, yValues);
    LinkedListTabulatedFunction listOfArrayTwo = new LinkedListTabulatedFunction(xValuesTwo, yValuesTwo);
    LinkedListTabulatedFunction listOfMathFunction = new LinkedListTabulatedFunction(testFunction, 20, 40, 60);

    @Test
    public void testLeftBound() {
        assertEquals(listOfArray.leftBound(), 1, DELTA);
        assertEquals(listOfMathFunction.leftBound(), 20, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(listOfArrayOne.rightBound(), 500, DELTA);
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
        listOfArrayThree.setY(3, testValueY);
        assertEquals(listOfArrayThree.getY(3), testValueY, DELTA);
        listOfArrayThree.setY(5, testValueY1);
        assertEquals(listOfArrayThree.getY(5), testValueY1, DELTA);
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
        assertEquals(listOfArrayTwo.extrapolateLeft(500), 5, DELTA);
        assertEquals(listOfArrayTwo.extrapolateLeft(200), 20, DELTA);
        assertEquals(listOfArrayTwo.extrapolateLeft(250), 17.5, DELTA);
        assertEquals(listOfArrayTwo.extrapolateLeft(750), -7.5, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(listOfArrayTwo.extrapolateRight(1000), 190, DELTA);
        assertEquals(listOfArrayTwo.extrapolateRight(750), 115.0, DELTA);
        assertEquals(listOfArrayTwo.extrapolateRight(500), 40.0, DELTA);
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
        assertEquals(listOfArrayOne.getCount(), 7, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(listOfMathFunction.apply(15), -145.636, DELTA);
        assertEquals(listOfMathFunction.apply(40), -1.1172, DELTA);
        assertEquals(listOfMathFunction.apply(30), 32.7061, DELTA);
        assertEquals(listOfMathFunction.apply(60), 76.048, DELTA);
    }

}