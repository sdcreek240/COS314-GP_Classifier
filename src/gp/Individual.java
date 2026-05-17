package gp;

import tree.Tree;

public class Individual {

    public Tree tree;
    public double fitness;

    public Individual(Tree tree){
        this.tree = tree;
        this.fitness = 0.0;
    }//constr

    public Individual copy() {
        Individual clone = new Individual(tree.copy());
        clone.fitness = this.fitness;
        return clone;
        
    }
}//Individual