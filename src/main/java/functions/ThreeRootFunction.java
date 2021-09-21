package functions;

public class ThreeRootFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x,1.0/3.0);
    }
}
