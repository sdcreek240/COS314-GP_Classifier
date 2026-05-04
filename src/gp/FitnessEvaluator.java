package gp;

import data.*;
import tree.Tree;


public class FitnessEvaluator {

    private final Dataset ds;

    public FitnessEvaluator(Dataset dataset){
        this.ds = dataset;
    }//constr

    public double evaluate(Individual ind){

        int correct=0;

        for (Instance i : ds.train) {

            double out = ind.tree.evaluate(i.features);
            int predicted = out>0.0? 1 : 0;
            if (predicted==i.label) correct++;
        }//END_i

        double accuracy = (double) correct/ds.train.size();
        ind.fitness = accuracy;
        return accuracy;
    }//END_evaluate
}//FitnessEvaluator