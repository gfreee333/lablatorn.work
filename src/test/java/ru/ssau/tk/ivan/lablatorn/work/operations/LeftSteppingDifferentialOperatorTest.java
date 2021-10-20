package ru.ssau.tk.ivan.lablatorn.work.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.SqrFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {
    private final static double STEP = 0.01;
    private final double DELTA = 0.0001;
    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(STEP);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1.990, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 5.98999, DELTA);
    }

}