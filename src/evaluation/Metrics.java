package evaluation;

import data.*;
import gp.Individual;
import java.util.*;

public class Metrics {

    public static double accuracy(Individual ind, List<Instance> instances){

        int correct = 0;

        for (Instance inst : instances){

            int predicted = ind.tree.evaluate(inst.features)>0.0? 1 : 0;
            if (predicted==inst.label) correct++;
        }//inst

        return (double) correct/instances.size();
    }//END_accuracy

    public static double fMeasure(Individual ind, List<Instance> instances){

        int tp=0, fp=0, fn=0;

        for (Instance inst : instances){

            int predicted = ind.tree.evaluate(inst.features)>0.0? 1 : 0;

            if (predicted==1 && inst.label==1) tp++;
                else if (predicted==1 && inst.label==0) fp++;
                    else if (predicted==0 && inst.label==1) fn++;
        }//END_inst

        double precision = (tp + fp == 0)? 0 : (double) tp/(tp+fp);
        double recall = (tp+fn == 0)? 0 : (double) tp/(tp+fn);

        return (precision+recall==0)? 0 : 2*precision*recall / (precision+recall);
    }//END_fMeasure
}//Metrics