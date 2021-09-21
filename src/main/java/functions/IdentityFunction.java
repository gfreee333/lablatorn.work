package functions;

public class IdentityFunction implements MathFunction {
public double number1;
    @Override
    public double apply(double x) {
        return x;
    }
}
