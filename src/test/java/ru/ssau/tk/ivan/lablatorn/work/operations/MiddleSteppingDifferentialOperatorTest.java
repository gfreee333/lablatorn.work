package ru.ssau.tk.ivan.lablatorn.work.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.SqrFunction;

import static org.testng.Assert.*;

public class MiddleSteppingDifferentialOperatorTest {

    private static final double STEP = 0.01;
    private final double DELTA = 0.0001;

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(STEP);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 5.9999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(6), 11.9999, DELTA);
    }

}