package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    public final static double DELTA = 0.0001;
    
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{10, 20, 30, 40, 50};

    private final double[] xValues1 = new double[]{6, 7, 8, 9, 10};
    private final double[] yValues1 = new double[]{60, 70, 80, 90, 100};

    @Test
    public void testApply() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction threeRootFunction = new ThreeRootFunction();
        MathFunction doubleIdentityFunction = new CompositeFunction(identityFunction, identityFunction);
        assertEquals(doubleIdentityFunction.apply(1), 1, DELTA);
        MathFunction identityLnFunction = new CompositeFunction(identityFunction, threeRootFunction);
        assertEquals(identityLnFunction.apply(1), 1, DELTA);
        MathFunction sqrFunction = new SqrFunction();
        MathFunction unitFunction = new UnitFunction();
        MathFunction sqrLnUnitFunction = sqrFunction.andThen(threeRootFunction).andThen(unitFunction);
        assertEquals(sqrLnUnitFunction.apply(4), 1, DELTA);
        assertNotEquals(sqrLnUnitFunction.apply(1), 0, DELTA);
        assertEquals(sqrLnUnitFunction.apply(1651), 1, DELTA);
        double result = threeRootFunction.andThen(sqrFunction).andThen(identityFunction).apply(100);
        assertEquals(result, 21.5443469, DELTA);
        assertNotEquals(result, 100, DELTA);
        assertNotEquals(result, 1, DELTA);
        MathFunction listFunction = new LinkedListTabulatedFunction(xValues, yValues);
        MathFunction arrayFunction = new ArrayTabulatedFunction(xValues1, yValues1);
        MathFunction arrayListSqrFunction = arrayFunction.andThen(listFunction).andThen(sqrFunction);
        assertEquals(arrayListSqrFunction.apply(1), 10000, DELTA);
        assertEquals(arrayListSqrFunction.apply(0.5), 2500, DELTA);
        assertEquals(arrayListSqrFunction.apply(1.5), 22500, DELTA);

    }
}