package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    MathFunction first = new ConstantFunction(5);
    MathFunction second = new SqrFunction();
    private final MathFunction firstFunction = first.andThen(second);
    private final MathFunction secondFunction = second.andThen(first);

    @Test
    public void andThen() {
        assertEquals(firstFunction.apply(5), 25, 0.00001);
        assertEquals(secondFunction.apply(5), 5, 0.000001);
        assertEquals(firstFunction.apply(-4), 25, 0.00001);
    }
}