package tree;
//prefix notation


public class Tree {

    private Node root;
    private int depth;
    private double fitness; //???

    public Tree(Node root, int depth){

        this.root = root;
        this.depth = depth;
    }//Constructor

    public double evaluate(double[] features) { return root.evaluate(features); }//eval

    // public int depth() { return root.depth(); }//depth

    public Tree copy(){ return new Tree(root.copy(), root.depth()); }//cc

    public Node getRoot()              { return root; }
    public void setRoot(Node root)     { this.root = root; }
    public int getDepth()           { return this.Depth; }
    public double getFitness()         { return fitness; }
    public void setFitness(double f)   { this.fitness = f; }

    @Override
    public String toString() {
        return root.toString();   //prefix notation
    }
}//Tree