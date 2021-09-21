package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RootCubicFunctionTest {
private final static double DELTA = 0.0001;
    @Test
    public void testApply() {
        MathFunction root = new RootCubicFunction();
        assertEquals(root.apply(27),3 , DELTA );
        assertEquals(root.apply(16),2.5198 , DELTA );
    }
}