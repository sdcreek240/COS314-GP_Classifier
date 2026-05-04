package tree;

import java.util.*;

//Build using full / grow methods
// Ramped half-and-half: alternate between full and grow across pop
public class TreeFactory {

    private final int numFeatures;
    private final Random rng;

    public TreeFactory(int numFeatures, Random rng){
        this.numFeatures = numFeatures;
        this.rng = rng;
    }//constr

    public Tree build(int maxDapth, boolean userFull){
        Node root = userFull? full(1, maxDepth) : grow(1, maxDepth);
        return new Tree(root, maxDepth);
    }//END_build

    private Node full(int currDepth, int maxDepth){

        if (currDepth==maxDepth) return randomTerminal();
        return new FunctionNode(randomOp(), full(currentDepth+1, maxDepth), full(currentDepth+1, maxDepth));
    }//END_full

    private Node grow(int currDepth, int maxDepth){

        if (currDepth==maxDepth) return randomTerminal();
        if (rng.nextBoolean()) return randomTerminal();

        return new FunctionNode(randomOp(), grow(currDepth+1, maxDepth), grow(currentDepth+1, maxDepth));
    }//END_grow

    private FunctionNode.Op randomOp() {
        FunctionNode.Op[] ops = FunctionNode.Op.value();
        return ops[rng.nextInt(ops.length)];
    }

    private TerminalNode randomTerminal() {

        if (rng.nextBoolean()) return new TerminalNode(rng.nextInt(numFeatures));
        return new TerminalNode(rng.nextDouble()*2 - 1);
    }
}//TreeFactory 