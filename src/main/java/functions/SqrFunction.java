package functions;

public class SqrFunction implements MathFunction {
    public double apply(double x) {
        x = java.lang.Math.pow(x, 2);
        return x;
    }
}
