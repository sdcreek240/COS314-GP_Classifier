package tree;

import java.util.*;

public class TreeFactory {

    private final int numFeatures;
    private final Random rng;
    private final boolean isDecisionTree; // New Flag!

    public TreeFactory(int numFeatures, Random rng, boolean isDecisionTree){
        this.numFeatures = numFeatures;
        this.rng = rng;
        this.isDecisionTree = isDecisionTree;
    }

    public Tree build(int maxDepth, boolean useFull){
        Node root = useFull ? full(1, maxDepth) : grow(1, maxDepth);
        return new Tree(root, maxDepth);
    }

    private Node full(int currDepth, int maxDepth){
        if (currDepth == maxDepth) return randomTerminal();
        return createRandomNode(currDepth, maxDepth, true);
    }

    private Node grow(int currDepth, int maxDepth){
        if (currDepth == maxDepth) return randomTerminal();
        if (rng.nextBoolean()) return randomTerminal();
        return createRandomNode(currDepth, maxDepth, false);
    }

    // Helper method to build the correct node type
    private Node createRandomNode(int currDepth, int maxDepth, boolean isFull) {
        if (isDecisionTree) {
            LogicalNode.Op[] ops = LogicalNode.Op.values();
            LogicalNode.Op op = ops[rng.nextInt(ops.length)];
            
            Node left = isFull ? full(currDepth + 1, maxDepth) : grow(currDepth + 1, maxDepth);
            Node right = isFull ? full(currDepth + 1, maxDepth) : grow(currDepth + 1, maxDepth);
            
            if (op == LogicalNode.Op.IF_THEN_ELSE) {
                Node cond = isFull ? full(currDepth + 1, maxDepth) : grow(currDepth + 1, maxDepth);
                return new LogicalNode(op, cond, left, right);
            }
            return new LogicalNode(op, left, right);
        } else {
            FunctionNode.Op[] ops = FunctionNode.Op.values();
            FunctionNode.Op op = ops[rng.nextInt(ops.length)];
            Node left = isFull ? full(currDepth + 1, maxDepth) : grow(currDepth + 1, maxDepth);
            Node right = isFull ? full(currDepth + 1, maxDepth) : grow(currDepth + 1, maxDepth);
            return new FunctionNode(op, left, right);
        }
    }

    private TerminalNode randomTerminal() {
        if (rng.nextBoolean()) return new TerminalNode(rng.nextInt(numFeatures));
        return new TerminalNode(rng.nextDouble() * 2 - 1);
    }
}