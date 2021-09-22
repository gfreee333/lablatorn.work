package functions;

import org.testng.annotations.Test;

import java.util.concurrent.Callable;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    @Test
    public void testApply() {
        double pi = 3.14;

        MathFunction number1 = new ConstantFunction(pi);

        assertEquals(number1.apply(13), pi);
        assertEquals(number1.apply(24.2), pi);
        assertEquals(number1.apply(234), pi);
    }
}