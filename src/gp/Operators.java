package gp;
import tree.*;
import data.*;
import java.util.*;

public class Operators {

    private final Random rng;
    private final int maxDepth;
    private final int numFeatures;
    private final TreeFactory factory;

    public Operators(Random rng, int maxDepth, int numFeatures){
        this.rng = rng;
        this.maxDepth = maxDepth;
        this.numFeatures = numFeatures;
        this.factory = new TreeFactory(numFeatures, rng);
    }

    public Individual[] crossover(Individual a, Individual b){

        Individual offA = a.copy();
        Individual offB = b.copy();
        int sizeA = countNodes(offA.tree.getRoot());
        int sizeB = countNodes(offB.tree.getRoot());
        int crossPointA = rng.nextInt(sizeA);
        int crossPointB = rng.nextInt(sizeB);
        Node subTreeA = getNode(offA.tree.getRoot(), crossPointA, new int[]{0}).copy();
        Node subTreeB = getNode(offB.tree.getRoot(), crossPointB, new int[]{0}).copy();
        Node newRootA = replaceNode(offA.tree.getRoot(), crossPointA, new int[]{0}, subTreeB);
        Node newRootB = replaceNode(offB.tree.getRoot(), crossPointB, new int[]{0}, subTreeA);
        if (newRootA.depth()<=maxDepth) {
            offA.tree.setRoot(newRootA);
        }
        if (newRootB.depth()<=maxDepth) {
            offB.tree.setRoot(newRootB);
        }

        return new Individual[]{offA, offB};
    }//crossover

    public Individual mutate(Individual ind){
        
        Individual offspring = ind.copy();
        int size = countNodes(offspring.tree.getRoot());
        int mutatePoint = rng.nextInt(size);
        int mutationDepth = Math.max(1,rng.nextInt(maxDepth/2)+1);
        Node newSubTree = factory.build(mutationDepth, false).getRoot();
        Node newRoot = replaceNode(offspring.tree.getRoot(), mutatePoint, new int[]{0},newSubTree);

        if (newRoot.depth() <= maxDepth) {
            offspring.tree.setRoot(newRoot);
        }

        return offspring;
    }

    private int countNodes(Node n) {
        if (n instanceof TerminalNode) {
            return 1;
            
        }
        FunctionNode fn = (FunctionNode) n;
        return 1 + countNodes(fn.getLeft()) + countNodes(fn.getRight());
    }

    private Node getNode(Node n, int target, int[] current){
        if (current[0] == target) {
            return n;
        }
        current[0]++;

        if (n instanceof FunctionNode) {
            FunctionNode fn = (FunctionNode) n;
            Node leftRes = getNode(fn.getLeft(),target,current);
            if (leftRes != null) {
                return leftRes;
                
            }
            return getNode(fn.getRight(),target,current);
        }

            return null;  
    }

    private Node replaceNode(Node n, int target, int[] current, Node replacement){
        if (current[0] == target) {
            current[0]++;
            return replacement;
            
        }
        current[0]++;

        if (n instanceof FunctionNode) {
            FunctionNode fn = (FunctionNode) n;
            Node newLeft = replaceNode(fn.getLeft(), target, current, replacement);
            Node newRight = replaceNode(fn.getRight(), target, current,replacement);
            return new FunctionNode(fn.getOp(),newLeft,newRight);

        }
        return n.copy();
    }
}