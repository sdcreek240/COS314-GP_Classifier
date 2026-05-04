package gp;

import tree.Tree;

public class Individual {

    public Tree tree;
    public double fitness;

    public Individual(Tree tree){
        this.tree = tree;
        this.fitness = 0.0;
    }//constr

    public Individual copy() { return new Individual(tree.copy()); }
}//Individual