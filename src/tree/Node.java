package tree;

public abstract class Node {

    public abstract double evaluate(double[] features);

    public abstract int depth();

    public abstract Node copy();

    // Prefix notation
    @Override
    public abstract String toString();
}//Node