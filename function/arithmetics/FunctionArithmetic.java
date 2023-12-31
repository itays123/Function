package function.arithmetics;

import function.Function;

/**
 * Represents actions that can be done on two functions - multiplying, dividing,
 * composing, and so on.
 */
public abstract class FunctionArithmetic extends Function {

    protected Function left;
    protected Function right;

    /**
     * Generate a new function arithmetic
     * 
     * @param left  the left side of the operation
     * @param right the right side of the operation
     */
    public FunctionArithmetic(Function left, Function right) {
        this.left = left;
        this.right = right;
    }

    public Function getLeft() {
        return left;
    }

    public Function getRight() {
        return right;
    }

    @Override
    public boolean equals(Object other) {
        if (!getClass().equals(other.getClass()))
            return false;
        FunctionArithmetic arithmetic = (FunctionArithmetic) other;
        return left == arithmetic.left && right == arithmetic.right;
    }

}
