package gp;

import tree.*;
import data.*;
import java.util.*;

public class Operators {

    private final Random rng;
    private final int maxDepth;
    private final int numFeatures;

    public Operators(Random rng, int maxDepth, int numFeatures){
        this.rng = rng;
        this.maxDepth = maxDepth;
        this.numFeatures = numFeatures;
    }///constr

    public Individual[] crossover(Individual a, Individual b){

        Individual offA = a.copy();
        Individual offB = b.copy();



        return new Individual[]{offA, offB};
    }//crossover

    public Individual mutate(Individual ind){
        
        Individual offspring = ind.copy();


        return offspring;
    }
}