package tree;

public class FunctionNode extends Node {

    public enum Op { ADD, SUB, MUL, DIV }

    private final Op op;
    private final Node left;
    private final Node right;

    public FunctionNode(Op op, Node l, Node r){
        this.op = op;
        this.left = l;
        this.right = r;
    }

    @Override
    public double evaluate(double[] features) {
        double l = left.evaluate(features);
        double r = right.evaluate(features);
        switch (op) {
            case ADD: return l + r;
            case SUB: return l - r;
            case MUL: return l * r;
            case DIV: return (r == 0.0) ? 1.0 : l / r;  // protected division
            default:  return 0.0;
        }
    }

    @Override
    public int depth() {
        return 1 + Math.max(left.depth(), right.depth());
    }

    @Override
    public Node copy() {
        return new FunctionNode(op, left.copy(), right.copy());
    }

    @Override
    public String toString() {
        return "(" + op.name().toLowerCase() + " " + left + " " + right + ")";
    }

    // Getters
    public Op getOp()     { return op; }
    public Node getLeft() { return left; }
    public Node getRight(){ return right; }
}//FunctionNode