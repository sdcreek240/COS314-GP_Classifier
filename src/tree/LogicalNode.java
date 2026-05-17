package tree;

public class LogicalNode extends Node{

    public enum Op { IF_THEN_ELSE, LESS_THAN, GREATER_THAN, AND, OR, EQUAL }

    private final Op op;
    private final Node condition; // Only used if Op is IF_THEN_ELSE
    private final Node left;
    private final Node right;

    public LogicalNode(Op op, Node left, Node right){

        this.op = op;
        this.condition = null;
        this.left = left;
        this.right = right;

    }

    public LogicalNode(Op op, Node condition, Node left, Node right) {
        this.op = op;
        this.condition = condition;
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(double[] features){

        double l = left.evaluate(features);
        double r = right.evaluate(features);

        switch (op) {

            case LESS_THAN:    return (l < r) ? 1.0 : 0.0;
            case GREATER_THAN: return (l > r) ? 1.0 : 0.0;
            case EQUAL:        return (l == r) ? 1.0 : 0.0;
            case AND:          return (l > 0.0 && r > 0.0) ? 1.0 : 0.0;
            case OR:           return (l > 0.0 || r > 0.0) ? 1.0 : 0.0;
            case IF_THEN_ELSE:
                 
                double cond = condition.evaluate(features);
                return (cond > 0.0) ? l : r;
            default: return 0.0;
            
        }
    }

    @Override
    public int depth(){
        int d = Math.max(left.depth(), right.depth());
        if (condition != null) {
            d = Math.max(d,condition.depth());
            
        }
        return 1 + d;
    }

    @Override
    public Node copy() {
        if (op == Op.IF_THEN_ELSE) {
            return new LogicalNode(op, condition.copy(), left.copy(), right.copy());
    
        }
        return new LogicalNode(op, left.copy(), right.copy());
    }

    @Override
    public String toString(){
        if (op == Op.IF_THEN_ELSE) {
            return "(if " + condition + " " + left + " " + right + ")";
        }
        return "(" + op.name().toLowerCase() + " " + left + " " + right + ")";
    }

    public Op getOp() { return op; }
    public Node getCondition() { return condition; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }
    
}
