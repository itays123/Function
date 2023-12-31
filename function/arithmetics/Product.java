package function.arithmetics;

import function.Function;

/**
 * Represents a product of two (should be) unmatching functions, such as x*sinx
 * 
 * @apiNote DO NOT use directly! This is called via the default times
 *          method in the Function class
 */
public class Product extends FunctionArithmetic {

    public Product(Function left, Function right) {
        super(left, right);
    }

    @Override
    public double evaluate(double x) throws ArithmeticException {
        return left.evaluate(x) * right.evaluate(x);
    }

    @Override
    public Function derive() {
        // based on formula [fg]'=f'g+fg'
        return left.derive().times(right)
                .plus(left.times(right.derive()));
    }

    @Override
    public String substitute(String x) {
        return left.substitute(x, true) + " * " + right.substitute(x, true);
    }

    @Override
    public Function times(Function other) {
        Function prod;
        if (other instanceof Quotient)
            return other.times(this);

        // try resolving to the left
        prod = left.times(other);
        if (!(prod instanceof Product))
            return new Product(prod, right);

        // try resolving to the right
        prod = right.times(other);
        if (!(prod instanceof Product))
            return new Product(left, prod);

        return super.times(other);
    }

    @Override
    public Function div(Function other) {
        Function prod;

        // try resolving to the left
        prod = left.div(other);
        if (!(prod instanceof Quotient))
            return new Product(prod, right);

        // try resolving to the right
        prod = right.div(other);
        if (!(prod instanceof Quotient))
            return new Product(left, prod);

        return super.div(other);
    }

    @Override
    public Function pow(Function exponent) {
        return left.pow(exponent).times(right.pow(exponent));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Product))
            return false;
        Product prod = (Product) other;
        return (left.equals(prod.left) && right.equals(prod.right)) ||
                (left.equals(prod.right) && right.equals(prod.left)); // since multiplication is commutative, we mark
                                                                      // ab=ba
    }

}
