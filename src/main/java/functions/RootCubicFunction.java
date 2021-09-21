package functions;

public class RootCubicFunction implements MathFunction {
    public double apply(double x)
    {
        return Math.pow(x,1.0/3.0);
    }
}
