package function;

import function.arithmetics.Power;
import function.arithmetics.Product;
import function.arithmetics.Quotient;

/**
 * Represents the function ln(x)
 */
public class NaturalLogFunction extends Function {

    @Override
    public double evaluate(double x) throws ArithmeticException {
        if (x <= 0)
            throw new ArithmeticException("Cannot compute natural logarithm of non-positive numbers");
        return Math.log(x);
    }

    @Override
    public Function derive() {
        return Power.inverse(new Identity());
    }

    @Override
    public String substitute(String x) {
        return "ln" + x;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NaturalLogFunction;
    }

    @Override
    public Function compose(Function inner) {
        if (inner instanceof Constant)
            return Constant.of(0);
        double scalar = FunctionVector.getScalar(inner);
        if (scalar <= 0)
            throw new ArithmeticException("Cannot compose non-positive in logarithmic function");

        // apply rule ln(ab) = ln a + ln b
        if (scalar != 1) {
            return Constant.of(Math.log(scalar)).plus(compose(FunctionVector.getScaledFunc(inner)));
        }
        if (inner instanceof Product) {
            Product prod = (Product) inner;
            return compose(prod.getLeft()).plus(compose(prod.getRight()));
        }

        // apply rule ln(a/b) = ln a - ln b
        if (inner instanceof Quotient) {
            Quotient prod = (Quotient) inner;
            return compose(prod.getLeft()).minus(compose(prod.getRight()));
        }

        // apply rule ln a^b = b * ln a
        if (inner instanceof Power) {
            Power pow = (Power) inner;
            return pow.getRight().times(compose(pow.getLeft()));
        }

        return super.compose(this);
    }

    @Override
    public Function integrate() {
        return Power.inverse(new Identity());
    }

}
