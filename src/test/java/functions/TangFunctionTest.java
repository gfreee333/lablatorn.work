package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TangFunctionTest {
    MathFunction first = new TangFunction();

    @Test
    public void andThen() {
        assertEquals(first.apply(0), 0.0000, 0.0001);
        assertEquals(first.apply(30.0), 0.5774, 0.0001);
        assertEquals(first.apply(45.0), 1.0000, 0.0001);
    }
}